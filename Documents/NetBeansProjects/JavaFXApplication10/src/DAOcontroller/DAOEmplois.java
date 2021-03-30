/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOcontroller;

import avoirConnection.AvoirConnection;
import managerClass.Emplois;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author anest
 */
public class DAOEmplois {

    private Connection con;
    private Statement ste;
    private PreparedStatement pre;
    private Date sqlDate;

    public DAOEmplois() {
        con = AvoirConnection.getConnection();
    }


    public void Accept(int id) throws SQLException {
        pre = con.prepareStatement("update `pidev`.`reservation` set etat='accepté' where id =(?)");
        pre.setInt(1, id);
        pre.executeUpdate();
        System.out.println("ok");

    }

    public void refuse(int id) throws SQLException {
        pre = con.prepareStatement("update `pidev`.`reservation` set etat='refusé' where id =(?)");
        pre.setInt(1, id);
        pre.executeUpdate();
        System.out.println("ok");

    }


    
    
     public void ajouter(Emplois E) throws SQLException
    {
        List<Integer> idinv = new ArrayList<>();
        List<Integer> idRes = new ArrayList<>();
        List<Float> montantinv = new ArrayList<>();
        pre = con.prepareStatement("INSERT INTO `pidev`.`emplois` ( `client_id`,`partenaire_id`,`pointAchat`,``date`,`prix`,`listAchats`) VALUES ( ?,?,?,?,?,?);");
    pre.setInt(1, E.getClient_id());
    pre.setInt(2, E.getenseignant_id());
    pre.setString(3, E.getmatiere());
    pre.setDate(5, sqlDate);
    pre.setFloat(6, E.getPrix()); 
       pre.setString(7, E.getListAchats());
    pre.executeUpdate();
    
    
    
   
    }
    }
        