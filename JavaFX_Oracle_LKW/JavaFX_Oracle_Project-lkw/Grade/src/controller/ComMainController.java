package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Component;


public class ComMainController implements Initializable{
	
	@FXML private TableView<Component> componentTable;
	@FXML private TableColumn<Component, String> ComName;/* = new TableColumn<Component, String>("부품이름");*/
	@FXML private TableColumn<Component, Integer> Comguk; /*= new TableColumn<Component, Integer>("국어");*/
	@FXML private TableColumn<Component, Integer> ComPlace; /* = new TableColumn<Component, String>("위치"); */
	@FXML private TableColumn<Component, Integer> Comeng; /* = new TableColumn<Component, String>("영어"); */ 
	@FXML private TableColumn<Component, Integer> Commath; /* = new TableColumn<Component, String>("수학"); */ 
	@FXML private TableColumn<Component, Integer> Comsci; /* = new TableColumn<Component, String>("과학"); */ 
	@FXML private TableColumn<Component, Integer> Comtotal; /* = new TableColumn<Component, String>("합계"); */ 
	@FXML private TableColumn<Component, Integer> Comavg; /* = new TableColumn<Component, String>("평균"); */ 
	@FXML private TableColumn<Component, Integer> Comrank; /* = new TableColumn<Component, String>("랭킹"); */ 
	
	private Main mainapp;
	private Stage PrimaryStage;

	public void setPrimaryStage(Stage PrimaryStage) {
		this.PrimaryStage = PrimaryStage;
	}

	public TableView<Component> getComponentTable() {
		return componentTable;
	}


	public ComMainController() {
		// TODO Auto-generated constructor stub

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ComName.setCellValueFactory(new PropertyValueFactory<Component, String>("componentName"));
		Comguk.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentguk"));
		Comeng.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componenteng"));
		Commath.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentmath"));
		Comsci.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentsci"));		
		Comtotal.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componenttotal"));
		Comavg.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentavg"));
		Comrank.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentrank"));

		
	}

	public void setMain(Main main) {
		this.mainapp = main;
		
		componentTable.setItems(main.getComponentList());
	}
	@FXML
	private void addAction() {
		Component component = new Component("", 0, 0, 0, 0, 0, 0.0, "");
		int returnValue = mainapp.setComponentDataView(component);
		if ( returnValue == 1) {
			mainapp.getComponentList().add(component);
		}
	}
	@FXML
	private void editAction() {
		Component component = componentTable.getSelectionModel().getSelectedItem();
		if ( component != null ) {
			mainapp.setComponentDataView(component);
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainapp.getPrimaryStage());
			alert.setTitle("오류 메시지");
			alert.setHeaderText("선택 오류가 발생했습니다.");
			alert.setContentText("수정할 학생을 선택해주세요.");
			alert.showAndWait();
		}
	}
	@FXML
	private void deleteActiono() {
		int selectedIndex = componentTable.getSelectionModel().getSelectedIndex();
		if ( selectedIndex >= 0) {
			componentTable.getItems().remove(selectedIndex);
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainapp.getPrimaryStage());
			alert.setTitle("오류 메시지");
			alert.setHeaderText("선택 오류가 발생했습니다.");
			alert.setContentText("삭제할 학생을 선택해주세요.");
			alert.showAndWait();
		}	
	}
	
	@FXML
	private void searchAction() {
		mainapp.setSearchView();
	}

}
