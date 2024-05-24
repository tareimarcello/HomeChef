package progettoispw.letmeknow;
import javafx.event.ActionEvent;
public  class PageMenu extends Page {
    public void switchToChat(ActionEvent event)  {
        this.switchTo("chat/prenotazione-prova.fxml",event,"Chat");
    }
    public void switchToPersonalForm(ActionEvent event)  {
        this.switchTo("formCollectionResults/prenotazione-prova.fxml",event,"Your result");
    }
    public void switchToHome(ActionEvent event){
        this.switchTo("homepage/prenotazione-prova.fxml",event,"Homepage");
    }
    public void switchToISC(ActionEvent event)  {
        this.switchTo("initialSearchAndChat/prenotazione-prova.fxml",event,"What do you need?  ");
    }
    public void switchToSettings(ActionEvent event)  {
        this.switchTo("settings/prenotazione-prova.fxml",event,"Settings");
    }
    public void switchToSearch(ActionEvent event ){this.switchTo("search/prenotazione-prova.fxml",event,"Search");}
}