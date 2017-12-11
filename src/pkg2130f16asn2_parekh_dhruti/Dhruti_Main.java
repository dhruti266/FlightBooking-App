/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2130f16asn2_parekh_dhruti;

/* Course Code : COMP2130
   Student ID  : 101039706
   Dhruti Parekh
*/

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javafx.scene.shape.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Dhruti_Main extends Application {
           
    Stage primaryStage;    
    Dhruti_Flight dFlight;
    Dhruti_Passenger dPass;
    Dhruti_TravelReservation dTravel;
    Dhruti_FrequentFlyer_Passenger dFreqPass;
    ArrayList<Dhruti_TravelReservation> flightReservations = new ArrayList<Dhruti_TravelReservation>();
    boolean flagReservation = false;
    int[] fareArr = new int[5];
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {     
        this.primaryStage = primaryStage;       
        this.displayHomeScene();
    }   
    
    public void displayHomeScene() {            
        
        this.flagReservation = false;
        Text reservationResult = new Text("");
        Text info = new Text("");
        Text txtMain = new Text("Welcome to Filght Hub");
                
        // define all buttons
        Button btnBook = new Button("Book Flight");
        Button btnDisplay = new Button("Display Ticket");
        
        // css for buttons
        btnBook.setStyle("-fx-font: 22 arial; -fx-base: #f5f5f5;");
        btnDisplay.setStyle("-fx-font: 22 arial; -fx-base: #f5f5f5;");
        
        // define button position        
        btnBook.setLayoutX(120);
        btnBook.setLayoutY(250);
        btnDisplay.setLayoutX(300);
        btnDisplay.setLayoutY(250);
                
        reservationResult.setX(140);
        reservationResult.setY(350);
        reservationResult.setFill(Color.RED);
        reservationResult.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        
        txtMain.setX(200);
        txtMain.setY(120);
        txtMain.setFill(Color.BLACK);
        txtMain.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        
        info.setX(140);
        info.setY(320);
        info.setFill(Color.BLACK);
        info.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        
        // define group
        Group root = new Group();              
        
        // define header
        this.getHeader(root);

        // book a flight action
        btnBook.setOnAction(e -> {
            
            // reset objects
            this.dFlight = new Dhruti_Flight("", "", "", 0.0, "");
            this.dPass   = new Dhruti_Passenger("", "", "");
            this.dFreqPass = new Dhruti_FrequentFlyer_Passenger("", "", "", "");
            this.dTravel = new Dhruti_TravelReservation(this.dFlight, this.dPass);
            
            this.displayScene1();
        });
        
        // add buttons to root element
        root.getChildren().add(btnBook);
        root.getChildren().add(btnDisplay);
                
        // display action
        btnDisplay.setOnAction(e -> {
            if(this.flightReservations.size() > 0){
                ArrayList tempArrList = new ArrayList();
                for (int i = 0; i < this.flightReservations.size(); i++)
                {
                   Dhruti_TravelReservation temp = this.flightReservations.get(i);
                   tempArrList.add(temp.GetDFlight().GetFlightNumber()+ " - " + temp.GetDFlight().GetTravelDate());
                } 
                ObservableList oList = FXCollections.observableArrayList(tempArrList);
                final ComboBox comboBox = new ComboBox();
                comboBox.setItems(oList);
                comboBox.setLayoutX(230);
                comboBox.setLayoutY(350);
                root.getChildren().add(comboBox);
                info.setText("Select flight number to see reservation details");

                comboBox.setOnAction((event)->{
                    this.dTravel = this.flightReservations.get(comboBox.getSelectionModel().getSelectedIndex());
                    this.displayScene3();
                });
            }
            else{
                reservationResult.setText("Reservation details not found...");
            }
            
    
        });
        
        root.getChildren().add(reservationResult);
        root.getChildren().add(info);
        root.getChildren().add(txtMain);
        this.primaryStage.setScene(new Scene(root, 600, 550, Color.WHITE));
        this.primaryStage.show();
    }
    
    public void displayScene1() {            

        // define text/buttons
        Button btnNext = new Button("Next");
        Text txtMain = new Text("Flight Information Form");
        
        // generate random number
        for(int i = 0 ; i < 5 ; i++) {
            Random rand = new Random();
            this.fareArr[i] = rand.nextInt(5000) + 1;
        }

        // get existing info 
        String tfFlightNumberText = dFlight.GetFlightNumber();
        String tfSourceText = dFlight.GetSource();
        String tfDestinationText = dFlight.GetDestination();
        String tfTravelDateText = dFlight.GetTravelDate();
        
        // create new text boxes and set default text values using dFlight object.
        TextField tfFlightNumber = new TextField(tfFlightNumberText);
        TextField tfSource = new TextField(tfSourceText);
        TextField tfDestination = new TextField(tfDestinationText);
        TextField tfTravelDate = new TextField(tfTravelDateText);

        // define error labels
        Label errorNum = new Label("");
        Label errorSrc = new Label("");
        Label errorDest = new Label("");
        Label errorDate = new Label("");
        Label errorFare = new Label("");
    
        // label color
        errorNum.setTextFill(Color.RED);
        errorSrc.setTextFill(Color.RED);
        errorDest.setTextFill(Color.RED);
        errorDate.setTextFill(Color.RED);
        
        // text alignments
        tfFlightNumber.setAlignment(Pos.BOTTOM_RIGHT);
        tfSource.setAlignment(Pos.BOTTOM_RIGHT);
        tfDestination.setAlignment(Pos.BOTTOM_RIGHT);
        tfTravelDate.setAlignment(Pos.BOTTOM_RIGHT);
        
        // field positioning
        txtMain.setX(200);
        txtMain.setY(120);
        txtMain.setFill(Color.BLACK);
        txtMain.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        
        
        // next button position
        btnNext.setLayoutX(540);
        btnNext.setLayoutY(510);
        
        // define root element
        Group root = new Group();              
        GridPane gridPane = new GridPane();
    
        // define header
        this.getHeader(root);

        // field positioning        
        gridPane.setPadding(new Insets(160, 30, 30, 30));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(new Label("Flight Number(eg. AC121)  :"), 0, 0);
        gridPane.add(tfFlightNumber, 1, 0);
        gridPane.add(new Label("Source(from)(eg. Toronto) :"), 0, 1);
        gridPane.add(tfSource, 1, 1);
        gridPane.add(new Label("Destination(to)(eg. Delhi) :"), 0, 2);
        gridPane.add(tfDestination, 1, 2);
        gridPane.add(new Label("Travel Date (dd/mm/yyyy)  :"), 0, 3);
        gridPane.add(tfTravelDate, 1, 3);
        gridPane.add(errorNum, 2, 0);
        gridPane.add(errorSrc, 2, 1);
        gridPane.add(errorDest, 2, 2);  
        gridPane.add(errorDate, 2, 3);
        gridPane.add(errorFare, 1, 5);
            
        // define radio boxes
        RadioButton rb1 = new RadioButton("");
        RadioButton rb2 = new RadioButton("");
        RadioButton rb3 = new RadioButton("");
        RadioButton rb4 = new RadioButton("");
        RadioButton rb5 = new RadioButton("");
        ToggleGroup tgroup = new ToggleGroup();

        // add button validation event
        btnNext.setOnAction(e -> {
                                
            boolean flagFNum = true;
            boolean flagFSource = true;
            boolean flagFDest = true;
            boolean flagDate = true;

            // remove all radio boxes
            gridPane.getChildren().removeAll(rb1,rb2,rb3,rb4,rb5,errorFare);

            // get text field values
            String flightNumber = tfFlightNumber.getText();
            String source = tfSource.getText();
            String destination = tfDestination.getText();
            String travelDate = tfTravelDate.getText();
        
            // reset errors
            errorNum.setText("");
            errorSrc.setText("");
            errorDest.setText("");
            errorDate.setText("");
        
            // flight number validation
            if (!flightNumber.matches("[A-Z]{2,5}[0-9]{2,5}+$")) {
                flagFNum = false;
                errorNum.setText("Invalid flight number format");
            }
                    
            // flight source validation
            if (!source.matches("[A-Za-z]{3,15}+$")) {
                flagFSource = false;
                errorSrc.setText("Min 3 characters long");
            }
            
            // flight destination validation
            if (!destination.matches("[A-Za-z]{3,15}+$")) {
                flagFDest = false;
                errorDest.setText("Min 3 characters long");
            } 
            else if(source.equals(destination)) {                
                flagFDest = false;
                errorDest.setText("Source and destination should be different");
            }
                         
            try {          
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date userDate = sdf.parse(travelDate);
                Date toDay = new Date();

                String[] dateArr = travelDate.split("/");
                int day = Integer.parseInt(dateArr[0]);
                int month = Integer.parseInt(dateArr[1]);
                int year = Integer.parseInt(dateArr[2]);  
                
                if(userDate.before(toDay)){
                    flagDate = false;
                    errorDate.setText("You can not enter past date.");
                }
                else if((day < 1 || day > 31 )){
                    flagDate = false;
                    errorDate.setText("Enter valid future date.");
                }
                else if(month < 1 || month > 12){
                    flagDate = false;   
                    errorDate.setText("Enter valid future date.");
                }
                else if(year > 2017 || year < 2015){
                    flagDate = false;
                    errorDate.setText("Enter valid future date.");
                }
            }
            catch(ParseException ex){                                    
                flagDate = false;                
                errorDate.setText("Invalid Date Format!");
            }
            
            // flight fare calculation
            if(flagFNum && flagFSource && flagFDest && flagDate)
            {                                                           
                // set radio box values
                rb1.setText("$ " + this.fareArr[0] + "- Flight via Chicago");
                rb2.setText("$ " + this.fareArr[1] + "- Flight via London");
                rb3.setText("$ " + this.fareArr[2] + "- Flight via New Jersey");
                rb4.setText("$ " + this.fareArr[3] + "- Flight via Dubai");
                rb5.setText("$ " + this.fareArr[4] + "- Flight via Paris");

                gridPane.add(rb1, 1, 6);
                gridPane.add(rb2, 1, 7);
                gridPane.add(rb3, 1, 8);
                gridPane.add(rb4, 1, 9);
                gridPane.add(rb5, 1, 10);
        
                rb1.setToggleGroup(tgroup);
                rb2.setToggleGroup(tgroup);
                rb3.setToggleGroup(tgroup);
                rb4.setToggleGroup(tgroup);
                rb5.setToggleGroup(tgroup);
                                
                double totalFare = 0.0;                    
                errorFare.setText("Select one flight from the given list"); 
                
                if (tgroup.getSelectedToggle() != null) {
                    String flightFare = tgroup.getSelectedToggle().toString();
                    if(flightFare.contains("Flight via Chicago")){
                        totalFare = this.fareArr[0];
                    }
                    else if(flightFare.contains("Flight via London")){
                        totalFare = this.fareArr[1];
                    }
                    else if(flightFare.contains("Flight via New Jersey")){
                        totalFare = this.fareArr[2];
                    }
                    else if(flightFare.contains("Flight via Dubai")){
                        totalFare = this.fareArr[3];
                    }
                    else if(flightFare.contains("Flight via Paris")){
                        totalFare = this.fareArr[4];
                    }
                    totalFare = totalFare + (totalFare * 0.13);
                    
                    // Overwriting all existing flight details with new user Input 
                    this.dFlight = new Dhruti_Flight(flightNumber, source, destination, totalFare, travelDate);
                    
                    this.displayScene2();
               }
               else{
                   errorFare.setTextFill(Color.RED);
                   gridPane.getChildren().add(errorFare);
               }
            }                                                        
        });
        
        // add fields to grid
        root.getChildren().add(btnNext);
        root.getChildren().add(gridPane);
        root.getChildren().add(txtMain);

        // display given scene
        this.primaryStage.setScene(new Scene(root, 600, 550, Color.WHITE));
        this.primaryStage.show();
    }

    public void displayScene2() {    

        // define form elements
        CheckBox chkYes = new CheckBox("Yes");
        Label lblFreqFly = new Label("Frequent Flyer Number(size 5)");
        Text txtMain = new Text("Passenger Information Form");
        
        // welcome text positioning
        txtMain.setX(200);
        txtMain.setY(120);  
        txtMain.setFill(Color.BLACK);
        txtMain.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        
        // set Text variables as null string using object properties
        String tfFreqFlyNumText  = dFreqPass.GetFreqFlyerNum();
        String tfFirstNameText   = dFreqPass.GetFirstName();
        String tfLastNameText    = dFreqPass.GetLastName();
        String tfAgeText         = dFreqPass.GetAge();
        
        // define form textbox
        TextField tfFirstName = new TextField(tfFirstNameText);
        TextField tfLastName = new TextField(tfLastNameText);
        TextField tfAge = new TextField(tfAgeText);
        TextField tfFreqFlyNum = new TextField(tfFreqFlyNumText);
    
        // text field alignment
        tfFirstName.setAlignment(Pos.BOTTOM_RIGHT);
        tfLastName.setAlignment(Pos.BOTTOM_RIGHT);
        tfAge.setAlignment(Pos.BOTTOM_RIGHT);
        tfFreqFlyNum.setAlignment(Pos.BOTTOM_RIGHT);
        
        // define error labels
        Label errorFirstName = new Label("");
        Label errorLastName = new Label("");
        Label errorAge = new Label("");
        Label errorFreqNum = new Label("");
    
        // define label color
        errorFirstName.setTextFill(Color.RED);
        errorLastName.setTextFill(Color.RED);
        errorAge.setTextFill(Color.RED);
        errorFreqNum.setTextFill(Color.RED);
        
        // define root
        Group root = new Group();              
        GridPane gridPane = new GridPane();

        // define header
        this.getHeader(root);

        // prev/next buttons
        Button btnPrev = new Button("Previous");
        Button btnNext = new Button("Display Ticket");

        // button positions
        btnPrev.setLayoutX(420);
        btnPrev.setLayoutY(510);
        btnNext.setLayoutX(500);
        btnNext.setLayoutY(510);
        
        // add things to grid pane
        gridPane.setPadding(new Insets(160, 30, 30, 30));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(new Label("First Name"), 0, 0);
        gridPane.add(tfFirstName, 1, 0);
        gridPane.add(new Label("Last Name"), 0, 1);
        gridPane.add(tfLastName, 1, 1);
        gridPane.add(new Label("Age(between 3 and 100)"), 0, 2);
        gridPane.add(tfAge, 1, 2);
        gridPane.add(new Label("Frequent Flyer ?"), 0, 3);
        gridPane.add(chkYes, 1, 3);
        gridPane.add(errorFirstName, 2, 0);
        gridPane.add(errorLastName, 2, 1);
        gridPane.add(errorAge, 2, 2);  
        gridPane.add(errorFreqNum, 2, 4);
        
        // checkbox validation
        chkYes.setOnAction(e -> {
            if (chkYes.isSelected()) {
                gridPane.add(lblFreqFly, 0, 4);
                gridPane.add(tfFreqFlyNum, 1, 4);
            }
            else {
                gridPane.getChildren().removeAll(lblFreqFly,tfFreqFlyNum);
            }
        });
        
        // button next event
        btnNext.setOnAction(e -> {
            
            // reset errors
            errorAge.setText("");
            errorFreqNum.setText("");
            errorLastName.setText("");
            errorFirstName.setText("");
        
            // get text values
            String age = tfAge.getText();
            String firstName = tfFirstName.getText();
            String lastName = tfLastName.getText();
            String freqNum = tfFreqFlyNum.getText();
            
            // set default flags
            boolean flagFn = true;
            boolean flagLn = true;
            boolean flagAge = true;
            boolean flagFreNum = true;

            if (!firstName.matches("[A-Za-z]{3,15}+$")) {
                flagFn = false;
                errorFirstName.setText("Invalid !");
            }
            
            if (!lastName.matches("[A-Za-z]{3,15}+$")) {
                flagLn = false;
                errorLastName.setText("Invalid !");
            }
            
            if(!age.matches("[0-9]+$")){
                flagAge = false;
                errorAge.setText("Only numeric value allowed.");
            }
            else if(Integer.parseInt(age) <= 3 || Integer.parseInt(age) >= 100){
               flagAge = false;
               errorAge.setText("Age out of range!");
            }
            
            if(chkYes.isSelected() && !freqNum.matches("[0-9]{5}$")){
                flagFreNum = false;
                errorFreqNum.setText("Only numeric value allowed");
            }
            
            // validation pass for text fields
            if(flagFn && flagLn && flagAge && flagFreNum) {                  
                if(chkYes.isSelected()) {                   
                     this.dFreqPass = new Dhruti_FrequentFlyer_Passenger(firstName, lastName, age, freqNum);
                     this.dTravel = new Dhruti_TravelReservation(this.dFlight, this.dFreqPass);
                }
                else {
                     this.dPass = new Dhruti_Passenger(firstName, lastName, age);
                     this.dTravel = new Dhruti_TravelReservation(this.dFlight, this.dPass);
                }
                this.flagReservation = true;
                this.displayScene3();
            }        
        });
        
        // button previous event
        btnPrev.setOnAction(e -> {
            this.displayScene1();
        });
        
        // add things to gri
        root.getChildren().add(gridPane);
        root.getChildren().add(txtMain);               
        root.getChildren().add(btnNext);
        root.getChildren().add(btnPrev);

        // display current scene
        this.primaryStage.setScene(new Scene(root, 600, 550, Color.WHITE));
        this.primaryStage.show();
    }

    public void displayScene3() {    
        // define root/pane
        Group root = new Group();              
        GridPane gridPaneFlight = new GridPane();
        GridPane gridPaneCust = new GridPane();
        
        // welcome texts
        Text flightTicket = new Text("Flight Ticket");
        Text flightInfo = new Text("Flight Details");
        Text flight = new Text("Flight");
        Text from = new Text("From");
        Text to = new Text("To");
        Text onDate = new Text("On Date");
        Text custInfo = new Text("Passenger Details");        
        Text name = new Text("Name");
        Text age = new Text("Age");
        Text freqNum = new Text("");
        Text miles = new Text("");
        Text fare = new Text("Total Fare(including 13% HST)");
       
        //converts fare value to 2 decimal places
        String fareString = new DecimalFormat("$.##").format(dTravel.GetDFlight().GetFlightFare());
        
        // gather information we recieved earlier
        String valFlightText = ": " + dTravel.GetDFlight().GetFlightNumber();
        String valSrcText    = ": " + dTravel.GetDFlight().GetSource();
        String valDestText   = ": " + dTravel.GetDFlight().GetDestination();
        String valDateText   = ": " + dTravel.GetDFlight().GetTravelDate();
        String valFareText   = ": " + fareString;
        
        String valAgeText = "";
        String valFnameText = "";
        String valLnameText = "";
        String valFreqNumText = "";
        String valMilesText   = "";
        
        if(dTravel.GetDPass() == null){
            valAgeText = ": " + dTravel.GetDFreqPass().GetAge();
            valFnameText = ": " + dTravel.GetDFreqPass().GetFirstName();
            valLnameText =  dTravel.GetDFreqPass().GetLastName();
            valFreqNumText = ": " + dTravel.GetDFreqPass().GetFreqFlyerNum();
            valMilesText   = ": " + String.valueOf(dTravel.GetDFreqPass().GetMiles());
            freqNum.setText("Frequent Flyer");
            miles.setText("Total Miles");
        }
        else{
            valAgeText = ": " + dTravel.GetDPass().GetAge();
            valFnameText = ": " + dTravel.GetDPass().GetFirstName();
            valLnameText  = dTravel.GetDPass().GetLastName();  
        }       
        
        // text fields    
        Text valFlight = new Text(valFlightText);
        Text valSrc = new Text(valSrcText);
        Text valDest = new Text(valDestText);
        Text valDate = new Text(valDateText);
        Text valFname = new Text(valFnameText);
        Text valLname = new Text(valLnameText);
        Text valAge = new Text(valAgeText);
        Text valFreqNum = new Text(valFreqNumText);
        Text valMiles = new Text(valMilesText);
        Text valFare = new Text(valFareText);
    
        // position text box
        flightTicket.setX(240);
        flightTicket.setY(120);  
        flightTicket.setFill(Color.BLACK);
        flightTicket.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        flightInfo.setX(30);
        flightInfo.setY(150);   
        flightInfo.setFill(Color.DEEPSKYBLUE);
        flightInfo.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        custInfo.setX(30);
        custInfo.setY(330);
        custInfo.setFill(Color.DEEPSKYBLUE);
        custInfo.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        
        // define header
        this.getHeader(root);

        // flight grid pane
        gridPaneFlight.setPadding(new Insets(180, 30, 30, 30));
        gridPaneFlight.setHgap(10);
        gridPaneFlight.setVgap(10);
        
        gridPaneFlight.add(flight, 0, 0);
        gridPaneFlight.add(from, 0, 1);
        gridPaneFlight.add(to, 0, 2);
        gridPaneFlight.add(onDate, 0, 3);
        gridPaneFlight.add(valFlight, 1, 0);
        gridPaneFlight.add(valSrc, 1, 1);
        gridPaneFlight.add(valDest, 1, 2);
        gridPaneFlight.add(valDate, 1, 3);
        root.getChildren().add(flightTicket);
        root.getChildren().add(flightInfo);
        root.getChildren().add(gridPaneFlight);
        
        gridPaneCust.setPadding(new Insets(360, 30, 30, 30));
        gridPaneCust.setHgap(10);
        gridPaneCust.setVgap(10);
        
        gridPaneCust.add(name, 0, 0);
        gridPaneCust.add(age, 0, 1);
        gridPaneCust.add(fare, 0, 2);
        gridPaneCust.add(freqNum, 0, 3);
        gridPaneCust.add(miles, 0, 4);
              
        gridPaneCust.add(valFname, 1, 0);
        gridPaneCust.add(valLname, 2, 0);
        gridPaneCust.add(valAge, 1, 1);
        gridPaneCust.add(valFare, 1, 2);
        gridPaneCust.add(valFreqNum, 1, 3);
        gridPaneCust.add(valMiles, 1, 4);
        
        // previous button
        Button btnHome = new Button("Back To Home");
        
        // position previous button
        btnHome.setLayoutX(480);
        btnHome.setLayoutY(510);
                
        // button previous event
        btnHome.setOnAction(e -> {
            if(this.flagReservation)
            {  this.flightReservations.add(this.dTravel);}
            this.displayHomeScene();
        });
        
        // append to root
        root.getChildren().add(btnHome);
        root.getChildren().add(custInfo);
        root.getChildren().add(gridPaneCust);
        
        // display current scene
        this.primaryStage.setScene(new Scene(root, 600, 550, Color.WHITE));
        this.primaryStage.show();
    }
    
    public void getHeader(Group root) {     
                
        // define all text fields
        InnerShadow is = new InnerShadow();                
        Rectangle rectangle = new Rectangle();
        Rectangle rectangle1 = new Rectangle();
        Rectangle rectangle2 = new Rectangle();
        Text title = new Text("FlightHub");
        
        // adjust buttons
        is.setOffsetX(1.0f);
        is.setOffsetY(1.0f);
        title.setX(200);
        title.setY(60);
        title.setEffect(is);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
        title.setFill(Color.DEEPSKYBLUE);

        rectangle1.setWidth(600);
        rectangle1.setHeight(15);
        rectangle1.setFill(Color.DEEPSKYBLUE);
        
        rectangle.setWidth(600);
        rectangle.setHeight(70);
        rectangle.setY(15);
        rectangle.setFill(Color.WHITE);
        
        rectangle2.setWidth(600);
        rectangle2.setHeight(15);
        rectangle2.setY(85);
        rectangle2.setFill(Color.DEEPSKYBLUE);
        
        root.getChildren().add(rectangle1); 
        root.getChildren().add(rectangle);
        root.getChildren().add(rectangle2);        
        root.getChildren().add(title);
    }    
}
