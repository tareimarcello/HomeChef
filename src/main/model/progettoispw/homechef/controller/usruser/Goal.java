package progettoispw.letmeknow.controller.usruser;

import java.time.LocalDate;

public class Goal {
    private  String obiettivo;
    private   String tag;
    private  int[] dataEuropean;//EUROPEAN FORMAT DD-MM-YYYY
    public Goal(){
        obiettivo= "";
        tag="#";
        dataEuropean=new int[]{1,1,1};
    }
    public void setObiettivo(String string){
        obiettivo=string;
    }
    public void setStrDataAmericanEurope(String input){
        //CONVERTED FROM American to  INPUT DD-MM-YYYY
        if(input!=null){
        int end=input.indexOf("-");
        dataEuropean[2]=(Integer.parseInt(input.substring(0,end)));
        int beg=end;
        end=input.indexOf("-",end+1);
        dataEuropean[1]=(Integer.parseInt(input.substring(beg+1,end)));
        beg=end;
        end=input.length();
        dataEuropean[0]=(Integer.parseInt(input.substring(beg+1,end)));
    }}
    public void setStrDataEurope(String input){
        //CONVERTED FROM  DD-MM-YYYY to American
        int end=input.indexOf("-");
        dataEuropean[0]=(Integer.parseInt(input.substring(0,end)));
        int beg=end;
        end=input.indexOf("-",end+1);
        dataEuropean[1]=(Integer.parseInt(input.substring(beg+1,end)));
        beg=end;
        end=input.length();
        dataEuropean[2]=(Integer.parseInt(input.substring(beg+1,end)));
        checkData();
    }
    private void checkData(){
        LocalDate inner=LocalDate.of(dataEuropean[2],dataEuropean[1],dataEuropean[0]);
        LocalDate compare=LocalDate.now().plusMonths(6);
        if(inner.isAfter(compare)){
            dataEuropean[0]=compare.getDayOfMonth();
            dataEuropean[1]= compare.getMonthValue();
            dataEuropean[2]= compare.getYear();
        }
    }
    public String getAmericanDataStr(){
        return String.format("%d-%d-%d", dataEuropean[2], dataEuropean[1], dataEuropean[0]);
    }
    public void setTag(String string){
        tag=string;
    }
    public String getObiettivo(){
        return obiettivo;
    }
    public int[] getDataEuropean(){return dataEuropean;}
    public String getTag(){
        return tag;
    }
    public boolean getExpired(){
        LocalDate inner=LocalDate.of(dataEuropean[2],dataEuropean[1],dataEuropean[0]);
        LocalDate compare =LocalDate.now();
        return compare.isAfter(inner);
    }
}
