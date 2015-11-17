package dsc.spotlight.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;


public class PersonAnnotator extends JCasAnnotator_ImplBase {

	List<Pattern> patterns;

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
	}

	@Override
	public void process(JCas arg0) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub

		String docText = arg0.getDocumentText();
		
		for (Pattern pattern : this.patterns) {
			Matcher matcher = pattern.matcher(docText);

			while (matcher.find()) {
				
				String personName = matcher.group(1);
				
				System.out.println("Found: " + personName);

				Person personAnnotation = new Person(arg0);

				personAnnotation.setBegin(matcher.start());
				personAnnotation.setEnd(matcher.end());
				personAnnotation.setName(personName);

				personAnnotation.addToIndexes(arg0);
			}
		}
	}
}
