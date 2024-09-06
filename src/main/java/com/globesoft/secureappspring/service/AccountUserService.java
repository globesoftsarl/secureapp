package com.globesoft.secureappspring.service;

import com.globesoft.secureappspring.dao.AccountUserRepository;
import com.globesoft.secureappspring.entities.AccountUserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountUserService {

    private final AccountUserRepository accountUserRepository;

    public AccountUserService(AccountUserRepository accountUserRepository) {
        this.accountUserRepository = accountUserRepository;
    }

    public List<AccountUserEntity> listOfAllUsers() {

        return accountUserRepository.findAll();
    }

    public Optional<AccountUserEntity> oneUserById(Long id) {
        // j'utilise Optional parce que le resulat peut etre null
        return accountUserRepository.findById(id);
    }

    public AccountUserEntity createUser(AccountUserEntity user) {
        return accountUserRepository.save(user);
    }

    public AccountUserEntity modifierLesInfosDunUser(Long id, AccountUserEntity userDetails) {
        return accountUserRepository.findById(id)
                .map(user -> {
                    user.setEmail(userDetails.getEmail());
                    user.setPassword(userDetails.getPassword());
                    user.setState(userDetails.isState());
                    return accountUserRepository.save(user);
                })
                .orElseGet(() -> {
                    userDetails.setId(id);
                    return accountUserRepository.save(userDetails);
                });
    }

    public void enleverUnUser(Long id) {
        accountUserRepository.deleteById(id);
    }
}
