package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Component;


public class ComDataController {
	
	private Component component;
	private Stage dialogStage;
	private int ReturnValue;
	
	@FXML private TextField cnameField;
	@FXML private TextField cgukField;
	@FXML private TextField cengField;
	@FXML private TextField cmathField;
	@FXML private TextField csciField;
	@FXML private Button btn1;
	
	@FXML
	private void confirmAction() {
		if(valid()) {
			component.setComponentName(cnameField.getText());
			component.setComponentguk(Integer.valueOf(cgukField.getText()));
			component.setComponenteng(Integer.valueOf(cengField.getText()));
			component.setComponentmath(Integer.valueOf(cmathField.getText()));
			component.setComponentsci(Integer.valueOf(csciField.getText()));
			component.setComponenttotal();
			component.setComponentavg();
			component.setComponentrank();
			ReturnValue = 1;
			dialogStage.close();
		}
	}
	
	@FXML
	private void censerAction() {
		dialogStage.close();
	}
	
	private boolean valid() {
		String errorMessge = "";
		if( cnameField.getText() == null || cnameField.getText().equals("")) {
			errorMessge += "이름을 입력하세요. \n";
		}
		if( cgukField.getText() == null || cgukField.getText().equals("")) {
			errorMessge += "국어 점수를 입력하세요. \n";
		}
		if( cengField.getText() == null || cengField.getText().equals("")) {
			errorMessge += "영어 점수를 입력하세요. \n";
		}
		if( cmathField.getText() == null || cmathField.getText().equals("")) {
			errorMessge += "수학 점수를 입력하세요. \n";
		}
		if( csciField.getText() == null || csciField.getText().equals("")) {
			errorMessge += "과학 점수를 입력하세요. \n";
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
	
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
		cnameField.setText(component.getComponentName());
		cgukField.setText(String.valueOf(component.getComponentguk()));
		cengField.setText(String.valueOf(component.getComponenteng()));
		cmathField.setText(String.valueOf(component.getComponentmath()));
		csciField.setText(String.valueOf(component.getComponentsci()));

	}

	public Stage getDialogStage() {
		return dialogStage;
	}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public int getReturnValue() {
		return ReturnValue;
	}
	public void setReturnValue(int returnValue) {
		ReturnValue = returnValue;
	}
	

}
