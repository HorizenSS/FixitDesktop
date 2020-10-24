/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

public class CommentAnn {
    private String d;
    private String commentAnn;
    private int idA;
    private int idUser;

    public CommentAnn() {
    }

    public CommentAnn(String d, String commentAnn, int idA, int idUser) {
        this.d = d;
        this.commentAnn = commentAnn;
        this.idA = idA;
        this.idUser = idUser;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getCommentAnn() {
        return commentAnn;
    }

    public void setCommentAnn(String commentAnn) {
        this.commentAnn = commentAnn;
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
