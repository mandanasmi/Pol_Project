/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbu.controller;

import com.sbu.dao.model.Employer;
import com.sbu.dao.model.Project;
import com.sbu.dao.model.Skills;
import com.sbu.service.EmployerManager;
import com.sbu.service.JobPositionManager;
import com.sbu.service.ProjectManager;
import com.sbu.service.SkillsManager;
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
@WebServlet(name = "ServletPersonalEmployerController", urlPatterns = {"/ServletPersonalEmployerController"}, loadOnStartup = 1)
public class ServletPersonalEmployerController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EmployerManager employerService;
    private ProjectManager projectService;
    private SkillsManager skillsService;
    
    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        employerService = context.getBean(EmployerManager.class);
        projectService = context.getBean(ProjectManager.class);
        skillsService  = context.getBean(SkillsManager.class);
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
        Employer employer = new Employer();        
        List<Employer> user;
        List<Project>  proj;
        List<Skills>   skills = new Vector<>();;
        List<Project> projects = new Vector<>();
        //List<Skills> skills = new Vector<>();
        user = employerService.getAllEmployers();
        //proj = projectService.getAllProjects();
        //allskills = skillsService.getAllSkills();
        int id = Integer.parseInt(request.getParameter("id"));
        employer = employerService.getEmployer(id);
        //for(int i = 0 ; i < user.size() ;i++)
        //{
            if(!((projectService.getProjectByEmployerId(id))== null))
                   {      
                        projects = projectService.getProjectByEmployerId(id);                    
                       
                    }

            if(!((skillsService.getSkillByEmployerId(id))== null))
                {
                    skills = skillsService.getSkillByEmployerId(id);

                }   
            /*if(user.get(i).getIdEmployer()==Integer.parseInt(request.getParameter("id")))
            {
                
                for(int j = 0 ; j < proj.size() ; j++)
                    {
                        if(proj.get(j).getEmployeridEmployer().equals(user.get(i)))
                        {
                            projects.add(proj.get(j));
                        }
                   }
                 
                for(int j = 0; j < allskills.size() ; j++)
                    {
                        if(skills.get(j).getEmployeridEmployer().equals(user.get(i)))
                        {
                            skills.add(allskills.get(j));
                        }
                   }*/
                //employer = user.get(i);
                            
                request.setAttribute("User", employer);
                request.setAttribute("projects", projects);
                request.setAttribute("skills", skills);
                  getServletContext().getRequestDispatcher("/Personal_Employer.jsp").forward(request, response);
                //break;
           // }
        //}
        getServletContext().getRequestDispatcher("/Personal_Employer.jsp").forward(request, response);
       
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