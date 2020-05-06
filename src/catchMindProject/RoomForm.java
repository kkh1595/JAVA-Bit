package catchMindProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class RoomForm extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private DefaultTableModel model,model2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RoomForm frame = new RoomForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public RoomForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 768);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnNewButton = new JButton("참여하기");
        btnNewButton.setBounds(35, 10, 110, 43);
        contentPane.add(btnNewButton);
        
        JButton button = new JButton("방만들기");
        button.setBounds(157, 10, 110, 43);
        contentPane.add(button);
        
        JButton button_1 = new JButton("내 그림");
        button_1.setBounds(279, 10, 110, 43);
        contentPane.add(button_1);
        
        JButton button_3 = new JButton("게임종료");
        button_3.setBounds(873, 10, 97, 43);
        contentPane.add(button_3);
        
        JTextArea textArea_2 = new JTextArea();
        textArea_2.setText("채팅창");
        textArea_2.setEditable(false);
        textArea_2.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent arg0) {
            }
        });
        textArea_2.setBounds(35, 443, 667, 200);
        contentPane.add(textArea_2);
        
        textField = new JTextField();
        textField.setText("ㅎㅇㅎㅇ");
        textField.setBounds(35, 653, 540, 21);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton_1 = new JButton("보내기");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton_1.setBounds(587, 652, 115, 23);
        contentPane.add(btnNewButton_1);
        
        String[] ar = {"NO", "제목","방장","인원","비고","상태"};
        model=new DefaultTableModel(ar,0);
        table = new JTable(model);
        table.setRowSelectionAllowed(false);
        table.setAutoCreateRowSorter(true);
        table.setBounds(35, 63, 667, 357);
        contentPane.add(table);
        Vector<String> v = new Vector<String>();
        v.add(ar[0]);
        v.add(ar[1]);
        v.add(ar[2]);
        v.add(ar[3]);
        v.add(ar[4]);
        v.add(ar[5]);
        model.addRow(v);
       // JScrollPane scroll = new JScrollPane(table);
        //contentPane.add(scroll);
        
        JTextArea textArea_1 = new JTextArea();
        textArea_1.setText("내정보");
        textArea_1.setBounds(763, 443, 207, 200);
        contentPane.add(textArea_1);
                

        String[] ar2={" ", "닉네임", "레벨", "위치"};
        model2=new DefaultTableModel(ar2,0);
        JTable table_1 = new JTable(model2);
        table_1.setRowSelectionAllowed(false);
        table_1.setBounds(763, 63, 207, 357);
        table_1.setAutoCreateRowSorter(true);
        contentPane.add(table_1);
        Vector<String> v1 =new Vector<String>();
        v1.add(ar2[0]);
        v1.add(ar2[1]);
        v1.add(ar2[2]);
        v1.add(ar2[3]);
        model2.addRow(v1);
        
        JButton button_2 = new JButton("로그아웃");
        button_2.setActionCommand("로그아웃");
        button_2.setBounds(764, 10, 97, 43);
        contentPane.add(button_2);
        
        JButton button_4 = new JButton("친구목록");
        button_4.setBounds(401, 10, 110, 43);
        contentPane.add(button_4);
    }
}
