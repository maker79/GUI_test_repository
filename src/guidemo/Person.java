/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guidemo;

import java.time.LocalDate;
import java.time.Period;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 *
 * @author Vladan
 */
public class Person {

    // to create a table cannot use a String SimpleStringProperty needs to be used
    // which alows to make modifications
    private SimpleStringProperty firstName, lastName;
    private LocalDate birthday;
    private Image photo;
    
    // this constructor will create a person object with a default image 
    public Person(String firstName, String lastName, LocalDate birthday) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
        photo = new Image("defaultImage.png");
    }
    
    // this second constructor will allow us to create a person with an image if they selected one
    public Person(String firstName, String lastName, LocalDate birthday, Image photo) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
        this.photo = photo;
    }
    
    public Image getImage(){
        return photo;
    }
    
    public void setImage(Image newPicture){
        this.photo = newPicture;
    }
    
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

}
