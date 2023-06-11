package com.emp.constants;

public enum UserRoles {

        ADMIN("ADMIN"),
    MANAGER("MANAGER");

    private final String name;

    private UserRoles(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

}
