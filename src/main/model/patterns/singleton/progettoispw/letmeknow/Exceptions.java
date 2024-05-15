package progettoispw.letmeknow;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import progettoispw.letmeknow.controller.ConnectionDBMS;

import java.util.Optional;

public final class Exceptions {
    private Exceptions(){
        //the other class can't edit Exceptions
    }
    public static void exceptionPageOccurred(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Invalid resources ");
        alert.setHeaderText("we found found some trouble during the execution of the program");
        alert.setContentText("go to settings and report the problem");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            ConnectionDBMS.closeConn();
            System.exit(0);
        }
        Platform.exit();
    }
    public static void exceptionConnectionOccurred(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Connection failed ");
        alert.setHeaderText("We found found some trouble during the getSTMT on the Database");
        alert.setContentText("Please retry your access");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            System.exit(0);
        }
        ConnectionDBMS.closeConn();
        Platform.exit();
    }
    public static void exceptionSignupOccurred(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Signup failed");
        alert.setHeaderText("We weren't be able to retrieve your data, please try  again!");
        alert.setContentText("Please, fill Email and Password Fields. They cannot be empty.");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            event.consume();
        }
        else{
           Page controller=new Page();
           controller.backTo();
        }
    }
    public static String exceptionInputChatOccurred(String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Sending message failed");
        alert.setHeaderText("We weren't be able to retrieve your data, please try  again!");
        alert.setContentText("Your text can't contain any char ");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            return text;
        }
        else{
            return "";
        }
    }
    public static void exceptionEditProfileOccurred(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Update profile failed");
        alert.setHeaderText("We weren't be able edit your data, please try  again!");
        alert.setContentText("Please,checkActive if " +
                "tag and description begin in '#'," +
                "the data is in format 'day-month-year'" +
                "text input not contain char like / ' \" ");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            event.consume();
        }
        else {
            PageMenu controller=new PageMenu();
            controller.backTo();
        }
    }
}
