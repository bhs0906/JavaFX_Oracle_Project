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
	
	public ComponentDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","bg","bg");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String searchInfo(String name) {
		String SQL = "SELECT * FROM component_stock1";
		String info="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			int cnt=0;
			while(rs.next()) {
				if(rs.getString("NAME").equals(name)) {
					info = "이름 : " +rs.getString("NAME") +"\n국어 : " + rs.getInt("KOR")+" 영어 : "+ 
							rs.getInt("ENG")+" 수학 : " + rs.getInt("MATH") + " 과학 : "+ rs.getInt("SCI") +"\n총합 : "+ rs.getInt("TOTAL")+" 평균 : "+
							rs.getDouble("AVG") +" 등급 : "+ rs.getString("GRADE");
					cnt++;
				}
			}
			if(cnt==0) {
				info = "그런 학생은 없습니다.";
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return info;
	}
	
	public ObservableList<Component> getComponentList(){
		
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
