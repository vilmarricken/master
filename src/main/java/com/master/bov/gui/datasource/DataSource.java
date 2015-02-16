package com.master.bov.gui.datasource;

public interface DataSource {

	int getInt(String name);

	void setInt(String name, int value);

	String getString(String name);

	void setString(String name, String value);

}
