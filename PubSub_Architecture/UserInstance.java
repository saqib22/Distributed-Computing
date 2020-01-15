import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UserInstance extends Thread{
	private String username;
	private Socket mySocket;
    private DataInputStream in;
    private ChatGroup group;

	public UserInstance(Socket socket) throws IOException {
	    this.mySocket = socket;
        in = new DataInputStream(mySocket.getInputStream());
        group = group.getGroup();
    }

	public void run() {
        String message;
        String username;
	    while(true){
            try {
                message = in.readUTF();
                if (message.contains("@"))
                    group.message_user(message);
                else
                    group.broadcast(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

	public void setUsername(String username){
	    this.username = username;
	}
    public String getUsername(){
	    return username;
    }
    public Socket getMySocket(){
	    return mySocket;
    }

}