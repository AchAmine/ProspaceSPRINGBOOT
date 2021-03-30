/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerClass;
import java.util.Objects;
import managerCLass.User1;
/**
 *
 * @author anest
 */
public class Emplois {
    private int id;
     private User1 client;
    private int enseignant_id;
    private int client_id;
    private String matiere;
    private String listAchats;
    private float prix;
    private java.sql.Date  date_emplois;
    private String etat;
    

    public Emplois(int id,int client_id, int enseignant_id,  String matiere,  java.sql.Date  date_emplois ,float prix , String listAchats,String etat,User1 user1) {
         this.id=id;
        this.enseignant_id = enseignant_id;
        this.client_id = client_id;
        this.matiere = matiere;
        this.listAchats = listAchats;
        this.prix = prix;
        this.date_emplois = date_emplois;
        this.client=user1;
      
    }
   public Emplois(int id,int client_id, int enseignant_id,  String matiere, java.sql.Date  date_emplois ,float prix , String listAchats, String etat) {
         this.id=id;
        this.enseignant_id = enseignant_id;
        this.client_id = client_id;
        this.matiere = matiere;
        this.listAchats = listAchats;
        this.prix = prix;
        this.date_emplois = date_emplois;
    
      
    }
    public User1 getClient() {
        return client;
    }

    public void setClient(User1 client) {
        this.client = client;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getenseignant_id() {
        return enseignant_id;
    }

    public void setenseignant_id(int enseignant_id) {
        this.enseignant_id = enseignant_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getmatiere() {
        return matiere;
    }

    public void setmatiere(String matiere) {
        this.matiere = matiere;
    }

 
    public String getListAchats() {
        return listAchats;
    }

    public void setListAchats(String listAchats) {
        this.listAchats = listAchats;
    }



    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public java.sql.Date  getDate_Emplois() {
        return date_emplois;
    }

    public void setDate_Emplois(java.sql.Date  date_Emplois) {
        this.date_emplois = date_Emplois;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.client);
        hash = 59 * hash + this.enseignant_id;
        hash = 59 * hash + this.client_id;
        hash = 59 * hash + Objects.hashCode(this.matiere);
        hash = 59 * hash + Objects.hashCode(this.listAchats);
        hash = 59 * hash + Float.floatToIntBits(this.prix);
        hash = 59 * hash + Objects.hashCode(this.date_emplois);
        hash = 59 * hash + Objects.hashCode(this.etat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Emplois other = (Emplois) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.enseignant_id != other.enseignant_id) {
            return false;
        }
        if (this.client_id != other.client_id) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.matiere, other.matiere)) {
            return false;
        }
        if (!Objects.equals(this.listAchats, other.listAchats)) {
            return false;
        }
     
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.date_emplois, other.date_emplois)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Emplois{" + "id=" + id + ", client=" + client + ", enseignant_id=" + enseignant_id + ", client_id=" + client_id + ", matiere=" + matiere + ", listAchats=" + listAchats + ", prix=" + prix + ", date_Emplois=" + date_emplois + ", etat=" + etat + '}';
    }

    public Object getDate_emplois() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
