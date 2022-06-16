package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ComponentDAO {

	private static final int COMPONENT_ENG = 0;
	private static final int COMPONENT_GUK = 0;
	private static final int COMPONENT_MATH = 0;
	private static String COMPONENT_total;
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
	
	public ObservableList<Component> getComponentList(){
		
		String SQL = "SELECT * FROM component_stock1";
		ObservableList<Component> componentList = FXCollections.observableArrayList();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Component component = new Component(rs.getString("COMPONENT_NAME"), rs.getInt("COMPONENT_GUK"), 
						rs.getInt("COMPONENT_ENG"), rs.getInt("COMPONENT_MATH"), rs.getInt("COMPONENT_TOTAL"), rs.getInt("COMPONENT_AVG"), rs.getInt("COMPONENT_RANK"));
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
			SQL += "(COMPONENT_NAME, COMPONENT_GUK, COMPONENT_ENG, COMPONENT_MATH)";
			SQL += " VALUES(?,?,?,?)";
				
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			for (Component component : componentList) {
			String name = component.getComponentName();
			int guk = component.getComponentguk();
			int eng = component.getComponenteng();
			int math = component.getComponentmath();
			int total = component.getComponenttotal();
			int avg = component.getComponentavg();
			int rank = component.getComponentrank();
			
				
			pstmt.setString(1, name);
			pstmt.setInt(2, guk);
			pstmt.setInt(3, eng);
			pstmt.setInt(4, math);
			pstmt.setInt(5, total);
			pstmt.setInt(6, avg);
			pstmt.setInt(7, rank);
			
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
