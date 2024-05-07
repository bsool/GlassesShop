package com.bsool.glasses.glassesshop.data.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntityUserRepository extends JpaRepository<EntityUser, Long> {

    Optional<EntityUser> findByUsernameIgnoreCaseAndPassword(String username, String password);
}