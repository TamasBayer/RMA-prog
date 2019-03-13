
package rma;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Tamas Bayer
 */
public class ViewController implements Initializable {
    
    @FXML
    TableView rmaTable;
    @FXML
    Button suchenButton;
    @FXML
    ChoiceBox suchenChoice;
    @FXML
    TextField suchenTextField;
    @FXML
    StackPane menuPane;
    @FXML
    Pane rmaRegistPane;
    @FXML
    Pane rmaListPane;
    
    private final ObservableList<rmaData> data =
               FXCollections.observableArrayList(
               new rmaData("RMA145353", "35533532", "Frau Kesper", "11.03.2019"),
               new rmaData("RMA124322", "35425321", "Herr Thomas", "01.03.2019"),        
               new rmaData("RMA123452", "35422744", "Herr Dits", "23.02.2019"),        
               new rmaData("RMA123634", "35424587", "Frau Deutsch", "03.03.2019"),        
               new rmaData("RMA123252", "35422215", "Frau Klar", "26.02.2019"),        
               new rmaData("RMA127453", "35476574", "Herr Adam", "12.03.2019"),
               new rmaData("RMA123435", "35422343", "Herr Dominik", "14.03.2019"));
            
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       TableColumn rmaNumCol = new TableColumn("RMA");
       rmaNumCol.setMinWidth(130);
       rmaNumCol.setCellFactory(TextFieldTableCell.forTableColumn());
       rmaNumCol.setCellValueFactory(new PropertyValueFactory<rmaData, String>("rmaNummer"));
       
       TableColumn cridCol = new TableColumn("Crid");
       cridCol.setMinWidth(140);
       cridCol.setCellFactory(TextFieldTableCell.forTableColumn());
       cridCol.setCellValueFactory(new PropertyValueFactory<rmaData, String>("cridNummer"));
       
       TableColumn kundeCol = new TableColumn("Kunde");
       kundeCol.setMinWidth(140);
       kundeCol.setCellFactory(TextFieldTableCell.forTableColumn());
       kundeCol.setCellValueFactory(new PropertyValueFactory<rmaData, String>("kunde"));
       
       TableColumn datumCol = new TableColumn("Datum");
       datumCol.setMinWidth(100);
       datumCol.setCellFactory(TextFieldTableCell.forTableColumn());
       datumCol.setCellValueFactory(new PropertyValueFactory<rmaData, String>("datum"));
       
       rmaTable.getColumns().addAll(rmaNumCol, cridCol, kundeCol, datumCol);
       rmaTable.setItems(data);
    }    
    
}
