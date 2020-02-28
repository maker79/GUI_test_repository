/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guidemo;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vladan
 */
public class ExampleOfTableViewController implements Initializable {

    // configure the table
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, LocalDate> birthdayColumn;

    //These instance variables are used to crate new Person objects
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private DatePicker birthdayDatePicker;
    
    @FXML private Button detailedPersonViewButton;

    /**
     * This method will allow a user to double click on a cell and update the
     * first name of the person
     */
    public void changeFirstNameCellEvent(CellEditEvent edittedCell) {

        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setFirstName(edittedCell.getNewValue().toString());
    }
    
    /**
     * This method will enable the detailed view button once the row in the table is selected
     */
    public void userClickedOnTable(){
        this.detailedPersonViewButton.setDisable(false);
    }

    /**
     * This method will allow a user to double click on a cell and update the
     * first name of the person
     */
    public void changeLastNameCellEvent(CellEditEvent edittedCell) {

        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setLastName(edittedCell.getNewValue().toString());
    }

    /**
     * When this method is called, it will change the scene to a TableView
     * example
     */
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //this line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    
    /**
     * When this method is called, it will pass the selected Person object to a detailed view
     * example
     */
    public void changeSceneToDetailedPersonView(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PersonView.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method 
        PersonViewController controller = loader.getController();
        controller.initData(tableView.getSelectionModel().getSelectedItem());

        //this line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // set up the columnx in the table
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Person, LocalDate>("birthday"));

        // load dummy date
        tableView.setItems(getPeople());

        //Updated the table to allow for the first and last name fields to be editable
        tableView.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //This will allow the table to select multiple rows at once 
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        // Disable the detailed person view button until row is selected
        this.detailedPersonViewButton.setDisable(true);

    }

    /**
     * This method will remove the selected roe(s) from the table
     */
    public void deleteButtonPushed() {
        ObservableList<Person> selectedRows, allPeople;
        allPeople = tableView.getItems();

        // this will give us the rows that were selected
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        // loop over the selected rows and remove the Person objects from the table        
        for (Person person : selectedRows) {
            allPeople.remove(person);
        }
    }

    /**
     * This method will create a new Person object and add it to the table
     */
    public void newPersonButtonPushed() {

        Person newPerson = new Person(firstNameTextField.getText(),
                lastNameTextField.getText(),
                birthdayDatePicker.getValue());

        // Get all the items from the table as a list, then add a new person to the list
        tableView.getItems().add(newPerson);
    }

    /**
     * This method will return ObservableList of People objects
     */
    public ObservableList<Person> getPeople() {

        ObservableList<Person> people = FXCollections.observableArrayList();
        people.add(new Person("Vladan", "Djurkovic", LocalDate.of(1979, Month.JUNE, 8), new Image("Vladan.jpg")));
        people.add(new Person("Jelena", "Djurkovic", LocalDate.of(1976, Month.JUNE, 14)));
        people.add(new Person("Ema", "Djurkovic", LocalDate.of(2009, Month.JUNE, 19)));
        people.add(new Person("Luka", "Djurkovic", LocalDate.of(2010, Month.NOVEMBER, 14)));
        people.add(new Person("Marko", "Djurkovic", LocalDate.of(2014, Month.FEBRUARY, 28)));

        return people;
    }

}
