package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.oracle.jrockit.jfr.RequestableEvent;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import data.Data;
import entity.User;
import service.UserService;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String step=request.getParameter("step");
		if("loginOut".equals(step)){
			request.getSession(true).removeAttribute("loginUser");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doGet(request, response);
		
		String step=request.getParameter("step");
		if("regist".equals(step)){
			registPost(request,response);
		}else if("login".equals(step)){
			loginPost(request,response);
		}else if("edit".equals(step)){
			editPost(request,response);
		}
		
		
		
		
		
	}

	private void editPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String editPsw=request.getParameter("editPsw");
		String oldPsw=request.getParameter("oldPsw");
		
		HttpSession session=request.getSession(true);
		
		int c=UserService.edit(session,editPsw,oldPsw);
		if(c==1){
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if(c==2){	//旧密码错误
			request.setAttribute("error", "旧密码错误");
			request.getRequestDispatcher("edit.jsp").forward(request, response);
		}else if(c==0){	//新密码与旧密码需不一致
			request.setAttribute("error", "新密码与旧密码需不一致");
			request.getRequestDispatcher("edit.jsp").forward(request, response);
		}
		
	}

	private void loginPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		
		System.out.println(userName);
		System.out.println(password);
		
		HttpSession session=request.getSession(true);
		
		int b=UserService.login(userName, password, session);
		System.out.println(b);
		if(b==1){
			
			System.out.println("登录成功===="+Data.allUsers);
			
			System.out.println("登录成功===="+Data.allFriends);
			System.out.println("登录成功===="+Data.allRecord);
			
			request.getRequestDispatcher("main.jsp").forward(request, response);
			response.sendRedirect("main.jsp");
		}else if(b==2){	/*密码错误*/
			request.setAttribute("pswError", "密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if(b==3){	/*用户不存在*/
			request.setAttribute("userError", "用户名不存在");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

	private void registPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String psw=request.getParameter("psw");
		String mail=request.getParameter("mail");
		
		User users=new User(username,psw,mail);
		int a=UserService.registe(users);
		if(a==1){
			request.setAttribute("errorMsg", "用户名已存在！");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
		}else {
			request.setAttribute("sucMsg", "注册成功！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

}
