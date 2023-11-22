/** -------------------------------------------------------
 * Assignment (3)
 * Due March 25th
 * Written by: (Natasha Uwase)
 * For COMP 249 Section (WW) – Winter 2022
 * --------------------------------------------------------
 * This is the CSV2HTML class that implements the previous classes which includes one @param track used to see if a html file was created successfully
 * The class contains three methods: @method tokenizeTitle and @method tokenizeAttributes @method tokenizeData to split the content of a csv file to make a html file out of it
 * @method ConvertCSVtoHTML is used to take in an a file and invoke the tokenizer methods to take the broken down data and add it to an html file
 * @method main all the above methods and displays the content of the html file if all the conditions in the ConvertCSVtoHTML method are passed one and one is formed successfully
 */
import java.io.*;
import java.util.*;

public class CSV2HTML {
	private static boolean track = false;
	public static void tokenizeTitle(String text, String delim, PrintWriter pw){
		StringTokenizer st = new StringTokenizer(text,delim);
		while (st.hasMoreTokens()) {
			pw.println("<caption>"+st.nextToken()+"</caption>");
		}
	}
	public static void tokenizeAttributes(String text, String delim, PrintWriter pw){
		StringTokenizer st = new StringTokenizer(text,delim);
		pw.println("\t<tr>");
		while (st.hasMoreTokens()) {
			pw.println("\t<th>"+st.nextToken()+"</th>");
		}
		pw.println("\t</tr>");
	}
	public static void tokenizeData(String text, String delim, PrintWriter pw){
		StringTokenizer st = new StringTokenizer(text,delim);
		pw.println("\t<tr>");
		while (st.hasMoreTokens()) {
			pw.println("\t<td>"+st.nextToken()+"</td>");
		}
		pw.println("\t</tr>");
	}
	public static void ConvertCSVtoHTML(String filename) {
		String filenameCSVExtension = filename+".csv";
		String filenameHTMLExtension = filename+".html";
		File temp; File anotherTemp;
		Scanner sc = null;
		PrintWriter pw = null; 
		boolean exists = false;
		boolean isReadable = false;
		try {
			temp = new File(filenameCSVExtension);
			anotherTemp = new File(filenameHTMLExtension);
			sc = new Scanner(new FileInputStream(filenameCSVExtension));
			pw = new PrintWriter(new FileOutputStream(filenameHTMLExtension));
			exists = temp.exists();
			isReadable = temp.canRead();
			if(exists == true && isReadable == true) {
				String Note = "";
				String title = sc.nextLine();
				pw.println("<html>\n<style>\ntable {font-family: arial, sans-serif;border-collapse: collapse;}\ntd, th {border: 1px solid #000000;text-align: left;padding: 8px;}\ntr:nth-child(even) {background-color: #dddddd;}\nspan{font-size: small}\n</style>");
				pw.println("<body>\n<table>");
				tokenizeTitle(title,",",pw);
				String attributes = sc.nextLine();
				if(attributes.contains(", ,") || attributes.charAt(attributes.length()-1) == ',') {
					new CSVAttributeMissing(filenameCSVExtension);
					anotherTemp.deleteOnExit();
					track = true;
					System.out.println("File "+filenameHTMLExtension+" could not be created");
				}
				else {
					tokenizeAttributes(attributes,",",pw);
					int line = 3;
					while(sc.hasNextLine()){
						String eachLine = sc.nextLine();
						boolean isNote = true;
						if(eachLine.contains("Note") == isNote) {
							StringTokenizer st = new StringTokenizer(eachLine,",");
							Note = st.nextToken();
						}
						if(eachLine.contains("Note") != isNote) {
							if(eachLine.contains(", ,") || eachLine.contains(",\n")) {
								new CSVDataMissing(filenameCSVExtension,line);
							}
							else{tokenizeData(eachLine,",",pw);}
							line+=1;
						}
					}
					pw.println("</table>");
					pw.println("<span>"+Note+"</span>");
					pw.println("</body>\n</html>");

					System.out.println("Your html file was created successfully");
				}
			}
			sc.close();
			pw.close();
		}
		catch(NoSuchElementException e){
			e.printStackTrace();
		}
		catch(FileNotFoundException e){
			System.out.println("Could not open file "+filenameCSVExtension+" for reading.\nPlease check that the file exists and is readable.\nThis program will terminate after closing any opened files.");
			track = true;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Enter the name of the CSV file you want to change to HTML: ");
		String filename = keyBoard.nextLine();
		ConvertCSVtoHTML(filename);
		int limit = 1;
		boolean stop = false;
		BufferedReader br = null;
		if(track != true) {
			while(limit < 3) {
				System.out.println("Enter the name of the HTML file you want displayed: ");
				String myfile = keyBoard.nextLine();
				myfile+=".html";
				try {
					br = new BufferedReader(new FileReader(myfile));
					boolean ready = br.ready();
					while(ready) {
						System.out.println(br.readLine());
						ready = br.ready();
					}
					limit+=1;
					br.close();
				}
				catch(FileNotFoundException e){
					System.out.println("File "+myfile+" could not be opened or is not readable");
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				limit += 1;
			}
			keyBoard.close();
		}
		System.out.println("Thank you for using this program. GOOD BYE!");
	}
}
