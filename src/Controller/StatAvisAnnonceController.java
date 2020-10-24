/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import services.AvisAnnoncesServices;

public class StatAvisAnnonceController implements Initializable {
    @FXML
    private PieChart pie;
    @FXML
    private Label l1;
 @FXML
    private Label l2;
  @FXML
    private Label l3;
   @FXML
    private Label l4;
    @FXML
    private Label l5;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int i,j,k,f,r;
       AvisAnnoncesServices A = new AvisAnnoncesServices();
       i=A.CountAvis("Mauvais");
       k=A.CountAvis("Passable");
       f=A.CountAvis("Bien");
       r=A.CountAvis("Assez Bien");
       j=A.CountAvis("TrésBien");
        ObservableList<PieChart.Data> pieChartData =
               FXCollections.observableArrayList(
                       new PieChart.Data("Mauvais",i),
                       new PieChart.Data("Passable",k),
                       new PieChart.Data("Bien",f),
                       new PieChart.Data("Assez Bien",r),
                       new PieChart.Data("TrésBien",j)
               );
        l1.setText((Integer.toString((int) i)));
       l2.setText((Integer.toString((int) k)));
          l3.setText((Integer.toString((int) f)));
             l4.setText((Integer.toString((int) r)));
                l5.setText((Integer.toString((int) j)));
                
        pie.setData(pieChartData);
    }    

    
}
