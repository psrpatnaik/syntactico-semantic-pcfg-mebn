import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.parser.lexparser.LexicalizedParserQuery;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.ScoredObject;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			String PCG_MODEL = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
			DocumentPreprocessor dp = new DocumentPreprocessor("sentences.txt");
			LexicalizedParser lp = LexicalizedParser.loadModel();
			LexicalizedParserQuery lpq = lp.lexicalizedParserQuery();
			
			// To save the PCFG Grammar.
			//lp.saveParserToTextFile("PCFG-grammar.txt");
			
			PrintWriter pw=new PrintWriter(new FileWriter("output.txt",true));
			int counter=1;
			for(List<HasWord> sentence : dp) {

				System.out.print(counter+" Parsed?: "+lpq.parseAndReport(sentence,pw));
				//Tree parse = lpq.getBestPCFGParse();
				//parse.indentedListPrint(pw,true);
				
				
				/*
				// To check status of various operations on query
				System.out.println("Fallback?"+lpq.parseFallback());
				System.out.println("No Memory?"+lpq.parseNoMemory());
				System.out.println("Skipped?"+lpq.parseSkipped());
				System.out.println("Unparsable?"+lpq.parseUnparsable());
				 */

				//To get the value of best PCFGScore of the parsing.
				//System.out.println("Score: "+lpq.getPCFGScore());

				
				// To  track the sentence ids in dataset.
				pw.println("#"+counter+" "+sentence.toString());
				counter++;
				// If interested in exploring other parse trees and their parse probabilities
				// To get the tree in PCFG's unary and binary grammar symbols
				List<ScoredObject<Tree>> kBest = lpq.getPCFGParser().getKBestParses(2);//getKBestPCFGParses(2);//getBestPCFGParses();
				
				// To get the tree in readable format
				//List<ScoredObject<Tree>> kBest = lpq.getKBestPCFGParses(2);
				System.out.print(" # kBest: "+kBest.size());

				for(ScoredObject<Tree> tree : kBest) {
					//System.out.println(tree.toString());

					tree.object().indentedListPrint(pw, false);
					System.out.print(" "+tree.score());
					//System.out.println(tree.object().depth());
					//tree.object().pennPrint(pw);
					//System.out.println("\n"+tree.object().getChildrenAsList());

					//To print the parse tree in form of XML tree.
					//tree.object().indentedXMLPrint(pw,true);
					
				}
				
			System.out.println("");	
			}
			pw.flush();
			pw.close();


		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something wrong");
			e.printStackTrace();
		}
	}

}