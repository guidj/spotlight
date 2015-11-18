package dsc.spotlight.entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import dsc.spotlight.utils.Text;

public class PersonAnnotator extends JCasAnnotator_ImplBase {

	class POSAid {

		public POSModel getPosModel() {
			return posModel;
		}

		public void setPosModel(POSModel posModel) {
			this.posModel = posModel;
		}

		public POSTaggerME getTagger() {
			return tagger;
		}

		public void setTagger(POSTaggerME tagger) {
			this.tagger = tagger;
		}

		public SentenceDetectorME getSentenceDetector() {
			return sentenceDetector;
		}

		public void setSentenceDetector(SentenceDetectorME sentenceDetector) {
			this.sentenceDetector = sentenceDetector;
		}

		POSModel posModel;
		POSTaggerME tagger;
		SentenceDetectorME sentenceDetector;

		public POSAid(POSModel posModel, POSTaggerME tagger,
				SentenceDetectorME sentenceDetector) {
			super();
			this.posModel = posModel;
			this.tagger = tagger;
			this.sentenceDetector = sentenceDetector;
		}
	}

	List<Pattern> patterns;

	POSAid posAid;

	@SuppressWarnings("unchecked")
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);

		String[] patternStrings = (String[]) aContext
				.getConfigParameterValue("patterns");

		this.patterns = new ArrayList<Pattern>();

		System.out.println(this.getClass().getName() + " is using patterns:");
		for (String patternString : patternStrings) {
			this.patterns.add(Pattern.compile(patternString));
			System.out.println("\t- " + patternString);
		}

		POSModel model = new POSModelLoader()
				.load(new File(
						"/Users/guilherme/code/617/spotlight/resources/POS/en-pos-maxent.bin"));
		POSTaggerME tagger = new POSTaggerME(model);

		SentenceDetectorME sentenceDetector = null;

		try {
			sentenceDetector = new SentenceDetectorME(
					new SentenceModel(
							new File(
									"/Users/guilherme/code/617/spotlight/resources/POS/en-sent.bin")));
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.posAid = new POSAid(model, tagger, sentenceDetector);
	}
	
	private String getKindOfPerson(String patternString) {
		System.out.println("patternString: " + patternString);
		if (patternString.contains("created/VBN by/IN")) {
			return "Director";
		} else if (patternString.contains("screenwriter[s]?/NN[S]?")) {
			return "Screenwriter";
		} else if (patternString.contains("cinematographer/NN(?: ,/,)?")) {
			return "Cinematographer";
		} else if (patternString.contains("played/VBN by/IN") || patternString.contains("([A-z]*)/NNS plays/VBZ")) {
			return "Actor";
		} else if (patternString.contains("[A-z]*/NN [A-z]*/NNS plays/VBZ")) {
			return "FictionalCharactor";
		} else {
			return null;
		}
	}

	@Override
	public void process(JCas arg0) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub

		String docText = arg0.getDocumentText();

		String[] sentences = posAid.getSentenceDetector().sentDetect(docText);
		StringBuilder nameSB = new StringBuilder();

		for (String sentence : sentences) {
			String words[] = WhitespaceTokenizer.INSTANCE.tokenize(sentence);
			String[] tags = posAid.getTagger().tag(words);

			String[] taggedText = Text.joinByToken(words, tags, "/");

			nameSB.append(String.join(" ", taggedText));
			nameSB.append("\n");
		}

		String annotatedText = nameSB.toString();

		for (Pattern pattern : this.patterns) {

			Matcher matcher = pattern.matcher(annotatedText);

			while (matcher.find()) {
				StringBuilder personName = new StringBuilder();
				
				for(int i= 1; i <= matcher.groupCount(); i++) {
					personName.append(matcher.group(i));
					if (i != matcher.groupCount()) {
						personName.append(" ");
					}
				}

				System.out.println("Found: " + personName);

				Person personAnnotation = new Person(arg0);

				personAnnotation.setBegin(matcher.start(1));
				personAnnotation.setEnd(matcher.end(matcher.groupCount()));
				personAnnotation.setName(personName.toString());
				personAnnotation.setKind(getKindOfPerson(pattern.toString()));

				personAnnotation.addToIndexes(arg0);
			}
		}
	}
}
