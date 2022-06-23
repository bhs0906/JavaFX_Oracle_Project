package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
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
//	@FXML private TableColumn<Component, Integer> ComPlace; /* = new TableColumn<Component, String>("위치"); */
	@FXML private TableColumn<Component, Integer> Comeng; /* = new TableColumn<Component, String>("영어"); */ 
	@FXML private TableColumn<Component, Integer> Commath; /* = new TableColumn<Component, String>("수학"); */ 
	@FXML private TableColumn<Component, Integer> Comsci; /* = new TableColumn<Component, String>"과학"); */ 
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
	public void initialize(URL location, ResourceBundle resources) {//각각 테이블 칼럼에 뷰를 세팅해주는 걸로 알고있음..
		ComName.setCellValueFactory(new PropertyValueFactory<Component, String>("componentName"));
		Comguk.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentguk"));
		Comeng.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componenteng"));
		Commath.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentmath"));
		Comsci.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentsci"));		
		Comtotal.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componenttotal"));
		Comavg.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentavg"));
		Comrank.setCellValueFactory(new PropertyValueFactory<Component, Integer>("componentrank"));	
	}

	public void setMain(Main main) { //테이블뷰에 main에 있는 componetList를 세팅해준다.
		this.mainapp = main;
		
		componentTable.setItems(main.getComponentList());
	}
	@FXML
	private void addAction() { // 추가 기능
		Component component = new Component("", 0, 0, 0, 0, 0, 0.0, ""); //아무것도 입력되지 않은 Component 객체를 생성해서 추가 값을 받아온다.
		int returnValue = mainapp.setComponentDataView(component);//main에 있는 추가기능 창을 띄워줌
		if ( returnValue == 1) { // 입력 잘했으면 main에 componentList에 추가
			mainapp.getComponentList().add(component);
		}
	}
	@FXML
	private void editAction() { // 수정 기능
		Component component = componentTable.getSelectionModel().getSelectedItem(); //테이블에서 선택한 값을 component에 넣어줌 
		if ( component != null ) { //테이블뷰에서 선택한 사항이 있는지 확인하고 선택했다면 선택한 데이터를 들고 추가기능과 똑같은 창을 띄움
			mainapp.setComponentDataView(component); //추가와 수정은 기능은 동일 하나 들고 가는 데이터가 다름
			ObservableList<Component> editList = mainapp.getComponentList();
			for(int i=0;i<editList.size();i++) {
				if(component==editList.get(i)) {//실시간으로 테이블 뷰 수정
					editList.remove(i);
					editList.add(component);
				}
			}
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
	private void deleteActiono() { // 삭제 기능
		int selectedIndex = componentTable.getSelectionModel().getSelectedIndex();
		if ( selectedIndex >= 0) {//테이블뷰에서 선택한 사항이 있는지 확인하고 선택했다면 테이블에서 삭제
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
	private void searchAction() { // 검색 기능
		String grade = mainapp.setSearchView(); //검색창을 띄운다. ComSearchControl에서 입력한 등급을 가져와서 
		if(grade!="") mainapp.setSearchResultView(grade);     //검색 결과 창을 띄운다.		
	}

}
