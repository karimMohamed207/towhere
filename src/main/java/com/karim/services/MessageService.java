package com.karim.services;

import com.karim.database.MessageRepo;
import com.karim.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepo repo ;

    public List<Message> getMessages(){
        return repo.findAll();
    }

    public Message getMessage(long id){
        return repo.findById(id).get();
    }

    public Message addMessage(Message message){
        return repo.save(message);
    }

    public void deleteMessage(long id){
        repo.deleteById(id);
    }

    public void deleteAll(){
        repo.deleteAll();
    }


}
