package ru.mirea.edu.drivingschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.edu.drivingschool.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

}
