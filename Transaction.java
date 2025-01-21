package com.rewards.model;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;
    @Column(name="AMOUNTSPENT")
    private double amountSpent; // Amount spent in the transaction
    private Timestamp transactionDate;
    @Column(name = "rewards", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int rewards;

    @ManyToOne
    @JoinColumn(name = "customer_cid")
    private Customer customer;

    public Transaction() {
		super();
	}
    
	public Transaction(Long tid, double amountSpent, Timestamp transactionDate, int rewards, Customer customer) {
		super();
		this.tid = tid;
		this.amountSpent = amountSpent;
		this.transactionDate = transactionDate;
		this.rewards = rewards;
		this.customer = customer;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public double getAmountSpent() {
		return amountSpent;
	}

	public void setAmountSpent(double amountSpent) {
		this.amountSpent = amountSpent;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getRewards() {
		return rewards;
	}

	public void setRewards(int rewards) {
		this.rewards = rewards;
	}

	

    
}
