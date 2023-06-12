package com.acorn_mentor.acorn_mentor.repository;

import com.acorn_mentor.acorn_mentor.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
