package dsc.spotlight;

import java.io.File;
import java.io.IOException;

import dsc.spotlight.utils.Text;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InvalidFormatException,
			IOException {
//		POSModel model = new POSModelLoader().load(new File(
//				"resources/POS/en-pos-maxent.bin"));
//		PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
//		POSTaggerME tagger = new POSTaggerME(model);
//
//		SentenceDetectorME sentenceDetector;
//		sentenceDetector = new SentenceDetectorME(new SentenceModel(new File(
//				"resources/POS/en-sent.bin")));
//
//		String[] sentences = sentenceDetector
//				.sentDetect("I am a human being. Human beings are...");
//
//		for (String sentence : sentences) {
//			
//			String words[] = WhitespaceTokenizer.INSTANCE.tokenize(sentence);
//
//			String[] tags = tagger.tag(words);
//
//			// String[] taggedText = tagger.tag(tags);
//			//TODO: combine these with `/` and use our regex
////			POSSample sample = new POSSample(words, tags);
//			String[] taggedWords = Text.joinByToken(words, tags, "/");
//			
//			System.out.println(String.join(" ", taggedWords));
//		}
//
	}
}
