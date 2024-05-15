package progettoispw.letmeknow.controller.psyuser;

import java.time.LocalDate;

public class Calendar {
    int month;
    int year;
    static final String [] monthNames=new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public Calendar(){
        LocalDate currentdate = LocalDate.now();
        month=currentdate.getMonthValue();
        year=currentdate.getYear();
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getYear() {
        return year;
    }
    public void decremMonth(){
        if(month==1){
            month=12;
            year--;
        }
        else{
            month-=1;
        }
    }
    public void incremMonth(){
        if(month==12){
            month=1;
            year++;
        }else{
            month+=1;
        }
    }
    public int getMonth() {
        return month;
    }
    public String getMonthName(){
        return monthNames[month-1];
    }
}
