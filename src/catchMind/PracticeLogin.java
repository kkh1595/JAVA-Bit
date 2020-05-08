package catchMind;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PracticeLogin {
	private JLabel title, id, pw;
	private JTextField inputID;
	private JPasswordField inputPW;
	private JButton login, signUp, lostId, lostPW, update; 
	private JFrame frame;
	private List<PracticeDTO> db;

	public PracticeLogin() {
		frame = new JFrame("Catch Mind");
		db = new ArrayList<PracticeDTO>();
		
		//---------------------titlePanel----------------------------
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(350, 200, 300, 50);
		titlePanel.setLayout(new GridLayout(1,1));
		title = new JLabel("Catch Mind");
		title.setFont(new Font("굴림", Font.BOLD, 50));
		
		titlePanel.add(title);
		
		
		//-------------------------frame----------------------------
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(titlePanel);
		lostId = new JButton("이메일 찾기");
		lostId.setBounds(313, 556, 180, 50);
		frame.getContentPane().add(lostId);
		login = new JButton("로그인");
		login.setBounds(638, 346, 103, 90);
		frame.getContentPane().add(login);
		signUp = new JButton("회원가입");
		signUp.setBounds(313, 496, 180, 50);
		frame.getContentPane().add(signUp);
		lostPW = new JButton("비밀번호 찾기");
		lostPW.setBounds(505, 556, 180, 50);
		frame.getContentPane().add(lostPW);
		id = new JLabel("이메일 주소");
		id.setBounds(287, 346, 135, 40);
		frame.getContentPane().add(id);
		id.setFont(new Font("굴림", Font.BOLD, 15));
		pw = new JLabel("\t\t\t비밀번호");
		pw.setBounds(287, 396, 120, 40);
		frame.getContentPane().add(pw);
		pw.setFont(new Font("굴림", Font.BOLD, 15));
		inputPW = new JPasswordField();
		inputPW.setBounds(406, 396, 200, 40);
		frame.getContentPane().add(inputPW);
		inputPW.setFont(new Font("굴림", Font.BOLD, 15));
		inputID = new JTextField();
		inputID.setBounds(406, 346, 200, 40);
		frame.getContentPane().add(inputID);
		inputID.setFont(new Font("굴림", Font.BOLD, 15));
		
		//------------------------button Actions---------------------
		update = new JButton("회원정보수정");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.setEnabled(false);
				new PracticeUpdate(PracticeLogin.this);
			}
		});
		update.setBounds(505, 496, 180, 50);
		frame.getContentPane().add(update);
		
		inputPW.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inputID.getText() == null || inputID.getText().length() == 0) {
					JOptionPane.showMessageDialog(frame, "ID를 입력하세요");
					return;
				}else if(inputPW.getPassword() == null || inputPW.getPassword().length == 0) {
					JOptionPane.showMessageDialog(frame, "PW를 입력하세요");
					return;
				}
				
				String userInputID = inputID.getText();
				char[] userInputPW = inputPW.getPassword();
				
				for(PracticeDTO dto : db) {
					if(dto.getEmail().equals(userInputID) && Arrays.equals(dto.getPw(), userInputPW)) {
						if(dto.getTempSW() == 1) {
							new PracticeChangePW(dto.getEmail(), PracticeLogin.this);
							inputID.setText("");
							inputPW.setText("");
						}else {
							JOptionPane.showMessageDialog(frame, "로그인 성공");
							new RoomForm(PracticeLogin.this);
							dto.setOnline(1);
							//대기실로 이동
						}
						return;
					}
					if(dto.getEmail().equals(userInputID) && !Arrays.equals(dto.getPw(), userInputPW)) {
						JOptionPane.showMessageDialog(frame, "비밀번호가 다릅니다");
						return;
					}
				}
				JOptionPane.showMessageDialog(frame, "존재하지 않는 ID입니다");
			}
		});
		
		//lostPw button
		lostPW.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lostPW.setEnabled(false);
				new PracticeLostPW(PracticeLogin.this);
			}
		});
		
		//signUp button
		signUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				signUp.setEnabled(false);
				new PracticeSignUp(PracticeLogin.this);
			}
		});
		
		
		//login button
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inputID.getText() == null || inputID.getText().length() == 0) {
					JOptionPane.showMessageDialog(frame, "ID를 입력하세요");
					return;
				}else if(inputPW.getPassword() == null || inputPW.getPassword().length == 0) {
					JOptionPane.showMessageDialog(frame, "PW를 입력하세요");
					return;
				}
				
				String userInputID = inputID.getText();
				char[] userInputPW = inputPW.getPassword();
				
				for(PracticeDTO dto : db) {
					if(dto.getEmail().equals(userInputID) && Arrays.equals(dto.getPw(), userInputPW)) {
						if(dto.getTempSW() == 1) {
							new PracticeChangePW(dto.getEmail(), PracticeLogin.this);
							inputID.setText("");
							inputPW.setText("");
						}else {
							JOptionPane.showMessageDialog(frame, "로그인 성공");
							new RoomForm(PracticeLogin.this);
							dto.setOnline(1);
							
							//대기실로 이동
						}
						return;
					}
					if(dto.getEmail().equals(userInputID) && !Arrays.equals(dto.getPw(), userInputPW)) {
						JOptionPane.showMessageDialog(frame, "비밀번호가 다릅니다");
						return;
					}
				}
				JOptionPane.showMessageDialog(frame, "존재하지 않는 ID입니다");
			}
		});
		
		//lostId button
		lostId.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lostId.setEnabled(false);
				new PracticeLostID(PracticeLogin.this);
				
			}
		});
		frame.setVisible(true);
		frame.setSize(1024,768);
		frame.setLocation(800,50);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		
		//-----------------------test id----------------------------
		PracticeDTO testDTO = new PracticeDTO();
		testDTO.setEmail("jpcnani@naver.com");
		testDTO.setNickName("Richard");
		char[] testpw = {'1','2','3','4'};
		testDTO.setPw(testpw);
		testDTO.setMobile("01012345678");
		testDTO.setTempSW(1);
		db.add(testDTO);
		
		for(int i = 0; i < 9; i++) {
			PracticeDTO dto = new PracticeDTO();
			dto.setEmail("test"+i+"@test.com");
			char[] pw = {'t','e','s','t'};
			dto.setPw(pw);
			dto.setNickName("test"+i);
			dto.setTempSW(0);
			dto.setMobile(i+"");
			if(i%3 == 0) {
				dto.setOnline(1);
			}
			db.add(dto);
		}
	}
	//practiceLogin Constructor
	
	//--------------------------getter & setter------------------------------
	public JButton getLostPW() {	return lostPW;	}

	public void setLostPW(JButton lostPW) {	this.lostPW = lostPW;	}

	public JButton getUpdate() {	return update;	}

	public void setUpdate(JButton update) {	this.update = update;	}

	public JLabel getTitle() {	return title;	}

	public void setTitle(JLabel title) {	this.title = title;	}

	public JLabel getId() {	return id;	}

	public void setId(JLabel id) {	this.id = id;	}

	public JLabel getPw() {	return pw;	}

	public void setPw(JLabel pw) {	this.pw = pw;	}

	public JTextField getInputID() {	return inputID;	}

	public void setInputID(JTextField inputID) {	this.inputID = inputID;	}

	public JPasswordField getInputPW() {	return inputPW;	}

	public void setInputPW(JPasswordField inputPW) {	this.inputPW = inputPW;	}

	public JButton getLogin() {	return login;	}

	public void setLogin(JButton login) {	this.login = login;	}

	public JButton getSignUp() {	return signUp;	}

	public void setSignUp(JButton signUp) {	this.signUp = signUp;	}

	public JButton getLostId() {	return lostId;	}

	public void setLostId(JButton lostId) {	this.lostId = lostId;	}

	public JButton getLostPw() {	return lostPW;	}

	public void setLostPw(JButton lostPw) {	this.lostPW = lostPw;	}


	public void setFrame(JFrame frame) {this.frame = frame;	}

	public List<PracticeDTO> getDB() {	return this.db;}
	
	public JFrame getFrame() {	return this.frame;	}
	//--------------------------End of getter & setter------------------------------
	
	
	public static void main(String[] args) {
		new PracticeLogin();
	}
}
//practiceLogin class