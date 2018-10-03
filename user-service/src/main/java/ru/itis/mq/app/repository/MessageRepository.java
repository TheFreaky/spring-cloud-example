package ru.itis.mq.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.mq.app.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
