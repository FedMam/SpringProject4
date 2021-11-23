package com.fedormamaevv.SpringProjectTemp;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByAgeBetween(Long startAge, Long endAge);
    List<User> findByAgeBetween(Long startAge, Long endAge, Sort sort);
}
