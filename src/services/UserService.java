/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;
import org.mindrot.jbcrypt.BCrypt;



public class UserService {
    
    private final Connection c = MyConnection.getInstance().getConnection();
     private static  UserService instance;
    public static int conn ; 
    
    
    public String login(User u) {
        String f ="hi";
        try {
            String loginQry = "SELECT id,roles FROM user WHERE username = ? AND password= ?";
            PreparedStatement ste;
            ste = c.prepareStatement(loginQry);
            ste.setString(1, u.getUsername());
            ste.setString(2, u.getPassword());
            ResultSet rs = ste.executeQuery();
            if(rs.next()){
                System.out.println("Connexion accomplie");
                f=rs.getString("roles");
                conn=rs.getInt("id");
                }
            else if(rs==null){
                f="hi";
            }
            return f;
        } 
        catch (SQLException ex) {
            System.out.println("erreur lors de la connexion ");
        }
        return null;
    }
  
    public User RechercherUserByName(String nom) {
        try {
            PreparedStatement ps;
            String query = "select id,`username`, `password`, `nom`, `prenom`, `email`, `numTel`, `gender`, `roles`, `age`, `adresse` from user where username LIKE '%"+nom+"%'";
            ps= c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                User A=new User();
                A.setId(rs.getInt(1));
                A.setUsername(rs.getString(2));
                A.setPassword(rs.getString(3));
                A.setFname(rs.getString(4));
                A.setLname(rs.getString(5));
                A.setEmail(rs.getString(6));
                A.setPhoneNumber(rs.getInt(7));   
                A.setGender(rs.getString(8));
                A.setRole(rs.getString(9));
                A.setAge(rs.getInt(10));
                A.setAddress(rs.getString(11));
                return A;
            }    
        }
        catch (SQLException ex) {
               System.out.println("erreur lors de la recherche du user " + ex.getMessage());
        }   
      return null;       
    }
  
   public boolean VerifRole(String nom,String s)
   { 
        try {
            PreparedStatement ps;
            String query = "select id,`username`, `password`, `nom`, `prenom`, `email`, `numTel`, `gender`, `roles`, `age`, `adresse` from user where username LIKE '%"+nom+"%' and roles LIKE '%"+s+"%'";
            ps= c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
              return true; 
            }    
        }
        catch (SQLException ex) {
               System.out.println("erreur lors de la recherche du user " + ex.getMessage());
        }   
      return false; 
   }

    public void signIn(User u) 
    {
        try {
            String req = "INSERT INTO `user`(`username`, `password`, `nom`, `prenom`, `email`, `numTel`, `gender`, `roles`, `age`, `adresse`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste;
            ste= c.prepareStatement(req);
            ste.setString(1, u.getUsername());
            String m =u.getPassword(); 
            String hashed = BCrypt.hashpw(m, BCrypt.gensalt());
            ste.setString(2,hashed );
            ste.setString(3, u.getFname());
            ste.setString(4, u.getLname());
            ste.setString(5, u.getEmail());
            ste.setInt(6, u.getPhoneNumber());
            ste.setString(7, u.getGender());
            ste.setString(8, u.getRole());
            ste.setInt(9, u.getAge());
            ste.setString(10, u.getAddress());
            ste.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de l'inscription " + ex.getMessage());
        }
    }
   
    public boolean MdpO(User u) 
    {
        PreparedStatement pt;  
        boolean b=false;
        try{
            String query = "update user set password=? where username='"+u.getUsername()+"'and email='"+u.getEmail()+"'";
            pt=c.prepareStatement(query);
            String m =u.getPassword(); 
            String hashed = BCrypt.hashpw(m, BCrypt.gensalt());
            pt.setString(1,hashed );
            pt.executeUpdate();
            b=true;
        
        }
         catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour du mot de passe oublié " + ex.getMessage());
        }
        return b;
    }
   
    public String RechercherUsertByPass(String p) {
        try {
            String res;
            PreparedStatement pt;
            String query = "select password from user where username='"+p+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                res=rs.getString(1);
                return res;
            }
        }
        catch (SQLException ex) {
              System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
        return null;
    }
   
    public List<User> RechercherUserByRole(String nom) {
        try {
            List<User> u=new ArrayList<>();
            PreparedStatement ps;
            String query = "select nom , prenom,adresse from user where roles LIKE '%"+nom+"%'";
            ps= c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                User A=new User();
                A.setFname(rs.getString(1));
                A.setLname(rs.getString(2));
                A.setAddress(rs.getString(3));
                u.add(A);
            }    
            return u;
        }
        catch (SQLException ex) {
               System.out.println("erreur lors de la recherche du user " + ex.getMessage());
        }   
      return null;       
    }

     public User RechercherUsertById(int id)  {
        try {
            PreparedStatement pt;
            String query = "select id,username,email, password, nom, prenom ,roles,  adresse,numTel from user where id='"+id+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
        
            if (rs.next()) {
                User A=new User();
                A.setId(rs.getInt(1));
                A.setUsername(rs.getString(2));
                A.setEmail(rs.getString(3));
                A.setPassword(rs.getString(4));
                A.setFname(rs.getString(5));
                A.setLname(rs.getString(6));
                A.setRole(rs.getString(6));
                A.setAddress(rs.getString(7));
                A.setRole(rs.getString(8));
                A.setPhoneNumber(rs.getInt(9));
                return A;
            }}
            catch (SQLException ex) {
                System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
        return null;
     }
      public static UserService getInstance()
    {
        if (instance == null) {
            instance = new  UserService();
        }
        return instance; 
    }
    
    
}
