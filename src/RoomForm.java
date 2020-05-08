import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;

public class RoomForm1 extends JFrame {
    private JButton b1,b2,b3,b4,b5,b6,b7;
    private JTable table1, table2;
    private DefaultTableModel model1,model2;
    private JTextArea chatArea,name,level,win;
    private JTextField chat;
    
    
    
    public RoomForm1() {
        getContentPane().setLayout(null);
        
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        p4.setLayout(null);
        p4.setBounds(706, 456, 285, 195);
        
        
        b1=new JButton("참여하기");
        b2=new JButton("방만들기");
        b3=new JButton("내그림");
        b4=new JButton("친구 목록");
        b5=new JButton("로그아웃");
        b6=new JButton("게임종료");
        b7=new JButton("보내기");
        
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);
        p2.add(b5);
        p2.add(b6);
        p1.setBounds(22, 27, 672, 33);
        p2.setBounds(706,27,285,33);
      
        String[] roomField = {"NO", "제목","방장","인원","비고","상태"};
        model1=new DefaultTableModel(roomField,0);
        table1=new JTable(model1);
        table1.setRowHeight(25);
        JScrollPane scroll1=new JScrollPane(table1);
        scroll1.setBounds(22, 70, 672, 366);

        Object room[]= {"25","재밌는게임","겜돌이","3/4","공개","대기"};       
        model1.addRow(room);
        
        String[] userField = {"","닉네임","레벨","위치"};
        model2=new DefaultTableModel(userField,0);
        table2=new JTable(model2);
        JScrollPane scroll2=new JScrollPane(table2);
        scroll2.setBounds(706, 70, 285, 366);
        scroll2.setVerticalScrollBarPolicy
        (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        Object data2[]= {"","겜돌이","20","25번방"};
        model2.addRow(data2);
        
        JScrollPane scroll3 = new JScrollPane();
        scroll3.setVerticalScrollBarPolicy
        (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll3.setBounds(22, 456, 672, 195);
        
        chat=new JTextField();
        chat.setBounds(22, 659, 553, 21);
        
        chatArea=new JTextArea();
        chatArea.setBounds(22, 465, 653, 184);
        
        b7.setBounds(579,659, 115, 22);
        level=new JTextArea();
        level.setBounds(804, 516, 147, 30);
        win=new JTextArea();
        win.setBounds(804, 567, 147, 30);
        
        JLabel nameL = new JLabel("닉네임");
        nameL.setBounds(735, 469, 57, 15);
        
        JLabel levelL = new JLabel("레벨");
        levelL.setBounds(735, 520, 57, 15);
        
        JLabel winL = new JLabel("전적");
        winL.setBounds(735, 571, 57, 15);
        
        name=new JTextArea();
        name.setBounds(804, 465, 147, 30);
        
        p4.add(name);
        p4.add(level);
        p4.add(win);
        p4.add(nameL);
        p4.add(levelL);
        p4.add(winL);
        Container c =getContentPane();
        c.add(p1);
        c.add(p2);
        c.add(scroll1);
        c.add(scroll2);
        c.add(scroll3);
        c.add(chat);
        c.add(b7);       
        c.add(chatArea);
        c.add(p4);
        
        
        setTitle("캐치마인드");
        setSize(1024,768);  
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new RoomForm1();
    }
}

