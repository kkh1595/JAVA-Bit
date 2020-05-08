package catchMind;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PracticeChangePW implements Runnable{
	
	private JButton done;
	private JPasswordField inputPW1, inputPW2;
	private JLabel PWV;
	private PracticeLogin practiceLogin;
	
	public PracticeChangePW (String email, PracticeLogin practiceLogin) {
		this.practiceLogin = practiceLogin;
		JFrame frame = new JFrame("비밀번호 변경");
		JOptionPane.showMessageDialog(practiceLogin.getFrame(), "임시 비밀번호로 로그인하셨습니다");
		
		frame.getContentPane().setLayout(null);
		
		frame.setVisible(true);
		frame.setSize(300,450);
		frame.setLocationRelativeTo(practiceLogin.getFrame());
		JLabel pw1 = new JLabel("새 비밀번호");
		pw1.setSize(84, 37);
		frame.getContentPane().add(pw1);
		
		pw1.setLocation(36,75);
		JLabel pw2 = new JLabel("재입력");
		pw2.setSize(47, 37);
		frame.getContentPane().add(pw2);
		pw2.setLocation(60,121);
		inputPW1 = new JPasswordField(); 
		inputPW1.setSize(120, 37);
		frame.getContentPane().add(inputPW1);
		inputPW1.setLocation(124,75);
		inputPW2 = new JPasswordField();
		inputPW2.setSize(120, 37);
		frame.getContentPane().add(inputPW2);
		inputPW2.setLocation(124,125);
		PWV = new JLabel("비밀번호를 입력하세요");
		PWV.setBounds(116, 172, 128, 37);
		frame.getContentPane().add(PWV);
		PWV.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton back = new JButton("뒤로가기");
		back.setSize(190, 40);
		frame.getContentPane().add(back);
		back.setLocation(50,330);
		done = new JButton("확인");
		done.setSize(190, 40);
		frame.getContentPane().add(done);
		done.setLocation(50,280);
		
		done.setEnabled(false);
		
		done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] newPW = inputPW1.getPassword();
				for (PracticeDTO dto : practiceLogin.getDB()) {
					if (dto.getEmail().equals(email)) {
						dto.setPw(newPW);
						dto.setTempSW(0);
						JOptionPane.showMessageDialog(frame, "비밀번호가 변경되었습니다");
						frame.setVisible(false);
						return;
					}
				}
				JOptionPane.showMessageDialog(frame, "일치하는 ID가 없습니다");
			}
		});
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		inputPW2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] newPW = inputPW1.getPassword();
				for (PracticeDTO dto : practiceLogin.getDB()) {
					if (dto.getEmail().equals(email)) {
						dto.setPw(newPW);
						dto.setTempSW(0);
						JOptionPane.showMessageDialog(frame, "비밀번호가 변경되었습니다");
						frame.setVisible(false);
						return;
					}
				}
				JOptionPane.showMessageDialog(frame, "일치하는 ID가 없습니다");
			}
		});
		
		Thread t = new Thread(this);
		t.start();
		
		
		
	}	
	@Override
	public void run() {
		while(true) {
			if(inputPW1.getPassword() == null || inputPW1.getPassword().length == 0
				|| inputPW2.getPassword() == null || inputPW2.getPassword().length == 0) {
				PWV.setForeground(Color.black);
				PWV.setText("");
				done.setEnabled(false);
			}else {
				if(Arrays.equals(inputPW1.getPassword(), inputPW2.getPassword())) {
					PWV.setForeground(Color.blue);
					PWV.setText("유효한 비밀번호");
					done.setEnabled(true);
				}else {
					PWV.setForeground(Color.red);
					PWV.setText("비밀번호 다름");
					done.setEnabled(false);
				}
			}
		}
	}
	
	
}
