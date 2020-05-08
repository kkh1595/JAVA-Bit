package catchMind;

import java.io.Serializable;

enum Info{
    JOIN, EXIT, SEND
}
public class UserDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String nickName, message;
    private Info command;

    public String getNickName() {
        return nickName;
    }
    public String getMessage() {
        return message;
    }
    public Info getCommand() {
        return command;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setCommand(Info command) {
        this.command = command;
    }

}
