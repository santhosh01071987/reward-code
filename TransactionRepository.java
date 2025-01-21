package com.rewards.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rewards.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.customer.cid = :customerId AND FUNCTION('DATE_FORMAT', t.transactionDate, '%Y-%m') = :month")
    List<Transaction> findTransactionsByCustomerIdAndMonth(Long customerId, String month);

    
    @Query("SELECT t FROM Transaction t WHERE t.customer.cid = :cid AND BETWEEN :startDate AND :endDate")
    List<Transaction> findTransactionsByCustomerAndDateRange(@Param("cid") Long cid, 
                                                             @Param("startDate") String startDate, 
                                                             @Param("endDate") String endDate);
    
    @Query("SELECT t FROM Transaction t WHERE t.customer.cid = :cid AND t.transactionDate BETWEEN :startDate AND :endDate")
    List<Transaction> findTransactionsByCustomerAndDateRange(
            @Param("cid") Long cid, 
            @Param("startDate") LocalDate startDate, 
            @Param("endDate") LocalDate endDate);




    }