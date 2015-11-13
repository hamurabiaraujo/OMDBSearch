import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.text.BadLocationException;

/**
 * 
 */

/**
 * @author Hamurabi Araújo
 *
 */
public class OMDBSearch {
	private static final String PROTOCOL = "http://";
	private static final String AUTHORITY = "www.omdbapi.com/";
	private static final String PATH = "?";

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		formatQuery(getStringSearch());
		

	}
	
	/**
	 * @return the string to search
	 * @throws IOException
	 */
	
	//	http://www.omdbapi.com/?t=froze&y=&plot=full&r=json
	public static String getStringSearch() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String searchString = reader.readLine();
		return searchString;
	}
	
	/**
	 * @param searchString the string to search unformatted
	 * @return query formatted
	 */
	public static String formatQuery(String searchString) {
		String query = "t=" + searchString.replaceAll(" ", "+") + "&count=100";
		return query;
	}
	
	/**
	 * @param url to pull the content
	 * @return a StringBuffer with the all content 
	 * @throws IOException
	 * @throws BadLocationException 
	 */
	public static StringBuffer getContent(URL url) throws IOException, BadLocationException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStreamReader reader = new InputStreamReader((java.io.InputStream) connection.getContent());

		BufferedReader buffReader = new BufferedReader(reader);
		StringBuffer content = new StringBuffer();
		String line;
		do {
			line = buffReader.readLine();
		    content.append(line + "\n");
		} while (line != null);
		return content;
	}

}
