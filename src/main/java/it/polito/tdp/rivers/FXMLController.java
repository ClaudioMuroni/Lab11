/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.db.RiversDAO;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.RaccoltaValoriFiume;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	RiversDAO dao = new RiversDAO();
    	this.model.setDao(dao);
    }

	public void riempiTendina() {
		
		List<River> listaFiumi = model.getAllRivers();
		
		boxRiver.getItems().clear();
		boxRiver.getItems().addAll(listaFiumi);
		
		//boxRiver.setValue(boxRiver.getItems().get(0));
	}
	
	@FXML
    void doAggiornaValoriFiume(ActionEvent event) {
    	
		River r = boxRiver.getValue();
		
		RaccoltaValoriFiume rac = model.ottieniValoriFiume(r.getId());
		
		txtStartDate.setText(""+rac.getPrimaData());
		txtEndDate.setText(""+rac.getUltimaData());
		txtNumMeasurements.setText(""+rac.getConta());
		txtFMed.setText(""+rac.getMedia());
    }
	
	@FXML
    void doSimula(ActionEvent event) {
		
		River r = boxRiver.getValue();
		double fMed = Double.parseDouble(txtFMed.getText());
		double k = Double.parseDouble(txtK.getText());
		int numGiorni = Integer.parseInt(txtNumMeasurements.getText());
		
		String result = this.model.simula(r.getId(), fMed, k, numGiorni);
		
		txtResult.setText(result);
    }
    
	
	@FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
}
