package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Component {

	private final StringProperty componentName;
	private final IntegerProperty componentguk;
	private final IntegerProperty componenteng;
	private final IntegerProperty componentmath;
	private final IntegerProperty componenttotal;
	private final IntegerProperty componentavg;
	private final IntegerProperty componentrank;
	
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
	
	
	public int getComponenttotal() {
		return componenttotal.get();
	}
	
	
	public void setComponenttotal() {
		this.componenttotal.set(componenteng.get()+componentguk.get()+componentmath.get());
	}
	
	
	public int getComponentavg() {
		return componentavg.get();
	}
	
	
	public void setComponentavg() {
		this.componentavg.set(componenttotal.get()/3);
	}
	
	
	public int getComponentrank() {
		return componentrank.get();
	}
	
	
	public void setComponentrank() {
		//this.componentrank.set(componentrank);
		this.componentrank.set(1);
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
	
	
	public IntegerProperty comavgProperty() {
		return componentavg;
	}
	
	
	public IntegerProperty comrankProperty() {
		return componentrank;
	}
	
	public Component(String componentName, Integer componentguk, Integer componenteng,
			Integer componentmath, Integer componenttotal, Integer componentavg, Integer componentrank) {
		this.componentName = new SimpleStringProperty(componentName);
		this.componentguk = new SimpleIntegerProperty(componentguk);
		this.componenteng = new SimpleIntegerProperty(componenteng);
		this.componentmath = new SimpleIntegerProperty(componentmath);
		this.componenttotal = new SimpleIntegerProperty(componenttotal);
		this.componentavg = new SimpleIntegerProperty(componentavg);
		this.componentrank = new SimpleIntegerProperty(componentrank);
	}
	
	

	
}
