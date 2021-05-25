package com.ideas2it.projectmanagement.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.ideas2it.projectmanagement.controller.ProjectController;

/**
 * ProjectView interacts with users and forwards user requests to control layer
 *
 * @version 1.0   30-04-2021
 *
 * @author Gopal G
 */

public class ProjectView {
    private Scanner scanner = new Scanner(System.in);
    private ProjectController projectController = new ProjectController();
	
   /**
    * Gets name as input from user
    *
    * @return name provided by the user
    */
    public String getName() {
        System.out.print("\n\nEnter Project name: ");
        scanner.skip("[\r\n]+");
        return scanner.nextLine();
    }
	
   /**
    * Gets manager as input from user
    *
    * @return manager manager value provided by the user
    */
    public String getManager(boolean skip) {
        System.out.print("\n\nEnter manager name: ");
        if (skip) {
            scanner.skip("[\r\n]+");
        }
        return scanner.nextLine();
    }
	
    /**
    * Gets client as input from user
    *
    * @return client client provided by the user
    */
    public String getClient(boolean skip) {
        System.out.print("\n\nEnter client: ");
        if (skip) {
            scanner.skip("[\r\n]+");
        }
        return scanner.nextLine();
    }
	
   /**
    * Gets deadline as input from user
    *
    * @return deadline deadline value provided by the user
    */
    public Date getDeadline() {
        System.out.print("\n\nEnter deadline date: ");
        String deadlineDate = scanner.next();
        Date deadline = projectController.getDeadline(deadlineDate);
        while (null == deadline) {
            System.out.println("\nInvalid date!!!\nEnter a valid date for deadline: ");
            deadlineDate = scanner.next();
            deadline = projectController.getDeadline(deadlineDate);
        }
        return deadline;
    }
	
   /**
    * Reads details of the project which is to be added
    */
    public void addProject() {
        String name = getName();
        String manager = getManager(false);
        String client = getClient(false);
        Date deadline = getDeadline();
        if (projectController.addProject(name, manager, client, deadline)) {
            System.out.println("Project details added successfully...");
        } else {
            System.out.println("Project addition wasn't successful.");
        }
    }
	
   /**
    * Gets the ID value of the project which is to be deleted
    * Prints successful/unsuccessful completion status of the deletion
    */
    public void deleteProject() {
        System.out.println("\nEnter project ID for deletion: ");
        int id = scanner.nextInt();
        boolean success = false;
        if (projectController.checkProjectID(id, 1)) {
            System.out.print("\nProject ID exists...\n");
            success = projectController.deleteProject(id);
        }
        String displayMessage = (success ? "Project deletion successful\n" : 
                                "Deletion unsuccessful. Check if project ID is valid...\n");
        System.out.print(displayMessage);
    }
	
   /**
    * Gets the ID value of the project which is to be restored
    * Prints successful/unsuccessful completion status of the restoration
    */
    public void restoreProject() {
        System.out.println("\nEnter project ID for restoration: ");
        int id = scanner.nextInt();
        boolean success = false;
        if (projectController.checkProjectID(id, 2)) {
            success = projectController.restoreProject(id);
        }
        String displayMessage = (success ? "Project restoration successful\n" : 
                                "Restoration unsuccessful. Check whether the id is valid or "
                                + "the id is already active.\n");
        System.out.print(displayMessage);
    }
	
   /**
    *Displays details of all projects
    */
    public void displayAllProjects() {
        List<String> projects = projectController.getAllProjects();
        int index = 0;
        System.out.println("\nList of all projects:\n");
        if (projects.size() < 1) {
            System.out.print("\nNo projects available now!!!\n\n");
        }
        else {
            for (String project : projects) {
               System.out.println("\n\t"+(++index)+". "+ project);
            }
        }
    }
	
   /**
    * Gets search input from user and displays search result
    */
    public void searchProject() {
        String projectDetails = "";
        System.out.println("Enter project ID: ");
        int projectID = scanner.nextInt();
        if (projectController.checkProjectID(projectID, 1)) {
            projectDetails = projectController.searchProjectID(projectID);
        }
		
        if(projectDetails.equals("")) {
            System.out.println("\nSearch result:\nNo result found for this search.");
        } else {
            System.out.println("\nSearch result:\n\t" + projectDetails);
        }
    }
	
   /**
    * Gets new value for name of the project
    *
    * @param projectID id of the project whose name is to be updated
    *
    * @return a boolean that says whether name update is successful or not
    */
    private boolean updateName(int projectID) {
        Date deadline = null;
        String client = "";
        String manager = "";
        String name = getName();
        return projectController.updateProjectDetails(projectID, name, manager, client, deadline);
    }
	
   /**
    * Gets new value for manager of the project
    *
    * @param projectID id of the project whose manager is to be updated
    *
    * @return a boolean that says whether manager update is successful or not
    */
    private boolean updateManager(int projectID) {
        Date deadline = null;
        String client = "";
        String name = "";
        String manager = getManager(true);
        return projectController.updateProjectDetails(projectID, name, manager, client, deadline);
    }
	
   /**
    * Gets new value for client of the project
    *
    * @param projectID id of the project whose client is to be updated
    *
    * @return a boolean that says whether client update is successful or not
    */
    private boolean updateClient(int projectID) {
        Date deadline = null;
        String name = "";
        String manager = "";
        String client = getClient(true);
        return projectController.updateProjectDetails(projectID, name, manager, client, deadline);
    }
	
   /**
    * Gets new value for deadline of the project
    *
    * @param projectID id of the project whose deadline is to be updated
    *
    * @return a boolean that says whether deadline update is successful or not
    */
    private boolean updateDeadline(int projectID) {
        String client = "";
        String manager = "";
        String name = "";
        Date deadline = getDeadline();
        return projectController.updateProjectDetails(projectID, name, manager, client, deadline);
    }
	
   /**
    * Gets new value for deadline of the project
    *
    * @param projectID id of the project whose deadline is to be updated
    *
    * @return a boolean that says whether deadline update is successful or not
    */
    private boolean updateAllDetails(int projectID) {
        String name = getName();
        String manager = getManager(false);
        String client = getClient(false);
        Date deadline = getDeadline();
        return projectController.updateProjectDetails(projectID, name, manager, client, deadline);
    }
	
   /**
    * Provides appropriate user interface for update operation
    *
    * @param projectID id of the project which is to be updated
    */
    private void showUpdateUserInterface(int projectID) {
        System.out.println("\n Project ID " + projectID
                          + " matches with record. Proceeding to updation process."
                          + "\nChoose the attribute to be updated:\n1. Name\n"
                          + "2. Manager,\n3. Client,\n4. Deadline"
                          + "\n5. Update all details\nEnter your option: ");
    }
	
   /**
    * Gets mode of update from user and makes corresponding calls
    *
    * @param projectID id of the project whose details are to be updated
    *
    * @return success boolean that denotes successful/unsuccessful completion of update
    */
    private boolean performUpdateOperation(int projectID) {
        boolean success = false;
        showUpdateUserInterface(projectID);
        int attributeId = scanner.nextInt();
        switch (attributeId) {
            case 1:
                success = updateName(projectID);
                break;
            case 2:
                success = updateManager(projectID);
                break;
            case 3:
                success = updateClient(projectID);
                break;
            case 4:
                success = updateDeadline(projectID);
                break;
            case 5:
                success = updateAllDetails(projectID);
                break;
            default:
                System.out.print("\nWrong option!!");
                success = false;
                break;
        }
        return success;
    }
	
   /**
    * Gets ID of the project whose details are to be updated
    */
    private void updateProject() {
        boolean success = false;
        System.out.println("\nEnter the project ID that needs update: ");
        int projectID = scanner.nextInt();
        if (projectController.checkProjectID(projectID, 1)) {
           if (performUpdateOperation(projectID)) {
               System.out.println("Detail(s) updated successfully.");
           } else {
               System.out.print("\nCouldn't update details\n");
           }
        } else {
            System.out.println("\nProject ID unavailable. Cannot update.\n");
        }
    }
	
   /**
    * Provides appropriate user interface for assignment management
    *
    * @param projectID id of the project which is to be updated
    */
    private void showAssignmentInterface(int projectID) {
        System.out.println("\n Project ID " + projectID
                          + " matches with record. You can manage employee assignments for this project."
                          + "\nOperations available:\n1. Assign employee\n"
                          + "2. Unassign employee,\n3. Show assigned employees"
                          + "\nEnter your option: ");
    }
	
   /**
    * Receives new employee assignment information for the given project
    *
    * @param projectID id of the project for which employees are assigned
    */
    public void assignEmployee(int projectID) {
        Set<String> employeeIdSet = new LinkedHashSet<String>();
        int count = 0;
        int option = 1;
        String id = "";
        do {
             System.out.print("\nEnter an employee ID to be added: ");
             id = scanner.next();
             employeeIdSet.add(id);
             System.out.print("\nDo you want to add another employee ID?(1-> yes, any number -> no): ");
             option = scanner.nextInt();
        } while(1 == option);
        List<String> failedAssignments = projectController.assignEmployee(projectID, employeeIdSet);
        if (0 == failedAssignments.size()) {
           System.out.print("\nEmployee assignment(s) successful.");
        } else {
           System.out.print("\n" + failedAssignments.size()+ " out of " + employeeIdSet.size() 
                            + " employee assignments failed. "
                            + "Please Check \n1. Whether the employee IDs are valid.\n2. Whether they're assigned " 
                            + "to this project already.\n\nIDs that cannot be assigned: ");
            for (String employeeID : failedAssignments) {
                System.out.print("\n" + (++count) + ". " + employeeID);
            }
        }
    }
	
   /**
    * Receives employee unassign information for the given project
    *
    * @param projectID id of the project which requires unassignment
    */
    public void unassignEmployee(int projectID) {
        List<String> assignedEmployeeDetails = projectController.getAssignedEmployees(projectID, 2);
        int count = 0;
        String resultNotification = "";
        System.out.print("\nList of assigned Employee IDs:\n");
        for (String employeeDetails : assignedEmployeeDetails) {
            System.out.println("\n" + (++count) + ". " + employeeDetails);
        }
        System.out.print("\nEnter the employee ID that you want to unassign: ");
        String employeeID = scanner.next();
        boolean success = projectController.unassignEmployee(employeeID, projectID);
        resultNotification = (success ? "Unassignment successful\n" : 
                                        "Unassignment couldn't succeed. Please Check"
                                        + "\n1. whether the ID is valid\n2. whether it's assigned to the project");
        System.out.println("\n" + resultNotification);
    }
	
   /**
    * Displays all employees assigned to this project
    *
    * @param projectID id of the project for which assigned employees are displayed
    */
    public void showAssignedEmployees(int projectID) {
        List<String> assignedEmployeesList = projectController.getAssignedEmployees(projectID, 2);
        int count = 0;
        if (assignedEmployeesList.size() > 0) {
            System.out.print("\nList of Assigned Employees: ");
            for (String employeeDetails : assignedEmployeesList) {
                System.out.println("\n\t" + (++count) + ". " + employeeDetails);
            }
        } else {
            System.out.println("\nNo employee assigned for this project.");
        }
    }
	
	
   /**
    * Gets operation choice from user and makes corresponding calls
    *
    * @param projectID id of the project whose employee assignments are manipulated
    */
    private void handleAssignment(int projectID) {
        showAssignmentInterface(projectID);
        int operationID = scanner.nextInt();
        switch (operationID) {
            case 1:
                assignEmployee(projectID);
                break;
            case 2:
                unassignEmployee(projectID);
                break;
            case 3:
                showAssignedEmployees(projectID);
                break;
            default:
                System.out.print("\nWrong option!!");
                break;
        }
    }
	
   /**
    * Gets ID of the project whose employee assignments are managed
    */
    private void manageEmployeeAssignment() {
        boolean success = false;
        System.out.println("\nEnter the project ID: ");
        int projectID = scanner.nextInt();
         if (projectController.checkProjectID(projectID, 1)) {
            handleAssignment(projectID);
         } else {
            System.out.println("\nProject ID doesn't exist");
         }
    }

   /**
    * Overseer method that acts as an intermediate between user interaction methods & CRUD operation methods
    * Also the origin of control flow for all operations being performed
    */
    public void doProjectCRUDOperations() {
        int option = 1;
        int operation = 0;
        String operationsMenu = "Operations to perform:\n1. Add Project"
                                + "\n2. Search Project\n3. Display all projects"
                                + "\n4. Delete a project\n5. Update a project\n6. Restore project"
                                + "\n7. Manage Employee assignment\n\nEnter an operation: ";
        do {
             System.out.println(operationsMenu);
             operation = scanner.nextInt();
             switch (operation) {
                case 1:
                    addProject();
                    break;		
                case 2:
                    searchProject();
                    break;
                case 3:
                    displayAllProjects();
                    break;		
                case 4:
                    deleteProject();
                    break;		
                case 5:
                    updateProject();				
                    break;
                case 6:
                    restoreProject();
                    break;
                case 7:
                    manageEmployeeAssignment();
                    break;
                default:
                    System.out.println("Invalid Option!!! "
                                      + "Please enter a valid option!!!");
                    break;
            }
            System.out.println("\nDo you want to perform another operation?"
                              + "(1 -> Yes, Any other number -> No)\nEnter option: ");
            option = scanner.nextInt();
        }while (1 == option);
        System.out.println("\n\n\n\t\t\tTHANK YOU!!!\n\n\n");
    }
}