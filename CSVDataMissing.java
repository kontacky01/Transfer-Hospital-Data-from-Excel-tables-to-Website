/** -------------------------------------------------------
 * Assignment (3)
 * Due March 25th
 * Written by: (Natasha Uwase)
 * For COMP 249 Section (WW) – Winter 2022
 * --------------------------------------------------------
 * This is the CSVDataMissing class that handles what should be done in case a data is missing from the content
 * the class takes one parameter @param filename which stores the name of the file to be processed and @param line that stores the number of the line where data is missing
 */
import java.io.*;
public class CSVDataMissing {
	private String filename;
	private int line;
	public CSVDataMissing(String filename, int line) {
		System.out.println("WARNING: In file "+filename+" there is a MISSING DATA on line "+line+". Line "+line+" is not converted to HTML.");
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter("Excetpions.log",true);
			pw = new PrintWriter(fw);
			pw.println("WARNING: In file "+filename+" there is a MISSING DATA on line "+line+". Line "+line+" is not converted to HTML.");
			fw.close();
			pw.flush();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.close();
	}
}
