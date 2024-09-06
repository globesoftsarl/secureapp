package com.globesoft.secureappspring.dao;

import com.globesoft.secureappspring.entities.AccountUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountUserRepository extends JpaRepository<AccountUserEntity, Long> {
}
