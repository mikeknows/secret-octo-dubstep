/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4a;

import javafx.geometry.Insets;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Mike
 */
public class Lab4a extends Application {

    TextField field = new TextField();
    FlowPane mainPane = new FlowPane();
    Label typeQuestion = new Label("<Type your question and press the button>");
    Label question = new Label("What is your question?");
    Label answer = new Label("probably random string here");
    Button openBtn = new Button("Ask the Magic Eight Ball");
    Button closeBtn = new Button("Close");
    Stage stage;
    Scene mainScreen = new Scene(mainPane, 300, 200);
    Stage popUpStage = new Stage();

    @Override
    public void start(Stage primaryStage) {
        
        stage = primaryStage;
        field.setTranslateX(130);
        field.setTranslateY(-90);
        question.setTranslateY(-50);
        question.setTranslateX(0);
        typeQuestion.setTranslateY(0);
        openBtn.setTranslateY(50);
        mainPane.getChildren().addAll(openBtn, question, field, typeQuestion);
        mainPane.setPadding(new Insets(15));
        FlowPane popUpPane = new FlowPane();
        popUpPane.getChildren().addAll(answer, closeBtn);
        mainPane.setHgap(20);
        mainPane.setVgap(20);
        popUpPane.setHgap(20);
        popUpPane.setVgap(20);
        
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        Scene popUp = new Scene(popUpPane, 150, 150);
        mainPane.setStyle("-fx-background-color:yellow;");
        popUpPane.setStyle("-fx-background-color:blue;");
        closeBtn.setOnAction(e -> close(e));
        openBtn.setOnMouseClicked((MouseEvent e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                
                popUpStage.setScene(popUp);
                popUpStage.show();

            }
        }
        );

        primaryStage.setTitle("Magic Eight Ball");
        primaryStage.setScene(mainScreen);
        primaryStage.show();

    }

    public void close(ActionEvent e) {
        if (e.getTarget() == closeBtn) {
            popUpStage.close();
        } else {
            popUpStage.showAndWait();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

}
