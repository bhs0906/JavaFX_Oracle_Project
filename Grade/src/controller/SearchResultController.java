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
	@FXML private TableColumn<Component, Integer> resEng; /* = new TableColumn<Component, String>("영어"); */ 
	@FXML private TableColumn<Component, Integer> resMath; /* = new TableColumn<Component, String>("수학"); */ 
	@FXML private TableColumn<Component, Integer> resSci; /* = new TableColumn<Component, String>"과학"); */ 
	@FXML private TableColumn<Component, Integer> resTotal; /* = new TableColumn<Component, String>("합계"); */ 
	@FXML private TableColumn<Component, Integer> resAvg; /* = new TableColumn<Component, String>("평균"); */ 
	@FXML private TableColumn<Component, Integer> resRank; /* = new TableColumn<Component, String>("랭킹"); */ 
	
	
	private Main mainapp;
	
	public void setMain(Main main) {
		this.mainapp = main;  //main 클래스에 연동
		
		resultTable.setItems(mainapp.getSearchResultList()); //검색결과 테이블에 main에서 값을 넣어논 리스트를 설정한다.
	}
	
	public void backAction() { // "목록으로" 버튼 누를 시
		srStage.close(); //화면 닫음
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {// 테이블 칼럼에 값을 세팅
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
