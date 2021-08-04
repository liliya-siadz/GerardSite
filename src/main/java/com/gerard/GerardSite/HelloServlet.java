package com.gerard.GerardSite;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       request.setAttribute("number", 100);
       request.setAttribute("language", request.getHeader("Accept-Language"));
       request.setAttribute("encoding", request.getHeader("Accept-Charset"));
       request.getRequestDispatcher("pages/dogs.jsp").forward(request,response);



    }

    public void destroy() {
    }
}