package catchMind;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class RoomChatHandler extends Thread{ //핸들러가 소켓,IO(reader,writer) 를 쥐고있음.
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private Socket socket;
    private List<RoomChatHandler> list;
  
    
    public RoomChatHandler(Socket socket, List<RoomChatHandler> list) {
          this.socket=socket;
          this.list=list;         
          try {
              writer=new ObjectOutputStream(socket.getOutputStream());
              reader=new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void run() {      
        UserDTO infoDTO=null;
        String nickName=null;
            //클라이언트로부터 받기
            //닉네임을 먼저 받는다.
        try {
            while(true) {
                infoDTO=(UserDTO)reader.readObject();
                
                if(infoDTO.getCommand()==Info.JOIN) { //입장시
                    nickName=infoDTO.getNickName();
                    UserDTO sendDTO = new UserDTO();
                    sendDTO.setCommand(Info.SEND);
                    sendDTO.setMessage(nickName+"님 입장하셨습니다");
                    broadcast(sendDTO);
                    
                }else if(infoDTO.getCommand()==Info.EXIT) { //종료시
                    UserDTO sendDTO = new UserDTO();
                    //나가려고 exit를 보낸 클라이언트에게 답변 보내기
                    sendDTO.setCommand(Info.EXIT);
                    writer.writeObject(sendDTO);
                    writer.flush();
                    
                    reader.close();
                    writer.close();
                    socket.close();
                    //남아있는 클라이언트에게 퇴장메세지 보내기
                    list.remove(this);
                    
                    sendDTO.setCommand(Info.SEND);
                    sendDTO.setMessage(nickName+"님 퇴장하셨습니다");
                    
                    broadcast(sendDTO);                    
                    break;
                    
                }else if(infoDTO.getCommand()==Info.SEND) {
                    UserDTO sendDTO = new UserDTO();
                    sendDTO.setCommand(Info.SEND);
                    String msg =infoDTO.getMessage();
                    sendDTO.setMessage("["+nickName+"]"+msg);  
                    
                    broadcast(sendDTO);
                }
            }//while          
        }catch(IOException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException e1) {
            e1.printStackTrace();
        }    
    }
    public void broadcast(UserDTO dto) {
        for(RoomChatHandler cho : list) {
            try {
                cho.writer.writeObject(dto);
                cho.writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
    }
  
}//ChatHandler
