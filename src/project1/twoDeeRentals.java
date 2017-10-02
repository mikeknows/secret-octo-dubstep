/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import javafx.geometry.Insets;

import static java.util.Collections.list;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static project1.Project1.list;

/**
 *
 * @author Mike
 */
public class twoDeeRentals extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ListView listView = new ListView(FXCollections.observableArrayList(list));
        TextField field = new TextField();
        TextField field2 = new TextField();
        field.setPromptText("Enter Title");
        field2.setPromptText("Enter Format");
        Button addBtn = new Button();
        Button removeBtn = new Button();
        addBtn.setText("Add item");
        removeBtn.setText("Remove Item");

        field.setOnKeyPressed((KeyEvent e) -> {//just doing some testing here
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    field.clear();
                    project1.Project1.add(field.getText(), field2.getText(), list);                   
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(twoDeeRentals.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    field.setPromptText("Media Inserted");
                }
            }
        });
        addBtn.setOnMouseClicked((MouseEvent e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                project1.Project1.add(field.getText(), field2.getText(), list);
            }
        });
         removeBtn.setOnMouseClicked((MouseEvent e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                project1.Project1.remove(field.getText(), list);
            }
        });
         
        BorderPane border = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints(500);
        ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
        column2.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(column1, column2);
        GridPane.setHalignment(addBtn, HPos.RIGHT);
        GridPane.setValignment(addBtn, VPos.TOP);
        GridPane.setHalignment(field, HPos.RIGHT);
        GridPane.setValignment(field, VPos.BOTTOM);
        GridPane.setHalignment(removeBtn, HPos.RIGHT);
        gridPane.add(field, 0, 2);
        gridPane.add(field2, 0, 3);
        gridPane.add(addBtn, 0, 4);
        gridPane.add(removeBtn, 0, 5);
        final FlowPane listPane = new FlowPane();
        listPane.setHgap(100);
        listPane.setPrefHeight(40);
        gridPane.add(listView, 0, 1);
        GridPane.setHalignment(listView, HPos.LEFT);
       
        StackPane root = new StackPane();
       

        Scene scene = new Scene(gridPane, 600, 250, Color.WHITESMOKE);

        

        primaryStage.setTitle("JavaFX Rentals");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
// btn.setOnMouseClicked(new EventHandler<MouseEvent>()
