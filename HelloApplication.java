package com.example.programming_3_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {

    static boolean passwordRequired = true;
    DatabaseManager db;
    Style styles = new Style();
    @Override
    public void start(Stage stage) throws IOException {



        //Label setup
        Label titleLabel = new Label("Please sign in to the database to access its data");
        Label usernameLabel = new Label("User:");
        Label passwordLabel = new Label("Password:");
        Label errorLabel = new Label("Error, could not connect to the database. Password and/or username may be incorrect");

        //TextField setup
        TextField username = new TextField("Enter username");
        TextField password = new PasswordField();

        //Button setup
        Button loginButton = new Button("Login");

        //Gridpane Setup
        GridPane loginPane = new GridPane();
        loginPane.add(usernameLabel, 0 , 0);
        loginPane.add(username, 1, 0);

        loginPane.add(passwordLabel, 0, 1);
        loginPane.add(password, 1, 1);

        VBox loginPage = new VBox();

        ArrayList<Label> labels = new ArrayList<Label>();
        labels.add(titleLabel);
        labels.add(usernameLabel);
        labels.add(passwordLabel);
        labels.add(errorLabel);

        styles.stylize(labels);

        loginPage.setStyle("-fx-background-color: #222222;");

        //VBox setup
        loginPage.getChildren().add(titleLabel);
        loginPage.getChildren().add(loginPane);
        loginPage.getChildren().add(loginButton);

        //Scene setup

        Scene loginScene = new Scene(loginPage);

        //Labels
        Label mainPageTitle = new Label("Search and Add");
        Label searchLabel = new Label("Search for a city's weather");

        Label addCityLabel = new Label("Add a new city's temperature");

        Label cityNameLabel = new Label("City name");
        Label countryNameLabel = new Label("Country of the city");
        Label temperatureLabel = new Label("Temperature:");
        Label feelLabel = new Label("What is the weather like?:");
        Label humidityLabel = new Label("Humidity:");
        Label result = new Label();

        //Text Fields
        TextField search = new TextField("Enter a city name:");
        TextField cityName = new TextField("Enter a city name:");
        TextField countryName = new TextField("");
        TextField temperature = new TextField("");
        TextField feel = new TextField("");
        TextField humidity = new TextField("");

        //Button Setup
        Button searchButton = new Button("Search");
        Button addButton = new Button("Add");

        //Gridpane Setup
        GridPane searchPane =  new GridPane();

        searchPane.add(searchLabel, 0, 0);
        searchPane.add(search, 1, 0);

        GridPane addPane = new GridPane();

        addPane.add(cityNameLabel, 0, 0);
        addPane.add(cityName, 1, 0);
        addPane.add(countryNameLabel, 0 , 1);
        addPane.add(countryName, 1, 1);
        addPane.add(temperatureLabel, 0, 2);
        addPane.add(temperature, 1, 2);
        addPane.add(feelLabel, 0 ,3);
        addPane.add(feel, 1, 3);
        addPane.add(humidityLabel, 0, 4);
        addPane.add(humidity, 1, 4);

        //VBox Setup
        VBox mainPage = new VBox();

        mainPage.getChildren().add(mainPageTitle);
        mainPage.getChildren().add(searchPane);
        mainPage.getChildren().add(searchButton);
        mainPage.getChildren().add(addCityLabel);
        mainPage.getChildren().add(addPane);
        mainPage.getChildren().add(addButton);
        mainPage.getChildren().add(result);

        //Scene Setup
        //Gridpane
        styles.alignGridBox(loginPane);

        //VBox
        styles.alignVBox(loginPage);
        Scene mainScene = new Scene(mainPage);

        styles.alignGridBox(searchPane);

        styles.alignGridBox(addPane);

        mainPage.setStyle("-fx-background-color: #222222");
        styles.alignVBox(mainPage);

        ArrayList<Label> args = new ArrayList<Label>();
        args.add(mainPageTitle);
        args.add(searchLabel);
        args.add(addCityLabel);
        args.add(cityNameLabel);
        args.add(countryNameLabel);
        args.add(temperatureLabel);
        args.add(feelLabel);
        args.add(humidityLabel);


        styles.stylize(args);

        result.setStyle("-fx-text-fill: red; -fx-background-color: green");

        mainPage.setStyle("-fx-background-color: #222222");


        if(passwordRequired == true)
            stage.setScene(loginScene);
        else{
            db = new DatabaseManager("root", "49!pw^!Y8@P!Q^7%");
            db.connectToDB();
            stage.setScene(mainScene);
        }
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String usernameInput = username.getText();
                String passwordInput = password.getText();
                db = new DatabaseManager(usernameInput, passwordInput);
                db.connectToDB();

                if(db.getConnectionStatus() == false){
                    loginPage.getChildren().add(errorLabel);
                    errorLabel.setAlignment(Pos.BOTTOM_RIGHT);
                }

                else if(db.getConnectionStatus() == true){
                    stage.setScene(mainScene);
                }
            }
        });

        stage.show();

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                result.setText(db.query(search.getText()));
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<String> args = new ArrayList<String>();

                args.add(cityName.getText());
                args.add(countryName.getText());
                args.add(temperature.getText());
                args.add(feel.getText());
                args.add(humidity.getText());

                result.setText(db.addElement(args));
            }
        });
    }

    public static void main(String[] args) {
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("nopassword")){
                passwordRequired = false;
            }
        }
        launch();
    }

    @Override
    public void stop() throws Exception {
        db.closeDB();
        super.stop();
    }
}