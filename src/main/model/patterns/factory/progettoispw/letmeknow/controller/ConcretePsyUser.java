package progettoispw.letmeknow.controller;

import progettoispw.letmeknow.controller.psyuser.PsyUser;
import progettoispw.letmeknow.controller.user.InitialUser;

public class ConcretePsyUser {
    static PsyUser psyUser;
    private ConcretePsyUser(){
       reset();
    }
    public static void reset(){
        psyUser =null;
    }
    public static boolean checkCredentials(String userid,String password){
        reset();
        boolean bool;
        InitialUser userInner;
        userInner=new InitialUser(userid);
        psyUser =new PsyUser(userid);
        bool=userInner.confirmCredentials(password);
        if(bool)bool= psyUser.getType().equals(userInner.getType());
        if(bool){
            setAll(userid);
        }else{
            reset();
        }
        return bool;
    }
    public static void setAll(String userid){
        reset();
        psyUser =new PsyUser(userid);
    }
    public static PsyUser getPsyUser(){ return psyUser;}
}
