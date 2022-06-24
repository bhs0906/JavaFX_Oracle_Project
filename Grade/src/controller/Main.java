package controller;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Component;
import model.ComponentDAO;


public class Main extends Application {
	
	@FXML private BorderPane rootLayout;
	@FXML private AnchorPane AnchorPane;
	
	private Stage primaryStage;
	
	private static final ObservableList<Component> componentList = FXCollections.observableArrayList(); // 메인화면에 성적들 보여주기 위한 리스트
	private static final ObservableList<Component> searchResultList = FXCollections.observableArrayList(); // 검색화면에 성적을 보여주기위해 저장해주는 리스트
	@Override
	public void start(Stage primaryStage) {   //첫 시작엔 RootLayOut 과 ComMain을 화면에 보여줌
		try {
			this.primaryStage = primaryStage;
			ComMainController comMainController = new ComMainController();
			comMainController.setPrimaryStage(primaryStage);
			
			setRootLayout();
			setComMainView();
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
//			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public void setComMainView() {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/ComMainView.fxml"));
			AnchorPane comMainView = (AnchorPane) loader.load();
			rootLayout.setCenter(comMainView);
			

			
			ComMainController controller = loader.getController();
			controller.setMain(this); 
			
			ComponentDAO componentDAO = new ComponentDAO(); //ComponentDAO 객체를 생성해 DB연걸
			ObservableList<Component> tempList = componentDAO.getComponentList(); //생성된 ComponentDAO 객체에서 getComponenetList()를 사용해 DB에 있는 전체 성적 데이터를 불러온다.
			for(int i = 0; i < tempList.size(); i++) {
				componentList.add(tempList.get(i)); 
			}
			// DB에서 받아온 전체 데이터를 componentList에 추가한다. (데이터 복사)
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public ObservableList<Component> getComponentList(){
		return componentList;
	}
	
	public ObservableList<Component> getSearchResultList(){
		return searchResultList;
	}
	
	public int setComponentDataView(Component component) { //추가 창을 띄워주는 함수
		try {
			FXMLLoader loader =  new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/ComDataView.fxml"));
			AnchorPane page =  (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("추가 정보");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			ComDataController controller = (ComDataController) loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setComponent(component);
			
			dialogStage.showAndWait();
			return controller.getReturnValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}	
	}
	
	public String setSearchView() { //검색 창을 띄워주는 함수
		try {
			FXMLLoader loader =  new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/SearchView.fxml"));
			AnchorPane page =  (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("등급 검색");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			
			ComSearchController controller = (ComSearchController) loader.getController();
			controller.setDialogStage(dialogStage);
			dialogStage.showAndWait();
			return controller.getGrade();
		}catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
public void setSearchResultView(String grade) { // 검색결과를 띄워주는 함수
		searchResultList.removeAll(searchResultList); //검색결과를 보여주기 위해 매번 리셋해줌
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/SearchResultView.fxml"));
			AnchorPane searchResultView = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("검색정보");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(searchResultView);
			dialogStage.setScene(scene);

			SearchResultController controller = loader.getController();
			controller.setMain(this);
			
			ComponentDAO componentDAO = new ComponentDAO();
			ObservableList<Component> resultList = componentDAO.getResultList(grade);// 입력한 등급을 매개변수를 삼아 결과 데이터를 받아온다.
			for(int i = 0; i < resultList.size(); i++) {
				searchResultList.add(resultList.get(i));                            // 받아온 데이터를 결과리스트에 넣어줌
			}
			controller.setSrStage(dialogStage);
			dialogStage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void saveAction() {
		ComponentDAO componentDAO = new ComponentDAO();
		System.out.println(componentList); //문제점 리스트에 값이 없음
		int result = componentDAO.saveComponentList(componentList); //DAO에서 세이브 가능한지 여부 확인 후 정수값을 반환받음
		if( result == 1) {                                     		//1을 반환 받으면 세이브 완료
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(primaryStage);
			alert.setTitle("성공 메시지");
			alert.setHeaderText("성공적으로 수행했습니다.");
			alert.setContentText("테이터베이스에 성공적으로 접근했습니다.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);               //아니면 오류메세지를 출력
			alert.initOwner(primaryStage);
			alert.setTitle("오류 메시지");
			alert.setHeaderText("오류가 발생하였습니다.");
			alert.setContentText("테이터베이스에 오류가 발생하였습니다.");
			alert.showAndWait();
		}
	}

	@FXML
	public void aboutAction() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("알아보기");
		alert.setHeaderText("<프로그램 정보>");
		alert.setContentText("프로그램 버전 : 1.0 Ver \n" +
				"프로그램 개발자 : ezen \n" +
				"프로그램 설명 : 자바FX DB프로그램 과제");
		alert.showAndWait();
	}
	
	@FXML
	public void endAction() {
		Platform.exit();
	}

}
