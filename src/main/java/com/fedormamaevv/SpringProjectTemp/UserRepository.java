package com.fedormamaevv.SpringProjectTemp;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
