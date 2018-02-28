package br.ufrpe.easyestacionamento.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainGuiController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
    	Pane loginPane = FXMLLoader.load(getClass()
                .getResource("/br/ufrpe/easyestacionamento/gui/telaLogin.fxml"));
        
        Scene scene = new Scene(loginPane);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Easy System - Login");
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
