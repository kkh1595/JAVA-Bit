package catchMind;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PracticeSignUp implements Runnable {
	
	private JLabel emailV, codeV, PWV, nickNameV, mobileV;
	private JTextField inputEmail, inputCode, inputNickName, inputMobile;
	private JPasswordField inputPW1, inputPW2;
	private JButton sendCode, codeCheck, back, signDone;
	private PracticeLogin practiceLogin;
	private String code;
	private JLabel[] jls;
	private JPanel[] jps;
	private Thread t;
	
	public PracticeLogin getPracticeLogin() {
		return this.practiceLogin;
	}
	public JTextField getInputEmail() {
		return this.inputEmail;
	}
	public JTextField getInputCode() {
		return this.inputCode;
	}
	public JTextField getInputNickName() {
		return this.inputNickName;
	}
	public JTextField getInputMobile() {
		return this.inputMobile;
	}
	public JPasswordField getInputPW1() {
		return this.inputPW1;
	}
	public JPasswordField getInputPW2() {
		return this.inputPW2;
	}
	
	public PracticeSignUp(PracticeLogin practiceLogin) {
		this.practiceLogin = practiceLogin;
		
		JFrame signUpFrame = new JFrame("회원가입");
		
		JPanel wholePanel = new JPanel();
		wholePanel.setLayout(new GridLayout(13,1));
		
		String[] labelNames = {"이메일 주소", "인증 코드", "비밀번호 입력", "비밀번호 재입력", "닉네임", "핸드폰 번호"};
		jls = new JLabel[6];
		for (int i = 0; i < jls.length; i++) {
			jls[i] = new JLabel(labelNames[i]);
		}
		
		jps = new JPanel[13];
		for (int i = 0; i < jps.length; i++) {
			jps[i] = new JPanel();
			jps[i].setLayout(new GridLayout(1,3));
		}
		
		
		JLabel showPW1 = new JLabel();
		
		
		inputEmail = new JTextField();
		inputEmail.setForeground(Color.black);
		inputCode = new JTextField();
		inputPW1 = new JPasswordField();
		inputPW2 = new JPasswordField();
		inputNickName = new JTextField();
		inputMobile = new JTextField();
		sendCode = new JButton("인증코드 발송");
		codeCheck = new JButton("인증코드 확인");
		back = new JButton("뒤로가기");
		signDone = new JButton("가입 완료");
		
		codeCheck.setEnabled(false);
		
		sendCode.setEnabled(false);
		inputCode.setEnabled(false);
		inputPW1.setEnabled(false);
		inputPW2.setEnabled(false);
		inputNickName.setEnabled(false);
		inputMobile.setEnabled(false);
		signDone.setEnabled(false);
		
		
		
		emailV = new JLabel();
		codeV = new JLabel();
		PWV = new JLabel();
		nickNameV = new JLabel();
		mobileV = new JLabel();
		
		jps[0].add(jls[0]);
		jps[0].add(inputEmail);
		jps[0].add(sendCode);
		jps[1].add(emailV);
		jps[1].setLayout(new FlowLayout(FlowLayout.CENTER));
		jps[2].add(jls[1]);
		jps[2].add(inputCode);
		jps[2].add(codeCheck);
		jps[3].add(codeV);
		jps[3].setLayout(new FlowLayout(FlowLayout.CENTER));
		jps[4].add(jls[2]);
		jps[4].add(inputPW1);
		jps[6].add(jls[3]);
		jps[6].add(inputPW2);
		jps[7].add(PWV);
		jps[7].setLayout(new FlowLayout(FlowLayout.CENTER));
		jps[8].add(jls[4]);
		jps[8].add(inputNickName);
		jps[9].add(nickNameV);
		jps[9].setLayout(new FlowLayout(FlowLayout.CENTER));
		jps[10].add(jls[5]);
		jps[10].add(inputMobile);
		jps[11].add(mobileV);
		jps[11].setLayout(new FlowLayout(FlowLayout.CENTER));
		jps[12].add(back);
		jps[12].add(signDone);
		
		
		for (int i = 0; i < jps.length; i++) {
			wholePanel.add(jps[i]);
		}
		wholePanel.setSize(350, 530);
		wholePanel.setLocation(25,25);
		signUpFrame.setLayout(null);
		signUpFrame.getContentPane().add(wholePanel);
		signUpFrame.setVisible(true);
		signUpFrame.setSize(400,600);
		signUpFrame.setLocationRelativeTo(practiceLogin.getFrame());
		signUpFrame.setResizable(false);
		signUpFrame.setDefaultCloseOperation(signUpFrame.DO_NOTHING_ON_CLOSE);
		
		//-------------------------Thread------------------------
		t = new Thread(this);
		t.start();
		
		
		//---------------------button actions---------------
		//windowClosing
		signUpFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				signUpFrame.setVisible(false);
				practiceLogin.getSignUp().setEnabled(true);
			}
		});
		
		
		//sendCode
		sendCode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				code = new PracticeEmail(PracticeSignUp.this).getCode();
				JOptionPane.showMessageDialog(signUpFrame, "인증코드가 발송되었습니다", "이메일 인증", JOptionPane.INFORMATION_MESSAGE);
				inputEmail.setEditable(false);
				inputCode.setEnabled(true);
				codeCheck.setEnabled(true);
				
			}
		});
		
		//codeCheck
		codeCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(code.equals(inputCode.getText())) {
					JOptionPane.showMessageDialog(signUpFrame, "정상적으로 인증되었습니다", "이메일 인증", JOptionPane.INFORMATION_MESSAGE);
					inputPW1.setEnabled(true);
					inputPW2.setEnabled(true);
					inputNickName.setEnabled(true);
					inputMobile.setEnabled(true);
					inputCode.setEnabled(false);
					codeCheck.setEnabled(false);
					sendCode.setEnabled(false);
				}else {
					JOptionPane.showMessageDialog(signUpFrame, "인증코드가 유효하지 않습니다", "이메일 인증", JOptionPane.INFORMATION_MESSAGE);
					inputNickName.setEnabled(false);
				}
			}
		});
		
		//뒤로가기
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				t = null;
				signUpFrame.setVisible(false);
				practiceLogin.getSignUp().setEnabled(true);
			}
		});

		//가입 완료
		signDone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				t.interrupt();
				String userInputID = inputEmail.getText();
				char[] userInputPW1 = inputPW1.getPassword();
				String userInputNickName = inputNickName.getText();
				String userInputMobile = inputMobile.getText();
				
				PracticeDTO dto = new PracticeDTO(userInputID, userInputPW1, userInputNickName, userInputMobile);
				practiceLogin.getDB().add(dto);
				
				JOptionPane.showMessageDialog(signUpFrame, "회원가입이 완료되었습니다", "회원 가입", JOptionPane.INFORMATION_MESSAGE);
				practiceLogin.getSignUp().setEnabled(true);
				signUpFrame.setVisible(false);
			}
		});
	}
	//practiceSignUp Constructor
	
	public boolean isValidEmail(String email) {
		boolean result = false; 
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; 
		Pattern p = Pattern.compile(regex); Matcher m = p.matcher(email); 
		if(m.matches()) { 
			result = true; 
		} 
		return result; 
	}
	
	public void emailVerifier() {
		if(inputEmail.getText() == null || inputEmail.getText().length() == 0) {
			emailV.setText("");
			return;
		}
		if (isValidEmail(inputEmail.getText())) {
			for(PracticeDTO dto : practiceLogin.getDB()) {
				if(dto.getEmail().equals(inputEmail.getText())) {
					emailV.setForeground(Color.red);
					emailV.setText("사용중인 이메일");
					sendCode.setEnabled(false);
					return;
				}
			}
			emailV.setForeground(Color.blue);
			emailV.setText("유효한 이메일 형식");
			sendCode.setEnabled(true);
		}else {
			emailV.setForeground(Color.red);
			emailV.setText("유효하지 않은 이메일 형식");
			sendCode.setEnabled(false);
		}
	}
	
	public void passwordVerifier() {
		if(!inputPW1.isEnabled()) {
			return;
		}
		
		if(inputPW1.getPassword() == null || inputPW1.getPassword().length == 0
			|| inputPW2.getPassword() == null || inputPW2.getPassword().length == 0) {
			PWV.setForeground(Color.black);
			PWV.setText("");
		}else {
			if(Arrays.equals(inputPW1.getPassword(), inputPW2.getPassword())) {
				PWV.setForeground(Color.blue);
				PWV.setText("비밀번호 일치");
			}else {
				PWV.setForeground(Color.red);
				PWV.setText("비밀번호가 다름");
			}
		}
	}
	
	public void nickNameVerifier() {
		if(!inputNickName.isEnabled()) {
			return;
		}
		if(inputNickName.getText() == null || inputNickName.getText().length() == 0) {
			nickNameV.setText("");
			return;
		}
		for(PracticeDTO dto : practiceLogin.getDB()) {
			if(dto.getNickName().equals(inputNickName.getText())) {
				nickNameV.setForeground(Color.red);
				nickNameV.setText("사용중인 닉네임");
			}else {
				nickNameV.setForeground(Color.blue);
				nickNameV.setText("사용 가능한 닉네임");
			}
		}
	}
	
	
	public void mobileVerifier() {
		if(!inputMobile.isEnabled()) {
			return;
		}
		if(inputMobile.getText() == null || inputMobile.getText().length() == 0) {
			mobileV.setText("");
			return;
		}
		for(PracticeDTO dto : practiceLogin.getDB()) {
			if(dto.getMobile().equals(inputMobile.getText())) {
				mobileV.setForeground(Color.red);
				mobileV.setText("사용중인 핸드폰 번호");
			}else {
				mobileV.setForeground(Color.blue);
				mobileV.setText("사용 가능한 핸드폰 번호");
			}
		}
	}
	
	public void signDoneVerifier() {
		if ((PWV.getText().equals("비밀번호 일치")) && (nickNameV.getText().equals("사용 가능한 닉네임")) && (mobileV.getText().equals("사용 가능한 핸드폰 번호")) ) {
			signDone.setEnabled(true);
		}else {
			signDone.setEnabled(false);
		}
	}
	
	public void run() {
		while (true) {
			if(!inputPW1.isEnabled()) {
				emailVerifier();
			}
			passwordVerifier();
			nickNameVerifier();
			mobileVerifier();
			signDoneVerifier();
		}
	}
	//run method
	
	
	
	
	
	
}
//practiceSignUp class
