package catchMind;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PracticeLostID {
	private PracticeLogin practiceLogin;
	
	public PracticeLostID(PracticeLogin practiceLogin) {
		this.practiceLogin = practiceLogin;
		
		JFrame frame = new JFrame("아이디 찾기");
		frame.setTitle("이메일 찾기");
		frame.setSize(300,250);
		frame.setLocationRelativeTo(practiceLogin.getFrame());

		
		frame.getContentPane().setLayout(null);
		
		JLabel mobile = new JLabel("핸드폰 번호");
		mobile.setSize(77, 37);
		frame.getContentPane().add(mobile);
		
		mobile.setLocation(45,57);
		JTextField inputMobile = new JTextField(10);
		inputMobile.setSize(120, 30);
		frame.getContentPane().add(inputMobile);
		inputMobile.setLocation(130,60);
		JButton cancel = new JButton("취소");
		cancel.setSize(100, 30);
		frame.getContentPane().add(cancel);
		cancel.setLocation(150,140);
		JButton done = new JButton("확인");
		done.setSize(100, 30);
		frame.getContentPane().add(done);
		done.setLocation(45,140);
		
		
		
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				practiceLogin.getLostId().setEnabled(true);
			}
		});
		
		
		
		
		inputMobile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String findMobile = inputMobile.getText();
				for (PracticeDTO dto : practiceLogin.getDB()) {
					if (dto.getMobile().equals(findMobile)) {
						JOptionPane.showMessageDialog(frame, "ID는 " + dto.getEmail() + " 입니다");
						frame.setVisible(false);
						return;
					}
				}
				
			}
		});
		done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String findMobile = inputMobile.getText();
				for (PracticeDTO dto : practiceLogin.getDB()) {
					if (dto.getMobile().equals(findMobile)) {
						JOptionPane.showMessageDialog(frame, "ID는 " + dto.getEmail() + " 입니다");
						frame.setVisible(false);
						practiceLogin.getLostId().setEnabled(true);
						return;
					}
				}
				
			}
		});
		
		
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				practiceLogin.getLostId().setEnabled(true);
			}
		});
		frame.setVisible(true);
	}
	
}
