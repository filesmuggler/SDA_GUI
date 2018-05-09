/*
************************************************************************
Copyright 2018 Krzysztof Stê¿a³a

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
************************************************************************
*/

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SDA_GUI extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SDA_GUI (beta)");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //Text scenetitle = new Text("Welcome");
        //scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        //grid.add(scenetitle, 0, 0, 2, 1);

        //display checkboxes to select if divide or merge
        //generate proper textfields and labels
        
        CheckBox if_divide = new CheckBox("Divide");
        grid.add(if_divide,0,1);
        CheckBox if_merge = new CheckBox("Merge");
        grid.add(if_merge,1,1);
        CheckBox if_encrypt = new CheckBox("Encrypt");
        grid.add(if_encrypt,0,2);
        CheckBox if_decrypt = new CheckBox("Decrypt");
        grid.add(if_decrypt,1,2);
        //CheckBox if_plain = new CheckBox("No encryption");
        //grid.add(if_plain,1,3);
        
        
        //Label userName = new Label("User Name:");
        //grid.add(userName, 0, 1);

        //TextField userTextField = new TextField();
        //grid.add(userTextField, 1, 1);

        //Label pw = new Label("Password:");
        //grid.add(pw, 0, 2);

        //PasswordField pwBox = new PasswordField();
        //grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Sign in button pressed");
            }
        });
        
        if_divide.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if_merge.setSelected(!newValue);
            	if_decrypt.setSelected(false);
            }
        });

        if_merge.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if_divide.setSelected(!newValue);
            	if_encrypt.setSelected(false);
            }
        });
        
        if_encrypt.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if_decrypt.setSelected(false);
            	//if_plain.setSelected(!newValue);
            	if_merge.setSelected(false);
            }
        });

        if_decrypt.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if_encrypt.setSelected(false);
            	//if_plain.setSelected(!newValue);
            	if_divide.setSelected(false);
            }
        });
        /*
        if_plain.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if_encrypt.setSelected(false);
            	if_decrypt.setSelected(false);
            	//if_divide.setSelected(!newValue);
            }
        });*/
        
        

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}

