package catchMind;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


//import network14.ChatHandlerObject;
//import network14.ChatServerObject;

public class RoomChatServer { // 각 클라이언트의 소켓에 대응할 서버소켓을 모아둠, 서버자체는 스레드가 되면 안된다.
    private ServerSocket serverSocket;
    private List<RoomChatHandler> list;

    public RoomChatServer() {
        try {
            serverSocket = new ServerSocket(9500);
            System.out.println("서버준비완료..");

            list = new ArrayList<RoomChatHandler>(); // 여러 소켓을 담을 리스트

            while (true) {
                Socket socket = serverSocket.accept(); // 소켓이 들어오는대로 낚아채준다.

                RoomChatHandler handler = new RoomChatHandler(socket, list); // ChatHandler가 Thread를 상속받았기에
                handler.start(); // 스레드 생성
                
                list.add(handler); // 핸들러의 갯수=클라이언트의 갯수
            } // while
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RoomChatServer();

    }

}