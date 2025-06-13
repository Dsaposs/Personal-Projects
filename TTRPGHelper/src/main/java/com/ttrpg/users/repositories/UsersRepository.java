package com.ttrpg.users.repositories;

import com.ttrpg.users.repositories.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User,Integer> {
}
