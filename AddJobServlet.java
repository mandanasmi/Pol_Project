/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbu.controller;

import com.sbu.dao.model.Employer;
import com.sbu.dao.model.Feed;
import com.sbu.service.EmployerManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 *
 * @author Niloofar
 */
@WebServlet(name = "AddJobServlet", urlPatterns = {"/AddJobServlet"}, loadOnStartup = 1)
public class AddJobServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EmployerManager employerService;
    public AddJobServlet() {
        super();
    }
      @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        employerService = context.getBean(EmployerManager.class);
    }
    //@Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession(); 
        
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("email");
        
        Employer user = employerService.getEmployer(id);
        String name = user.getCompanyName();
        Feed feed = new Feed();
        feed.setEmployeridEmployer(user);
        
        session.setAttribute("email",username);
        session.setAttribute("name",name);
        session.setAttribute("id",request.getParameter("id"));
        getServletContext().getRequestDispatcher("/AddJob.jsp").forward(request, response);
    }
}