package com.debesh.smartspend.repository;

import com.debesh.smartspend.entity.Account;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    @Query("select acc from Account acc join acc.user u where acc.id=?1")
    List<Account> findAllByUserId(Long userId);

    Optional<Account> findByAccNumber(String accNumber);
}
