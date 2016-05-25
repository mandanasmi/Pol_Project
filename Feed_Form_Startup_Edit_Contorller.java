/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbu.controller;

import com.sbu.dao.model.Education;
import com.sbu.dao.model.Employee;
import com.sbu.dao.model.Jobhistory;
import com.sbu.dao.model.Languages;
import com.sbu.dao.model.Member1;
import com.sbu.dao.model.Programminglanguages;
import com.sbu.dao.model.Startup;
import com.sbu.service.EducationManager;
import com.sbu.service.EmployeeManager;
import com.sbu.service.JobHistoryManager;
import com.sbu.service.JobPositionManager;
import com.sbu.service.LanguagesManager;
import com.sbu.service.MemberManager;
import com.sbu.service.ProgLanguagesManager;
import com.sbu.service.StartupManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
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
@WebServlet(name = "Feed_Form_Startup_Edit_Contorller", urlPatterns = {"/Feed_Form_Startup_Edit_Contorller"}, loadOnStartup = 1)
public class Feed_Form_Startup_Edit_Contorller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private StartupManager startupService;
    private MemberManager memberService;


    public Feed_Form_Startup_Edit_Contorller() {
        super();
    }
       
     @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        startupService = context.getBean(StartupManager.class);
        memberService = context.getBean(MemberManager.class);


    }
    
    //@Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        Startup User = new Startup();
        
        List<Member1> members= new ArrayList();

        
        request.setCharacterEncoding("UTF-8");
        int counter=1;
            
        HttpSession session=request.getSession();
   
        int id = Integer.parseInt(request.getParameter("id"));
        User = startupService.getStartup(id);
        members = memberService.getAllMembers();
        Vector<Member1> filtered_members = new Vector<>();
        //filtered_members = memberService.getMemberByStartupId(id);

        for(int i = 0 ; i < members.size(); i++)
        {
            if(members.get(i).getStartupidStartup().getIdStartup()== id)
                filtered_members.add(members.get(i));
        }
     
            
        session.setAttribute("User", User);
        session.setAttribute("members",  filtered_members);

        getServletContext().getRequestDispatcher("/accountsetting_Startup_Edit.jsp").forward(request, response);
       
    }
}