package com.emp.employee.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "address")
public class Address {
    @Id
    private String id;
    private String country;
    private String city;
    private int zipCode;
    private String province;

    public Address() {

        this.id = new ObjectId().toString();
    }

    public Address(String country, String city, String province) {
        this.id= new ObjectId().toString();
        this.country = country;
        this.city = city;
        this.province = province;
    }
}
