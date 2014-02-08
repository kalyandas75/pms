package pms.servlet;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pms.exceptions.PMSException;
import pms.logic.LoginService;
import pms.model.Login;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ResourceBundle bundle = ResourceBundle.getBundle("Messages");

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("msg", bundle.getString("getreq.notsupported"));
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LoginService loginService = new LoginService();
		String returnPage = "";
		try {

			Login user = loginService.validateUser(request.getParameter("username"), 
					request.getParameter("password"));
			if(user.getIsDoctor()){
				returnPage= "doctor.jsp";
			}
			else if(user.getIsAdministrator()){
				returnPage = "admin.jsp";
			}
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher(returnPage).forward(request, response);
		} catch (PMSException e) {
			// TODO Auto-generated catch block
/*			request.setCharacterEncoding("utf8");
	        //response.setCharacterEncoding("utf8");
	        response.setContentType("application/json");
	        JsonUtil.writeMessage("error", e.getMessage(), response.getOutputStream());*/
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", bundle.getString("system.error"));
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
