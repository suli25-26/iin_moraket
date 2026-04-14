package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MainController {
    int n = 90;
    CheckBox[] boxes = new CheckBox[n];

    @FXML
    private TextField countNums;

    @FXML
    private GridPane gridPane;

    @FXML void initialize() {
        for(int i=0; i<n; i++) {
            boxes[i] = new CheckBox();
            boxes[i].setText(String.valueOf(i+1));
            boxes[i].selectedProperty()
            .addListener((observable, oldValue, newValue) -> {
                this.countNums.setText(String.valueOf(countChecked()));
            });
            gridPane.add(boxes[i], i%7, i/7);
        }
    }

    @FXML
    void onClickSaveButton(ActionEvent event) {
        this.startSave();
    }

    void startSave() {
        System.out.println("Mentés...");
        if(countChecked() == 5) {
            Storage.writeContent(this.generateLine());
        }else {
            System.err.println("Hiba! 5 számot kell választanod!");
        }        
    }

    int countChecked() {
        int count = 0;
        for(CheckBox box : boxes) {
            if(box.isSelected()) {
                count++;
            }
        }
        return count;
    }

    String generateLine() {
        StringBuilder sb = new StringBuilder();
        for(CheckBox box : boxes) {
            if(box.isSelected()) {
                sb.append(box.getText());
                sb.append(",");
            }
        }        
        return sb.toString();
    }
}
