/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbu.controller;

import com.sbu.dao.model.Feed;
import com.sbu.dao.model.Jobpositions;
import com.sbu.dao.model.Member1;
import com.sbu.dao.model.Startup;
import com.sbu.service.FeedManager;
import com.sbu.service.JobPositionManager;
import com.sbu.service.MemberManager;
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
 * @author Mandana
 */
@WebServlet(name = "Update_Form_Startup_Controller", urlPatterns = {"/Update_Form_Startup_Controller"}, loadOnStartup = 1)
public class Update_Form_Startup_Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static StartupManager startupService;
    private static MemberManager memberService;
    private JobPositionManager jobpositionService;
    private FeedManager feedService;
    
    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        jobpositionService = context.getBean(JobPositionManager.class);
        memberService = context.getBean(MemberManager.class) ;
        startupService = context.getBean(StartupManager.class);
        feedService = context.getBean(FeedManager.class);
    }
    
    public Update_Form_Startup_Controller() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        request.setCharacterEncoding("UTF-8");
        Startup startup;
        List<Member1> members;
        String username = request.getParameter("email");
        String pass = request.getParameter("password");
         int id = startupService.Redundant(username);
            if(id>0)
            {
        startup = startupService.getStartup(id);
        startup.setName(request.getParameter("supervisorn"));
        startup.setLastname(request.getParameter("supervisorl"));
        startup.setEmail(username);
        startup.setPassword(pass);
        startup.setStartupName(request.getParameter("name"));
        startup.setAddress(request.getParameter("address"));
        startup.setPhone(request.getParameter("tell"));
        startup.setDescription(request.getParameter("description"));
        startup.setInstagram(request.getParameter("instagram"));
        startup.setFacebook(request.getParameter("facebook"));
        startup.setLinkedin(request.getParameter("linkedin"));
        startupService.editStartup(startup);
            
         /******************* Members **********************/                          

        if(!((memberService.getMemberByStartupId(startup.getIdStartup()))== null))
                   {      
                        members = memberService.getMemberByStartupId(startup.getIdStartup());
                        
        for(int j = 1 ; j < members.size() ; j++)
            {
                   
                    members.get(j).setName(request.getParameter("name"+j));
                    members.get(j).setLname(request.getParameter("lastname"+j));
                    members.get(j).setMajor(request.getParameter("major"+j));
                    members.get(j).setUniversity(request.getParameter("university"+j));
                    members.get(j).setStartupidStartup(startup);                   
                    memberService.editMember(members.get(j));
            }
       
     
          /** Recommendation **/
        Vector<Jobpositions> recomm = startupService.Recommendation();
        
        List<Feed> feedcontent= new ArrayList();
        feedcontent= feedService.getAllFeed();  
        
        session.setAttribute("recomm",recomm);
        session.setAttribute("User", startup);
        session.setAttribute("members", members);
        session.setAttribute("feedcontent", feedcontent);
        getServletContext().getRequestDispatcher("/Feed_Startup.jsp").forward(request, response);  
         }
            }
    }
    }