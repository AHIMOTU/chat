package service;

import java.awt.SecondaryLoop;

import javax.servlet.http.HttpSession;

import data.Data;
import entity.User;

public class UserService {
	public static int edit(HttpSession session,String editPsw,String oldPsw) {
		User loginUser=(User) session.getAttribute("loginUser");
		System.out.println(loginUser.getPsw()+"+"+editPsw);
		/*if(loginUser.getPsw()!=oldPsw){
			return 2;
		}else{*/
			if(loginUser.getPsw()!=editPsw){
				loginUser.setPsw(editPsw);
				Data.allUsers.put(loginUser.getUsername(), loginUser);
				Data.writeUser();
				return 1;
			}else{
				return 0;
			}
		/*}*/
		
		
	}
	
	public static int registe(User users) {
		if(Data.allUsers.containsKey(users.getUsername())){
			return 1;
		}else {
			Data.allUsers.put(users.getUsername(), users);
			Data.writeUser();
			return 2;
		}
	}
	
	public static int login(String userName,String password,HttpSession session) {
		System.out.println(Data.allUsers);
		if(Data.allUsers.containsKey(userName)){
			User userMsg=Data.allUsers.get(userName);
			System.out.println(userMsg);
			System.out.println(userMsg.getPsw());
			System.out.println(password);
			if(userMsg.getPsw().equals(password)){
				session.setAttribute("loginUser", userMsg);
				return 1;
			}else {
				return 2;
			}
			
		}else {
			return 3;
		}
	}
}
