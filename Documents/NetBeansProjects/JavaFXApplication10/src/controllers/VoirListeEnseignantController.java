/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import DAOcontroller.DAOEnseignants;
import avoirConnection.AvoirConnection;
import subclass.TempEnseignant;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import managerClass.Enseignant;

/**
 *
 * @author Noumodong
 */
public class VoirListeEnseignantController implements Initializable {

    @FXML
    private TableView<Enseignant> tableEnseignant;

    @FXML
    private TableColumn<Enseignant, String> idColumn;

    @FXML
    private TableColumn<Enseignant, String> nomColumn;

    @FXML
    private TableColumn<Enseignant, String> prenomsColumn;

    @FXML
    private TableColumn<Enseignant, String> adresseColumn;

    @FXML
    private TableColumn<Enseignant, String> emailColumn;

    @FXML
    private TableColumn<Enseignant, Integer> telephoneColumn;

    @FXML
    private TableColumn<Enseignant, String> statutColumn;

    @FXML
    private Pane panSnackbar;
    String idSupprimer;
    public static TempEnseignant enseignaneAModifier = null;
    Object diaglogFocus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChargementableEnseignant();
    }

    private void ChargementableEnseignant() {

        //Chargement de la table enseignant lors du démarrage
        try {
            ObservableList<Enseignant> data = FXCollections.observableArrayList(new DAOEnseignants().getAllEnseignants(new AvoirConnection().getConnnection()));
            chargerEnsembleEnseignants(data);
        } catch (Exception ex) {
        }
    }

    //Nous voulons supprimer un enseignant

    /**
     *
     * @param event
     */
    
           public void infosEnseignantCliquer(MouseEvent event) {
            
            }
           
           public void modifierCliquer (MouseEvent event) {
               
           }
    @FXML
    void supprimerCliquer(MouseEvent event) {
        try {
            new DAOEnseignants().effacerProduit(retourneId(), new AvoirConnection().getConnnection());
            this.idSupprimer = retourneId();
            ChargementableEnseignant();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
//            ChargementableEnseignant();
//            JFXSnackbar snac = new JFXSnackbar(this.panSnackbar);
//            snac.getStyleClass().add("jfx-snackbar-content");
//            snac.show("Enseignant ayant pour id :" + this.idSupprimer + "suppmer avec succès", 5000);

          //  NotificationType notification = NotificationType.CUSTOM;

            //TrayNotification tray = new TrayNotification();
            //tray.setTitle("Confirmation de suppression");
            //tray.setImage(new Image(getClass().getResource("/cm/supptic/images/logo_supptic.jpg").toString()));
            //tray.setMessage("Enseignant dont id :" + this.idSupprimer + " supprimer avec succès");
            //tray.setNotificationType(notification);
            //tray.showAndDismiss(Duration.millis(5000));
        }

    }

    //Modifier un enseignant
   
 

    //chargement du focus
  

    //retourn ID selectionner
    private String retourneId() {
        this.idSupprimer = this.tableEnseignant.getSelectionModel().getSelectedItem().getId_enseignant();
        return tableEnseignant.getSelectionModel().getSelectedItem().getId_enseignant();
    }

    //Charger les enseignants dans le tableau
    public void chargerEnsembleEnseignants(ObservableList<Enseignant> data) {
        try {
            //chargement de la table des enseignants au niveau de l'interface apres la demande de la liste des enseignants
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id_enseignant"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom_enseignant"));
            prenomsColumn.setCellValueFactory(new PropertyValueFactory<>("prenom_enseignant"));
            adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse_enseignant"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email_enseignant"));
            telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            statutColumn.setCellValueFactory(new PropertyValueFactory<>("statut_enseignant"));
        
            tableEnseignant.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class TrayNotification {

        public TrayNotification() {
        }
    }

    static class panFocuss {

        static void setEffect(BoxBlur ff) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public panFocuss() {
        }
    }
}
