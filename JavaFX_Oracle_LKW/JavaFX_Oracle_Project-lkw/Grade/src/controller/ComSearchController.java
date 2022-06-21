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
	
	@FXML private TextField searchField;
	
	
	
	@FXML 
	public void searchAction2() {
		ComponentDAO componentDAO = new ComponentDAO();
		String info="";
		if(valid()) {
			info = componentDAO.searchInfo(searchField.getText());
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("검색 결과");
		alert.setHeaderText("<검색 정보>");
		alert.setContentText(info);
		alert.showAndWait();
		dialogStage.close();
	}
	
	@FXML 
	public void cancelAction() {
		dialogStage.close();
	}
	
	private boolean valid() {
		String errorMessge = "";
		if( searchField.getText() == null || searchField.getText().equals("")) {
			errorMessge += "�씠由꾩쓣 �엯�젰�븯�꽭�슂. \n";
		}
		if( errorMessge.equals("")) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("�삤瑜� 硫붿떆吏�");
			alert.setHeaderText("媛믪쓣 �젣��濡� �엯�젰�븯�꽭�슂.");
			alert.setContentText(errorMessge);
			alert.showAndWait();
			return false;
		}
	}
	
	
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
