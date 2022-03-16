package model;

import java.util.Date;
import java.util.UUID;

public class Message {
    private String id;
    private String text;
    private String date;

    public Message (){};

    public Message(String text){
        this.text = text;
        this.id = UUID.randomUUID().toString();
        this.date = new Date().toString();
    }

    public String getId() {return id;}

    public String getText() {return text;}

    public String getDate() {return date;}

}
