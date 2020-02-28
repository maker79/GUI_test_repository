/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guidemo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Vladan
 */
public class PersonViewController implements Initializable {
    
    // we need to receive Pearson object, the person that has been selected from the table
    private Person selectedPerson;
    
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label birthdayLabel;
    @FXML private Label ageLabel;
    @FXML private ImageView photo;
    
    private FileChooser fileChooser;
    private File filePath;
    
    /**
     * This method will allow user to change the image on the screen
     */
    public void chooseImageButtonPushed(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open image");
        
        // Set to user's directory or go to the default C drive if cannot access
        String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
        File userDirectory = new File(userDirectoryString);
        
        if (!userDirectory.canRead())
            userDirectory = new File("c:/");
        
        fileChooser.setInitialDirectory(userDirectory);
        
        // this will go to a c drive and give you an option to choose from files
        this.filePath = fileChooser.showOpenDialog(stage);
        
        // Try to update the image by loading the new image
        try {
            java.awt.image.BufferedImage bufferedImage = ImageIO.read(filePath);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            selectedPerson.setImage(image);
            photo.setImage(selectedPerson.getImage());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * This method accepts a person to initialize a view
     */
    public void initData(Person person){
        
        selectedPerson = person;
        firstNameLabel.setText(selectedPerson.getFirstName());
        lastNameLabel.setText(selectedPerson.getLastName());
        birthdayLabel.setText(selectedPerson.getBirthday().toString());
        ageLabel.setText(Integer.toString(selectedPerson.getAge()));
        photo.setImage(selectedPerson.getImage());
        
    }
    
     /**
     * When this method is called, it will change the scene to a TableView
     * example
     */
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ExampleOfTableView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

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
        // TODO
    }    

    private static class BufferedImage {

        public BufferedImage() {
        }
    }
    
}
