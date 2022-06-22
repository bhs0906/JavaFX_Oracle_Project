package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ComponentDAO {

	private static Connection conn;
	private static ResultSet rs;

	
	public void setMain(Main main) {
	}
	
	public ComponentDAO() { //ComponentDAO가 생성되면 생성자가 호출되어 DB에 연결
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","pg","1234");
		} catch (Exception e) {                                                    //접속이름 비밀번호
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public int searchInfo(String grade) { 
		String SQL = "SELECT * FROM component_stock1";
		int cnt=0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString("GRADE").equals(grade)) { //DB에 있는 데이터에서 입력한 등급과 같은 등급이 있는지 확인 후 
					cnt++;                                //횟수를 카운트한다. 
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cnt; //카운트한 횟수 반환
	}
	
	public ObservableList<Component> getComponentList(){ //DB에 있는 테이블의 전체 데이터를 넣어서 반환해주는 함수
		
		String SQL = "SELECT * FROM component_stock1";
		ObservableList<Component> componentList = FXCollections.observableArrayList();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Component component = new Component(rs.getString("NAME"), rs.getInt("KOR"), 
						rs.getInt("ENG"), rs.getInt("MATH"), rs.getInt("SCI"), rs.getInt("TOTAL"), rs.getDouble("AVG"), rs.getString("GRADE"));
				componentList.add(component);
			}
			
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return componentList;
	}

	public ObservableList<Component> getResultList(String grade){
		String SQL = "SELECT * FROM component_stock1 WHERE GRADE = ?"; //WHERE 조건문으로 입력한 등급과 같은 등급의 데이터만 나오게해줌
		ObservableList<Component> resultList = FXCollections.observableArrayList();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, grade);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Component component = new Component(rs.getString("NAME"), rs.getInt("KOR"), 
						rs.getInt("ENG"), rs.getInt("MATH"), rs.getInt("SCI"), rs.getInt("TOTAL"), rs.getDouble("AVG"), rs.getString("GRADE"));
				resultList.add(component); 
			}
			
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultList; 
	}
	
	public int saveComponentList(ObservableList<Component> componentList) {
		if(deleteComponentList() == -1) {
			return -1;
		}
		if (insertComponentList(componentList) == -1) {
			return -1;
		}
		return 1;
	}
	
	int deleteComponentList() {

		try {
			String SQL = "DELETE FROM component_stock1";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.executeQuery();
			pstmt.close();
			return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
	
	int insertComponentList(ObservableList<Component> componentList) {
		
		try {
			
			System.out.println(componentList.size());
			String SQL = "INSERT INTO component_stock1";
			SQL += "(NAME, KOR, ENG, MATH, SCI, TOTAL, AVG, GRADE)";
			SQL += " VALUES(?,?,?,?,?,?,?,?)";
				
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			for (Component component : componentList) {
			String name = component.getComponentName();
			int guk = component.getComponentguk();
			int eng = component.getComponenteng();
			int math = component.getComponentmath();
			int sci = component.getComponentsci();
			int total = component.getComponenttotal();
			double avg = component.getComponentavg();
			String rank = component.getComponentrank();
			
				
			pstmt.setString(1, name);
			pstmt.setInt(2, guk);
			pstmt.setInt(3, eng);
			pstmt.setInt(4, math);
			pstmt.setInt(5, sci);
			pstmt.setInt(6, total);
			pstmt.setDouble(7, avg);
			pstmt.setString(8, rank);
			
			pstmt.executeUpdate();
			
			}
			pstmt.close();

			return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
	
	
	
	
	
}
