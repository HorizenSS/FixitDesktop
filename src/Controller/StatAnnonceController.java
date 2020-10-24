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
import services.AnnonceServices;

import javafx.scene.control.TextField;
public class StatAnnonceController implements Initializable {
    
    @FXML
    private PieChart pie;
    @FXML
    private Label demanden;
    @FXML
    private Label offren;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       int i,j;
       AnnonceServices A = new AnnonceServices();
       i=A.Count("Demande");
       j=A.Count("Offre");
        ObservableList<PieChart.Data> pieChartData =
               FXCollections.observableArrayList(
                       new PieChart.Data("Demande",i),
                       new PieChart.Data("Offre",j)
               );
        pie.setData(pieChartData);
        offren.setText((Integer.toString((int) j)));   
    demanden.setText((Integer.toString((int) i)));
    }
}
