package progettoispw.letmeknow.controller;

import progettoispw.letmeknow.controller.user.InitialUser;

public class Factory {
    public void concreteInitialUser(String userid){
        ConcreteInitialUser.reset();
        ConcreteInitialUser.setAll(userid);
    }
    public boolean tryLog(String userid, String password){
        boolean bool=true;
        ConcreteUsrUser.reset();
        ConcretePsyUser.reset();
        if (ConcreteUsrUser.checkCredentials(userid,password)){
            ConcreteUsrUser.setAll(userid);
            ConcreteUsrUser.getUsrUser();
            concreteInitialUser(userid);
        }
        else if(ConcretePsyUser.checkCredentials(userid,password)){
            ConcretePsyUser.setAll(userid);
            ConcreteUsrUser.getUsrUser();
            concreteInitialUser(userid);
        }
        return bool;
    }
    public User getUser(){
        if(ConcreteUsrUser.getUsrUser()==null){
            return ConcretePsyUser.getPsyUser();
        }
        else{
            return ConcreteUsrUser.getUsrUser();
        }
    }
    public InitialUser getInitialUser(){
        return ConcreteInitialUser.getUser();
    }
}
