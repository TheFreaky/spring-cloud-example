package ru.kpfu.itis.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
