/** -------------------------------------------------------
 * Assignment (3)
 * Due March 25th
 * Written by: (Natasha Uwase)
 * For COMP 249 Section (WW) – Winter 2022
 * --------------------------------------------------------
 * This is the CSVAttributeMissing class that handles what should be done in case an attribute is missing from the content
 * the class takes one parameter @param filename which stores the file to process
 */
import java.io.*;
public class CSVAttributeMissing {
	private String filename;
	public CSVAttributeMissing(String filename) {
		System.out.println("ERROR: In file "+filename+", there is a MISSING ATTRIBUTE. File is not converted to HTML.");
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter("Excetpions.log", true);
			pw = new PrintWriter(fw);
			pw.println("ERROR: In file "+filename+", there is a MISSING ATTRIBUTE. File is not converted to HTML.");
			fw.close();
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.close();
	}
}
