package com.emp.constants;

public enum EmployeeRoles {

    EMPLOYEE_ADMIN("Employee Admin"),
    EMPLOYEE_MANAGER("Employee Manager");

    private final String name;

    private EmployeeRoles(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }


}
