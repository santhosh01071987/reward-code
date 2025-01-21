package com.rewards.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.model.RewardsOfCustomer;
import com.rewards.repository.RewardCalculationService;

@RestController
@RequestMapping("/api/rewards")
public class RewardCaluculationController {

    private static final Logger logger = LoggerFactory.getLogger(RewardCaluculationController.class);

    @Autowired
    private RewardCalculationService rewardCalculationService;

    @GetMapping("/{fromDate}/{toDate}")
    public ResponseEntity<?> getCustomerRewardsHistory(@PathVariable String fromDate,
                                                       @PathVariable String toDate) {
        try {
            // Validate date format before calling the service
            if (!isValidDateFormat(fromDate) || !isValidDateFormat(toDate)) {
                return ResponseEntity.badRequest().body("Invalid date format. Please use 'yyyy-MM-dd'.");
            }

            // Calculate the customer rewards based on the given date range
            List<RewardsOfCustomer> rewardsOfCustomerList = rewardCalculationService.calculateCustomerRewards(fromDate, toDate);

            if (rewardsOfCustomerList.isEmpty()) {
                return ResponseEntity.noContent().build(); 
            }
            return ResponseEntity.ok(rewardsOfCustomerList); 

        } catch (IllegalArgumentException e) {
            logger.error("Invalid date format: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Invalid date format: " + e.getMessage());  
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            return ResponseEntity.status(500).body("Unexpected error occurred."); 
        }
    }

    // Helper method to validate date format
    private boolean isValidDateFormat(String date) {
        try {
            // Try parsing the date to ensure it's in the correct format
            java.time.LocalDate.parse(date, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
