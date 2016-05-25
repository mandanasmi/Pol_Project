/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbu.controller;

import com.sbu.dao.model.Employee;
import com.sbu.dao.model.Employer;
import com.sbu.dao.model.Feed;
import com.sbu.dao.model.Jobpositions;
import com.sbu.dao.model.Project;
import com.sbu.dao.model.Skills;
import com.sbu.service.EducationManager;
import com.sbu.service.EmployeeManager;
import com.sbu.service.EmployerManager;
import com.sbu.service.FeedManager;
import com.sbu.service.JobHistoryManager;
import com.sbu.service.JobPositionManager;
import com.sbu.service.LanguagesManager;
import com.sbu.service.ProjectManager;
import com.sbu.service.SkillsManager;
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
@WebServlet(name = "Update_Form_Employer_Controller", urlPatterns = {"/Update_Form_Employer_Controller"}, loadOnStartup = 1)
public class Update_Form_Employer_Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EmployerManager employerService;
    private EmployeeManager employeeService;
    private JobPositionManager jobService;
    private ProjectManager projectService;
    private SkillsManager skillsService;
    private FeedManager feedService;
    
     @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        skillsService = context.getBean(SkillsManager.class);
        employeeService = context.getBean(EmployeeManager.class);
        projectService = context.getBean(ProjectManager.class);
        employerService = context.getBean(EmployerManager.class);
        feedService = context.getBean(FeedManager.class);
    }    
    
    public Update_Form_Employer_Controller() {
        super();
    }

  
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Employer employer;
        List<Project> projects = null;
        List<Skills> skills = null;
        HttpSession session=request.getSession(); 
        List<Employer> users;
        List<Feed> feed = feedService.getAllFeed();
        String username = request.getParameter("username");
        String pass = request.getParameter("password");
         
            //if we have this employer in Database
            int id = employerService.Redundant(username);
            if(id>0)
            {
                    employer = employerService.getEmployer(id);
                    employer.setPassword(request.getParameter("password"));
                    employer.setCompanyName(request.getParameter("workname"));
                    employer.setIntroduction(request.getParameter("Intro"));
                    employer.setYear(request.getParameter("year"));
                    employer.setPhone(request.getParameter("phone"));
                    employer.setAddress(request.getParameter("address"));
                    employer.setCEOemail(request.getParameter("username"));
                    employer.setCeoName(request.getParameter("name"));
                    employer.setNews(request.getParameter("news"));
                    employer.setCeoLastname(request.getParameter("lastname"));
                    employer.setFacebook(request.getParameter("facebook"));
                    employer.setInstagram(request.getParameter("instagram"));
                    employer.setLinkedin(request.getParameter("linkedin"));
                    employer.setGithub(request.getParameter("github"));
                    //employer.setCompImage(request.getParts("CompImage"));
                    employerService.editEmployer(employer);
                    
                   if(!((projectService.getProjectByEmployerId(employer.getIdEmployer()))== null))
                   {      
                        projects = projectService.getProjectByEmployerId(employer.getIdEmployer());                    
                        for(int j = 0 ; j < projects.size() ; j++)
                        {
                            projects.get(j).setName(request.getParameter("project_name"+j));
                            projects.get(j).setDescription(request.getParameter("project_desc"+j));
                            projects.get(j).setDeadline(request.getParameter("project_year"+j));
                            projects.get(j).setEmployeridEmployer(employer);
                            if(request.getParameter("project_name"+j) != null){
                                projectService.editProject(projects.get(j));
                            }
                            
                        }
                        
                        
                    }

                    if(!((skillsService.getSkillByEmployerId(employer.getIdEmployer()))== null))
                    {
                        skills = skillsService.getSkillByEmployerId(employer.getIdEmployer());
                        for(int j = 0 ; j < skills.size() ; j++)
                        {
                            skills.get(j).setSkillName(request.getParameter("skill"+j));
                            skills.get(j).setPercent(request.getParameter("skill_Percent"+j));
                            skills.get(j).setEmployeridEmployer(employer);
                            if(request.getParameter("skill"+j) != null){
                                 skillsService.editSkill(skills.get(j));
                            }
                           
                        }
                        
                    }   
                    List<Employee> recomm = employerService.Recommendation();
                        /**Recommendation**/
                    session.setAttribute("recomm",recomm); 
                    session.setAttribute("feedcontent",feed);                    
                    session.setAttribute("User", employer);
                    getServletContext().getRequestDispatcher("/Feed_Employer.jsp").forward(request, response);
                    
            }
        
              
    }

}
