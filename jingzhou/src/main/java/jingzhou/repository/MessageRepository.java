package jingzhou.repository;

import jingzhou.MySQLTable.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findMessageByReceiverid(int receiverid);
}
