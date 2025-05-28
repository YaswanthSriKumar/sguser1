package com.user.sguser.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID customerId;
    private String selectedType;
    private int selectedTypeId;
    private String customerName;
    private String customerContact;


    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public int getSelectedTypeId() {
        return selectedTypeId;
    }

    public void setSelectedTypeId(int selectedTypeId) {
        this.selectedTypeId = selectedTypeId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "customerId=" + customerId +
                ", selectedType='" + selectedType + '\'' +
                ", selectedTypeId=" + selectedTypeId +
                ", customerName='" + customerName + '\'' +
                ", customerContact='" + customerContact + '\'' +
                '}';
    }
}
