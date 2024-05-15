package progettoispw.letmeknow.controller;

import progettoispw.letmeknow.WordCheck;
import progettoispw.letmeknow.controller.usruser.UsrUser;

public class InnerUsers {
    private final String userid;
    private String description;
    private String goal;
    private int lenMax;
    private WordCheck check=new WordCheck();
    public InnerUsers(UsrUser elem){
        lenMax=15;
        userid=elem.getUserid();
        description= elem.getDescript();
        goal=elem.getTag();
    }
    public String getUserid() {
        return userid;
    }
    public String getDescription() {
        return check.checkLen(description,lenMax);
    }
    public String getGoal() {
        return check.checkLen(goal,lenMax);
    }
}
