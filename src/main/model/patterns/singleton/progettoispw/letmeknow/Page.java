package progettoispw.letmeknow;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Page {
    static Stage stage1;
    static Scene scene1;
    static String title1;
    boolean checkActive =true;
    static final String INTERF1="interf1";
    static final String INTERF2="interf2";
    static final String FXML=".fxml";
    static void prevBack(ActionEvent event){
        stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene1 = ((Node) event.getSource()).getScene();
        title1= stage1.getTitle();
    }
    static String check(String name,Stage stage){
        int index;
        if(stage.getWidth()>500 && name.indexOf(INTERF1)>-1){
            index= name.indexOf(INTERF1);
            name=name.substring(0,index);
            name+="" + INTERF2+FXML;
        }
        else if(stage.getWidth()<500 && name.indexOf(INTERF2)>-1){
            index= name.indexOf(INTERF2);
            name=name.substring(0,index);
            name+=INTERF1+FXML;
            stage.setMaxWidth(414);
            stage.setMinHeight(736);
        }
        return name;
    }
    public void switchTo(String name, ActionEvent event, String title) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            if(checkActive)name=check(name,stage);
            prevBack(event);
            Parent root = FXMLLoader.load(getClass().getResource(name));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Exceptions.exceptionPageOccurred();
        }
    }
    public void backTo(){
        stage1.setScene(scene1);
        stage1.setTitle(title1);
        stage1.show();
    }
    public void setSize(String name,ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        int index;
        index= name.indexOf(INTERF2);
        if(index==-1) index= name.indexOf(INTERF1);
        if(stage.getWidth()>500) {
            name=name.substring(0,index);
            name+="" + INTERF1+FXML;
        }
        else{
            name=name.substring(0,index);
            name+="" + INTERF2+FXML;
        }
        checkActive =false;
        switchTo(name,event,stage.getTitle());
    }
}