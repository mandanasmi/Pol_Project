/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbu.controller;

import com.sbu.dao.model.Member1;
import com.sbu.dao.model.Startup;
import com.sbu.service.JobPositionManager;
import com.sbu.service.MemberManager;
import com.sbu.service.StartupManager;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name = "ServletPersonalStartupController", urlPatterns = {"/ServletPersonalStartupController"}, loadOnStartup = 1)
public class ServletPersonalStartupController extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    private StartupManager startupService;
    private MemberManager memberService;
    Vector<Member1> members;
    
    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        startupService = context.getBean(StartupManager.class);
        memberService  = context.getBean(MemberManager.class);
        members = new Vector<>();
    }
    
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
        {
            
                            
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        Startup SearchQueries = null;
       
                List<Startup> sta = startupService.getAllStartups();
                List<Member1> mem = memberService.getAllMembers();
                
                for(int i = 0 ; i < sta.size() ; i++)
                {
                    if(sta.get(i).getIdStartup()==Integer.parseInt(id))
                    {
                        SearchQueries = sta.get(i);
                        break;
                    }
                }
                for(int i = 0 ; i < mem.size() ; i++)
                {
                    if(mem.get(i).getStartupidStartup().equals(SearchQueries))
                    {
                        members.add(mem.get(i));
                    }
                }
                         request.setAttribute("SearchQueries",SearchQueries);
                         request.setAttribute("members",members);  
                         request.getRequestDispatcher("Personal_Startup.jsp").forward(request, response);    
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}