/**
 * 
 */
package com.master.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vilmar.Ricken
 * 
 */
public class ConnectionFactory {

	private static final ConnectionFactory INSTANCE = new ConnectionFactory();

	private static final String ATTRIBUTE_URL = "url";
	private static final String ATTRIBUTE_USER = "user";
	private static final String ATTRIBUTE_PASSWORD = "password";
	private static final String ATTRIBUTE_DRIVER = "driver";
	
	private Map<String, Map<String, String>> values = new HashMap<String, Map<String, String>>();

	public static ConnectionFactory getInstance() {
		return INSTANCE;
	}

	private ConnectionFactory() {
		values.put("sqlserver", getConnectionSqlServer());
	}

	private Map<String, String> getConnectionSqlServer() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(ATTRIBUTE_URL, "jdbc:jtds:sqlserver://192.168.200.24:1433/vilmar14;intance=TECH");
		map.put(ATTRIBUTE_USER, "sesuite");
		map.put(ATTRIBUTE_PASSWORD, "sesuite");
		map.put(ATTRIBUTE_DRIVER, "net.sourceforge.jtds.jdbc.Driver");
		return map;
	}

	public static Connection getConnection(String name) {
		return getInstance().getConectioByName(name);
	}

	private Connection getConectioByName(String name) {
		try {
			/*
			 * final String url = "jdbc:postgresql://des101:5432/forms13"; final
			 * String user = "postgres"; final String password = "111111";
			 * System.out.println(Class.forName(Driver.class.getName()));
			 */
			final String url = "jdbc:jtds:sqlserver://192.168.200.24:1433/vilmar14;intance=TECH";
			final String user = "sesuite";
			final String password = "sesuite";
			System.out.println(Class.forName("net.sourceforge.jtds.jdbc.Driver"));
			return DriverManager.getConnection(url, user, password);
		} catch (final Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
