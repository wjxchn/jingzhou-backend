package jingzhou.Service;

import jingzhou.MySQLTable.Message;
import jingzhou.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public void insertMessage(Message message){
        messageRepository.save(message);
    }
    public void insertMessageList(List<Message>messageList){
        messageRepository.saveAll(messageList);
    }

    public List<Message> getMessagesByReceiver(String receiver){
     return messageRepository.findMessageByReceiverusername(receiver);
    }
}
