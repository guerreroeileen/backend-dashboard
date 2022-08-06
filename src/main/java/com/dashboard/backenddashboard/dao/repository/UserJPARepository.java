package com.dashboard.backenddashboard.dao.repository;

import com.dashboard.backenddashboard.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJPARepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);
}
