package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Use;

import data.Data;
import entity.ChatRecord;
import entity.Message;
import entity.User;

public class FriendService {
	public static void add(String friendName,User self) {
		if(Data.allFriends.get(self.getUsername())==null){
			Data.allFriends.put(self.getUsername(), new HashSet<User>());
		}
		if(Data.allFriends.get(friendName)==null){
			Data.allFriends.put(friendName, new HashSet<User>());
		}
		Data.allFriends.get(self.getUsername()).add(Data.allUsers.get(friendName));
		Data.allFriends.get(friendName).add(self);
		Data.writeFriend();
		
		boolean is=false;
		for(ChatRecord cr:Data.allRecord){
			if((cr.getUser1().equals(friendName)&&cr.getUser2().equals(self.getUsername()))||(cr.getUser2().equals(friendName)&&cr.getUser1().equals(self.getUsername()))){
				is=true;
				break;
			}
		}
		if(!is){
			ChatRecord chatRecord=new ChatRecord();
			chatRecord.setUser1(self.getUsername());
			chatRecord.setUser2(friendName);
			chatRecord.setRecord(new ArrayList<Message>());
			Data.allRecord.add(chatRecord);
		}
	}
	
	
	
	
	public static Set<User> search(String searchFriend) {
		Set<User> set=new HashSet<User>();
		for(User user:Data.allUsers.values()){
			if(user.getUsername().contains(searchFriend)){
				set.add(user);
				System.out.println(set);
			}
		}
		return set;
	}
}
