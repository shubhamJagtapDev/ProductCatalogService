package com.jshubhamstore.productservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseModel {
    @Id
    // TODO: Add custom logic to generate UUIDs independently
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO Increment the id
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private State state; // To support soft delete

    public BaseModel() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.state = State.ACTIVE;
    }
}