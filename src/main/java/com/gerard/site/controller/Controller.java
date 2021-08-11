package com.gerard.site.controller;

import java.io.*;

import com.gerard.site.controller.action.Action;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet
public class Controller extends HttpServlet {

    private static final String ACTION_IDENTIFIER_REQUEST_PARAMETER_NAME = "command";
    private static final String VIEW_DIRECTORY_NAME = "/pages";
    private static final String VIEW_EXTENSION = ".jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(prepareView(request, response)); //uses relative url
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String path = VIEW_DIRECTORY_NAME + prepareView(request, response) + VIEW_EXTENSION;
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(request,response);
    }

    private String prepareView(HttpServletRequest request, HttpServletResponse response){
        String command = request.getParameter(ACTION_IDENTIFIER_REQUEST_PARAMETER_NAME);
        Action action = ActionFactory.INSTANCE.getAction(command);
        String view = action.execute(request, response);
        return view;
    }

}