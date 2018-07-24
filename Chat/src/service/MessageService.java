package service;

import java.util.Date;
import java.util.List;

import data.Data;
import entity.ChatRecord;
import entity.Message;

public class MessageService {
	public static void sendMessage(String sender,String receiver,String content) {
		Message message=new Message();
		message.setSender(sender);
		message.setReceiver(receiver);
		message.setContent(content);
		message.setTime(new Date());
		System.out.println("&&&&&&"+message);
		System.out.println(Data.allRecord);
		
		for(ChatRecord cr:Data.allRecord){
			if((cr.getUser1().equals(sender)&&cr.getUser2().equals(receiver))||(cr.getUser2().equals(sender)&&cr.getUser1().equals(receiver))){
				cr.getRecord().add(message);
			}
		}
		Data.writeRecord();
	}
	public  static List<Message> getHisMsg(String sender,String receiver) {
		for(ChatRecord cr:Data.allRecord){
			System.out.println("get"+cr.getUser1());
			if((cr.getUser1().equals(sender)&&cr.getUser2().equals(receiver))||(cr.getUser2().equals(sender)&&cr.getUser1().equals(receiver))){
				return cr.getRecord();
			}
		}
		return null;
	}
}








