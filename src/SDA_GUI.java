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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SDA_GUI extends Application {
    
	private boolean if_success;
	
	
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
        
        CheckBox if_divide = new CheckBox("Divide");
       
        CheckBox if_merge = new CheckBox("Merge");
        
        CheckBox if_encrypt = new CheckBox("Encrypt");
        
        CheckBox if_decrypt = new CheckBox("Decrypt");
        
        HBox OperationButtons = new HBox(5);
        OperationButtons.setSpacing(50);
        OperationButtons.getChildren().addAll(if_divide, if_merge);
        grid.add(OperationButtons, 0, 1);
        
        HBox EncryptionButtons = new HBox(5);
        EncryptionButtons.setSpacing(50);
        EncryptionButtons.getChildren().addAll(if_encrypt, if_decrypt);
        grid.add(EncryptionButtons, 0, 2);
        
        
        Label d_inputfile = new Label("Input File:");        
        Label d_outputfile_1 = new Label("Output File 1:");        
        Label d_outputfile_2 = new Label("Output File 2:");
        
        TextField d_inputfile_f = new TextField();
        TextField d_outputfile_1_f = new TextField();
        TextField d_outputfile_2_f = new TextField();

        Label m_inputfile_1 = new Label("Input File 1:");        
        Label m_inputfile_2 = new Label("Input File 2:");
        Label m_outputfile = new Label("Output File:");
        
        TextField m_inputfile_1_f = new TextField();
        TextField m_inputfile_2_f = new TextField();
        TextField m_outputfile_f = new TextField();
        
        
        //TODO define the children on DivideFields
        VBox DivideLabels = new VBox();
        DivideLabels.setSpacing(10);
        DivideLabels.getChildren().addAll(d_inputfile, d_outputfile_1, d_outputfile_2);
        grid.add(DivideLabels, 0, 3);
        DivideLabels.setVisible(false);
        
        //TODO define the children on MergeFields
        VBox MergeLabels = new VBox();
        MergeLabels.setSpacing(10);
        MergeLabels.getChildren().addAll(m_inputfile_1,m_inputfile_2,m_outputfile);
        grid.add(MergeLabels, 0, 3);
        MergeLabels.setVisible(false);
        
        //TODO define the children on DivideFields
        VBox DivideFields = new VBox();
        DivideFields.setSpacing(10);
        DivideFields.getChildren().addAll(d_inputfile_f, d_outputfile_1_f, d_outputfile_2_f);
        grid.add(DivideFields, 1, 3);
        DivideFields.setVisible(false);
        
        //TODO define the children on MergeFields
        VBox MergeFields = new VBox();
        MergeFields.setSpacing(10);
        MergeFields.getChildren().addAll(m_inputfile_1_f,m_inputfile_2_f,m_outputfile_f);
        grid.add(MergeFields, 1, 3);
        MergeFields.setVisible(false);
        
        Button btn = new Button("Execute");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 6);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Output of the operation");
            }
        });
        
        if_divide.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if_merge.setSelected(false);
            	if_decrypt.setSelected(false);
            	MergeFields.setVisible(false);
            	DivideLabels.setVisible(true);
            	MergeLabels.setVisible(false);
            	DivideFields.setVisible(true);
            }
        });

        if_merge.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if_divide.setSelected(false);
            	if_encrypt.setSelected(false);
            	MergeFields.setVisible(true);
            	DivideLabels.setVisible(false);
            	MergeLabels.setVisible(true);
            	DivideFields.setVisible(false);
            }
        });
        
        if_encrypt.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if_decrypt.setSelected(false);
            	if_merge.setSelected(false);
            }
        });

        if_decrypt.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if_encrypt.setSelected(false);
            	if_divide.setSelected(false);
            }
        });
       
        
        

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}

