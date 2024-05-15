package progettoispw.letmeknow.controller.usruser;

import progettoispw.letmeknow.controller.User;
import progettoispw.letmeknow.controller.user.UserDAO;

public class UsrUser implements User {
    UsrUserDAO userDataUSR;
    private String userid;
    private String des;
    private Goal personalObb;
    private ParameterSliders parameterSliders;
    private boolean errorOccurred;
    public UsrUser(String who) {
        userid =who;
        userDataUSR =new UsrUserDAO();
        personalObb=new Goal();
        parameterSliders=new ParameterSliders();
        errorOccurred=false;
        dataHomeUsr();
        queryResult();
    }
    @Override
    public String getUserid() {
        return userid;
    }
    @Override
    public String getType() {
        return "usr";
    }
    @Override
    public boolean confirmCredentials(String password) {
        UserDAO innerDao=new UserDAO();
        return password.equals(innerDao.getPswd(userid));
    }
    public void  dataHomeUsr () {
        if(userid !=null){
            String [] inner= userDataUSR.selectUsrUser(userid);
                parameterSliders.setEmp(inner[0]);
                parameterSliders.setHum(inner[1]);
                parameterSliders.setOpt(inner[2]);
                des = inner[3];
                personalObb.setObiettivo(inner[4]);
                personalObb.setTag(inner[6]);
                personalObb.setStrDataAmericanEurope(inner[5]);
        }
    }
    public int getEmp(){
        return parameterSliders.getEmp();
    }
    public int getHum(){
        return parameterSliders.getHum();
    }
    public int getOpt(){
        return parameterSliders.getOpt();
    }
    public String getDescript(){
        return des;
    }
    public String getTag(){
        return personalObb.getTag();
    }
    public String getGoal(){
        return personalObb.getObiettivo();
    }
    public int[] getDate(){
        return personalObb.getDataEuropean();
    }
    public void setPersonalDes(String newS)  {
        des =newS;
        errorOccurred=  !userDataUSR.setDescription(userid,newS);
    }
    public void setPersonalGoal(String newS)  {
        personalObb.setObiettivo(newS);
        errorOccurred= !userDataUSR.setGoal(userid,newS);
    }
    public void setPersonalTag(String newS)  {
        personalObb.setTag(newS);
        errorOccurred= !userDataUSR.setTag(userid,newS);
    }
    public void setPersonalData(String newData)  {
        personalObb.setStrDataEurope(newData);
        errorOccurred= !userDataUSR.setData(userid,personalObb.getAmericanDataStr());
    }
    public void setParams(){
        queryResult();
        dataHomeUsr();
    }
    public boolean getExpired(){
        return personalObb.getExpired();
    }
    public boolean queryResult() {
        return userDataUSR.getResult(userid,parameterSliders.getAll());
    }
    public boolean isErrorOccurred() {
        return errorOccurred;
    }
    @Override
    public void setErrorOccurred(boolean errorOccurred) {
        this.errorOccurred = errorOccurred;
    }
}