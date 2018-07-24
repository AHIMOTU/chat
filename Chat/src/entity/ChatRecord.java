package entity;

import java.io.Serializable;
import java.util.List;

public class ChatRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user1;
	private String user2;
	private List<Message> record;
	public String getUser1() {
		return user1;
	}
	public void setUser1(String user1) {
		this.user1 = user1;
	}
	public String getUser2() {
		return user2;
	}
	public void setUser2(String user2) {
		this.user2 = user2;
	}
	public List<Message> getRecord() {
		return record;
	}
	public void setRecord(List<Message> record) {
		this.record = record;
	}
	@Override
	public String toString() {
		return "ChatRecord [user1=" + user1 + ", user2=" + user2 + ", record=" + record + "]";
	}
	
	
}
