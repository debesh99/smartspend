package com.debesh.smartspend.repository;

import com.debesh.smartspend.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select acc from Account acc join acc.customer c where acc.id=?1")
    List<Account> findAllByCustomerId(Long CustomerId);

    Optional<Account> findByAccNumber(String accNumber);
}
