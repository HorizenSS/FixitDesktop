/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.CheckBox;

public class Annonce {
    private int id;
    private String NomAnnonce ;
    private String Description;
    private String type;
    private double Prix;
    private String image;
    private int etat;
    private CheckBox select;
    private int idUser;

    public Annonce() {}

    public Annonce(String NomAnnonce, String Description, String type, double Prix) {
        this.NomAnnonce = NomAnnonce;
        this.Description = Description;
        this.type = type;
        this.Prix = Prix;
    } 

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", NomAnnonce=" + NomAnnonce + ", Description=" + Description + ", type=" + type + ", Prix=" + Prix + ", image=" + image + ", etat=" + etat + ", select=" + select + ", idUser=" + idUser + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomAnnonce(String NomAnnonce) {
        this.NomAnnonce = NomAnnonce;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public String getNomAnnonce() {
        return NomAnnonce;
    }

    public String getDescription() {
        return Description;
    }

    public String getType() {
        return type;
    }

    public double getPrix() {
        return Prix;
    }

    public String getImage() {
        return image;
    }

    public int getEtat() {
        return etat;
    }

    public CheckBox getSelect() {
        return select;
    }

    public int getIdUser() {
        return idUser;
    }


}
