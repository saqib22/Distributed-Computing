/*
 Name : Saqib ALi Khan
 Class : BSCS-6C
 CMS-ID: 174586
 *
 */
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Publisher {
	private int port;
	private DataInputStream in;
	private ChatGroup group;

	public Publisher(int port) {
	    this.port = port;
	    group = group.getGroup();
	}
	
	public void connectClient() throws IOException {
		ServerSocket pub_socket = createSocket(port);
		System.out.println("Server is running .....");
		Socket sub_socket;

		while(true) {
			sub_socket = pub_socket.accept();
			System.out.println("Subscriber Connected at " + sub_socket.getPort() + " with IP " + sub_socket.getInetAddress().toString());
			in = new DataInputStream(sub_socket.getInputStream());
			UserInstance user = new UserInstance(sub_socket);
			user.setUsername(in.readUTF());
			user.start();
            group.addUser(user);
		}
	}
	
	public ServerSocket createSocket(int port) throws IOException {
		ServerSocket socket = new ServerSocket(port);
		return socket;
	}
	
	public static void main(String[] args) throws IOException {
		Publisher pub = new Publisher(25000);
		pub.connectClient();
	}
}