package com.ideas2it.employeemanagement.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;

/**
 * EmployeeController forms an intermediary layer between view and service
 *
 * @version 1.0   28-04-2021
 *
 * @author Gopal G
 */
public class EmployeeController {
    private EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

   /**
    * Controls employee ID validation between model and view layers
    *
    * @param id ID value which is to be validated
    * @param mode Mode for check
    *
    * @return a boolean to denote successful/unsuccessful validation of employee ID
    */
    public boolean validateEmployeeID(String id, int mode) {
        return employeeServiceImpl.validateEmployeeID(id, mode);
    }
	
   /**
    * Controls employee ID check between model and view layers
    *
    * @param id ID value which is to be checked
    *
    * @return a boolean to denote the existence status of the given employee ID
    */
    public boolean checkIfIdExists(String id) {
        return employeeServiceImpl.checkIfIdExists(id);
    }
	
   /**
    * Controls Date Of Birth validation between model and view layers
    *
    * @param birthDate date value which is to be validated
    *
    * @return date value of Date type
    */
    public Date getBirthDate(String birthDate) {
        return employeeServiceImpl.getBirthDate(birthDate);
    }
	
   /**
    * Controls email ID validation between model and view layers
    *
    * @param id email ID value which is to be validated
    *
    * @return a boolean to denote successful/unsuccessful validation of email ID
    */
    public boolean validateEmail(String email) {
        return employeeServiceImpl.validateEmail(email);
    }
	
   /**
    * Controls search by ID operation between model and view layers
    *
    * @param id ID value that is searched
    *
    * @return employee details concatenated together as a string
    */
    public String searchEmployeeID(String employeeID) {
        return employeeServiceImpl.searchEmployeeID(employeeID);
    }

   /**
    * Gets a list of all employees from service layer on request
    *
    * @return list of all employees with their respective details as individual strings
    */
    public List<String> getAllEmployees() {
        return employeeServiceImpl.getAllEmployees();
    }
	
   /**
    * Controls delete operation between model and view layers
    *
    * @param id ID value that is to be deleted
    *
    * @return a boolean to denote successful/unsuccessful deletion of employee
    */
    public boolean deleteEmployee(String id) {
        return employeeServiceImpl.deleteEmployee(id);
    }
	
   /**
    * Controls restore operation between model and view layers
    *
    * @param id ID value that is to be restored
    *
    * @return a boolean to denote successful/unsuccessful restoration of employee
    */
    public boolean restoreEmployee(String id) {
        return employeeServiceImpl.restoreEmployee(id);
    }
	
   /**
    * Passes on the address list request to service layer and returns the resource to view layer
    *
    * @param employeeID id of the employee whose address list is requested
    *
    * @return a list of String arrays carrying address details of the employee
    */
    public List<String[]> getEmployeeAddressList(String employeeID) {
        return employeeServiceImpl.getEmployeeAddressList(employeeID);
    }
	
   /**
    * Controls update of all details of employee between model and view layers
    *
    * @param employeeID id of the employee whose details are updated
    * @param name New value for name update
    * @param dateOfBirth New value for DOB update
    * @param salary New value for salary update
    * @param emailID New value for email ID update
    *
    * @return a boolean to denote successful/unsuccessful update of all details
    */
    public boolean updateEmployeeDetails(String employeeID, String name, 
                                        Date dateOfBirth, double salary, String emailID) {
        return employeeServiceImpl.updateEmployeeDetails(employeeID, name, dateOfBirth,
                                                        salary, emailID);
    }
	
   /**
    * Receives the address update request from view layer and passes it on to the service layer
    *
    * @param addressID id of the address whose details are to be updated
    * @param doorNumer New door number value of the address
    * @param street New street value of the address
    * @param locality New locality value of the address
    * @param pincode New pincode value of the address
    * @param district New district value of the address
    * @param state New state value of the address
    *
    * @return a boolean to denote successful/unsuccessful update of address details
    */
    public boolean updateExistingAddress(int addressID, String doorNumber, String street,
                                         String locality, String pincode, String district,
                                         String state, String employeeID) {
        return employeeServiceImpl.updateExistingAddress(addressID, doorNumber, street,
                                                        locality, pincode, district,
                                                        state, employeeID);
    }
	
   /**
    * Receices the delete address request from view layer and passes it on to service layer
    *
    * @param addressID id of the address which is to be deleted
    *
    * @return a boolean to denote successful/unsuccessful deletion of address
    */
    public boolean deleteAddress(int addressID, String employeeID) {
        return employeeServiceImpl.deleteAddress(addressID, employeeID);
    }
	
   /**
    * Receices the permanent address update request from view layer and passes it on to service layer
    *
    * @param oldPermanentAddress id of the current permanent address
    * @param newPermanentAddress id of the new permanent address
    *
    * @return a boolean to denote successful/unsuccessful update of permanent address
    */
    public boolean updatePermanentAddress(int oldPermanentAddress, int newPermanentAddress, String employeeID) {
        return employeeServiceImpl.updatePermanentAddress(oldPermanentAddress,newPermanentAddress, employeeID);
    }
	
   /**
    * Receices the add new address request from view layer and passes it on to service layer
    *
    * @param employeeID id of the employee whose new address is added
    * @param addressDetails A String array with details of the new address
    *
    * @return a boolean to denote successful/unsuccessful addition of new address
    */
    public boolean addNewAddress(String employeeID, String[] addressDetails) {
        return employeeServiceImpl.addNewAddress(employeeID, addressDetails);
    }

   /**
    * Controls employee addition operation between model and view layers
    *
    * @param name Name of the employee
    * @param employeeID Employee id
    * @param salary Employee's salary
    * @param dateOfBirth Employee's DOB
    * @param email Email ID of the employee
    * @param addresses List of String arrays carrying address details
    *
    * @return a boolean to denote successful/unsuccessful addition of employee
    */
    public boolean addEmployee(String name, String employeeID, double salary,
                    Date dateOfBirth, String email, List<String[]> addresses) {
        return employeeServiceImpl.addEmployee(name, employeeID, salary, dateOfBirth, email, addresses);
    }
	
   /**
    * Assigns a new project to the given employee
    *
    * @param employeeID id of the employee to whom project(s) is/are assigned
    * @param projectIdList list of project IDs to be assigned
    *
    * @return success a boolean that says whether the assignment was successful
    */
    public List<Integer> assignProject(String employeeID, Set<Integer> projectIdSet) {
        return employeeServiceImpl.assignProject(employeeID, projectIdSet);
    }
	
   /**
    * Fetches a list of assigned projects for the employee
    *
    * @param employeeID id of the employee whose assigned projects are fetched
    *
    * @return assignedProjectList List of projects assigned to the employee
    */
    public List<String> getAssignedProjects(String employeeID) {
        return employeeServiceImpl.getAssignedProjects(employeeID);
    }
	
   /**
    * Unassigns a project for the given employee
    *
    * @param employeeID id of the employee for whom the project is unassigned
    * @param projectID id of the project that is to be unassigned
    *
    * @return success a boolean that says whether the unassignment was successful
    */
    public boolean unassignProject(String employeeID, int projectID) {
       return employeeServiceImpl.unassignProject(employeeID, projectID);
    }
}