/*
 Name : Saqib ALi Khan
 Class : BSCS-6C
 CMS-ID: 174586
 *
 */

package sample;
import org.json.JSONException;
import org.json.simple.JSONObject;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private String address;
	private int port;
	private Socket socket;
	
	public Client(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public void request_server() throws IOException, JSONException {
		createSocket();
		queryWord("jolty");
		Scanner scanner = new Scanner(System.in);
		String operation;
		String word;
		System.out.println("1: Search/Query\n2: Add Word\n3: Delete Word");
		while (true){
			System.out.print("\nEnter the operation you want to perform: ");
			operation = scanner.next();
			if (Integer.parseInt(operation) == 1){
				System.out.print("Enter the word: ");
				word = scanner.next();
				this.queryWord(word);

			}
			else if (Integer.parseInt(operation) == 2){
				System.out.print("Enter the word you want to add: ");
				word = scanner.next();
				System.out.print("Enter the meaning of this word: ");
				String meaning = scanner.next();
				this.addWord(word, meaning);
			}
			else if (Integer.parseInt(operation) == 3){
				System.out.print("Enter the word you want to delete: ");
				word = scanner.next();
				this.deleteWord(word);
			}
			else{
				System.out.println("Please enter valid operation number. Try Again !");
			}
		}
	}


	public void createSocket() throws IOException {
		socket = new Socket(address, port);
	}

	public void queryWord(String word) throws IOException, JSONException {
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		DataInputStream in = new DataInputStream(socket.getInputStream());
		//Create an object to send over TCP to server
		JSONObject json = new JSONObject();
		json.put("operation", "1");
		json.put("word", word);
		out.writeUTF(json.toString());
		System.out.println("Server Response: " + in.readUTF());
	}

	public void addWord(String word, String meaning) throws IOException {
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		DataInputStream in = new DataInputStream(socket.getInputStream());

		//Creating the request object to pass over TCP to server
		JSONObject json = new JSONObject();
		json.put("operation", "2");
		json.put("word", word);
		json.put("meaning", meaning);
		out.writeUTF(json.toString());
		System.out.println("Server Response: " + in.readUTF());
	}

	public void deleteWord(String word) throws IOException {
		DataOutputStream out  = new DataOutputStream(socket.getOutputStream());
		DataInputStream in = new DataInputStream(socket.getInputStream());

		//Creating the JSON object to pass over TCP to server
		JSONObject json = new JSONObject();
		json.put("operation", "3");
		json.put("word", word);
		out.writeUTF(json.toString());
		System.out.println("Server Response: " + in.readUTF());
	}

	public static void main(String[] args) throws IOException, JSONException {
		Client client = new Client("127.0.0.1", 5000);
		client.request_server();
	}
}