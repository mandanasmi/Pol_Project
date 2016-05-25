
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
import com.sbu.service.EmployeeManager;
import com.sbu.service.EmployerManager;
import com.sbu.service.FeedManager;
import com.sbu.service.JobPositionManager;
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
@WebServlet(name = "ServletSaveJobController", urlPatterns = {"/ServletSaveJobController"}, loadOnStartup = 1)
public class ServletSaveJobController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ServletSaveJobController() {
        super();
    }
    private EmployerManager employerService;
    private JobPositionManager jobPositionService;
    private ProjectManager projectService;
    private SkillsManager skillsService;
    private EmployeeManager employeeService;
    private FeedManager feedService;
    
    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        employerService = context.getBean(EmployerManager.class);
        jobPositionService = context.getBean(JobPositionManager.class);
        projectService = context.getBean(ProjectManager.class);
        skillsService = context.getBean(SkillsManager.class);
        employeeService = context.getBean(EmployeeManager.class);
        feedService = context.getBean(FeedManager.class);
    }
    //@Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession(); 
  
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("email");
        Employer user = employerService.getEmployer(id);
        Feed feed = new Feed();
        List<Feed> feeds = new ArrayList();
        Jobpositions jobposition = new Jobpositions();

        jobposition.setCategory(request.getParameter("category"));
        jobposition.setDegree(request.getParameter("certificate"));
        jobposition.setLanguages(request.getParameter("familiar"));
        jobposition.setMajor(request.getParameter("major"));
        jobposition.setPosTitle(request.getParameter("posname"));
        jobposition.setSalary(request.getParameter("minsalary"));
        jobposition.setSex(request.getParameter("sex"));
        jobposition.setSkills(request.getParameter("Skills"));
        jobposition.setDate(request.getParameter("Date"));
        jobposition.setWorkTime(request.getParameter("workhours"));
        jobposition.setUniversity(request.getParameter("uni"));
        jobposition.setDescription(request.getParameter("description"));
        jobposition.setEmployeridEmployer(user);
        jobPositionService.addJobpositions(jobposition);
        //feed
        feed.setEmployeridEmployer(user);
        feed.setDescription(request.getParameter("description"));
        feed.setTitle(request.getParameter("posname"));
        feed.setDegree(request.getParameter("certificate"));
        feed.setMajor(request.getParameter("major"));
        feed.setSkills(request.getParameter("Skills"));
        feed.setSalary(request.getParameter("minsalary"));
        feed.setWorkTime(request.getParameter("workhours"));
        feed.setDate(request.getParameter("Date"));
        feedService.addFeed(feed);
        
        feeds = feedService.getAllFeed();
        
        /**Recommendation**/
         
         List<Employee> emps = employeeService.getAllEmployees();
             Vector<Employee> recomm = new Vector<>();

                for(int j = 0 ; j < emps.size() ; j++)
                    {
                                    
                      if(user.getAddress().contains(emps.get(j).getCity()))
                       {
                            recomm.add(emps.get(j));
                       }

                    }
         
         /**Recommendation**/
        session.setAttribute("recomm",recomm);
        session.setAttribute("id", id);
        session.setAttribute("email",username);
        session.setAttribute("feedcontent", feeds);
        session.setAttribute("User",user);
        getServletContext().getRequestDispatcher("/Feed_Employer.jsp").forward(request, response);
 
    }
}