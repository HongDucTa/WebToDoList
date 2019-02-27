package com.hongduc.web.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import javax.servlet.http.Cookie;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	@Resource(name="jdbc/todolist")
	private DataSource dataSource;
	AccountDBUtil accountDbUtil;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On récupère le cookie contenant l'username s'il existe
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{
				if (cookie.getName().equals("username"))
				{
					request.setAttribute("username", cookie.getValue());
				}
			}
		}
		request.getRequestDispatcher("/login.jsp").forward(request, response);;

	}
	
	@Override
	public void init() throws ServletException
	{
		super.init();
		accountDbUtil = new AccountDBUtil(dataSource);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{/* fonction appelé lorsque l'utilisateur envoie sa requête de connexion */
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Account account = new Account(username,password);
		Account DBAccount = checkLogin(account);
		if (DBAccount != null)
		{/* si le mot de passe est correct */
			HttpSession session = request.getSession();
			session.setAttribute("account", DBAccount);
			response.addCookie(new Cookie("username",account.getUsername()));
			response.sendRedirect("/JAVAEE-TODOLIST/MainPage"); // permet d'envoyer l'utilisateur sur une autre page
		}
		else
		{/* s'il y a une erreur */
			request.setAttribute("erreur", "Error ! Wrong username or password");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	private boolean CheckPassword(String input,String password)
	{/* compare le mot de passe entré par l'utilisateur et le mot de passe réel pour un compte donné
	*/
		boolean result = true;
		if (input.length() == password.length())
		{
			int i = 0;
			while (i < input.length() && result == true)
			{
				if (input.charAt(i) != password.charAt(i))
				{
					result = false;
				}
				i = i + 1;
			}
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	private Account checkLogin(Account input)
	{/* Renvoie l'account si l'username et le password concordent.
	Renvoie null sinon.
	*/
		Account DBAccount = accountDbUtil.fetchAccountByUsername(input.getUsername());
		Account result = null;
		if (DBAccount != null)
		{
			if (CheckPassword(input.getPassword(),DBAccount.getPassword())==true)
			{
				result = DBAccount;
			}
		}
		return result;
	}

}
