
package Controller;

import Entities.User;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class AnnArtisanMapController implements Initializable {
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> NomAnnonce;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private AnchorPane mapGoogle;
    @FXML
    private TextField seach;
    @FXML
    private GoogleMapView gmap;
    @FXML
    private Label AdresseTxt;
    
    private GoogleMap map;
    private GeocodingService G;
    private boolean ready;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //gmap.addMapInializedListener((MapComponentInitializedListener) this);
        UserService Ann=new UserService();
        
        Text t;
        String s="technicien";
        
        //a:1:{i:0;s:12:"ROLE_ARTISAN";}
        ArrayList A= (ArrayList) Ann.RechercherUserByRole(s);
        ObservableList ob=FXCollections.observableArrayList(A);
        table.setItems(ob);
        NomAnnonce.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        type.setCellValueFactory(new PropertyValueFactory<>("Fname"));
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        FilteredList<User> fil= new FilteredList<>(ob,e->true);
        seach.setOnKeyReleased((KeyEvent e) -> {
            seach.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
                fil.setPredicate((Predicate <? super User>) User->{
                    if(newValue==null||newValue.isEmpty()){return true;}
                    String lower=newValue.toLowerCase();
                    if(User.getFname().toLowerCase().contains(lower)){return true;}
                    else if(User.getLname().toLowerCase().contains(lower)){return true;}
                    return false;
                });
            });
            SortedList<User> k = new SortedList<>(fil);
            k.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(k);
        });
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
              //  mapGoogle.setVisible(true);
                mapInitialized();
                AdresseTxt.setVisible(true);
                AdresseTxt.setText(newSelection.getAddress());
                }}); 
    }    

    public void createMap() {
        map = new GoogleMap();
         mapGoogle.setVisible(true);
        G = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        mapOptions.center(new LatLong(33.8869, 9.5375))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(true)
                .zoomControl(true)
                .zoom(6);

        map = gmap.createMap(mapOptions);
    }
    public void mapInitialized() {

        createMap();
        User e = table.getSelectionModel().getSelectedItem();
        if(e!=null){
        String L = e.getAddress();
 
        G.geocode(L, (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG " + latLong.getLongitude() + "   " + latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
        });
    }
    }

}
