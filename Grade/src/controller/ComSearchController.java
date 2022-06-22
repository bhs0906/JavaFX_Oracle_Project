package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Component;
import model.ComponentDAO;

public class ComSearchController {
	private Stage dialogStage;
	private String grade = "";
	@FXML private TextField searchField;
	
	
	
	@FXML 
	public void searchAction2() { //검색버튼을 누를 시 
		ComponentDAO componentDAO = new ComponentDAO();
		String searchG = searchField.getText();
		if(valid()) {
			if(componentDAO.searchInfo(searchG)>0) grade = searchG;
		}
		dialogStage.close();
	}
	
	public String getGrade() {
		return grade;
	}

	@FXML 
	public void cancelAction() {
		dialogStage.close();
	}
	
	private boolean valid() { //입력창에 아무것도 입력하지않으면 오류메세지를 출력해주는 함수
		String errorMessge = "";
		
		if( searchField.getText() == null || searchField.getText().equals("")) {
			errorMessge += "등급을 적으세요. \n";
		}
		if( errorMessge.equals("")) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("오류 메시지");
			alert.setHeaderText("값을 제대로 입력하세요.");
			alert.setContentText(errorMessge);
			alert.showAndWait();
			return false;
		}
	}
	
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
