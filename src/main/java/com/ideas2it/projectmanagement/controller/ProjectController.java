package com.ideas2it.projectmanagement.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ideas2it.projectmanagement.service.impl.ProjectServiceImpl;

/**
 * EmployeeController forms an intermediary layer between view and service
 *
 * @version 1.0   30-04-2021
 *
 * @author Gopal G
 */

public class ProjectController {
    private ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
	
   /**
    * Controls deadline validation between model and view layers
    *
    * @param deadlineDate Deadline date value as String
    *
    * @return deadline Deadline value in date format
    */
    public Date getDeadline(String deadlineDate) {
        Date deadline = projectServiceImpl.getDeadline(deadlineDate);
        return deadline;
    }
	
   /**
    * Controls project addition operation between model and view layers
    *
    * @param name Name of the project
    * @param manager Manager of the project
    * @param client Client of the project
    * @param deadline Deadline of the project
    *
    * @return a boolean to denote successful/unsuccessful addition of project
    */
    public boolean addProject(String name, String manager, String client, Date deadline) {
        return projectServiceImpl.addProject(name, manager, client, deadline);
    }
	
   /**
    * Controls project ID validation between model and view layers
    *
    * @param projectID id value of the project
    * @param mode Mode of validation
    *
    * @return a boolean to denote validity of the project ID
    */
    public boolean checkProjectID(int projectID, int mode) {
        return projectServiceImpl.checkProjectID(projectID, mode);
    }
	
   /**
    * Controls project deletion operation between model and view layers
    *
    * @param projectID id of the project
    *
    * @return a boolean to denote successful/unsuccessful deletion of project
    */
    public boolean deleteProject(int projectID) {
        return projectServiceImpl.deleteProject(projectID);
    }
	
   /**
    * Controls project restoration operation between model and view layers
    *
    * @param projectID id of the project
    *
    * @return a boolean to denote successful/unsuccessful restoration of project
    */
    public boolean restoreProject(int projectID) {
        return projectServiceImpl.restoreProject(projectID);
    }
	
   /**
    * Controls project retrieval operation between model and view layers
    *
    * @return a list of strings with all project details
    */
    public List<String> getAllProjects() {
        return projectServiceImpl.getAllProjects();
    }
	
   /**
    * Controls project search operation between model and view layers
    *
    * @param projectID id of the project
    *
    * @return Detials of the searched project concatenated together as a string
    */
    public String searchProjectID(int projectID) {
        return projectServiceImpl.searchProjectID(projectID);
    }
	
   /**
    * Controls project update operation between model and view layers
    *
    * @param projectID id of the project
    * @param name Name of the project
    * @param manager Manager of the project
    * @param client Client of the project
    * @param deadline Deadline of the project
    *
    * @return a boolean to denote successful/unsuccessful update of project
    */
    public boolean updateProjectDetails(int projectID, String name, String manager, String client, 
                                        Date deadline) {
        return projectServiceImpl.updateProjectDetails(projectID, name, manager, client, deadline);
    }
	
   /**
    * Controls assign employee operation between model and view layers
    *
    * @param projectID id of the project
    * @param employeeIdList list of employee IDs to be assigned to the project
    *
    * @return a list of strings with employee IDs that couldn't be assigned to the project
    */
    public List<String> assignEmployee(int projectID, Set<String> employeeIdSet) {
        return projectServiceImpl.assignEmployee(projectID, employeeIdSet);
    }
	
   /**
    * Controls fetching of assigned employees of a project operation between model and view layers
    *
    * @param projectID id of the project
    * @param mode Mode of fetch
    *
    * @return a list of strings with details of employees assigned to the project
    */
    public List<String> getAssignedEmployees(int projectID, int mode) {
        return projectServiceImpl.getAssignedEmployees(projectID, mode);
    }
	
	
   /**
    * Controls unassign employee operation between model and view layers
    *
    * @param employeeID id of the employee who is to be unassigned
    * @param projectID id of the project
    *
    * @return a boolean to denote successful/unsuccessful unassignment
    */
    public boolean unassignEmployee(String employeeID, int projectID) {
        return projectServiceImpl.unassignEmployee(employeeID, projectID);
    }
}