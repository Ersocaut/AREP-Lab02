package services;

import model.Message;

import java.util.List;

public interface IntMessageService {

    public void postMessage(Message message);

    public List<String> getAllMessages();
}
