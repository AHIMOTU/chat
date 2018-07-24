package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Use;

import entity.ChatRecord;
import entity.User;

public class Data {
	public static Map<String, User> allUsers=new HashMap<String,User>();
	public static Map<String, Set<User>> allFriends=new HashMap<String,Set<User>>();
	public static List<ChatRecord> allRecord=new ArrayList<ChatRecord>();
	
	static{
		readUser();
		if(allUsers==null){
			allUsers=new HashMap<String,User>();
		}
		
		readFriend();
		if(allFriends==null){
			allFriends=new HashMap<String,Set<User>>();
		}
		
		readRecord();
		if(allRecord==null){
			allRecord=new ArrayList<ChatRecord>();
		}
	}
	
	
	
	public static void writeRecord() {
		try {
			ObjectOutputStream Oos=new ObjectOutputStream(new FileOutputStream("d:\\test2.txt"));
			Oos.writeObject(allRecord);
			Oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void readRecord() {
		try {
			ObjectInputStream Ois=new ObjectInputStream(new FileInputStream("d:\\test2.txt"));
			try {
				allRecord =(List<ChatRecord>) Ois.readObject();
				Ois.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeFriend() {
		try {
			ObjectOutputStream Oos=new ObjectOutputStream(new FileOutputStream("d:\\test1.txt"));
			Oos.writeObject(allFriends);
			Oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void readFriend() {
		try {
			ObjectInputStream Ois=new ObjectInputStream(new FileInputStream("d:\\test1.txt"));
			try {
				allFriends =(Map<String, Set<User>>) Ois.readObject();
				Ois.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void readUser() {
		
		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("D:\\test.txt"));
			try {
				allUsers=(Map<String, User>)ois.readObject();
				ois.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void writeUser() {
		try {
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("D:\\test.txt"));
			oos.writeObject(allUsers);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
