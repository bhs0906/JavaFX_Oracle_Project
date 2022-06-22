package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Component;

public class SearchResultController implements Initializable{	
	private Stage srStage;

	@FXML private TableView<Component> resultTable;
	@FXML private TableColumn<Component, String> resName;/* = new TableColumn<Component, String>("부품이름");*/
	@FXML private TableColumn<Component, Integer> resKor; /*= new TableColumn<Component, Integer>("국어");*/
//	@FXML private TableColumn<Component, Integer> ComPlace; /* = new TableColumn<Component, String>("위치"); */
	@FXML private TableColumn<Component, Integer> resEng; /* = new TableColumn<Component, String>("영어"); */ 
	@FXML private TableColumn<Component, Integer> resMath; /* = new TableColumn<Component, String>("수학"); */ 
	@FXML private TableColumn<Component, Integer> resSci; /* = new TableColumn<Component, String>"과학"); */ 
	@FXML private TableColumn<Component, Integer> resTotal; /* = new TableColumn<Component, String>("합계"); */ 
	@FXML private TableColumn<Component, Integer> resAvg; /* = new TableColumn<Component, String>("평균"); */ 
	@FXML private TableColumn<Component, Integer> resRank; /* = new TableColumn<Component, String>("랭킹"); */ 
	
	
	private Main mainapp;
	
	public void setMain(Main main) {
		this.mainapp = main;
		
		resultTable.setItems(mainapp.getSearchResultList());
	}
	
	public void backAction() {
		srStage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		resName.setCellValueFactory(new PropertyValueFactory<Component, String>("componentName"));
		resKor.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentguk"));
		resEng.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componenteng"));
		resMath.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentmath"));
		resSci.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentsci"));		
		resTotal.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componenttotal"));
		resAvg.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentavg"));
		resRank.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentrank"));	
	}
	
	public void setSrStage(Stage srStage) {
		this.srStage = srStage;
	}
}
