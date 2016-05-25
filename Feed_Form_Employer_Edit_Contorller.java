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
 * @author Niloofar
 */
@WebServlet(name = "Feed_Form_Employer_Edit_Contorller", urlPatterns = {"/Feed_Form_Employer_Edit_Contorller"}, loadOnStartup = 1)
public class Feed_Form_Employer_Edit_Contorller extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EmployerManager employerService;
    private ProjectManager projectService;
    private SkillsManager skillsService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        employerService = context.getBean(EmployerManager.class);
        projectService = context.getBean(ProjectManager.class);
        skillsService = context.getBean(SkillsManager.class);
    }
    public Feed_Form_Employer_Edit_Contorller() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        List<Project> projects;
        List<Skills> skills;
        Vector<Project> filtered_projects = new Vector<>();
        Vector<Skills> filtered_skills = new Vector<>();
        List<Employer> users;
        Employer user = null;
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession(); 
   
        int id = Integer.parseInt(request.getParameter("id"));
        
        //user = employerService.getEmployer(id);
        
        skills = skillsService.getAllSkills();
        projects = projectService.getAllProjects();
        users = employerService.getAllEmployers();
        for(int i = 0 ; i < users.size();i++)
        {
            if(users.get(i).getIdEmployer()==id)
            {
                user=users.get(i);
                break;
            }
        }
        
        for(int i = 0 ; i < skills.size(); i++)
        {
            if(skills.get(i).getEmployeridEmployer().equals(user))
                filtered_skills.add(skills.get(i));
        }
       
        for(int i = 0 ; i < projects.size();i++)
        {
            if(projects.get(i).getEmployeridEmployer().equals(user))
                filtered_projects.add(projects.get(i));
        }
        
        request.setAttribute("User", user);
        request.setAttribute("projects", filtered_projects);
        request.setAttribute("skills",filtered_skills);

        getServletContext().getRequestDispatcher("/accountsetting_Employer_Edit.jsp").forward(request, response);
       
    }
}