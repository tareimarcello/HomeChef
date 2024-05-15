package progettoispw.letmeknow.controller;

import progettoispw.letmeknow.WordCheck;
import progettoispw.letmeknow.controller.chat.Message;

public class LastMessage {
    private String userid;
    private String lastmsg;
    private WordCheck check;
    private int lenMax=40;
    public LastMessage(String elem, Message message){
        userid=elem;
        check=new WordCheck();
        lastmsg=message.getText();
    }
    public String getUserid() {
        return userid;
    }
    public String getText() {
        lastmsg=check.checkLen(lastmsg,lenMax);
        return lastmsg;
    }
}
