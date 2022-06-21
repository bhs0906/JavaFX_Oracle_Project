package model;

import javafx.beans.property.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Component {

	private final StringProperty componentName;
	private final IntegerProperty componentguk;
	private final IntegerProperty componenteng;
	private final IntegerProperty componentmath;
	private final IntegerProperty componentsci;
	private final IntegerProperty componenttotal;
	private final DoubleProperty componentavg;
	private final StringProperty componentrank;
	
	public String getComponentName() {
		return componentName.get();
	}


	public void setComponentName(String componentName) {
		this.componentName.set(componentName);;
	}

	public int getComponentguk() {
		return componentguk.get();
	}


	public void setComponentguk(int componentguk) {
		this.componentguk.set(componentguk);
	}
	
	public int getComponenteng() {
		return componenteng.get();
	}
	
	
	public void setComponenteng(int componenteng) {
		this.componenteng.set(componenteng);
	}

	
	public int getComponentmath() {
		return componentmath.get();
	}
	
	
	public void setComponentmath(int componentmath) {
		this.componentmath.set(componentmath);
	}
	
	public int getComponentsci() {
		return componentsci.get();
	}


	public void setComponentsci(int componentsci) {
		this.componentsci.set(componentsci);
	}
	
	
	
	public int getComponenttotal() {
		return componenttotal.get();
	}
	
	
	public void setComponenttotal() {
		this.componenttotal.set(componenteng.get()+componentguk.get()+componentmath.get()+componentsci.get());
	}
	
	
	public double getComponentavg() {
		return componentavg.get();
	}
	
	
	public void setComponentavg() {
		this.componentavg.set(componenttotal.get()/4);
	}
	
	
	public String getComponentrank() {
		return componentrank.get();
	}
	
	
	public void setComponentrank() {
		//this.componentrank.set(componentrank);
		int num = (int)componentavg.get()/10;
		
		String rk;
		switch(num) {
		case 10:
		case 9: rk = "A"; break;
		case 8: rk = "B"; break; 
		case 7: rk = "C"; break; 
		case 6: rk = "D"; break; 
		default: rk = "E"; 
		}
		this.componentrank.set(rk);
	}
	

	public StringProperty comNameProperty() {
		return componentName;
	}
	
	public IntegerProperty comgukProperty() {
		return componentguk;
	}	
	
	public IntegerProperty comengProperty() {
		return componenteng;
	}
	
	
	public IntegerProperty commathProperty() {
		return componentmath;
	}
	
	
	public IntegerProperty comtotalProperty() {
		return componenttotal;
	}
	
	
	public DoubleProperty comavgProperty() {
		return componentavg;
	}
	
	
	public StringProperty comrankProperty() {
		return componentrank;
	}
	
	public Component(String componentName, Integer componentguk, Integer componenteng,
			Integer componentmath,Integer componentsci, Integer componenttotal, Double componentavg, String componentrank) {
		this.componentName = new SimpleStringProperty(componentName);
		this.componentguk = new SimpleIntegerProperty(componentguk);
		this.componenteng = new SimpleIntegerProperty(componenteng);
		this.componentmath = new SimpleIntegerProperty(componentmath);
		this.componentsci = new SimpleIntegerProperty(componentsci);
		this.componenttotal = new SimpleIntegerProperty(componenttotal);
		this.componentavg = new SimpleDoubleProperty(componentavg);
		this.componentrank = new SimpleStringProperty(componentrank);
	}
	
	

	
}
