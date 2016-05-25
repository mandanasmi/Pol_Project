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
@WebServlet(name = "Feed_Personal_Employer_View_Controller", urlPatterns = {"/Feed_Personal_Employer_View_Controller"}, loadOnStartup = 1)
public class Feed_Personal_Employer_View_Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Feed_Personal_Employer_View_Controller() {
        super();
    }
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
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        Employer employer = new Employer();
        Employer user;
        List<Project> projects = new ArrayList();
        List<Skills> skills = new ArrayList();
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession(); 
        //finding the user
        int id = Integer.parseInt(request.getParameter("id"));
        user = employerService.getEmployer(id);

        skills = skillsService.getSkillByEmployerId(user.getIdEmployer());
        projects = projectService.getProjectByEmployerId(user.getIdEmployer());
        
        session.setAttribute("User", user);
        session.setAttribute("projects", projects);
        session.setAttribute("skills",skills);
        //session.setAttribute("allEmployers", employerService.getAllEmployers());
        
        request.getRequestDispatcher("Personal_Employer.jsp").forward(request, response);
       

    }

}