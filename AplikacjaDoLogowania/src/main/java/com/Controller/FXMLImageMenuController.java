package com.Controller;

import com.Api.Scanner;
import com.Style.ButtonColorsEnum;
import com.Windows.Popups;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;

public class FXMLImageMenuController {

    public Button changeToListMenuButton;
    public GridPane gridPane;
    public ScrollPane scrollPane;
    private Scene listMenuScene;


    @FXML
    public void initialize(){

        scrollPane.setFitToWidth(true);

        int buttonNumber = 1;

        for (int i = 0; i < gridPane.getRowCount(); i++) {

            for (int j = 0; j < gridPane.getColumnCount(); j++) {

                Button button = new Button(String.valueOf(buttonNumber));
                button.setBackground(new Background(new BackgroundFill(Paint.valueOf(ButtonColorsEnum.getColor(buttonNumber)), CornerRadii.EMPTY, Insets.EMPTY)));
                button.setStyle("-fx-min-width:100px;" +
                        "-fx-mix-height: 30;");
                int finalButtonNumber = buttonNumber;
                button.setOnAction((actionEvent) ->{
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.findAndRegisterModules();
                    try {
                        Scanner scanner = mapper.readValue(new URL("http://131.2.33.8:8080/scanners/" + finalButtonNumber), new TypeReference<>() {
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
                });
                gridPane.add(button, j, i);
                buttonNumber++;
            }

        }

    }

    public void setlistMenuScene(Scene listMenuScene){
        this.listMenuScene = listMenuScene;
    }

    public void onMouseClickedChangeToListMenu(MouseEvent mouseEvent) {

        Stage primaryStage = (Stage)((Parent)mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(listMenuScene);

    }

    public void onMouseEntered(MouseEvent mouseEvent) {

        changeToListMenuButton.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 20; -fx-border-radius: 20;");

    }

    public void onMouseExited(MouseEvent mouseEvent) {

        changeToListMenuButton.setStyle("-fx-background-color: #40ff76; -fx-background-radius: 20; -fx-border-radius: 20;");

    }

}
