package progettoispw.letmeknow.controller.usruser;

import progettoispw.letmeknow.controller.ParamSliders;

public class ParameterSliders implements ParamSliders {
    private int  emp;
    private int hum;
    private int opt;
    public ParameterSliders() {
        emp=hum=opt=1;
    }
    public int getOpt() {
        return opt;
    }
    public int getHum() {
        return hum;
    }
    public int getEmp() {
        return emp;
    }
    public void setEmp(String  emp) {
        if(emp!=null)this.emp=Integer.parseInt(emp);
    }
    public void setHum(String  hum) {
        if(hum!=null)this.hum=Integer.parseInt(hum);
    }
    public void setOpt(String opt) {
        if(opt!=null)this.opt=Integer.parseInt(opt);
    }
    public int[] getAll(){
        return new int[]{emp,hum,opt};
    }
}
