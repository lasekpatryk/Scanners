package com.Windows;

import com.Controller.FXMLImageMenuController;
import com.Controller.FXMLListMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window extends Application {


    public void run(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader listMenuLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent listMenu = listMenuLoader.load();
        Scene listMenuScene = new Scene(listMenu);


        FXMLLoader imageMenuLoader = new FXMLLoader(getClass().getResource("ImageMenu.fxml"));
        Parent imageMenu = imageMenuLoader.load();
        Scene imageMenuScene = new Scene(imageMenu);


        FXMLListMenuController listMenuController = (FXMLListMenuController) listMenuLoader.getController();
        listMenuController.setImageMenuScene(imageMenuScene);

        FXMLImageMenuController imageMenuController = (FXMLImageMenuController) imageMenuLoader.getController();
        imageMenuController.setlistMenuScene(listMenuScene);

        primaryStage.setScene(listMenuScene);
        primaryStage.show();

    }
}
