package com.example.projectcompiler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CompilerProjectApplication extends Application {

    private TextField filePathField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PARSER CHECKER");

        Label titleLabel = new Label("PARSER CHECKER");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill:  #006CA5;");
        Button startButton = new Button("Start");
        startButton.setStyle("-fx-font-size: 18px; -fx-text-fill: #FFFFFF; -fx-background-color: #006CA5; -fx-pref-width: 140px; -fx-pref-height: 40px;");

        VBox firstPageLayout = new VBox(10, titleLabel, startButton);
        firstPageLayout.setStyle("-fx-background-color: #d0efff; -fx-padding: 20; -fx-pref-width: 600; -fx-pref-height: 400;");
        firstPageLayout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene firstScene = new Scene(firstPageLayout, 600, 400);

        Label chooseFileLabel = new Label("CHOOSE A FILE");
        chooseFileLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill:  #006CA5;");
        Button browseButton = new Button("Browse");
        browseButton.setStyle("-fx-font-size: 18px; -fx-text-fill: #FFFFFF; -fx-background-color: #006CA5; -fx-pref-width: 100px; -fx-pref-height: 40px;");
        filePathField = new TextField();
        filePathField.setStyle("-fx-pref-width: 200px;  -fx-pref-height: 40px;");

        browseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Controller controller=new Controller();
                filePathField.setText(controller.browseFile());
            }
        });

        VBox secondPageLayout = new VBox(10, chooseFileLabel, new VBox(10, browseButton, filePathField));
        secondPageLayout.setStyle("-fx-background-color: #d0efff; -fx-padding: 20; -fx-pref-width: 600; -fx-pref-height: 400;");
        secondPageLayout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene secondScene = new Scene(secondPageLayout, 600, 400);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(secondScene);
            }
        });

        primaryStage.setScene(firstScene);
        primaryStage.show();
    }
}

