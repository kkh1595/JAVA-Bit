package catchMind;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PracticeUpdate {
	private PracticeLogin practiceLogin;
	private JTextField inputEmail;
	private JPasswordField passwordField;
	private JFrame frame;
	public PracticeUpdate(PracticeLogin practiceLogin) {
		this.practiceLogin = practiceLogin;
		
		
		frame = new JFrame("회원정보수정");
		
		frame.setSize(300,250);
		frame.setVisible(true);
		frame.setLocationRelativeTo(practiceLogin.getFrame());
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("이메일주소");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 44, 122, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 85, 122, 33);
		frame.getContentPane().add(lblNewLabel_1);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(115, 44, 135, 30);
		frame.getContentPane().add(inputEmail);
		inputEmail.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(115, 85, 135, 30);
		frame.getContentPane().add(passwordField);
		
		JButton back = new JButton("취소");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				practiceLogin.getUpdate().setEnabled(true);
			}
		});
		back.setBounds(150, 140, 100, 30);
		frame.getContentPane().add(back);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				practiceLogin.getUpdate().setEnabled(true);
			}
		});
		
		
		
		JButton done = new JButton("확인");
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(PracticeDTO dto : practiceLogin.getDB()) {
					if(dto.getEmail().equals(inputEmail.getText()) && Arrays.equals(dto.getPw(), passwordField.getPassword())) {
						new PracticeUpdateUpdate(practiceLogin, inputEmail.getText());
						frame.setVisible(false);
						return;
					}
				}
				JOptionPane.showMessageDialog(frame, "일치하는 정보가 없습니다.");
			}
		});
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(PracticeDTO dto : practiceLogin.getDB()) {
					if(dto.getEmail().equals(inputEmail.getText()) && Arrays.equals(dto.getPw(), passwordField.getPassword())) {
						new PracticeUpdateUpdate(practiceLogin, inputEmail.getText());
						frame.setVisible(false);
						return;
					}
				}
				JOptionPane.showMessageDialog(frame, "일치하는 정보가 없습니다.");
			}
		});
		done.setBounds(45, 140, 100, 30);
		frame.getContentPane().add(done);
	}
		
}
