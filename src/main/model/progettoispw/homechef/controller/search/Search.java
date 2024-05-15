package progettoispw.letmeknow.controller.search;

import progettoispw.letmeknow.controller.usruser.UsrUser;

import java.util.ArrayList;
import java.util.List;

public class Search {
    private String userid;
    private SearchDAO searchData;
    private Sliders slider;
    private ArrayList<String> foundList;
    private String touched;
    public Search(String who) {
        foundList= new ArrayList <>();
        searchData=new SearchDAO();
        userid=who;
        touched=null;
        slider=new Sliders(0);
    }
    public void setAffinity(int val) {
        slider = new Sliders(val);
    }
    public void parametricSetSearch(int[] val) {
        slider.setEmp(val[0]);
        slider.setHum(val[1]);
        slider.setOpt(val[2]);
        parametricSearch();
    }
    public void parametricSearch() {
        int[] array = slider.getAll();
        ArrayList<String> inner= (ArrayList<String>) searchData.paramSearch(userid, array[0], array[1], array[2]);
        foundList.addAll(inner);
    }
    public void goalSearch(String goal) {
        ArrayList<String>inner= (ArrayList<String>) searchData.paramSearch(userid, 1, 1, 1);
        UsrUser me=new UsrUser(userid);
        for(String elem:inner){
            UsrUser user=new UsrUser(elem);
            if((user.getTag()!=null)  && me.getTag()!=null && user.getTag().contains(goal)){
                foundList.add(elem);
            }
        }
    }
    public void descrSearch(String descr) {
        ArrayList<String> inner= (ArrayList<String>) searchData.paramSearch(userid, 1, 1, 1);
        for(String elem:inner){
            UsrUser user=new UsrUser(elem);
            if(user.getDescript().contains(descr)){
                foundList.add(elem);
            }
        }
    }
    public List<String> getArrayList(){
        List <String>inner=new ArrayList<>();
        if(foundList.isEmpty()){
            inner= searchData.getVisit(userid);
        }
        for(String str:foundList){
            if(!inner.contains(str))inner.add(str);
        }
        return inner;
    }
    public boolean setTouched(String userid2) {
        boolean bool;
        bool=searchData.addVisited(userid,userid2);
        if(userid!=null){
            this.touched = userid2 ;
        }
        return bool;
    }
    public int[] getnVisit(){
        int [] inner;
        inner=searchData.getnVisit(userid);
        return inner;
    }
    public String getTouched() {
        return touched;
    }
    public void reset(){
        foundList=new ArrayList<>();
    }
}

