package com.rewards.repository;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewards.model.Customer;
import com.rewards.model.RewardsOfCustomer;
import com.rewards.model.Transaction;

@Service
public class RewardCalculationService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<RewardsOfCustomer> calculateCustomerRewards(String fromDateStr, String toDateStr) {
        LocalDate fromDate = null;
        LocalDate toDate = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            List<Customer> customerList = customerRepository.findAll();
            fromDate = LocalDate.parse(fromDateStr, formatter);
            toDate = LocalDate.parse(toDateStr, formatter);

            List<RewardsOfCustomer> rewardsOfCustomerList = new ArrayList<>();

            for (Customer customer : customerList) {
                Map<Month, Double> monthlyRewards = new HashMap<>();  // Move map initialization here

                RewardsOfCustomer rewardsOfCustomer = new RewardsOfCustomer();
                rewardsOfCustomer.setCustomerId(customer.getCid());
                rewardsOfCustomer.setCustomerMailId(customer.getEmail());
                rewardsOfCustomer.setCustomerPhoneNumber(customer.getPhone());

                // Retrieve transactions for the customer in the specified date range
                List<Transaction> transactions = transactionRepository.findTransactionsByCustomerAndDateRange(
                        customer.getCid(),
                        fromDate, // pass LocalDate directly
                        toDate     // pass LocalDate directly
                );

                // Loop through transactions and calculate reward points
                for (Transaction transaction : transactions) {
                    double rewardPoints = calculateRewardPoints(transaction.getAmountSpent());

                    // Get the month of the transaction
                    Month month = transaction.getTransactionDate().toLocalDateTime().getMonth();

                    // Update monthly reward points in the map
                    monthlyRewards.put(month, monthlyRewards.getOrDefault(month, 0.0) + rewardPoints);
                }

                // Now set the rewards for the customer
                int i = 0;
                Double totalRewards = 0.0;
                for (Entry<Month, Double> entry : monthlyRewards.entrySet()) {
                    i++;
                    Double rewards = entry.getValue();
                    totalRewards += rewards;
                    if (i == 1) {
                        rewardsOfCustomer.setFirstMonthRewards(rewards);
                    }
                    if (i == 2) {
                        rewardsOfCustomer.setSecondMonthRewards(rewards);
                    }
                    if (i == 3) {
                        rewardsOfCustomer.setThirdMonthRewards(rewards);
                    }
                    rewardsOfCustomer.setThreeMonthsRewards(totalRewards);
                }

                rewardsOfCustomerList.add(rewardsOfCustomer); // Add to the list after processing a customer
            }

            return rewardsOfCustomerList;

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + fromDateStr, e);
        }
    }

    private double calculateRewardPoints(double amountSpent) {
        double points = 0;

        if (amountSpent > 100) {
            points += (amountSpent - 100) * 2;
            amountSpent = 100;
        }

        if (amountSpent >= 50) {
            points += (amountSpent - 50);
        }

        return points;
    }
}
