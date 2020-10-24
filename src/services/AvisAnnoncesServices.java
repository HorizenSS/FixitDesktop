/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.AvisAnnonce;
//import Interfaces.IAvisAnnonce;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import static services.UserService.conn;
import utils.MyConnection;

public class AvisAnnoncesServices {
    private final Connection c = MyConnection.getInstance().getConnection();
    public void  AvisAnnoncesServices() {}
   
    public void AjouterAvisAnnonce(AvisAnnonce a) {
        PreparedStatement st;
     String query="insert into avisannonce (avis) values (?) ";
        try {
            st= c.prepareStatement(query);
            st.setString(1,a.getAvis());
            st.executeUpdate();
            System.out.println("Ajout accompli avec succés");
            
        } 
        catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout de l'avis sur le service des annonces " + ex.getMessage());
        }
    }

    public void AjouterAvisAnnonce2(AvisAnnonce a) {
        PreparedStatement st;
     String query="insert into avisannonce (avis,idA,idUser) values (?,?,?) ";
        try {
            st= c.prepareStatement(query);
            st.setString(1,a.getAvis());
            st.setInt(2,a.getIdA());
            st.setInt(3,a.getIdUser());
            st.executeUpdate();
            System.out.println("Ajout accompli avec succés");
            
        } 
        catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout de l'avis sur le service des annonces " + ex.getMessage());
        }
    }
    
   
    public int CountAvis(String id) {
        int i=0;
        try {
            PreparedStatement pt;
            String query = "select * from avisannonce where avis='"+id+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                i+=1;
            }
        }
         catch (SQLException ex) {
            System.out.println("Erreur " + ex.getMessage());
        }  
        return i;
    }
    
    
    public AvisAnnonce RechercherAvisAnnonceById(int idA,int idUser) {
        try {
            PreparedStatement pt;
            String query = "select avis,idA,idUser from avisannonce where (idA='"+idA+"') AND (idUser='"+idUser+"') ";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
            AvisAnnonce a = new AvisAnnonce();
            while (rs.next()) {
                a.setAvis(rs.getString(1));
                a.setIdA(rs.getInt(2));
                a.setIdUser(rs.getInt(3));
                return a;
            }
        } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche de l'annonce " + ex.getMessage());
        }   
        return null;
    }
    
     public void ModifierAvisAnnonce(AvisAnnonce b) {
         try {
            PreparedStatement pt;
            String query = "update avisannonce set avis=? where idUser='"+1+"' and idA='"+b.getIdA()+"'";
            pt=c.prepareStatement(query);
            pt.setString(1,b.getAvis());
            pt.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        }
         catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour de l'annonce " + ex.getMessage());
        }               
    }
     public int CountAvis2(String id,int idA) {
        int i=0;
        try {
            PreparedStatement pt;
            String query = "select * from avisannonce where avis='"+id+"' and idA='"+idA+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                i+=1;
            }
        }
         catch (SQLException ex) {
            System.out.println("Erreur " + ex.getMessage());
        }  
        return i;
    }
    
}
