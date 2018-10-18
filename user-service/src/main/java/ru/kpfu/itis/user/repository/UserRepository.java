package ru.kpfu.itis.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
