package com.gerard.GerardSite.controller;

import java.io.*;

import com.gerard.GerardSite.controller.action.Action;
import com.gerard.GerardSite.controller.action.ActionFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet
public class Controller extends HttpServlet {

    private static final String ACTION_IDENTIFIER_REQUEST_PARAMETER_NAME = "command";
    private static final String VIEW_DIRECTORY_NAME = "/pages";
    private static final String VIEW_EXTENSION = ".jsp";
    private static String view;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.sendRedirect("http://localhost:8080/GerardSite/dogs");
        response.sendRedirect(view); //uses relative url
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/pages" + view + "jsp").forward(request,response);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter(ACTION_IDENTIFIER_REQUEST_PARAMETER_NAME);
        Action action = ActionFactory.INSTANCE.getAction(command);
        view = action.execute(request, response);
        super.service(request, response);
    }

}