package catchMind;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

public class RoomForm extends JFrame implements ActionListener, Runnable {
    private JButton enterB,makingB,myPaintB,friendB,logoutB,exitB,sendB;
    private JTable table1, table2;
    private DefaultTableModel model1,model2;
    private JTextArea chatArea,nickName,level,score;
    private JTextField chat;
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    FriendListForm friend;
    PracticeLogin practiceLogin;
    RoomForm roomForm ;
     
    public RoomForm(PracticeLogin practiceLogin) {
        this.practiceLogin = practiceLogin;

        getContentPane().setLayout(null);       
        JPanel ButtonP1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel ButtonP2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel StatusP = new JPanel();
        StatusP.setLayout(null);
        StatusP.setBackground(new Color(211, 211, 211));
        StatusP.setBounds(706, 456, 285, 195);
        
        //버튼 생성 및 위치조정=====================================================
        enterB=new JButton("참여하기");
        makingB=new JButton("방만들기");
        myPaintB=new JButton("내그림");
        friendB=new JButton("친구 목록");
        logoutB=new JButton("로그아웃");
        exitB=new JButton("게임종료");
        sendB=new JButton("보내기");
        sendB.setBounds(579,659, 115, 22);
       
        ButtonP1.add(enterB);
        ButtonP1.add(makingB);
        ButtonP1.add(myPaintB);
        ButtonP1.add(friendB);
        ButtonP2.add(logoutB);
        ButtonP2.add(exitB);
        ButtonP1.setBounds(22, 27, 672, 33);
        ButtonP2.setBounds(706,27,285,33);
        
        //대기방 리스트 테이블 =====================================================
        String[] roomField = {"NO", "제목","방장","인원","비고","상태"};
        model1=new DefaultTableModel(roomField,0);
        table1=new JTable(model1);
        table1.setRowHeight(25); //테이블 행 높이 설정
        table1.getTableHeader().setReorderingAllowed(false);
        JScrollPane scroll1=new JScrollPane(table1);
        scroll1.setBounds(22, 70, 672, 366);

        Object room[]= {"25","재밌는게임","겜돌이","3/4","공개","대기"};  //임시 생성 값
        model1.addRow(room);
        
        //접속중인 유저 테이블 ======================================================
        String[] userField = {"","닉네임","레벨","위치"};
        model2=new DefaultTableModel(userField,0);
        table2=new JTable(model2); 
        table2.setRowHeight(25);
        table2.getTableHeader().setReorderingAllowed(false);
        JScrollPane scroll2=new JScrollPane(table2);
        scroll2.setBounds(706, 70, 285, 366);
        scroll2.setVerticalScrollBarPolicy
        (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        Object data2[]= {"","겜돌이","20","25번방"}; //임시 유저 생성
        model2.addRow(data2);
        
        //채팅창 및 채팅입력창=======================================================
        chatArea=new JTextArea();
        chatArea.setFont(new Font("Monospaced", Font.BOLD, 20));
        chatArea.setEditable(false);
        chatArea.setBounds(22, 465, 653, 184);       
        JScrollPane scroll3 = new JScrollPane(chatArea);
        scroll3.setBounds(22, 456, 672, 195);
        scroll3.setVerticalScrollBarPolicy
        (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        chat=new JTextField();
        chat.setBounds(22, 659, 553, 21);
        
        //내 정보 관련  =====================================================
        JLabel nameL = new JLabel("닉네임");
        nameL.setBounds(22, 22, 55, 28);
        StatusP.add(nameL);
        
        nickName=new JTextArea();
        nickName.setEditable(false);
        nickName.setBounds(89, 22, 97, 24);
        StatusP.add(nickName);
        
        JLabel levelL = new JLabel("레벨");
        levelL.setBounds(22, 84, 37, 15);
        StatusP.add(levelL);
        
        level=new JTextArea();
        level.setEditable(false);
        level.setBounds(89, 80, 97, 24);
        StatusP.add(level);
        
        JLabel winL = new JLabel("전적");
        winL.setBounds(22, 141, 37, 15);
        StatusP.add(winL);
        
        score=new JTextArea();
        score.setEditable(false);
        score.setBounds(89, 137, 97, 24);
        StatusP.add(score);
        
        //컨테이너에 각 패널,스크롤,버튼 올리기====================================
        Container c =getContentPane();
        c.add(ButtonP1);
        c.add(ButtonP2);
        c.add(scroll1);
        c.add(scroll2);
        c.add(scroll3);
        c.add(chat);
        c.add(sendB);       
        c.add(StatusP);
       
        //프레임 관련=======================================================
        setTitle("캐치마인드");
        setSize(1024,768);  
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        service(roomForm);
        
        
        enterB.addActionListener(this);
        makingB.addActionListener(this);
        myPaintB.addActionListener(this);
        friendB.addActionListener(this);
        logoutB.addActionListener(this);
        exitB.addActionListener(this);
        sendB.addActionListener(this);
        chat.addActionListener(this);
        addWindowListener(new WindowAdapter() {       
            @Override
            public void windowClosing(WindowEvent e) {
                UserDTO userDTO = new UserDTO();
                userDTO.setCommand(Info.EXIT);
                try {
                    writer.writeObject(userDTO);
                    writer.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }    
    //채팅구현====================================================
    public void service(RoomForm roomForm) {
        String serverIP = JOptionPane.showInputDialog(this, "서버IP를 입력하세요","192.168.0.2");
        if(serverIP==null || serverIP.length()==0 ) {
            System.out.println("서버IP가 입력되지 않았습니다");
            System.exit(0);
        }
        //닉네임
        String nickName=JOptionPane.showInputDialog(this,"닉네임을 입력하세요","닉네임",JOptionPane.INFORMATION_MESSAGE);
        if(nickName==null || nickName.length()==0 ) {
            nickName="guest";
        }
        //소켓생성
        try {
            socket=new Socket(serverIP,9500);
            writer=new ObjectOutputStream(socket.getOutputStream());
            reader=new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            System.out.println("서버를 찾을 수 없습니다");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("서버와 연결이 되지 않았습니다");
            e.printStackTrace();
            System.exit(0);
        }
        //서버로 닉네임 보내기
        UserDTO userDTO=new UserDTO();
        userDTO.setCommand(Info.JOIN);
        userDTO.setNickName(nickName);
        try {
            writer.writeObject(userDTO);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //스레드생성
        Thread t = new Thread(this);
        t.start();
        
    }
    //스레드 실행=============================================================
    public void run() {
        //서버로 부터 받기
        UserDTO userDTO =null;
         while(true) {
             try {              
                 userDTO = (UserDTO)reader.readObject();
                 if(userDTO.getCommand()==Info.EXIT) {
                     reader.close();
                     writer.close();
                     socket.close();
                     
                     System.exit(0);
                 }else if(userDTO.getCommand()==Info.SEND) {
                     chatArea.append(userDTO.getMessage()+"\n"); //그게아니면 area에 받은값 출력
                       
                     int pos = chatArea.getText().length();
                     chatArea.setCaretPosition(pos);//텍스트 길어져도 새 입력값 화면보여주기
                 }                
             } catch (IOException e) {
                 e.printStackTrace();
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             } 
         }
    }
    //각 버튼 기능 구현=======================================================
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==enterB) { 
            
        }else if(e.getSource()==makingB) {
            
        }else if(e.getSource()==myPaintB) {
            
        }else if(e.getSource()==friendB) {
            friend = new FriendListForm();
            
        }else if(e.getSource()==logoutB) {
            int result;
            result=JOptionPane.showConfirmDialog(this, "로그아웃 하시겠습니까?","로그아웃",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
                System.exit(0);
            }
            
        }else if(e.getSource()==exitB) {
            int result;
            result=JOptionPane.showConfirmDialog(this, "종료하시겠습니까?","종료",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION) {
                UserDTO userDTO = new UserDTO();
                userDTO.setCommand(Info.EXIT);
                try {
                    writer.writeObject(userDTO);
                    writer.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            
        }else if(e.getSource()==sendB || e.getSource()==chat) {
            String data = chat.getText();
            if(data==null || data.length()==0) {
                return;
            }
            UserDTO userDTO = new UserDTO();
            // infoDTO.setMessage(data);
            if(data.equals("/exit")) {
                userDTO.setCommand(Info.EXIT);   //exit입력하면 종료    
            }else {
                userDTO.setCommand(Info.SEND); //그게아니면 다 메세지로 send
                userDTO.setMessage(data);
            }
            try {
                writer.writeObject(userDTO);
                writer.flush();
            }catch(IOException e1) {
                e1.printStackTrace();
            }
            chat.setText("");
        }
    }
    //메인메소드==========================================================
//    public static void main(String[] args) {
//        new RoomForm(practiceLogin).service();
//    }
}

