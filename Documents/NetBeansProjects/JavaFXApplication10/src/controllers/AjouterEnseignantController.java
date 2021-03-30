/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAOcontroller.DAOEnseignants;
import avoirConnection.AvoirConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import managerClass.Enseignant;

/**
 *
 * @author Noumodong
 */
public class AjouterEnseignantController implements Initializable {

    @FXML
    private TextField id;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField adresse;

    @FXML
    private TextField email;

    @FXML
    private TextField telephone;

    @FXML
    private ComboBox<?> statut;

    private ComboBox<?> code_categorie;

    
    public void vider(javafx.event.ActionEvent actionEvent) {
        id.clear();
        nom.clear();
        prenom.clear();
        adresse.clear();
        email.clear();
        telephone.setText(null);
        statut.getSelectionModel().selectLast();
    }

    
    
    @FXML
    void enregistrerCliquer(MouseEvent event) throws SQLException, ClassNotFoundException {
        try {
            Enseignant enseignant = new Enseignant(id.getText(), nom.getText(), prenom.getText(), adresse.getText(), email.getText(),
                    Integer.valueOf(telephone.getText()), statut.getSelectionModel().getSelectedItem().toString());

            new DAOEnseignants().addProduit(enseignant, new AvoirConnection().getConnnection());
            
        } catch (ClassNotFoundException | NumberFormatException | SQLException ex) {
        Alert alert=new Alert(Alert.AlertType.valueOf("Veillez vérifier vos informatiions"));
        alert.show();
        }
    }

    @Override
          
    public void initialize(URL location, ResourceBundle resources) {
        
        ObservableList<String> status = FXCollections.observableArrayList("Vacataire", "Permanent");
        statut.setItems((ObservableList) status);
        statut.getSelectionModel().selectLast();
    }  
}
