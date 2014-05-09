package me.istrate.restaurantapp;

public class Alert {
	
	private String m_sAlert;
	
	public Alert() {}
	
	//builder pattern
	public Alert AlertContent(String s) {
		m_sAlert = s;
		return this;
	}
	
	public void setAlert(String s) {
		m_sAlert = s;
	}
	
	public String getAlert() {
		return m_sAlert;
	}
	@Override
	public String toString() {
		return "Alert: " + getAlert();
	}
}
