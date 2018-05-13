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



import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    
	private boolean if_div;
	private boolean if_mer;
	private boolean if_enc;
	private boolean if_dec;
	
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
        primaryStage.setTitle("SDA_GUI (beta)");							//setting title of the window
        GridPane grid = new GridPane();										//creating primary grid
        grid.setAlignment(Pos.CENTER);										//centering all contents		
        grid.setHgap(10);			
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        /*
         * CheckBoxes are used to select the mode of operation
         */
        CheckBox if_divide = new CheckBox("Divide");
        CheckBox if_merge = new CheckBox("Merge");        
        CheckBox if_encrypt = new CheckBox("Encrypt");        
        CheckBox if_decrypt = new CheckBox("Decrypt");
        
        /*
         * Organizing CheckBoxes for divide and merge
         */
        HBox OperationButtons = new HBox(5);
        OperationButtons.setSpacing(50);
        OperationButtons.getChildren().addAll(if_divide, if_merge);
        grid.add(OperationButtons, 0, 1);
        
        /*
         * Organizing CheckBoxes for encrypt and decrypt
         */
        HBox EncryptionButtons = new HBox(5);
        EncryptionButtons.setSpacing(50);
        EncryptionButtons.getChildren().addAll(if_encrypt, if_decrypt);
        grid.add(EncryptionButtons, 0, 2);
        
        /*
         * Labels and textfields for dividing mode of operation
         */        
        Label d_inputfile = new Label("Input File:");        
        Label d_outputfile_1 = new Label("Output File 1:");        
        Label d_outputfile_2 = new Label("Output File 2:");
        TextField d_inputfile_f = new TextField();
        TextField d_outputfile_1_f = new TextField();
        TextField d_outputfile_2_f = new TextField();

        /*
         * Labels and textfields for merging mode of operation
         */
        Label m_inputfile_1 = new Label("Input File 1:");        
        Label m_inputfile_2 = new Label("Input File 2:");
        Label m_outputfile = new Label("Output File:");        
        TextField m_inputfile_1_f = new TextField();
        TextField m_inputfile_2_f = new TextField();
        TextField m_outputfile_f = new TextField();
        
        /*
         * Organizing labels and textfields for dividing mode of operation
         */
        VBox DivideLabels = new VBox();
        DivideLabels.setSpacing(10);
        DivideLabels.getChildren().addAll(d_inputfile, d_outputfile_1, d_outputfile_2);
        grid.add(DivideLabels, 0, 3);
        DivideLabels.setVisible(false);
        
        VBox DivideFields = new VBox();
        DivideFields.setSpacing(10);
        DivideFields.getChildren().addAll(d_inputfile_f, d_outputfile_1_f, d_outputfile_2_f);
        grid.add(DivideFields, 1, 3);
        DivideFields.setVisible(false);
        
        /*
         * Organizing labels and textfields for merging mode of operation
         */
        VBox MergeLabels = new VBox();
        MergeLabels.setSpacing(10);
        MergeLabels.getChildren().addAll(m_inputfile_1,m_inputfile_2,m_outputfile);
        grid.add(MergeLabels, 0, 3);
        MergeLabels.setVisible(false);
                
        VBox MergeFields = new VBox();
        MergeFields.setSpacing(10);
        MergeFields.getChildren().addAll(m_inputfile_1_f,m_inputfile_2_f,m_outputfile_f);
        grid.add(MergeFields, 1, 3);
        MergeFields.setVisible(false);
        
        /*
         * Buton that fires up the chosen operation
         */
        Button btn = new Button("Execute");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 6);

        /*
         * Creating feedback text after operation is completed
         */
        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);

        /*
         * Setting the event handler for the button, when pressed 
         */
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	 if(if_div) {
            		 actiontarget.setFill(Color.GREEN);
                     actiontarget.setText("Dividing");
                     
                     FileDivider divider = new FileDivider();
                     divider.chooseFile(d_inputfile_f.getText());
                     
                     try {
                         divider.divideFile(d_outputfile_1_f.getText(), d_outputfile_2_f.getText());
                         actiontarget.setFill(Color.BLUE);
                         actiontarget.setText("Divided");
                     }
                     catch(IOException ex) {
                         ex.printStackTrace();
                         actiontarget.setFill(Color.RED);
                         actiontarget.setText("IOException error");
                     }
                     
                     
            	 }
            	 else if(if_mer) {
            		 actiontarget.setFill(Color.GREEN);
                     actiontarget.setText("Merging");
                     System.out.println(m_inputfile_1_f.getText());
                     System.out.println(m_inputfile_2_f.getText());
                     System.out.println(m_outputfile_f.getText());
                     
                     FileMerger merger = new FileMerger();
                 	 merger.chooseFiles(m_inputfile_1_f.getText(),m_inputfile_2_f.getText());
                 	 try {
                 	    merger.mergeFiles(m_outputfile_f.getText());
                 	   actiontarget.setFill(Color.BLUE);
                       actiontarget.setText("Merged");
                 	 }
                 	 catch(IOException ex) {
                 	    ex.printStackTrace();
                 	   actiontarget.setFill(Color.RED);
                       actiontarget.setText("IOException error");
                 	 }
            	 }
            	 else {
            		 actiontarget.setFill(Color.FIREBRICK);
                     actiontarget.setText("Choose any mode of operation");
            	 }
            	
            	
                
            }
        });
        
        
        /*
         * if_divide.selectedProperty() is used to define contents to be displayed after clicking the "Divide" checkbox
         */
        if_divide.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if_merge.setSelected(false);
            	if_decrypt.setSelected(false);
            	MergeFields.setVisible(false);
            	DivideLabels.setVisible(true);
            	MergeLabels.setVisible(false);
            	DivideFields.setVisible(true);
            	if(newValue) {
            		if_div = true;
                	if_mer = false;
            	}
            	else {
            		if_div = false;
                	if_mer = false;
            	}
            	
            }
        });

        /*
         * if_merge.selectedProperty() is used to define contents to be displayed after clicking the "Merge" checkbox
         */
        if_merge.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if_divide.setSelected(false);
            	if_encrypt.setSelected(false);
            	MergeFields.setVisible(true);
            	DivideLabels.setVisible(false);
            	MergeLabels.setVisible(true);
            	DivideFields.setVisible(false);
            	if(newValue) {
            		if_div = false;
                	if_mer = true;
            	}
            	else {
            		if_div = false;
                	if_mer = false;
            	}
            }
        });
        
        /*
         * if_encrypt.selectedProperty() is used to define contents to be displayed after clicking the "Encrypt" checkbox
         */
        if_encrypt.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if_decrypt.setSelected(false);
            	if_merge.setSelected(false);
            	if(newValue) {
            		if_enc = true;
                	if_dec = false;
            	}
            	else {
            		if_enc = false;
                	if_dec = false;
            	}
            }
        });

        /*
         * if_decrypt.selectedProperty() is used to define contents to be displayed after clicking the "Decrypt" checkbox
         */
        if_decrypt.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            	if_encrypt.setSelected(false);
            	if_divide.setSelected(false);
            	if(newValue) {
            		if_enc = false;
                	if_dec = true;
            	}
            	else {
            		if_enc = false;
                	if_dec = false;
            	}
            }
        });
       
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
       
    }
    
}

