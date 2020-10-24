/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static Controller.AnnoncesClientController.d;
import Entities.CommentAnn;
//import Interfaces.ICommentAnnonce;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import static services.UserService.conn;
import utils.MyConnection;

public class CommentAnnoncesServices {
    private final Connection c = MyConnection.getInstance().getConnection();
    private Object a;
    public void  CommentAnnoncesServices() {}
    
   
    public void AjouterCommentAnnonce(CommentAnn a) {
         PreparedStatement st;
     String query="insert into commentaire (Commentaire,dateC) values (?,?) ";
        try {
            st= c.prepareStatement(query);
            st.setString(1,a.getCommentAnn());
            st.setString(2,a.getD());
            st.executeUpdate();
            System.out.println("Ajout accompli avec succés");
            
        } 
        catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout d'un commentaire sur le service des annonces " + ex.getMessage());
        }
    }
    
    public void AjouterCommentAnnonce2(CommentAnn a) {
         PreparedStatement st;
     String query="insert into commentaire (Commentaire,dateC,idA,idUser) values (?,?,?,?) ";
        try {
            st= c.prepareStatement(query);
            st.setString(1,a.getCommentAnn());
            st.setString(2,a.getD());
            st.setInt(3,a.getIdA());
            st.setInt(4,/*a.getIdUser()*/1);
            st.executeUpdate();
            System.out.println("Ajout accompli avec succés");
            
        } 
        catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout d'un commentaire sur le service des annonces " + ex.getMessage());
        }
    }
    
    
    public List<CommentAnn> AfficherAllComment()
    {
        List<CommentAnn> Ann= new ArrayList<>();
        
       String query="select commentaire,dateC from commentaire ";
       
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {
                CommentAnn A=new CommentAnn();
                A.setCommentAnn(rs.getString(1));
                A.setD(rs.getString(2));
                Ann.add(A);
            }
            return Ann;
        } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de tous les commentaires " + ex.getMessage());
        }
        return null;
    }
    
     public List<CommentAnn> AfficherAllComment2(int id)
    {
        List<CommentAnn> Ann= new ArrayList<>();
        
       String query="select commentaire,dateC,idUser,idA from commentaire where idA='"+id+"' ";
       
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {
                CommentAnn A=new CommentAnn();
                A.setCommentAnn(rs.getString(1));
                A.setD(rs.getString(2));
                A.setIdUser(rs.getInt(3));
                A.setIdA(rs.getInt(4));
                Ann.add(A);
            }
            return Ann;
        } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de tous les commentaires " + ex.getMessage());
        }
        return null;
    }
  
     public void SupprimerCommentA(CommentAnn a) 
     {
        try {
            PreparedStatement st;
            String query = "delete from commentaire where Commentaire='"+a.getCommentAnn()+"'";
            st=c.prepareStatement(query);
            st.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la suppression du commentaire " + ex.getMessage());
        }
    }
     
    public void SupprimerCommentA2(CommentAnn a) 
     { 
        try {
            PreparedStatement st;
            String query = "delete from commentaire where Commentaire='"+a.getCommentAnn()+"' and idUser='"+1+"'";
            st=c.prepareStatement(query);
            st.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la suppression du commentaire " + ex.getMessage());
        }
       
    }
    public int CountCom(CommentAnn a) {
        int i=0;
        try {
            PreparedStatement pt;
            String query = "select * from Commentaire where Commentaire='"+a.getCommentAnn()+"' and idUser='"+1+"' and idA='"+d.getId()+"'";
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
