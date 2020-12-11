package jingzhou.repository;

import jingzhou.MySQLTable.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findMessagesBySender(String sender);
}
