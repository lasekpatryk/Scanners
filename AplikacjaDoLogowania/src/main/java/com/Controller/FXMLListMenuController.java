package com.Controller;

import com.Api.Scanner;
import com.Windows.Popups;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

public class FXMLListMenuController {


    public Button allScannersButton;
    public Button searchScannersButton;
    public Button serviceScannersButton;
    public TableView<Scanner> tableView;
    public TableColumn<Scanner, Integer> scannerNumberColumn;
    public TableColumn<Scanner, String> loggedColumn;
    public TableColumn<Scanner, LocalDateTime> dateColumn;
    public TableColumn<Scanner, CheckBox> serviceColumn;
    public Button changeToImageMenuButton;

    private Scene imageMenuScene;


    public void setImageMenuScene(Scene imageMenuScene){
        this.imageMenuScene = imageMenuScene;
    }




    @FXML
    public void initialize() {
        scannerNumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getScannerNumber()).asObject());
        loggedColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLoggedUser()));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfLogin"));
        serviceColumn.setCellValueFactory(arg0 -> {
            Scanner scanner = arg0.getValue();

            CheckBox checkBox = new CheckBox();
            checkBox.setDisable(true);
            checkBox.selectedProperty().setValue(scanner.isInService());
            return  new SimpleObjectProperty<>(checkBox);
        });

        onMouseClickedAllScannersButton();

    }

    public void onMouseEntered(MouseEvent mouseEvent) {

        if (allScannersButton.equals(mouseEvent.getSource())) {
            allScannersButton.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 20; -fx-border-radius: 20;");
        } else if (searchScannersButton.equals(mouseEvent.getSource())) {
            searchScannersButton.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 20; -fx-border-radius: 20;");
        } else if (serviceScannersButton.equals(mouseEvent.getSource())) {
            serviceScannersButton.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 20; -fx-border-radius: 20;");
        } else if (changeToImageMenuButton.equals(mouseEvent.getSource())) {
            changeToImageMenuButton.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 20; -fx-border-radius: 20;");
        }

        }

    public void onMouseExited(MouseEvent mouseEvent) {

        if (allScannersButton.equals(mouseEvent.getSource())) {
            allScannersButton.setStyle("-fx-background-color: #40ff76; -fx-background-radius: 20; -fx-border-radius: 20;");
        } else if (searchScannersButton.equals(mouseEvent.getSource())) {
            searchScannersButton.setStyle("-fx-background-color: #40ff76; -fx-background-radius: 20; -fx-border-radius: 20;");
        } else if (serviceScannersButton.equals(mouseEvent.getSource())) {
            serviceScannersButton.setStyle("-fx-background-color: #40ff76; -fx-background-radius: 20; -fx-border-radius: 20;");
        } else if (changeToImageMenuButton.equals(mouseEvent.getSource())) {
            changeToImageMenuButton.setStyle("-fx-background-color: #40ff76; -fx-background-radius: 20; -fx-border-radius: 20;");
        }

    }

    public void onMouseClickedAllScannersButton() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            List<Scanner> scannerList = mapper.readValue(new URL("http://131.2.33.8:8080/scanners"), new TypeReference<>() {
            });
            ObservableList<Scanner> observableList = FXCollections.observableArrayList(scannerList);

            tableView.setItems(observableList);

        } catch (ConnectException e) {

            Popups.connectionErrorWindow();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void onMouseClickedSearchScannerButton() {

        Label infoLabel = new Label("Wpisz numer skanera");

        infoLabel.setStyle(
                "-fx-aligment: center;" +
                "-fx_background-color: #baffce;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weigth: 3"
        );

        TextField scannerNumberField = new TextField();


        VBox vBox = new VBox();
        vBox.setStyle(
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 100px;" +
                "-fx-resizable:false;" +
                "-fx-background-color: #baffce;" +
                "-fx-padding: 10px");

        vBox.getChildren().addAll(infoLabel, scannerNumberField);

        Scene scene = new Scene(vBox);

        Stage searchingStage = new Stage();
        searchingStage.setScene(scene);
        searchingStage.show();

        scannerNumberField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().toString().equals("ENTER")){
                try{
                    setTableViewForSearch(Integer.parseInt(scannerNumberField.getText()));
                    searchingStage.hide();
                } catch (NumberFormatException e){

                    infoLabel.setText("Proszę wprowadzić cyfry");

                }
            }
        });

    }

    public void onMouseClickedInServiceScannersButton() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            List<Scanner> serviceList = mapper.readValue(new URL("http://131.2.33.8:8080/scanners/service/true"), new TypeReference<>() {
            });
            ObservableList<Scanner> observableList = FXCollections.observableArrayList(serviceList);

            tableView.setItems(observableList);

        } catch (ConnectException e) {
            Popups.connectionErrorWindow();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setTableViewForSearch(int scannerNumber){

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            Scanner scanner = mapper.readValue(new URL("http://131.2.33.8:8080/scanners/" + scannerNumber), new TypeReference<>() {
            });
            ObservableList<Scanner> observableList = FXCollections.observableArrayList(scanner);

            tableView.setItems(observableList);

        } catch (ConnectException e) {

            Popups.connectionErrorWindow();

        }catch (FileNotFoundException e){

            Popups.fileNotFoundWindow();

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void onMouseClickedChangeToImageMenu(MouseEvent mouseEvent) {

        Stage primaryStage = (Stage)((Parent)mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(imageMenuScene);

    }

    public void mouseClickedOnTableView(TableColumn.CellEditEvent<Scanner, Integer> scannerIntegerCellEditEvent) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            Scanner scanner = mapper.readValue(new URL("http://131.2.33.8:8080/scanners/" + scannerIntegerCellEditEvent.getRowValue().getScannerNumber()), new TypeReference<>() {
            });

            if (scanner.isInService()){

                Popups.scannerInServiceWindow();

            } else {
                Popups.updateWindow(scanner);
            }

        } catch (ConnectException e) {

            Popups.connectionErrorWindow();

        }catch (FileNotFoundException e){

            Popups.fileNotFoundWindow();

        } catch (IOException e){
            e.printStackTrace();
        }


    }
}
