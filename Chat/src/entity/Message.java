package entity;

import java.io.Serializable;
import java.util.Date;

import sun.print.resources.serviceui;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sender;
	private String receiver;
	private String content;
	private Date time;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Message(String sender, String receiver, String content, Date time) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.time = time;
	}
	public Message() {
		super();
	}
	@Override
	public String toString() {
		return "Message [sender=" + sender + ", receiver=" + receiver + ", content=" + content + ", time=" + time + "]";
	}
	
	

}
