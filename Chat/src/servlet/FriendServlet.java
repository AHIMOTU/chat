package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.FREE_MEM;

import com.google.gson.Gson;
import com.sun.xml.internal.fastinfoset.util.StringArray;

import data.Data;
import entity.User;
import service.FriendService;
import sun.management.counter.Variability;

/**
 * Servlet implementation class FriendServlet
 */
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String friendName=request.getParameter("friendname");
		System.out.println(friendName);
		System.out.println("******");
		User self=(User) request.getSession(true).getAttribute("loginUser");
		
		FriendService.add(friendName,self);
		System.out.println(Data.allFriends.get(self.getUsername()));
		Gson gson=new Gson();
		String allFriendsGson=gson.toJson(Data.allFriends.get(self.getUsername()));
		
		PrintWriter out = response.getWriter();
        out.write(allFriendsGson);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String step=request.getParameter("step");
		User inLine = (User) request.getSession(true).getAttribute("loginUser");
		if(step.equals("search")){
			System.out.println("seach111111");
			
			String searchFriend=request.getParameter("searchFriend");
			System.out.println("搜索...");
			Set<User> friends=FriendService.search(searchFriend);
			
			
			System.out.println(Data.allFriends);
			
			Iterator<User> it=friends.iterator();
			while(it.hasNext()){
				User u=it.next();
				if(u.getUsername().equals(inLine.getUsername())){
					it.remove();
					break;
				}
			}
			System.out.println(friends);
			
			Gson gson=new Gson();
			String gFriends=gson.toJson(friends);
			
			PrintWriter out = response.getWriter();
	        out.write(gFriends);
		}else if(step.equals("friend")){
			System.out.println("friend22222222");
			System.out.println(Data.allFriends.get(inLine.getUsername()));
			
			Gson gson=new Gson();
			String jsonAll=gson.toJson(Data.allFriends.get(inLine.getUsername()));
			response.getWriter().write(jsonAll);
		}
		
		
		
		/*request.setAttribute("result", friends);
		request.getRequestDispatcher("main.jsp").forward(request, response);*/
	}

}
