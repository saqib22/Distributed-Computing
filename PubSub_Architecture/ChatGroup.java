import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class ChatGroup{

    public ArrayList<UserInstance> subscribers = new ArrayList<UserInstance>();
    private static ChatGroup group = new ChatGroup();
    private DataOutputStream out;

    private ChatGroup(){ }

    public static ChatGroup getGroup(){
        return group;
    }

    public void addUser(UserInstance user){
        subscribers.add(user);
    }

    public void leave(UserInstance user){
        subscribers.remove(user);
    }

    public void broadcast(String msg) throws IOException {
        for (int i = 0; i < subscribers.size(); i++){
            out = new DataOutputStream(subscribers.get(i).getMySocket().getOutputStream());
            out.writeUTF(msg);
        }
    }

    public void message_user(String msg) throws IOException {
        String[] message = msg.split("@", 2);
        for (int i = 0; i < subscribers.size(); i++){
            if (subscribers.get(i).getUsername().equals(message[1])){
                out = new DataOutputStream(subscribers.get(i).getMySocket().getOutputStream());
                out.writeUTF(msg);
                break;
            }
        }
    }
}
