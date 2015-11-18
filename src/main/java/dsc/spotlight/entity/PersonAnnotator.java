package dsc.spotlight.entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String modelMEFilePath = (String) aContext
				.getConfigParameterValue("modelMEFilePath");
		String sentenceMEFilePath = (String) aContext
				.getConfigParameterValue("sentenceMEFilePath");

		this.patterns = new ArrayList<Pattern>();

		System.out.println(this.getClass().getName() + " is using patterns:");
		for (String patternString : patternStrings) {
			this.patterns.add(Pattern.compile(patternString));
			System.out.println("\t- " + patternString);
		}

		POSModel model = new POSModelLoader().load(new File(modelMEFilePath));
		POSTaggerME tagger = new POSTaggerME(model);

		SentenceDetectorME sentenceDetector = null;

		try {
			sentenceDetector = new SentenceDetectorME(new SentenceModel(
					new File(sentenceMEFilePath)));
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.posAid = new POSAid(model, tagger, sentenceDetector);
	}

	private enum PersonType {

		ACTOR("Actor"), DIRECTOR("Director"), CINEMATOGRAPHER("Cinematographer"), SCREENWRITER(
				"Screenwriter"), FICTIONAL_CHARACTER("FictionalCharacter"), UNKNOWN(
				"Unknown");

		private final String type;

		PersonType(String type) {
			this.type = type;
		}

		private String type() {
			return type;
		}
	}

	private PersonType getPersonType(String patternString) {
		if (patternString.contains("created/VBN by/IN")) {
			return PersonType.DIRECTOR;
		} else if (patternString.contains("screenwriter[s]?/NN[S]?")) {
			return PersonType.SCREENWRITER;
		} else if (patternString.contains("cinematographer/NN(?: ,/,)?")) {
			return PersonType.CINEMATOGRAPHER;
		} else if (patternString.contains("played/VBN by/IN")
				|| patternString.contains("([A-z]*)/NNS plays/VBZ")) {
			return PersonType.ACTOR;
		} else if (patternString.contains("[A-z]*/NN [A-z]*/NNS plays/VBZ")) {
			return PersonType.FICTIONAL_CHARACTER;
		} else {
			return PersonType.UNKNOWN;
		}
	}

	@Override
	public void process(JCas arg0) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub

		String docText = arg0.getDocumentText();

		String[] sentences = posAid.getSentenceDetector().sentDetect(docText);
		Matcher matcher;

		int startOffset;
		String name;
		PersonType personType;

		for (String sentence : sentences) {

			startOffset = docText.indexOf(sentence);

			String words[] = WhitespaceTokenizer.INSTANCE.tokenize(sentence);
			String[] tags = posAid.getTagger().tag(words);

			String[] taggedWords = Text.joinByToken(words, tags, "/");
			String annotatedSentence = String.join(" ", taggedWords);

			for (Pattern pattern : this.patterns) {

				matcher = pattern.matcher(annotatedSentence);

				while (matcher.find()) {
					StringBuilder personNameSB = new StringBuilder();

					for (int i = 1; i <= matcher.groupCount(); i++) {
						personNameSB.append(matcher.group(i));
						if (i != matcher.groupCount()) {
							personNameSB.append(" ");
						}
					}

					name = personNameSB.toString();
					personType = getPersonType(pattern.toString());

					switch (personType) {
					case ACTOR:
						Actor actorAnnotation = new Actor(arg0);
						actorAnnotation.setBegin(sentence.indexOf(name)
								+ startOffset);
						actorAnnotation.setEnd(sentence.indexOf(name)
								+ startOffset + name.length());
						actorAnnotation.setName(personNameSB.toString());
						actorAnnotation.addToIndexes(arg0);
						break;
					case DIRECTOR:
						Director directorAnnotation = new Director(arg0);
						directorAnnotation.setBegin(sentence.indexOf(name)
								+ startOffset);
						directorAnnotation.setEnd(sentence.indexOf(name)
								+ startOffset + name.length());
						directorAnnotation.setName(personNameSB.toString());
						directorAnnotation.addToIndexes(arg0);
						break;
					case CINEMATOGRAPHER:
						Cinematographer cinematographerAnnotation = new Cinematographer(
								arg0);
						cinematographerAnnotation.setBegin(sentence
								.indexOf(name) + startOffset);
						cinematographerAnnotation.setEnd(sentence.indexOf(name)
								+ startOffset + name.length());
						cinematographerAnnotation.setName(personNameSB
								.toString());
						cinematographerAnnotation.addToIndexes(arg0);
						break;
					case SCREENWRITER:
						Screenwriter screenwriterAnnotation = new Screenwriter(
								arg0);
						screenwriterAnnotation.setBegin(sentence.indexOf(name)
								+ startOffset);
						screenwriterAnnotation.setEnd(sentence.indexOf(name)
								+ startOffset + name.length());
						screenwriterAnnotation.setName(personNameSB.toString());
						screenwriterAnnotation.addToIndexes(arg0);
						break;
					case FICTIONAL_CHARACTER:
						FictionalCharacter fictionalCharacterAnnotation = new FictionalCharacter(
								arg0);
						fictionalCharacterAnnotation.setBegin(sentence
								.indexOf(name) + startOffset);
						fictionalCharacterAnnotation.setEnd(sentence
								.indexOf(name) + startOffset + name.length());
						fictionalCharacterAnnotation.setName(personNameSB
								.toString());
						fictionalCharacterAnnotation.addToIndexes(arg0);
						break;
					default:
						Person personAnnotation = new Person(arg0);
						personAnnotation.setBegin(sentence.indexOf(name)
								+ startOffset);
						personAnnotation.setEnd(sentence.indexOf(name)
								+ startOffset + name.length());
						personAnnotation.setName(personNameSB.toString());
						personAnnotation.addToIndexes(arg0);
						break;
					}

				}
			}

		}

	}
}
