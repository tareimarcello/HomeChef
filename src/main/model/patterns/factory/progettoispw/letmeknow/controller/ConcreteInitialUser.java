package progettoispw.letmeknow.controller;

import progettoispw.letmeknow.controller.user.InitialUser;

public class ConcreteInitialUser {
    static InitialUser user;
    private ConcreteInitialUser (){
        reset();
    }
    public static void reset(){
        user=null;
    }
    public static void setAll(String userid){
        user=new InitialUser(userid);
    }
    public static InitialUser getUser() {
        return user;
    }
}
