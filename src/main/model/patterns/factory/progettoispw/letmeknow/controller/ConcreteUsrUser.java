package progettoispw.letmeknow.controller;

import progettoispw.letmeknow.controller.chat.Messages;
import progettoispw.letmeknow.controller.form.ResultForm;
import progettoispw.letmeknow.controller.search.Search;
import progettoispw.letmeknow.controller.user.InitialUser;
import progettoispw.letmeknow.controller.usruser.UsrUser;

public class ConcreteUsrUser {
    static UsrUser usrUser;
    static Search search;
    static Messages  chat;
    static ResultForm form;
    private ConcreteUsrUser(){
        reset();
    }
    public static void reset() {
        usrUser =null;
        search = null;
        chat = null;
        form = null;
    }
    public static boolean checkCredentials(String userid,String password){
        reset();
        boolean bool;
        InitialUser userInner;
        userInner=new InitialUser(userid);
        usrUser =new UsrUser(userid);
        bool=userInner.confirmCredentials(password);
        if(bool)bool= usrUser.getType().equals(userInner.getType());
        if(bool){
            setAll(userid);
        }else{
            reset();
        }
        return bool;
    }
    public static void setAll(String userid){
        reset();
        usrUser = new UsrUser(userid);
        search=new Search(userid);
        chat=new Messages(userid);
    }
    public static UsrUser getUsrUser(){ return usrUser;}
    public static Search getSearch(){
        return search;
    }
    public static Messages getChat(){
        return chat;
    }
    public static void setResultForm(){
        form=new ResultForm(usrUser.getUserid());
    }
    public static void setResultForm(int formid){
        form=new ResultForm(usrUser.getUserid(),formid);
    }
    public static ResultForm getResultForm(){
        return form;
    }
}
