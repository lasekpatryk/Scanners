package com.Windows;

import com.Api.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.io.IOException;

public class Popups {

    public static void updateWindow(Scanner scanner) throws IOException {

        Stage updateStage = new Stage();


        Button acceptScannerForSecurityButton = new Button();
        acceptScannerForSecurityButton.setMnemonicParsing(false);
        acceptScannerForSecurityButton.setOnMouseClicked(mouseEvent -> {
            HttpResponse<JsonNode> request = Unirest.patch("http://131.2.33.8:8080/scanners/" + scanner.getId())
                    .header("accept", "application/json")
                    .field("id", scanner.getId())
                    .field("loggedUser", "Portiernia")
                    .asJson();

            if (request.isSuccess()){
                updateStage.close();
            } else {
                Popups.connectionErrorWindow();
            }
        });
        acceptScannerForSecurityButton.setOnMouseEntered(event->
                acceptScannerForSecurityButton.setStyle("-fx-background-color: #ffffff; " +
                        "-fx-background-radius: 20;" +
                        " -fx-border-radius: 20;"));
        acceptScannerForSecurityButton.setOnMouseExited(event ->
                acceptScannerForSecurityButton.setStyle("-fx-background-color: #40ff76;" +
                        " -fx-background-radius: 20;" +
                        " -fx-border-radius: 20;"));
        acceptScannerForSecurityButton.setPrefHeight(27);
        acceptScannerForSecurityButton.setPrefWidth(116);
        acceptScannerForSecurityButton.setStyle("-fx-background-color: #40ff76;" +
                " -fx-background-radius: 20;" +
                " -fx-border-radius: 20;" +
                " -fx-font-size:13");
        acceptScannerForSecurityButton.setText("Przyjmij skaner");
        acceptScannerForSecurityButton.setTextAlignment(TextAlignment.CENTER);

        Button giveOutScanner = new Button();
        giveOutScanner.setMnemonicParsing(false);

        giveOutScanner.setOnMouseEntered(event->
                giveOutScanner.setStyle("-fx-background-color: #ffffff; " +
                        "-fx-background-radius: 20;" +
                        " -fx-border-radius: 20;"));
        giveOutScanner.setOnMouseExited(event ->
                giveOutScanner.setStyle("-fx-background-color: #40ff76;" +
                        " -fx-background-radius: 20;" +
                        " -fx-border-radius: 20;"));
        giveOutScanner.setPrefHeight(27);
        giveOutScanner.setPrefWidth(116);
        giveOutScanner.setStyle("-fx-background-color: #40ff76;" +
                " -fx-background-radius: 20;" +
                " -fx-border-radius: 20;" +
                " -fx-font-size:13");
        giveOutScanner.setText("Wydaj skaner");
        giveOutScanner.setTextAlignment(TextAlignment.CENTER);



        HBox buttonHBox = new HBox(acceptScannerForSecurityButton, giveOutScanner);
        buttonHBox.setAlignment(Pos.CENTER_LEFT);
        buttonHBox.setPrefHeight(39);
        buttonHBox.setPrefWidth(600);
        buttonHBox.setStyle("-fx-background-color: #3ae86b");
        buttonHBox.setMargin(giveOutScanner, new Insets(10));
        buttonHBox.setMargin(acceptScannerForSecurityButton, new Insets(10));


        ////////////////////////////////////////////////////////////////////////////

        Label numberInfoLabel = new Label("Numer skanera");
        numberInfoLabel.setAlignment(Pos.CENTER);
        numberInfoLabel.setContentDisplay(ContentDisplay.CENTER);
        numberInfoLabel.setPrefHeight(19);
        numberInfoLabel.setPrefWidth(109);
        numberInfoLabel.setTextAlignment(TextAlignment.CENTER);
        numberInfoLabel.setPadding(new Insets(0,20,0,0));
        numberInfoLabel.setStyle("-fx-font-size: 13");

        Label scannerNumberLabel = new Label(String.valueOf(scanner.getId()));
        scannerNumberLabel.setAlignment(Pos.CENTER);
        scannerNumberLabel.setStyle("-fx-font-family:System Bold;" +
                "-fx-fot-size: 14");



        VBox scannerNumberVBox = new VBox(numberInfoLabel, scannerNumberLabel);
        scannerNumberVBox.setAlignment(Pos.CENTER);
        scannerNumberVBox.setPrefHeight(51);
        scannerNumberVBox.setPrefWidth(115);

////////////////////////////////////////////////////////////////////////////////////

        Label userInfoLabel = new Label("Wpisz POL");
        userInfoLabel.setAlignment(Pos.CENTER);
        userInfoLabel.setPrefHeight(17);
        userInfoLabel.setPrefWidth(99);
        userInfoLabel.setTextAlignment(TextAlignment.CENTER);
        userInfoLabel.setStyle("-fx-font-family: System Bold;" +
                "-fx-font-size: 14;");

        TextField loggedUserTextField = new TextField();
        loggedUserTextField.setOnKeyPressed(keyEvent ->{

            if (keyEvent.getCode().toString().equals("ENTER")) {

                HttpResponse<JsonNode> request = Unirest.patch("http://131.2.33.8:8080/scanners/" + scanner.getId())
                        .header("accept", "application/json")
                        .field("id", scanner.getId())
                        .field("loggedUser", loggedUserTextField.getText())
                        .asJson();
                if (request.isSuccess()){
                    updateStage.close();
                } else {
                    Popups.connectionErrorWindow();
                }
            }
        });
        loggedUserTextField.setPrefHeight(25);
        loggedUserTextField.setPrefWidth(100);
        loggedUserTextField.setPadding(new Insets(0,0,0,40));

        giveOutScanner.setOnMouseClicked(mouseEvent -> {
            HttpResponse<JsonNode> request = Unirest.patch("http://131.2.33.8:8080/scanners/" + scanner.getId())
                    .header("accept", "application/json")
                    .field("id", scanner.getId())
                    .field("loggedUser", loggedUserTextField.getText())
                    .asJson();
            if (request.isSuccess()){
                updateStage.close();
            } else {
                Popups.connectionErrorWindow();
            }
        });

        VBox scannerUserVBox = new VBox(userInfoLabel, loggedUserTextField);
        scannerUserVBox.setPrefHeight(61);
        scannerUserVBox.setPrefWidth(116);
//////////////////////////////////////////////////////////////////////////
        HBox scannerUpdateHBox = new HBox(scannerNumberVBox, scannerUserVBox);
        scannerUpdateHBox.setAlignment(Pos.CENTER);
        scannerUpdateHBox.setPrefHeight(276);
        scannerUpdateHBox.setPrefWidth(600);
        scannerUpdateHBox.setPadding(new Insets(40,50,40,40));


        BorderPane mainPain = new BorderPane();
        mainPain.setPrefWidth(371);
        mainPain.setPrefHeight(161);
        mainPain.setTop(buttonHBox);
        mainPain.setCenter(scannerUpdateHBox);




        Scene updateScene = new Scene(mainPain);
        updateStage.setScene(updateScene);
        updateStage.show();
    }

    public static void scannerInServiceWindow(){
        Label errorLabel = new Label("Skaner w serwisie!!!");
        errorLabel.setStyle("-fx-background-color: #40ff76; -fx-font-size: 20px");
        Scene errorscene = new Scene(errorLabel);
        Stage errorStage = new Stage();
        errorStage.setScene(errorscene);
        errorStage.show();
    }

    public static void connectionErrorWindow(){
        Label errorLabel = new Label("Błąd połączenia z serwerem, skontaktuj się z local IT");
        errorLabel.setStyle("-fx-background-color: #40ff76; -fx-font-size: 20px");
        Scene errorscene = new Scene(errorLabel);
        Stage errorStage = new Stage();
        errorStage.setScene(errorscene);
        errorStage.show();
    }

    public static void fileNotFoundWindow(){
        Label errorLabel = new Label("Brak skanera o podanym numerze");
        errorLabel.setStyle("-fx-background-color: #40ff76; -fx-font-size: 20px");
        Scene errorscene = new Scene(errorLabel);
        Stage errorStage = new Stage();
        errorStage.setScene(errorscene);
        errorStage.show();
    }

}
