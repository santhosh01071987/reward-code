package com.rewards.model;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {

    
    @Id
    @Column(name="cid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;  // Unique identifier for each customer

    @Column(name="NAME")
    private String name;  // Customer's name
    @Column(name="EMAIL")
    private String email;  // Customer's email
    @Column(name="PHONE")
    private String phone;  // Customer's phone number
   
	public Customer() { }

	public Customer(Long cid, String name, String email, String phone, Integer rewardPoints, List<Transaction> transactions) {
		super();
		this.cid = cid;
		this.name = name;
		this.email = email;
		this.phone = phone;

	}

	
	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
