package progettoispw.letmeknow.controller.user;


import progettoispw.letmeknow.controller.User;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InitialUser implements User {
    protected String userid;
    private String password;
    private String type;
    private String email;
    private UserDAO userData;
    private Random randomno ;

    private static final  Lock mutex = new ReentrantLock(true);
    private boolean errorOccurred;
    public InitialUser(String who)  {
        if(who !=null && who.length()==7 ){
        userData=new UserDAO();
        String [] log=userData.selectUser(who);
        if(log[0]!=null && log[1]!=null){
            userid=log[0];
            password=log[1];
            type=log[2];
            email=log[3];
        }
        }
        else if(who==null){
            new InitialUser();
        }
    }
    public InitialUser(){
        userid=password=type=null;
        randomno = new Random();
    }
    public boolean  checkEmail(String input){
        UserDAO userDataInner=new UserDAO();
        errorOccurred=(userDataInner.checkMail(input));
        return errorOccurred;
    }
    public void composeMail(String to) {
        UserDAO userDataInner=new UserDAO();
        String []data = userDataInner.recover(to);
        if(data[0]!=null && data[1]!=null){
            JavaMailUtil host=new JavaMailUtil();
            String text="Your userid is .:     "+data[0]+"Your password is .:        "+data[1];
            host.setText(text);
            errorOccurred=!(host.sendMail(to));
        }else{
            errorOccurred=true;
        }
    }
    public void composeMail(String uid, String password, String mail) {
        JavaMailUtil host=new JavaMailUtil();
        String text="Your userid is .:     "+uid+"Your password is .:        "+password;
        host.setText(text);
        errorOccurred=!(host.sendMail(mail));
    }
    private String getUid(){
        int random;
        int min=1000000;
        int max=9999999;
        String check=null;
        boolean equal=true;
        UserDAO userDataInner=new UserDAO();
        mutex.lock();
        List<String> uidList=userDataInner.getUID();
        if(uidList==null)return "0000000";
        while(equal){
            if(randomno==null)randomno=new Random();
            random= (randomno.nextInt(max));
            equal=false;
            if(random<min-1)equal=true;
            check=""+random;
            if(!equal)for(String uid : uidList){
                if(check.equals(uid)){
                    equal=true;
                }
            }
        }
        mutex.unlock();
        return check;
    }
    @Override
    public String getType() {
        return type;
    }
    @Override
    public boolean confirmCredentials(String password) {
        if(password!=null){
            return this.password.equals(password);
        }
        return false;
    }
    @Override
    public boolean isErrorOccurred() {
        return errorOccurred;
    }

    @Override
    public void setErrorOccurred(boolean errorOccurred) {
        this.errorOccurred=errorOccurred;
    }

    @Override
    public String getUserid(){
        return userid;
    }
    public void setPassword(String input){
        errorOccurred=!userData.setPswd(userid,input);
        if(!errorOccurred){
            composeMail(email);
        }
    }
    public void setEmail(String input){
        if(userData.setEmail(userid,input)){
            email=input;
            composeMail(email);
        }
    }
    public void registrationUSR(String password,String email,String type,int [] val,String description,String goal)  {
        UserDAO userDataInner=new UserDAO();
        boolean bool;
        String uid=getUid();
        bool= userDataInner.registration(uid,password,type,val,description,email,goal);
        if(bool)composeMail(uid,password,email);
    }
    public void registrationPSY(String password, String email, String type) {
        UserDAO userDataInner=new UserDAO();
        boolean bool;
        String uid=getUid();
        bool= userDataInner.registration(uid,password,type,email);
        if(bool)composeMail(uid,password,email);
    }
    public void setFeed(String feed){
        if(!userData.feed(userid,feed)){
            errorOccurred=true;
        }
    }



}