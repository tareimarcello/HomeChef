package progettoispw.letmeknow.controller.search;

import progettoispw.letmeknow.controller.ParamSliders;

public class Sliders implements ParamSliders {
    private  int emp;
    private  int opt;
    private int hum;
    private int affinity;
    public Sliders (int val ){
        affinity=val;
    }
    public int check(int val ){
        if(val<=0)return 1;
        return val;
    }
    public void setHum(int val){
        hum=val;
    }
    public void setOpt(int val){
        opt=val;
    }
    public void setEmp(int val){
        emp=val;
    }
    public int getEmp(){
        return emp;
    }
    public int getHum(){
        return hum;
    }
    public int getOpt(){
        return opt;
    }
    public int [] getAll(){
        return new int [] {check(emp-affinity),check(hum-affinity),check(opt-affinity)};
    }

}
