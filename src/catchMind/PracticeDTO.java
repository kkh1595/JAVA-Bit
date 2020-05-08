package catchMind;

class PracticeDTO {
	
	private String email;
	private char[] pw;
	private String nickName;
	private String mobile;
	private int tempSW;
	private int online;
	//practiceDTO Field
	
	public PracticeDTO() {}
	

	public PracticeDTO(String email, char[] pw, String nickName, String mobile) {
		this.email = email;
		this.pw = pw;
		this.nickName = nickName;
		this.mobile = mobile;
	}
	//practiceDTO Constructor
	public int getOnline() {
		return online;
	}
	
	public void setOnline(int online) {
		this.online = online;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char[] getPw() {
		return pw;
	}

	public void setPw(char[] pw) {
		this.pw = pw;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getTempSW() {
		return tempSW;
	}

	public void setTempSW(int tempSW) {
		this.tempSW = tempSW;
	}
	
}
//practiceDTO class
