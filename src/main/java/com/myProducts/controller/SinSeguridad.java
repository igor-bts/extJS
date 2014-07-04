package com.myProducts.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SinSeguridad extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    SecureSoapTest sst =new SecureSoapTest();
    try {
		sst.sendRequestMyr();
	} catch (Exception e) {
		e.printStackTrace();
	}
    request.setAttribute("todo","" );
    request.getRequestDispatcher("/error.jsp").forward(request, response);
    
  }
}