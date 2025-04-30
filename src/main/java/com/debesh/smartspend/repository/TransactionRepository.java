package com.debesh.smartspend.repository;

import com.debesh.smartspend.entity.Account;
import com.debesh.smartspend.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByWallet(Account account);
}

