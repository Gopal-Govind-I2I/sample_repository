package com.ideas2it.employeemanagement.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.ideas2it.employeemanagement.controller.EmployeeController;

/**
 * EmployeeView interacts with users and forwards user requests to control layer
 *
 * @version 1.0   28-04-2021
 *
 * @author Gopal G
 */
public class EmployeeView {
    private Scanner scanner = new Scanner(System.in);
    EmployeeController employeeController = new EmployeeController();
	
   /**
    * Gets name as input from user
    *
    * @return name provided by the user
    */
    public String getName() {
        System.out.print("\n\nEnter Employee name: ");
        scanner.skip("[\r\n]+");
        return scanner.nextLine();
    }
	
   /**
    * Gets salary as input from user
    *
    * @return salary provided by the user
    */
    public double getSalary() {
        System.out.print("\n\nEnter Employee's salary: ");
        return scanner.nextDouble();
    }
	
   /**
    * Gets ID as input from user
    *
    * @return ID provided by the user
    */
    public String getId() {
        System.out.print("\n\nEnter Employee ID: ");
        String id = scanner.next();
        while (employeeController.checkIfIdExists(id)) {
            System.out.print("\nEmployee ID already exists!!!\nEnter a new ID: ");
            id = scanner.next();
        }
        return id;
    }
	
   /**
    * Gets email ID as input from user
    *
    * @return email ID provided by the user
    */
    public String getEmail() {
        System.out.print("\n\nEnter Employee email: ");
        String email = scanner.next();
        while(!employeeController.validateEmail(email)) {
            System.out.println("Invalid email!!!\n\nEnter a valid email address: ");
            email = scanner.next();
        }
        return email;
    }
	
   /**
    * Gets date of birth as input from user
    *
    * @return date of birth provided by the user
    */
    public Date getBirthDate() {
        System.out.print("\nEnter employee's DOB: ");
        String birthDate = scanner.next();
        Date dateOfBirth = employeeController.getBirthDate(birthDate);
        while (null == dateOfBirth) {
            System.out.println("\nInvalid date of birth!!!\nEnter a valid DOB: ");
            birthDate = scanner.next();
            dateOfBirth = employeeController.getBirthDate(birthDate);
        }
        return dateOfBirth;
    }
	
   /**
    * Reads details of the employee who is to be added
    */
    public void addEmployee() {
        String name = getName();
        String employeeID = getId();
        double salary = getSalary();
        String email = getEmail();
        Date dateOfBirth = getBirthDate();
        boolean permanentAddressFlag = false;
        List<String[]> addresses = new ArrayList<String[]>();
        int choice = 1;
        int addressCount = 0;
        do {
            System.out.print("\nEnter address " + (++addressCount) + " details: ");
            if(!permanentAddressFlag) {
                System.out.println("\nReminder: Permanent address is mandatory. " 
                                  + "Please give a permanent address before quitting.");
            }
            String address[] = new String[7];
            address = getAddress(permanentAddressFlag);
            if(!permanentAddressFlag) {
                permanentAddressFlag = ("true".equals(address[0]));
            }
            addresses.add(address);
            if (permanentAddressFlag) {
                System.out.print("\nDo you want to add another address?(1->yes, 2->any number): ");
            }
            choice = (permanentAddressFlag ? scanner.nextInt() : 1);
        } while(1 == choice);
        if (employeeController.addEmployee(name, employeeID, salary, dateOfBirth, email, addresses)) {
            System.out.println("Employee details added successfully...");
        } else {
            System.out.println("Employee addition wasn't successful.");
        }
    }
	
   /**
    * Gets permanent address flag from user
    *
    * @return permanentAddressFlag a boolean to mark an address as permanent
    */
    public boolean getPermanentAddressFlag() {
        System.out.print("\nDo you want to mark this address as permanent address? "
                        + "(1->yes, any number->no): ");
        int choice = scanner.nextInt();
        return (1 == choice ? true : false);		
    }
	
   /**
    * Gets door number from user
    *
    * @return doorNumber a string value for the door number of the employee
    */
    public String getDoorNumber() {
        System.out.print("\nEnter door number: ");
        return scanner.next();
    }
	
   /**
    * Gets street from user
    *
    * @return street a string value for the street of the employee
    */
    public String getStreet() {
        System.out.print("\nEnter street: ");
        scanner.skip("[\r\n]+");
        return scanner.nextLine();
    }
	
   /**
    * Gets locality from user
    *
    * @return locality a string value for the locality of the employee
    */
    public String getLocality() {
        System.out.print("\nEnter locality: ");
        return scanner.next();
    }
	
   /**
    * Gets pincode from user
    *
    * @return pincode a string value for the pincode of the employee
    */
    public String getPincode() {
        System.out.print("\nEnter pincode: ");
        return scanner.next();
    }
	
   /**
    * Gets district from user
    *
    * @return district a string value for the district of the employee
    */
    public String getDistrict() {
        System.out.print("\nEnter district: ");
        scanner.skip("[\r\n]+");
        return scanner.nextLine();
    }
	
   /**
    * Gets state from user
    *
    * @return state a string value for the state of the employee
    */
    public String getState() {
        System.out.print("\nEnter state: ");
        scanner.skip("[\r\n]+");
        return scanner.nextLine();
    }
	
   /**
    * Reads address details of the employee who is to be addded
    *
    * @param permanentAddressFlag a flag that says whether permanent address has been set already
    *
    * @return address a string array with address details/elements
    */
    public String[] getAddress(boolean permanentAddressFlag) {
        String address[] = new String[7];
        address[0] = "" + (permanentAddressFlag ? false : getPermanentAddressFlag());
        address[1] = getDoorNumber();
        address[2] = getStreet();
        address[3] = getLocality();
        address[5] = getDistrict();
        address[4] = getPincode();
        address[6] = getState();
        return address;
    }
	
   /**
    * Reads address details of the employee who is to be addded
    *
    * @param permanentAddressFlag a flag that says whether permanent address has been set already
    * @param employeeID Id of the employee whose address is received
    *
    * @return address a string array with address details/elements
    */
    public String[] getAddress(boolean permanentAddressFlag, String employeeID) {
        String address[] = new String[8];
        address[0] = "" + (permanentAddressFlag ? false : getPermanentAddressFlag());
        address[1] = getDoorNumber();
        address[2] = getStreet();
        address[3] = getLocality();
        address[5] = getDistrict();
        address[4] = getPincode();
        address[6] = getState();
        address[7] = employeeID;
        return address;
    }
    
	
   /**
    * Gets search modes, search input from user and displays search result
    */
    public void searchEmployee() {
        String employeeDetails = "";
        System.out.println("Enter employee ID: ");
        String employeeID = scanner.next();
        if (employeeController.validateEmployeeID(employeeID, 1)) {
            employeeDetails = employeeController.searchEmployeeID(employeeID);
        }
		
        if(employeeDetails.equals("")) {
            System.out.println("\nSearch result:\nNo result found for this search.");
        } else {
            System.out.println("\nSearch result:\n\t" + employeeDetails);
        }
    }
	
   /**
    * Gets the ID value of the employee who is to be deleted
    * Prints successful/unsuccessful completion status of the deletion
    */
    public void deleteEmployee() {
        System.out.println("\nEnter employee ID for deletion: ");
        String id = scanner.next();
        boolean success = false;
        if (employeeController.validateEmployeeID(id, 1)) {
            success = employeeController.deleteEmployee(id);
        }
        String displayMessage = (success ? "Employee deletion successful\n" : 
                                "Deletion unsuccessful. Check if employee ID is valid...\n");
        System.out.print(displayMessage);
    }
	
   /**
    * Gets the ID value of the employee who is to be restored
    * Prints successful/unsuccessful completion status of the restoration
    */
    public void restoreEmployee() {
        System.out.println("\nEnter employee ID for restoration: ");
        String id = scanner.next();
        boolean success = false;
        if (employeeController.validateEmployeeID(id, 2)) {
            success = employeeController.restoreEmployee(id);
        }
        String displayMessage = (success ? "Employee restoration successful\n" : 
                                "Restoration unsuccessful. Check whether the id is valid or "
                                + "the id is already active.\n");
        System.out.print(displayMessage);
    }
	
   /**
    *Displays details of all employees
    */
    public void displayAllEmployees() {
        List<String> employees = employeeController.getAllEmployees();
        int index = 0;
        System.out.println("\nList of all employees:\n");
        if (employees.size() < 1) {
            System.out.print("\nNo employees available now!!!\n\n");
        }
        else {
            for (String employee : employees) {
               System.out.println("\n\t"+(++index)+". "+employee);
            }
        }
    }
	
   /**
    * Provides appropriate user interface for update operation
    *
    * @param Employee ID
    */
    private void showUpdateUserInterface(String employeeID) {
        System.out.println("\n Employee ID " + employeeID
                          + " matches with record. Proceeding to updation process."
                          + "\nChoose the attribute to be updated:\n1. Name\n"
                          + "2. Email ID,\n3. Salary,\n4. Date of birth"
                          + "\n5. Update all details\n6. Update address\nEnter your option: ");
    }
	
   /**
    * Gets new value for name of the employee
    *
    * @param employeeID id of the employee whose name is to be updated
    *
    * @return a boolean that says whether name update is successful or not
    */
    private boolean updateName(String employeeID) {
        Date dateOfBirth = null;
        double salary = 0;
        String emailID = "";
        String name = getName();
        return employeeController.updateEmployeeDetails(employeeID, name, dateOfBirth, salary, emailID);
    }
	
   /**
    * Gets new value for salary of the employee
    *
    * @param employeeID id of the employee whose salary is to be updated
    *
    * @return a boolean that says whether salary update is successful or not
    */
    private boolean updateSalary(String employeeID) {
        double salary = getSalary();
        Date dateOfBirth = null;
        String emailID = "";
        String name = "";
        return employeeController.updateEmployeeDetails(employeeID, name, dateOfBirth, salary, emailID);
    }
	
   /**
    * Gets new value for DOB of the employee
    *
    * @param employeeID id of the employee whose DOB is to be updated
    *
    * @return a boolean that says whether DOB update is successful or not
    */
    private boolean updateDateOfBirth(String employeeID) {
        Date dateOfBirth = getBirthDate();
        double salary = 0;
        String emailID = "";
        String name = "";
        return employeeController.updateEmployeeDetails(employeeID, name, dateOfBirth, salary, emailID);
    }
	
   /**
    * Gets new value for email ID of the employee
    *
    * @param employeeID id of the employee whose email ID is to be updated
    *
    * @return a boolean that says whether email update is successful or not
    */
    private boolean updateEmailID(String employeeID) {
        String emailID = getEmail();
        Date dateOfBirth = null;
        double salary = 0;
        String name = "";
        return employeeController.updateEmployeeDetails(employeeID, name, dateOfBirth, salary, emailID);
    }

   /**
    * Gets all update details and forwards corresponding update request to controller
    *
    * @param employeeID id of the employee whose details are updated
    *
    * @return a boolean that denotes successful/unsuccessful completion of update process
    */
    public boolean updateAllInformation(String employeeID) {
        String name = getName();
        double salary = getSalary();
        String emailID = getEmail();
        Date dateOfBirth = getBirthDate();
        boolean success = employeeController.updateEmployeeDetails(employeeID, name, dateOfBirth, salary, emailID);
        return success;
    }

   /**
    * Makes a request for the list of addresses associated with the given employee
    *
    * @param employeeID id of the employee whose address list is to be fetched
    *
    * @return a list of String arrays containing address details of the employee
    */
    public List<String[]> getEmployeeAddressList(String employeeID) {
        return employeeController.getEmployeeAddressList(employeeID);
    }
	
   /**
    * Gets update details for an existing address of the employee
    *
    * @param employeeID id of the employee whose address details are updated
    *
    * @return a boolean that denotes successful/unsuccessful completion of update process
    */
    public boolean updateExistingAddress(String employeeID) {
        int index = 0;
        boolean success = false;
        List<String[]> addressList = getEmployeeAddressList(employeeID);
        for (String[] address : addressList) {
            System.out.println("\n" + (++index) + "\t" + address[1] + "\t" + address[2]
                              + "\t" + address[3] + "\t" + address[4] + "\t" + address[5]
                              + "\t" + address[6] + "\t"+ address[7]);
        }
        System.out.print("\nEnter address serial number for update: ");
        int addressNumber = scanner.nextInt();
        while (addressNumber > addressList.size()) {
            System.out.print("\nInvalid input!\nEnter a valid address serial number for update: ");
            addressNumber = scanner.nextInt();
        }
        String addressDetails[] = addressList.get(addressNumber-1);
        int addressID = Integer.parseInt(addressDetails[0]);
        String doorNumber = getDoorNumber();
        String street = getStreet();
        String locality = getLocality();
        String district = getDistrict();
        String pincode = getPincode();
        String state = getState();
        success = employeeController.updateExistingAddress(addressID, doorNumber,
                                                           street, locality, pincode,
                                                           district, state, employeeID);
        return success;
    }
	
   /**
    * Displays the address list of the employee and asks the user to select the
    * address that they want to remove
    *
    * @param employeeID id of the employee whose address is to be deleted
    *
    * @return a boolean that denotes successful/unsuccessful deeltion of selected address
    */
    public boolean deleteAddress(String employeeID) {
        int index = 0;
        boolean success = false;
        List<String[]> addressList = getEmployeeAddressList(employeeID);
        for (String[] address : addressList) {
            System.out.println("\n" + (++index) + "\t" + address[1] + "\t" + address[2]
                              + "\t" + address[3] + "\t" + address[4] + "\t" + address[5]
                              + "\t" + address[6] + "\t"+ address[7]);
        }
        System.out.print("\nEnter address serial number for update: ");
        int addressNumber = scanner.nextInt();
        while (addressNumber > addressList.size()) {
            System.out.print("\nInvalid input!\nEnter a valid address serial number for update: ");
            addressNumber = scanner.nextInt();
        }
        String addressDetails[] = addressList.get(addressNumber-1);
        int addressID = Integer.parseInt(addressDetails[0]);
        if ("true".equals(addressDetails[1])) {
            System.out.print("\nYou can't delete a permanent address.");
        } else {
            success = employeeController.deleteAddress(addressID, employeeID);
        }
        return success;
    }
	
   /**
    * Displays the address list of the employee and asks the user to select the
    * address that they want to mark as permanent address
    *
    * @param employeeID id of the employee whose permanent address is to be updated
    *
    * @return a boolean that denotes successful/unsuccessful completion of update process
    */
    public boolean updatePermanentAddress(String employeeID) {
        int index = 0;
        boolean success = false;
        int oldPermanentAddress = -1;
        List<String[]> addressList = getEmployeeAddressList(employeeID);
        for (String[] address : addressList) {
            System.out.println("\n" + (++index) + "\t" + address[1] + "\t" + address[2]
                              + "\t" + address[3] + "\t" + address[4] + "\t" + address[5]
                              + "\t" + address[6] + "\t"+ address[7]);
            if("true".equals(address[1])) {
                oldPermanentAddress = Integer.parseInt(address[0]);
            }
        }
        System.out.println("\nEnter the address serial number that is to be fixed "
                        + "as new permanent address: ");
        int addressNumber = scanner.nextInt();
        while (addressNumber > addressList.size()) {
            System.out.print("\nInvalid input!\nEnter a valid address serial number for update: ");
            addressNumber = scanner.nextInt();
        }
        String addressDetails[] = addressList.get(addressNumber-1);
        int newPermanentAddress = Integer.parseInt(addressDetails[0]);
        if (oldPermanentAddress == newPermanentAddress) {
            System.out.print("\nThis address " + newPermanentAddress +" is already marked as "
                            + "permanent address.");
        } else {
            success = employeeController.updatePermanentAddress(oldPermanentAddress, 
                                                                newPermanentAddress, employeeID);
        }
        return success;
    }
	
   /**
    * Gets details of the new address to be added
    *
    * @param employeeID id of the employee whose address details are added
    *
    * @return a boolean that denotes successful/unsuccessful addition of address
    */
    public boolean addNewAddress(String employeeID) {
        String addressDetails[] = getAddress(true, employeeID);
        return employeeController.addNewAddress(employeeID, addressDetails);
    }
	
   /**
    * Gets the mode of address update and makes corresponding calls to individual
    * update methods.
    *
    * @param employeeID id of the employee whose address details are updated
    *
    * @return a boolean that denotes successful/unsuccessful completion of update process
    */
    public boolean updateAddress(String employeeID) {
        String displayMessage = "Operations:\n1. Update an existing address, "
                         + "\n2. Delete an existing address\n3. Update permanent address"
                         + "\n4. Add new address\nEnter an operation: ";
        System.out.println(displayMessage);
        int option = scanner.nextInt();
        boolean success = false;
        switch (option) {
            case 1:
                success = updateExistingAddress(employeeID);
                break;
            case 2:
                success = deleteAddress(employeeID);
                break;
            case 3:
                success = updatePermanentAddress(employeeID);
                break;
            case 4:
                success = addNewAddress(employeeID);
                break;
            default:
                System.out.print("\nWrong option...");
                success = false;
        }
        return success;
    }
	
   /**
    * Gets mode of update from user and makes corresponding calls
    *
    * @param employeeID Employee ID of the employee whose details are to be updated
    *
    * @return success boolean that denotes successful/unsuccessful completion of update
    */
    private boolean performUpdateOperation(String employeeID) {
        boolean success = false;
        showUpdateUserInterface(employeeID);
        int attributeId = scanner.nextInt();
        switch (attributeId) {
            case 1:
                success = updateName(employeeID);
                break;
            case 2:
                success = updateEmailID(employeeID);
                break;
            case 3:
                success = updateSalary(employeeID);
                break;
            case 4:
                success = updateDateOfBirth(employeeID);
                break;
            case 5:
                success = updateAllInformation(employeeID);
                break;
            case 6:
                success = updateAddress(employeeID);
                break;
            default:
                System.out.print("\nWrong option!!");
                success = false;
                break;
        }
        return success;
    }

   /**
    * Gets ID of the employee whose details are to be updated
    */
    private void updateEmployee() {
        boolean success = false;
        System.out.println("\nEnter the employee's ID that needs update: ");
        String employeeID = scanner.next();
        if (employeeController.validateEmployeeID(employeeID, 1)) {
           if (performUpdateOperation(employeeID)) {
               System.out.println("Detail(s) updated successfully.");
            } 
            else {
               System.out.print("\nCouldn't update details\n");
          }
        } 
        else {
            System.out.println("\nEmployee ID unavailable. Cannot update.\n");
        }
    }
	
   /**
    * Provides appropriate user interface for assignment management
    *
    * @param projectID id of the project which is to be updated
    */
    private void showAssignmentInterface(String employeeID) {
        System.out.println("\n Employee ID " + employeeID
                          + " matches with record. You can manage project assignments for this employee."
                          + "\nOperations available:\n1. Assign project\n"
                          + "2. Unassign project,\n3. Show assigned projects"
                          + "\nEnter your option: ");
    }
	
   /**
    * Receives new project assignment information for the given employee
    *
    * @param employeeID id of the employee for which project is assigned
    */
    public void assignProject(String employeeID) {
        boolean success = false;
        Set<Integer> projectIdSet = new LinkedHashSet<Integer>();
        int projectID = 0;
        int option = 1;
        int count = 0;
        do {
            System.out.print("\nEnter a project ID for assignment: ");
            projectID = scanner.nextInt();
            projectIdSet.add(projectID);
            System.out.print("\nDo you want to add another project?(1-> yes, any number -> no): ");
            option = scanner.nextInt();
        } while(1 == option);
        List<Integer> unassignableIds = employeeController.assignProject(employeeID, projectIdSet);
        if (0 < unassignableIds.size()) {
            System.out.print("\n\n" + unassignableIds.size() + " out of " + projectIdSet.size()
                            + " assignments failed. \n");
            for (Integer number : unassignableIds) {
                System.out.print("\n" + (++count) + ". " + number);
            }
        } else {
            System.out.print("\nProject assignment(s) successful");
            success = true;
        }
        String message = (success ? "Assignment successful\n" : "Assignment couldn't be done. Please check"
                                                              + "\n1. whether project ID is valid,"
                                                              + "\n2. whether the project has been assigned already\n");
        System.out.println("\n" + message);
    }
	
   /**
    * Receives project unassign information for the given employee
    *
    * @param employeeID id of the employee who requires project unassignment
    */
    public void unassignProject(String employeeID) {
        List<String> assignedProjectDetails = employeeController.getAssignedProjects(employeeID);
        int count = 0;
        System.out.print("\nList of assigned Project IDs:\n");
        for (String projectDetails : assignedProjectDetails) {
            System.out.println("\n" + (++count) + ". " + projectDetails);
        }
        if(0 < assignedProjectDetails.size()) {
            System.out.print("\nEnter the project ID that you want to unassign: ");
		    int projectID = scanner.nextInt();
            boolean success = employeeController.unassignProject(employeeID, projectID);
            String resultNotification = (success ? "Unassignment successful\n" : 
                                        "Unassignment couldn't succeed. Please Check"
                                        + "\n1. whether the ID is valid\n2. whether it's assigned to the employee already");
            System.out.println("\n" + resultNotification);
        } else {
            System.out.print("\nNo project assigned yet. Unassign operation is impossible now.");
        }
    }
	
   /**
    * Displays all projects assigned to this employee
    *
    * @param projectID id of the project for which assigned employees are displayed
    */
    public void showAssignedProjects(String employeeID) {
        List<String> assignedProjects = employeeController.getAssignedProjects(employeeID);
        int count = 0;
        if (assignedProjects.size() > 0) {
            System.out.print("\nList of Assigned projects: ");
            for (String projectDetails : assignedProjects) {
                System.out.println("\n\t" + (++count) + ". " + projectDetails);
            }
        } else {
            System.out.println("\nNo project assigned for this employee.");
        }
    }
	
   /**
    * Gets operation choice from user and makes corresponding calls
    *
    * @param employeeID id of the employee whose project assignments are manipulated
    */
    private void handleAssignment(String employeeID) {
        showAssignmentInterface(employeeID);
        int operationID = scanner.nextInt();
        switch (operationID) {
            case 1:
                assignProject(employeeID);
                break;
            case 2:
                unassignProject(employeeID);
                break;
            case 3:
                showAssignedProjects(employeeID);
                break;
            default:
                System.out.print("\nWrong option!!");
                break;
        }
    }
	
   /**
    * Gets ID of the employee whose project assignments are managed
    */
    private void manageProjectAssignment() {
        boolean success = false;
        System.out.println("\nEnter the employee ID: ");
        String employeeID = scanner.next();
        if (employeeController.validateEmployeeID(employeeID, 1)) {
            handleAssignment(employeeID);
        } else {
            System.out.print("\nThis employee ID doesn't exist. Check ID validity before assignment.");
        }
    }
	
   /**
    * Overseer method that acts as an intermediate between user interaction methods & CRUD operation methods
    * Also the origin of control flow for all operations being performed
    */
    public void doEmployeeCRUDOperations() {
        int option = 1;
        int operation = 0;
        String operationsMenu = "Operations to perform:\n1. Add Employee"
                                + "\n2. Search Employee\n3. Display all employees"
                                + "\n4. Delete an employee\n5. Update an employee\n6. Restore employee"
                                + "\n7. Manage project assignments\n\nEnter an operation: ";
        do {
            System.out.println(operationsMenu);
            operation = scanner.nextInt();
            switch (operation) {
                case 1:
                    addEmployee();
                    break;		
                case 2:
                    searchEmployee();
                    break;
                case 3:
                    displayAllEmployees();
                    break;		
                case 4:
                    deleteEmployee();
                    break;		
                case 5:
                    updateEmployee();				
                    break;
                case 6:
                    restoreEmployee();
                    break;
                 case 7:
                    manageProjectAssignment();
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