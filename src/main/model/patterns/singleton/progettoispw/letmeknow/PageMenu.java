package progettoispw.letmeknow;
import javafx.event.ActionEvent;
public  class PageMenu extends Page {
    public void switchToChat(ActionEvent event)  {
        this.switchTo("chat/interf1.fxml",event,"Chat");
    }
    public void switchToPersonalForm(ActionEvent event)  {
        this.switchTo("formCollectionResults/interf1.fxml",event,"Your result");
    }
    public void switchToHome(ActionEvent event){
        this.switchTo("homepage/interf1.fxml",event,"Homepage");
    }
    public void switchToISC(ActionEvent event)  {
        this.switchTo("initialSearchAndChat/interf1.fxml",event,"What do you need?  ");
    }
    public void switchToSettings(ActionEvent event)  {
        this.switchTo("settings/interf1.fxml",event,"Settings");
    }
    public void switchToSearch(ActionEvent event ){this.switchTo("search/interf1.fxml",event,"Search");}
}