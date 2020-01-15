import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Subscriber {
    private String address = "127.0.0.1";
    private int port = 25000;
    private Socket socket;
    private String username;

    public Subscriber() {
    }

    public void connect() throws IOException, ClassNotFoundException {
        socket = createConnection();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        this.username = scanner.next();

        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(username);
    }


	public void chat() throws IOException {
        System.out.println("Joined the ChatRoom !\n");
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        // sendMessage thread
        Thread sendMessage = new Thread(new Runnable()
        {
            private String message;
            Scanner scanner = new Scanner(System.in);
            @Override
            public void run() {
                while (true) {
                    message = scanner.nextLine();
                    try {
                        out.writeUTF(username+":"+message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //readmessgae thread
        Thread readMessage = new Thread(new Runnable() {
            String[] message;
            @Override
            public void run() {
                while(true){
                    try {
                        message = in.readUTF().split(":", 2);
                        System.out.println(message[0] + ": " + message[1].split("@",2)[0]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        sendMessage.start();
        readMessage.start();

    }

    public Socket createConnection() throws IOException {
        Socket sock = new Socket(address, port);
        return sock;
    }


	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Subscriber sub = new Subscriber();
		sub.connect();
		sub.chat();
	}
}
