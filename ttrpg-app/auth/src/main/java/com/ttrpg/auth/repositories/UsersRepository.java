package com.ttrpg.auth.repositories;

import com.ttrpg.auth.repositories.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User,Integer> {
}
