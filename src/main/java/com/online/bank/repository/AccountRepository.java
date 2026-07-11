package com.online.bank.repository;

import com.online.bank.entity.Account;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);
    boolean existsByMobile(String mobile);
    @Nonnull
    Page<Account> findAll(@Nonnull Pageable pageable);
}