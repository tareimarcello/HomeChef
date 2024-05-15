package progettoispw.letmeknow.controller.psyuser;


import java.util.ArrayList;
import java.util.List;

public class Form  {
    int formid;
    String userid;
    int [] answers;
    static final Integer [] FORMSID={1,2,3};
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUserid() {
        return userid;
    }
    public void setFormid(int formid) {
        this.formid = formid;
    }
    public void setAnswers(int[] answersInp) {
        answers = answersInp;
    }
    public int getFormid() {
        return formid;
    }
    public int[] getAnswers() {
        return answers;
    }
    public List<Form> attach(int formid, int [] answers, String userid, List<Form> actual){
        if(actual==null)actual=new ArrayList<>();
        Form elem=new Form();
        elem.setFormid(formid);
        elem.setAnswers(answers);
        elem.setUserid(userid);
        actual.add(elem);
        return actual;
    }
    public int getCounter(List<Form> input, int id){
        int counter=0;
        for(Form elem: input)if (elem.getFormid()==id)counter++;
        return counter;
    }
    public Form getElem(List<Form> input, int val){
        for(Form elem:input)if(elem.getFormid()==val)return elem;
        return null;
    }
    public List<Form> getSum(List<Form> input) {
        int[] ids = new int[FORMSID.length];
        int[] innerAnswer;
        int[] answersCompare;
        ArrayList<Form> inner = new ArrayList<>();
        Form form;
        boolean in ;
        int val;
        String id;
        for (Form elem : input) {
            val = elem.getFormid();
            id=elem.getUserid();
            innerAnswer = elem.getAnswers();
            in=false;
            for (int i : ids) if (val == i) in = true;
            if (in) {
                form = getElem(inner,val);
                answersCompare = form.getAnswers();
                for (int i = 0; i < innerAnswer.length; i++) {
                    innerAnswer[i] += answersCompare[i];
                }
                form.setAnswers(innerAnswer);
            } else {
                attach(val, innerAnswer,id, inner);
                ids[val-1]=val;
            }
        }
        return inner;
    }
}
