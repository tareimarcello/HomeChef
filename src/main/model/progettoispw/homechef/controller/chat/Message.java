package progettoispw.letmeknow.controller.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String reciver;
    private String sender;
    private String text;
    private LocalDateTime date;
    //serve per distinguere ogni messaggio,
    // per perettere messaggi dallo stesso reciver||sender con stesso testo
    public String getText() {
        return text;
    }
    public String getReciver() {
        return reciver;
    }
    public void setReciver(String reciver) {
        this.reciver = reciver;
    }
    public void setDate(String dateSTR) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        date = LocalDateTime.parse(dateSTR, formatter);
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getSender() {
        return sender;
    }
    public LocalDateTime getDate(){
        return date;
    }
}
