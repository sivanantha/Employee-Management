/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */

import com.ideas2it.employeemanagement.view.EmployeeView;

/**
 * The EmployeeManagement class contains CRUD implementations for employee 
 * management system. An employee can have following details. A unique employee
 * id, name, date of birth, a unique mobile number and email, salary, date of
 * joining. Once a Employee id created it cannot be changed.
 *
 * @author  Sivanantham
 * @version 1.4
 */
public class EmployeeManagement {
    
    public static void main(String[] args) {
        EmployeeView employeeView = new EmployeeView();
        employeeView.showWelcomeMessage();
    }
}
