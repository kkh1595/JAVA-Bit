package catchMind;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PracticeLostPW {
	private PracticeLogin practiceLogin;
	private JTextField inputEmail;
	private JFrame frame;
	
	public PracticeLogin getPracticeLogin() {
		return practiceLogin;
	}

	public void setPracticeLogin(PracticeLogin practiceLogin) {
		this.practiceLogin = practiceLogin;
	}

	public JTextField getInputEmail() {
		return inputEmail;
	}

	public void setInputEmail(JTextField inputEmail) {
		this.inputEmail = inputEmail;
	}



	public PracticeLostPW(PracticeLogin practiceLogin) {
		this.practiceLogin = practiceLogin;
		
		frame = new JFrame("비밀번호 찾기");
		frame.setSize(300,250);
		frame.setLocationRelativeTo(practiceLogin.getFrame());
		frame.getContentPane().setLayout(null);
		JButton cancel = new JButton("취소");
		cancel.setBounds(150, 140, 100, 30);
		frame.getContentPane().add(cancel);
		JButton done = new JButton("확인");
		done.setBounds(45, 140, 100, 30);
		frame.getContentPane().add(done);
		
		JLabel email = new JLabel("이메일 주소");
		email.setBounds(44, 56, 79, 37);
		frame.getContentPane().add(email);
		inputEmail = new JTextField(15);
		inputEmail.setBounds(135, 60, 120, 30);
		frame.getContentPane().add(inputEmail);
			
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				practiceLogin.getLostPw().setEnabled(true);
			}
		});
		
		
		
		
		
		inputEmail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(PracticeDTO dto : practiceLogin.getDB()) {
					if(dto.getEmail().equals(inputEmail.getText())) {
						new PracticeEmail(inputEmail.getText(), practiceLogin);
						JOptionPane.showMessageDialog(frame, "메일 발송 완료");
						frame.setVisible(false);
						practiceLogin.getLostPw().setEnabled(true);
						return;
					}
				}
				JOptionPane.showMessageDialog(frame, "일치하는 ID가 없습니다");
			}
		});
		
		
		done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(PracticeDTO dto : practiceLogin.getDB()) {
					if(dto.getEmail().equals(inputEmail.getText())) {
						new PracticeEmail(inputEmail.getText(), practiceLogin);
						JOptionPane.showMessageDialog(frame, "메일 발송 완료");
						frame.setVisible(false);
						practiceLogin.getLostPw().setEnabled(true);
						return;
					}
				}
				JOptionPane.showMessageDialog(frame, "일치하는 ID가 없습니다");
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				practiceLogin.getLostPw().setEnabled(true);
			}
		});
		frame.setVisible(true);
		
	}


	
}
