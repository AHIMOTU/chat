package entity;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String psw;
	private String mail;
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPsw() {
		return psw;
	}


	public void setPsw(String psw) {
		this.psw = psw;
	}


	

	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public User(){
		
	}
	
	public User(String username, String psw,  String mail) {
		super();
		this.username = username;
		this.psw = psw;
		this.mail = mail;
	}


	public static void main(String[] args) {
		System.out.println();
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", psw=" + psw + ",  mail=" + mail + "]";
	}
}
