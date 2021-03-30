/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import managerCLass.User1;
/**
 * FXML Controller class
 *
 * @author anest
 */
public  class AjouterEmploisController implements Initializable {

     @FXML
    private ComboBox<User1> enseignant;
    @FXML
    private ComboBox<String> classe;
    @FXML
    private ComboBox<String> horraire;
    @FXML
    private Button ajout;
    @FXML
    private ComboBox<String> matiere;
    
    @FXML
    private DatePicker date_emplois;
    @FXML
          
    private Button btn_Timetable;
    
            //ObservableList Une liste qui permet de suivre les changements lorsqu'ils se produisent.
    ObservableList<String> Classe
            = FXCollections.observableArrayList(
                    "1er_années",
                    "2éme années",
                    "3éme années",
                    "4éme années",
                    "5éme années"
            );

    final ObservableList<String> matiere_1er_années
            = FXCollections.observableArrayList(
                    "Anglais",
                    "Mathematique");
                    
    final ObservableList<String> matiere_2éme_années
            = FXCollections.observableArrayList(
                    "Base_de_donnés",
                    "Electronique",
                    "Français");

    final ObservableList<String>  matiere_3éme_années
            = FXCollections.observableArrayList(                 
                    "Uml");
    final ObservableList<String>  matiere_4éme_années
            = FXCollections.observableArrayList(
                    "Physique",
                    "Progammation"
            );
    final ObservableList<String> matiere_5éme_années
            = FXCollections.observableArrayList(
                    "GL",
                    "java"
            );
    final ObservableList<String> horraire_Anglais
            = FXCollections.observableArrayList(
                    "8h_10h",
                    "15h_17h");
    final ObservableList<String> horraire_Mathematique
            = FXCollections.observableArrayList(
                    "11h_11h30min",
                    "15h30min_17h");
    final ObservableList<String> horraire_Electronique
            = FXCollections.observableArrayList(
                    "8h_9h"
            );
    final ObservableList<String> horraire_Français
            = FXCollections.observableArrayList(
                    "14h_15h",
                    "10h_12h"
            );
    final ObservableList<String> horraire_GL
            = FXCollections.observableArrayList(
                    "13h_15h",
                    "8h_10h");
    final ObservableList<String> horraire_java
            = FXCollections.observableArrayList(
                    "11h_12h",
                    "13h_14h");

    final ObservableList<String> horraire_Physique
            = FXCollections.observableArrayList("Pas d'horraire disponible");
    final ObservableList<String> horraire_Programmation
            = FXCollections.observableArrayList("Pas d'horraire disponible");
    final ObservableList<String> horraire_Base_de_donnés
            = FXCollections.observableArrayList("Pas d'horraire disponible");

  
  
    
     
    public void Reset(javafx.event.ActionEvent actionEvent) {
        classe.getSelectionModel().selectLast();
        
        matiere.getSelectionModel().selectLast();        
        horraire.getSelectionModel().selectLast();
               
        date_emplois.setValue(LocalDate.now());
    }

    /**
     * Initializes the controller class.
     */
      @Override
    public void initialize(URL location, ResourceBundle resources) {
        
   
        boolean C = classe.getSelectionModel().isEmpty();
        boolean H = horraire.getValue() == null;
        boolean M = matiere.getValue() == null;

        if (C) {
            ajout.setDisable(true);
            classe.setValue("choisir une Classe");
        }

        if (H) {
            ajout.setDisable(true);
            classe.setValue("choisir une Classe d'abord");
        }
        if (M) {
            ajout.setDisable(true);
            classe.setValue("choisir une matiere d'abord");

        }
    
      
        classe.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue == null || horraire.getValue() == "Pas d'horraire disponible"  || horraire.getValue() == null || matiere.getValue() == null  || date_emplois.getValue() == null) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
        horraire.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue == null ||newValue == "Pas d'horraire disponible" || classe.getValue() == null || matiere.getValue() == null ||  date_emplois.getValue() == null) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });

        matiere.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (newValue == null || classe.getValue() == null || horraire.getValue() == null || horraire.getValue() == "Pas d'horraire disponible" || date_emplois.getValue() == null) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
        date_emplois.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (date_emplois.getValue() == null || newValue == null ) {
                ajout.setDisable(true);
            } else {
                ajout.setDisable(false);
            }

        });
    }

//callback vous permet de définir ce que le corps de la méthode va en faire.
//La classe ou la méthode qui le prend comme paramètre détermine quand il sera appelé
//le premier paramètre spécifie le type de l'objet transmis à la méthode d'appel,
//le deuxième paramètre spécifiant le type de retour de la méthode

private void voirEmploisCLiquer(){
    

}
      
                
    @FXML
    private void goToCalendar(ActionEvent event) throws IOException {
          Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Timetable.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

          
                
                
                
    }

  

  

  

                
                
                
                
                
                
                
                
                






 

 

              
  
