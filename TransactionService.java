package com.rewards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rewards.model.Customer;
import com.rewards.model.Transaction;
import com.rewards.repository.CustomerRepository;
import com.rewards.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Transactional
	public Transaction createTransaction(Transaction transaction) {
		Customer customer = transaction.getCustomer();
		if (customer.getCid() != null && customerRepository.existsById(customer.getCid())) {
			transaction.setRewards(calculateRewardPoints(transaction.getAmountSpent()));
			return transactionRepository.save(transaction);
		} else {
			throw new IllegalArgumentException("Customer with the given ID does not exist.");
		}
	}

	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();

	}

	public int calculateRewardPoints(double amountSpent) {
		int points = 0;
		if (amountSpent > 100) {
			points += (amountSpent - 100) * 2;
			points += 50;
		} else if (amountSpent >= 50) {
			points += (amountSpent - 50);
		}

		return points;
	}

}
