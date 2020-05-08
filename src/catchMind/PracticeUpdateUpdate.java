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

public class PracticeUpdateUpdate {
	
	private PracticeLogin practiceLogin;
	private JButton done;
	private JButton cancel;
	private JTextField inputNickName;
	private JPasswordField inputPW1;
	private JPasswordField inputPW2;
	private JTextField inputMobile;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	
	public PracticeUpdateUpdate(PracticeLogin practiceLogin, String email) {
		this.practiceLogin = practiceLogin;
		
		
		JFrame updateFrame = new JFrame("회원정보수정");
		updateFrame.setVisible(true);
		updateFrame.setSize(300,300);
		updateFrame.setLocationRelativeTo(practiceLogin.getFrame());
		updateFrame.getContentPane().setLayout(null);

		
		done = new JButton("확인");
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(inputNickName.getText() == null || inputNickName.getText().length() == 0 ||
						inputMobile.getText() == null || inputMobile.getText().length() == 0 ) {
					JOptionPane.showMessageDialog(updateFrame, "입력되지 않은 값이 있습니다");
					return;
				}
				if(!Arrays.equals(inputPW1.getPassword(), inputPW2.getPassword())) {
					JOptionPane.showMessageDialog(updateFrame, "두 비밀번호가 일치하지 않습니다.");
					return;
				}
				
				
				if(inputPW1 == null || inputPW1.getPassword().length == 0 ||
						inputPW2 == null || inputPW2.getPassword().length == 0 ) {
					for(PracticeDTO dto: practiceLogin.getDB()) {
						if(dto.getEmail().equals(email)) {
							dto.setNickName(inputNickName.getText());
							dto.setMobile(inputMobile.getText());
							JOptionPane.showMessageDialog(updateFrame, "회원정보가 수정되었습니다");
							updateFrame.setVisible(false);
							practiceLogin.getUpdate().setEnabled(true);
						}
					}
				}else {
					for(PracticeDTO dto: practiceLogin.getDB()) {
						if(dto.getEmail().equals(email)) {
							dto.setNickName(inputNickName.getText());
							dto.setMobile(inputMobile.getText());
							dto.setPw(inputPW1.getPassword());
							dto.setTempSW(0);
							JOptionPane.showMessageDialog(updateFrame, "회원정보가 수정되었습니다");
							updateFrame.setVisible(false);
							practiceLogin.getUpdate().setEnabled(true);
						}
					}
				}
			}
		});
		
		
		
		updateFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				practiceLogin.getUpdate().setEnabled(true);
			}
		});
		
		
		done.setBounds(42, 209, 100, 30);
		updateFrame.getContentPane().add(done);
		
		cancel = new JButton("취소");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFrame.setVisible(false);
				practiceLogin.getUpdate().setEnabled(true);
			}
		});
		cancel.setBounds(154, 209, 100, 30);
		updateFrame.getContentPane().add(cancel);
		
		inputNickName = new JTextField();
		inputNickName.setBounds(125, 118, 138, 30);
		updateFrame.getContentPane().add(inputNickName);
		inputNickName.setColumns(10);
		
		inputPW1 = new JPasswordField();
		inputPW1.setBounds(125, 18, 138, 30);
		updateFrame.getContentPane().add(inputPW1);
		
		inputPW2 = new JPasswordField();
		inputPW2.setBounds(125, 58, 138, 30);
		updateFrame.getContentPane().add(inputPW2);
		
		inputMobile = new JTextField();
		inputMobile.setBounds(125, 158, 138, 30);
		updateFrame.getContentPane().add(inputMobile);
		inputMobile.setColumns(10);
		
		lblNewLabel = new JLabel("새로운 비밀번호");
		lblNewLabel.setBounds(25, 18, 107, 30);
		updateFrame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("재입력");
		lblNewLabel_1.setBounds(76, 58, 49, 30);
		updateFrame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("새 닉네임");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(53, 117, 61, 30);
		updateFrame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("새 핸드폰 번호");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(16, 157, 97, 30);
		updateFrame.getContentPane().add(lblNewLabel_3);
		
		
		
		
		
		
		
		for(PracticeDTO dto: practiceLogin.getDB()) {
			if(dto.getEmail().equals(email)) {
				inputNickName.setText(dto.getNickName());
				inputMobile.setText(dto.getMobile());
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
