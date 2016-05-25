/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbu.controller;

/**
 *
 * @author Mandana
 */
import com.sbu.dao.model.Member1;
import com.sbu.dao.model.Startup;
import com.sbu.service.EmployeeManager;
import com.sbu.service.MemberManager;
import com.sbu.service.StartupManager;
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


@WebServlet(name = "Feed_Personal_Startup_View_Controller", urlPatterns = {"/Feed_Personal_Startup_View_Controller"}, loadOnStartup = 1)
public class Feed_Personal_Startup_View_Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
   
    private StartupManager startupService;
    private MemberManager memberService;

    
    @Override
    public void init() throws ServletException 
    {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        startupService = context.getBean(StartupManager.class);
        memberService = context.getBean(MemberManager.class);
    }
    
    public Feed_Personal_Startup_View_Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
            
            
            request.setCharacterEncoding("UTF-8");
            
            Startup User = new Startup();
        
            List<Member1> members= new ArrayList();
            int counter=1;
            
            HttpSession session=request.getSession();
   
            User= (Startup) request.getSession().getAttribute("User");
            //members= (ArrayList<Member1>) request.getSession().getAttribute("members");
            members = memberService.getMemberByStartupId(User.getIdStartup());
            
        session.setAttribute("SearchQueries", User);
        session.setAttribute("members", members);
        session.setAttribute("counter", counter);
    
        //request.getRequestDispatcher("EmployeeTest.jsp").forward(request, response);
        request.getRequestDispatcher("/Personal_Startup.jsp").forward(request, response);
      
             
         
    }
}