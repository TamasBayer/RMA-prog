package rma;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Tamas Bayer
 */
public class ViewController implements Initializable {
    
//<editor-fold defaultstate="collapsed" desc="rmaListPane">
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
            Pane rmaListPane;
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="regListPane">
    @FXML
            Pane rmaRegistPane;
    @FXML
            TextField gpInput;
    @FXML
            TextField rmaInput;
    @FXML
            TextField rvInput;
    @FXML
            TextField cridInput;
    @FXML
            TextField sonsInput;
    @FXML
            CheckBox ntCheck;
    @FXML
            CheckBox stromCheck;
    @FXML
            CheckBox akkuCheck;
    @FXML
            CheckBox gdataCheck;
    @FXML
            CheckBox bullCheck;
    @FXML
            CheckBox acroCheck;
    @FXML
            CheckBox dockCheck;
    @FXML
            CheckBox tastaCheck;
    @FXML
            CheckBox mausCheck;
    @FXML
            CheckBox vgaCheck;
    @FXML
            CheckBox dviCheck;
    @FXML
            CheckBox tascheCheck;
    @FXML
            Button angelifertButton;
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Angelifert">
    @FXML
            Pane angelifertPane;
    @FXML
            TableView angelifertTableView;
    @FXML
            ChoiceBox angelifertSuchenChoiceBox;
    @FXML
            TextField angelifertSuchenTextField;
    @FXML
            Button angelifertSuchenButton;
    
//</editor-fold>
    
    
    private final String Menu_RMAList = "RMA List";
    private final String Menu_Reg = "RMA Bericht";
    private final String Menu_Angelifert = "Angelifert";
    DB db = new DB();
    
    private final ObservableList<rmaData> data = FXCollections.observableArrayList();
//               new rmaData("RMA145353", "35533532", "Frau Kesper", "11.03.2019"),
//               new rmaData("RMA124322", "35425321", "Herr Thomas", "01.03.2019"),        
//               new rmaData("RMA123452", "35422744", "Herr Dits", "23.02.2019"),        
//               new rmaData("RMA123634", "35424587", "Frau Deutsch", "03.03.2019"),        
//              new rmaData("RMA123252", "35422215", "Frau Klar", "26.02.2019"),        
//               new rmaData("RMA127453", "35476574", "Herr Adam", "12.03.2019"),
//               new rmaData("RMA123435", "35422343", "Herr Dominik", "14.03.2019"));
            
    public void setTableData(){
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
       data.addAll(db.getAllRMA());
       rmaTable.setItems(data);

    }
    
    private void setMenuData(){
    TreeItem<String> treeItemRoot1 = new TreeItem<>("Menü");
    TreeView<String> treeView = new TreeView<>(treeItemRoot1);
    treeView.setShowRoot(false);
    
    TreeItem<String> nodeItemA = new TreeItem<>(Menu_RMAList);
    TreeItem<String> nodeItemB = new TreeItem<>(Menu_Reg);
    TreeItem<String> nodeItemC = new TreeItem<>(Menu_Angelifert);
    
    treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB, nodeItemC);
    
    menuPane.getChildren().add(treeView);
    
    
        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                
                String selectedMenu;
                selectedMenu = selectedItem.getValue();
                
                if (null != selectedMenu){
                    switch(selectedMenu){
                        case Menu_RMAList:
                                rmaListPane.setVisible(true);
                                rmaRegistPane.setVisible(false);
                                angelifertPane.setVisible(false);
                                break;
                        case Menu_Reg:
                                rmaListPane.setVisible(false);
                                rmaRegistPane.setVisible(true);
                                angelifertPane.setVisible(false);
                                break;
                        case Menu_Angelifert:
                                rmaListPane.setVisible(false);
                                rmaRegistPane.setVisible(false);
                                angelifertPane.setVisible(true);
                            }
                    }
                }
        
        });
    }
    @FXML
            private void suchen(){
                FilteredList<rmaData> sRMAData = new FilteredList(data, e -> true);
                suchenChoice.getItems().addAll("RMA Nr.", "CRID Nr.", "Kunde", "Datum");
                suchenChoice.setValue("RMA Nr.");
                
                suchenTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                
                    sRMAData.setPredicate((Predicate<? super rmaData>) (rmaData rmaData)->{
               
                    if(newValue.isEmpty() || newValue==null){
                        return true;
                    } else if ((suchenChoice.getValue() == "RMA Nr.") && rmaData.getRmaNummer().contains(newValue)){
                        return true;
                    } else if ((suchenChoice.getValue() == "CRID Nr.") && rmaData.getCridNummer().contains(newValue)){
                        return true;
                    } else if ((suchenChoice.getValue() == "Kunde") && rmaData.getKunde().contains(newValue)){
                        return true;
                    } else if ((suchenChoice.getValue() == "Datum") && rmaData.getDatum().contains(newValue)){
                        return true;
                    } 
                    
                    return false;
                        });
                });
                

                SortedList sort = new SortedList(sRMAData);
                sort.comparatorProperty().bind(rmaTable.comparatorProperty());
                rmaTable.setItems(sort);
                
          
                        }
 //            private final ObservableList<rmaData> angelifertData = FXCollections.observableArrayList();
            
             
            
            private void setAngelifertTable(){
       TableColumn angermaNumCol = new TableColumn("RMA");
       angermaNumCol.setMinWidth(130);
       angermaNumCol.setCellFactory(TextFieldTableCell.forTableColumn());
       angermaNumCol.setCellValueFactory(new PropertyValueFactory<rmaData, String>("rmaNummer"));
       
       TableColumn angecridCol = new TableColumn("Crid");
       angecridCol.setMinWidth(140);
       angecridCol.setCellFactory(TextFieldTableCell.forTableColumn());
       angecridCol.setCellValueFactory(new PropertyValueFactory<rmaData, String>("cridNummer"));
       
       TableColumn angekundeCol = new TableColumn("Kunde");
       angekundeCol.setMinWidth(140);
       angekundeCol.setCellFactory(TextFieldTableCell.forTableColumn());
       angekundeCol.setCellValueFactory(new PropertyValueFactory<rmaData, String>("kunde"));
       
       TableColumn angedatumCol = new TableColumn("Datum");
       angedatumCol.setMinWidth(100);
       angedatumCol.setCellFactory(TextFieldTableCell.forTableColumn());
       angedatumCol.setCellValueFactory(new PropertyValueFactory<rmaData, String>("datum"));
       
        angelifertTableView.getColumns().addAll(angermaNumCol, angecridCol, angekundeCol, angedatumCol);
        
        
        
            }
            

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setTableData();
       setMenuData();
       suchen();
       setAngelifertTable();

    }    
    
}