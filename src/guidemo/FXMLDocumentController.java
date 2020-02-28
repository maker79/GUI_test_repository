/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guidemo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author Vladan
 */
public class FXMLDocumentController implements Initializable {
    
    //Items for the Checkbox example
    @FXML private Label pizzaOrderLabel;
    @FXML private CheckBox pepperoniCheckBox;
    @FXML private CheckBox pinappleCheckBox;
    @FXML private CheckBox baconCheckBox;
    
    //Items for the Choicebox example
    @FXML private ChoiceBox choiceBox;
    @FXML private Label choiceBoxLabel;
    
    //these items are for ComboBox
    
   @FXML private ComboBox comboBox;
   @FXML private Label comboBoxLabel;
   
   // these items are for RadioButtons
   @FXML private RadioButton javaRadioButton;
   @FXML private RadioButton pythonRadioButton;
   @FXML private RadioButton phpRadioButton;
   @FXML private RadioButton csharpRadioButton;
   @FXML private Label radioButtonsLabel;
   private ToggleGroup favLangToggleGroup;
   
   //These items are for the ListView and TextArea example
   @FXML private ListView listView;
   @FXML private TextArea golfTextArea;
   
   // This is the spinner object to store grade information
   @FXML private Spinner gradeSpinner;
   @FXML private Button getGradeButton;
   @FXML private Label gradeLabel;
   
    
    /**
     * This is for the label for choiceBox
     */
     
    public void choiceBoxButtonPushed(){
        
        choiceBoxLabel.setText("My favourite fruit is:\n" + 
                choiceBox.getValue().toString());
    }

/**
*This is for the CheckBox example
*/
    
    public void pizzaOrderButtonPushed(){
        
        String order = "Toppings are:";
        
        if (pinappleCheckBox.isSelected())
            order += "\npinapple";
        
        if (pepperoniCheckBox.isSelected())
            order += "\npepperoni";
        
        if (baconCheckBox.isSelected())
            order += "\nbacon";
        
        this.pizzaOrderLabel.setText(order);
    }
    
    /**
     *This will update the comboBoxLabel when the ComboBox is changed
     */
    
    public void comboBoxWasUpdated(){
        this.comboBoxLabel.setText("Course selected: \n" + comboBox.getValue().toString());
    }
    
    /**
     * This will update the radioButtonLabel whenever a different radio button is pushed
     */
    
    public void radioButtonChanged(){
    if (this.favLangToggleGroup.getSelectedToggle().equals(this.javaRadioButton))
        radioButtonsLabel.setText("The selected item is: Java");
    
    if (this.favLangToggleGroup.getSelectedToggle().equals(this.pythonRadioButton))
        radioButtonsLabel.setText("The selected item is: Python");
    
    if (this.favLangToggleGroup.getSelectedToggle().equals(this.csharpRadioButton))
        radioButtonsLabel.setText("The selected item is: C#");
    
    if (this.favLangToggleGroup.getSelectedToggle().equals(this.phpRadioButton))
        radioButtonsLabel.setText("The selected item is: PHP");
    
                }
    
    /**
     * This method will copy the strings from the listView and put them in the textArea
     */
    
    public void listViewButtonPushed(){
        String textAreaString = "";
        
        ObservableList listOfItems = listView.getSelectionModel().getSelectedItems();
        
        for (Object item : listOfItems){
            textAreaString += String.format("%s%n",(String) item);
        }
        this.golfTextArea.setText(textAreaString);
    }
    
    /**
     * When this method is called, it will change the scene to a TableView example
     */
    
    public void changeScreenButtonPushed(ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ExampleOfTableView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //this line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    /**
     * This method will read from the grade Spinner and update the label when pushed
     */
    public void getGradeButtonPushed(){
        // we are getting an object from the spinner, so it needs to be converted to a string at least
        this.gradeLabel.setVisible(true);
        this.gradeLabel.setText(this.gradeSpinner.getValue().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pizzaOrderLabel.setText("");
        
        // these items are for configuring choice box example
        choiceBoxLabel.setText("");
        choiceBox.getItems().add("apples");
        choiceBox.getItems().add("bananas");
        choiceBox.getItems().addAll("oranges", "pears", "strawberries");
        choiceBox.setValue("apples");
        
        //these items are for configuring comboBox
        comboBox.getItems().add("COMP1030");
        comboBox.getItems().addAll("COMP1008", "COMP1006", "MGMT3456");
        comboBoxLabel.setText("");
        
        //these items are for configuring radioButtons
        radioButtonsLabel.setText("");
        //we put the buttons in a toggle group so we can select only one at the time
        favLangToggleGroup = new ToggleGroup();
        this.javaRadioButton.setToggleGroup(favLangToggleGroup);
        this.pythonRadioButton.setToggleGroup(favLangToggleGroup);
        this.csharpRadioButton.setToggleGroup(favLangToggleGroup);
        this.phpRadioButton.setToggleGroup(favLangToggleGroup);
        
        // These items are for listView and textArea
        
        listView.getItems().addAll("Golf Balls", "Wedges", "Irons", "Tees", "Driver", "Putter");
        //in order to multiselect
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        // configure the spinner with values of 0 100
        SpinnerValueFactory<Integer> gradesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 75);
        this.gradeSpinner.setValueFactory(gradesValueFactory);
        gradeSpinner.setEditable(true);
        this.gradeLabel.setVisible(false);
        
    }    
    
}
