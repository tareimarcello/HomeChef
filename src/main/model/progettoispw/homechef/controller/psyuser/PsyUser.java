package progettoispw.letmeknow.controller.psyuser;
import progettoispw.letmeknow.controller.User;
import progettoispw.letmeknow.controller.user.UserDAO;

import java.util.List;

public class PsyUser implements User {
    PsyUserDAO userDataPsy;
    Form form;
    List<Form> listForms;
    String userid;
    int index;
    float[][]innerList;
    Calendar calendar;
    boolean errorFeedOccurred;
    public PsyUser(String who) {
        userid=who;
        form=new Form();
        userDataPsy=new PsyUserDAO();
        calendar=new Calendar();
        errorFeedOccurred=false;
    }
    @Override
    public String getUserid() {
        return userid;
    }
    @Override
    public String getType() {
        return "psy";
    }
    @Override
    public boolean confirmCredentials(String password) {
        UserDAO inner=new UserDAO();
        return password.equals(inner.getPswd(userid));
    }
    @Override
    public boolean isErrorOccurred() {
        return errorFeedOccurred;
    }
    @Override
    public void setErrorOccurred(boolean occurred){
        this.errorFeedOccurred=occurred;
    }

    public void suggestQuestion(String textInput) {
       errorFeedOccurred=userDataPsy.suggestForm(userid,textInput);
    }
    public List<Form> getSum(){
        return form.getSum(listForms);
    }
    public int getCounter(int formid){
        return form.getCounter(listForms,formid);
    }
    public void  collectForms(int month,int year) {
        listForms=userDataPsy.collectForms(month,year);
    }
    public float[] getSelected() {
        return innerList[index];
    }
    public float[] getList(){
        if(index<form.FORMSID.length){
            return innerList[index++];
        }else {
            index=0;
            return new float[0];
        }
    }
    public void getLists(){
        index=0;
        int month=calendar.getMonth();
        int year=calendar.getYear();
        collectForms(month,year);
        List<Form>list=getSum();
        int [] answers;
        int formid;
        int counter;
        innerList= new float[form.FORMSID.length][7];
        for(Form elem:list){
            formid=elem.getFormid();
            counter=getCounter(formid);
            innerList[formid-1][0]=counter;
            answers= elem.getAnswers();
            for(int i=1;i<7;i++){
                innerList[formid-1][i]=(float)answers[i-1]/counter;
            }
        }

    }
    public void incremMonth(){
        calendar.incremMonth();
        getLists();
    }
    public void decremMonth(){
        calendar.decremMonth();
    }
    public String getMonthName(){
        return calendar.getMonthName();
    }
    public void setIndex(int ind){
        index=ind;
    }








}
