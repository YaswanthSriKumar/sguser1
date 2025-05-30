package com.user.sguser.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.UUID;

@Entity
@Table(name = "sguser")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.CHAR) // This tells Hibernate to treat UUID as a CHAR(36)
    @Column(name = "customer_id", length = 36, nullable = false, updatable = false)
    private UUID customerId;
    private String selectedType;
    private int selectedTypeId;
    private String customerName;
    private String customerContact;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
                ", status='" + status + '\'' +
                '}';
    }
}
