//package com.emp.user.model;
//
//import lombok.Data;
//import org.bson.types.ObjectId;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Data
//@Document(collection = "role")
//public class Role {
//
//    @Id
//    private String id;
//    private String name;
//    public Role(String name) {
//        this.id = new ObjectId().toString();
//        this.name = name;
//    }
//
//    public Role() {
//        this.id = new ObjectId().toString();
//    }
//
//
//}
