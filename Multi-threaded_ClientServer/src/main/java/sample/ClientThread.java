/*
 Name : Saqib ALi Khan
 Class : BSCS-6C
 CMS-ID: 174586
 *
 */

package sample;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread extends Thread{
	
	private Socket clientSocket;
	private ServerSocket serverSocket;
	private WordDictionary dictionary;
	
	public ClientThread(ServerSocket server, Socket client, WordDictionary dictionary) {
		this.clientSocket = client;
		this.serverSocket = server;
		this.dictionary = dictionary;
	}

	public void run() {
		try {
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			JSONParser parser = new JSONParser();
			JSONObject request = null;

			while(true){
				request = (JSONObject) parser.parse(in.readUTF());

				String op = (String) request.get("operation");
				String word = (String) request.get("word");
				String result;
				switch (Integer.parseInt(op)){
					case 1:
						result = searchWord(word);
						out.writeUTF(result);
						break;
					case 2:
						String meaning = (String) request.get("meaning");
						result = addWord(word, meaning);
						out.writeUTF(result);
						break;
					case 3:
						result = deleteWord(word);
						out.writeUTF(result);
						break;
					default:
						out.writeUTF("Not a valid operation");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	public String searchWord(String word){
		if (this.dictionary.getWords().get(word) == null){
			return "NotFound: The given word does not exist in the dictionary";
		}
		else{
			return this.dictionary.getWords().get(word).toString();
		}
	}

	public String addWord(String word, String meaning) throws IOException {
		if (this.dictionary.getWords().get(word) != null){
			return "AddError: Word already exists in the database";
		}
		this.dictionary.getWords().put(word, meaning);
		return "Success";
	}

	public String deleteWord(String word) throws IOException {
		if (this.dictionary.getWords().get(word) == null){
			return "DeleteError: Word of this name does not exists";
		}
		this.dictionary.getWords().remove(word);
		return "Success";
	}
}
