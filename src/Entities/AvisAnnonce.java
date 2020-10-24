/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

public class AvisAnnonce {
    private String avis;
    private int idA;
    private int idUser;
    
    public AvisAnnonce() {}

    public AvisAnnonce(String avis, int idA, int idUser) {
        this.avis = avis;
        this.idA = idA;
        this.idUser = idUser;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
}
