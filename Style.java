package com.example.programming_3_project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Style {

    public void stylize(ArrayList<Label> args) {
        for(int i = 0; i < args.size(); i++){
            args.get(i).setStyle("-fx-text-fill: white;");
        }
    }

    public void alignGridBox(GridPane g){
        g.setVgap(10);
        g.setHgap(20);
        g.setAlignment(Pos.CENTER);
    }

    public void alignVBox(VBox v){
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(150));
        v.setSpacing(15);
    }
}
