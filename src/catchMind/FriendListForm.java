package catchMind;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FriendListForm extends JFrame implements ActionListener{
    private DefaultTableModel model;
    private JButton exitBtn;
    private JTable table;
     
    public FriendListForm() {    
        exitBtn=new JButton("닫기");
        
        String[] ar = {"닉네임","레벨","위치"};
        model=new DefaultTableModel(ar,0);
        table=new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        
        getContentPane().add("Center",scroll);
        getContentPane().add("South",exitBtn);

        setTitle("친구목록");
        setBounds(500,300,300,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
        exitBtn.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exitBtn) {
            dispose();           
        }
    }

}