import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.text.BadLocationException;

import org.json.JSONObject;

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
	 * @throws BadLocationException 
	 */
	public static void main(String[] args) throws IOException, BadLocationException {
		// TODO Auto-generated method stub
		URL url = new URL(formatQuery(getStringSearch()));
		JSONObject content = new JSONObject(getContent(url).toString());
		printFilmData(content);	
		Hashtable<Integer, String> options = createOptions();
		Hashtable<String, String> dictionary = createDictionary();
		
		optionMenu(options, dictionary, content);
	}
	
	public static void printFilmData (JSONObject object) {
		System.out.println("Título: " + object.getString("Title"));
		System.out.println("Ano: " + object.getString("Year"));
		System.out.println("Gênero: " + object.getString("Genre"));
		System.out.println("Diretor(es): " + object.getString("Director"));
		System.out.println("Autor(es): " + object.getString("Writer"));
	}
	
	public static Hashtable<String, String> createDictionary (){ 
		Hashtable<String, String> dictionary = new Hashtable<String, String>();
				
		dictionary.put("classificação", "Rated");
		dictionary.put("data", "Released");
		dictionary.put("duração", "Runtime");
		dictionary.put("atores", "Actors");
		dictionary.put("sinopse", "Plot");
		dictionary.put("linguagem", "Language");
		dictionary.put("país", "Country");
		dictionary.put("prêmios", "Awards");
		dictionary.put("poster", "Poster");
		dictionary.put("metascore", "Metascore");
		dictionary.put("nota", "imdbRating");
		dictionary.put("votos", "imdbRating");
		dictionary.put("imdbid", "imdbID");
		dictionary.put("tipo", "Type");
	
		return dictionary;
	}
	
	public static Hashtable<Integer, String>  createOptions (){
		Hashtable<Integer, String> options = new Hashtable<Integer, String>();
		
		options.put(1, "classificação");
		options.put(2, "data");
		options.put(3, "duração");
		options.put(4, "atores");
		options.put(5, "sinopse");
		options.put(6, "linguagem");
		options.put(7, "país");
		options.put(8, "prêmios");
		options.put(9, "poster");
		options.put(10, "metascore");
		options.put(11, "nota");
		options.put(12, "votos");
		options.put(13, "imdbid");
		options.put(14, "tipo");
		
		return options;
	}
	
	public static void optionMenu (Hashtable<Integer, String> options, Hashtable<String, String> dictionary, JSONObject content) throws IOException {
		System.out.println("\n\nPara ver mais informações elecione a opção:");
		
		for (int i = 1; i <= 14; i++){
			System.out.println(i + " - " + options.get(i));
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int option = Integer.parseInt(reader.readLine());
		
		String key = dictionary.get(options.get(option));
		getAditionalData(key, content);
	}
	
	public static void getAditionalData (String key, JSONObject content) {
		System.out.println(key.substring(0, 1).toUpperCase() + key.substring(1) + ": " + content.getString(key));
	}
	
	/**
	 * @return the string to search
	 * @throws IOException
	 */
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
		String query = PROTOCOL + AUTHORITY + PATH + "t=" + searchString.replaceAll(" ", "+") + "&plot=full&r=json";
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
