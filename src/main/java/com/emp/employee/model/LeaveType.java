//package com.emp.employee.model;
//
//import lombok.Data;
//import org.bson.types.ObjectId;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Data
//@Document(collection = "leave_type")
//public class LeaveType {
//    @Id
//    private String id;
//    private String name;
//
//    public LeaveType() {
//        this.id= new ObjectId().toString();
//    }
//
//    public LeaveType(String name) {
//        this.id = new ObjectId().toString();
//        this.name = name;
//    }
//}
