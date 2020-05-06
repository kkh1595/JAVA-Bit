package scoreProject;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class ScoreForm extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel l1,l2,l3,l4,l5;
    private JButton b1,b2,b3,b4,b5,b6;
    private JTextField t1,t2,t3,t4,t5;
    private JTable table;
    private DefaultTableModel model;
    private ScoreDTO dto;
    private Score score;
    private File file;
   // private ArrayList<ScoreDTO> list;
          
    public ScoreForm() {     
        JPanel jp1 = new JPanel(new GridLayout(5,2,0,20));
        JPanel buttonP = new JPanel(new GridLayout(1,6));
        JPanel center = new JPanel(new GridLayout(1,2));
        
        l1=new JLabel("학번");
        l2=new JLabel("이름");
        l3=new JLabel("국어");
        l4=new JLabel("영어");
        l5=new JLabel("수학");
             
        t1=new JTextField(10);
        t2=new JTextField(10);
        t3=new JTextField(10);
        t4=new JTextField(10);
        t5=new JTextField(10);
        
        b1=new JButton("입력");
        b2=new JButton("출력");
        b3=new JButton("학번검색");
        b4=new JButton("순위");
        b5=new JButton("열기");
        b6=new JButton("저장");
        
        jp1.add(l1);
        jp1.add(t1);
        jp1.add(l2);
        jp1.add(t2);
        jp1.add(l3);
        jp1.add(t3);
        jp1.add(l4);
        jp1.add(t4);
        jp1.add(l5);
        jp1.add(t5);
             
        buttonP.add(b1);
        buttonP.add(b2);
        buttonP.add(b3);
        buttonP.add(b4);
        buttonP.add(b5);
        buttonP.add(b6);
        
        String[] field = {"학번","이름","국어","영어","수학","총점","평균"};
        model=new DefaultTableModel(field,0);       
        table=new JTable(model);
        table.setAutoCreateRowSorter(true);
        score=new ScoreImpl();
        
        //list=new ArrayList<ScoreDTO>();
        JScrollPane scroll = new JScrollPane(table);        
        Container c = getContentPane();
        center.add(jp1); 
        center.add(scroll);             
        c.add("Center",center);
        c.add("South",buttonP);
           
        setTitle("성적관리");
        setBounds(600,200,600,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);               
    }  
    @Override
    public void actionPerformed(ActionEvent e) {           
           if(e.getSource()==b1) {             
               if(t1.getText()==null || t2.getText()==null || t3.getText()==null  
                ||t4.getText()==null || t5.getText()==null || t1.getText().length()==0
                ||t2.getText().length()==0|| t3.getText().length()==0||
                t4.getText().length()==0|| t5.getText().length()==0) {
                   JOptionPane.showMessageDialog(this,"정확히 입력해주세요");
                   return;
               }
           dto=new ScoreDTO();
           dto.setId(Integer.parseInt(t1.getText()));
           dto.setName(t2.getText());
           dto.setKor(Integer.parseInt(t3.getText()));
           dto.setEng(Integer.parseInt(t4.getText()));
           dto.setMath(Integer.parseInt(t5.getText()));
           dto.calc();
           t1.setText("");
           t2.setText("");
           t3.setText("");
           t4.setText("");
           t5.setText("");  
           score.input(dto);
           JOptionPane.showMessageDialog(this,"입력되었습니다"); 
          // list.add(dto);   
           
       }else if(e.getSource()==b2) { 
           
           //model.setRowCount[0];
           score.output(model);      
           
       }else if(e.getSource()==b3) {
           String id;
           id = JOptionPane.showInputDialog(this,"학번를 입력해주세요"); 
           if(id==null || id.equals("")) return;
           int sw = score.search(model,id); //1.모델과 학번을 보내고, 2.인트값으로 돌려받고 
                                            //3.sw에 그 값을 다시 넘겨준다
           if(sw==0) {
               JOptionPane.showMessageDialog(this,"입력된 정보가 없습니다.");
           }
           
       }else if(e.getSource()==b4) { //순위
           score.to_desc(); //총점으로 내림차순
           score.output(model);
       }else if(e.getSource()==b5) {
           score.load();
           score.output(model);
       }else if(e.getSource()==b6) {
           score.save();
       }     
    }
    

    
      
}//scoreForm class
