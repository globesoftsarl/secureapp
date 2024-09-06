package com.globesoft.secureappspring.controller;

import com.globesoft.secureappspring.entities.AccountUserEntity;
import com.globesoft.secureappspring.service.AccountUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class AccountUserController {

    private final AccountUserService accountUserService;
    // j'initialise le controller avec la classe de AccountUserService
    public AccountUserController(AccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    @GetMapping
    public List<AccountUserEntity> listeDesUtilisateurs() {

        return accountUserService.listOfAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountUserEntity> recupererUnUtilisateurParId(@PathVariable Long id) {
        Optional<AccountUserEntity> user = accountUserService.oneUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AccountUserEntity creerUtilisateur(@RequestBody AccountUserEntity user) {
        return accountUserService.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountUserEntity> modifierUnUtilisateur(@PathVariable Long id, @RequestBody AccountUserEntity userDetails) {
        return ResponseEntity.ok(accountUserService.modifierLesInfosDunUser(id, userDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        accountUserService.enleverUnUser(id);
        // Je retourne une réponse vide car c'est un delete
        return ResponseEntity.noContent().build();
    }
}

