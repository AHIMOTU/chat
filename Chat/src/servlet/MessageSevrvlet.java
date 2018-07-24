package servlet;

import java.io.IOException;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Message;
import entity.User;
import service.MessageService;

/**
 * Servlet implementation class MessageSevrvlet
 */
public class MessageSevrvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageSevrvlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String receiver=request.getParameter("receiver");
		User onLine=(User) request.getSession(true).getAttribute("loginUser");
		
		String sender = null;
		if(onLine!=null){
			sender=onLine.getUsername();
		}
		
		
		
		List<Message> hisMsg=MessageService.getHisMsg(sender, receiver);
		System.out.println("$$$"+hisMsg);
		
		Gson gson=new Gson();
		String j_hisMsg=gson.toJson(hisMsg);
		
		response.getWriter().write(j_hisMsg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String receiver=request.getParameter("receiver");
		String content=request.getParameter("content");
		System.out.println(receiver+"****"+content);
		User onLine=(User) request.getSession(true).getAttribute("loginUser");
		String sender=onLine.getUsername();
		System.out.println("2222222"+sender);
		
		MessageService.sendMessage(sender, receiver, content);
	}

}
