package com.syntax.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	private static List<Map<String, String>> listData;
	private static List<Map<String,String>> jobListData;
	private static List<Map<String,String>> nationalityData;
	private static List<Map<String,String>> locationData;
	
	/*
	 * method will establish connection to DB
	 */
	public static void getConnection() {
		try {
			conn = DriverManager.getConnection(ConfigsReader.getProperty("dbUrl"),
					ConfigsReader.getProperty("dbUsername"), ConfigsReader.getProperty("dbpassword"));
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Retrieves data and stored inside the list<Map>
	 * 
	 * 
	 * @returns list<Map<String,String>>
	 */
	public static List<Map<String, String>> storeDataFromDB(String sqlQuery) {
		try {
			getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sqlQuery);

			ResultSetMetaData rsMetaData = rs.getMetaData();
			listData = new ArrayList<>();

			while (rs.next()) {

				Map<String, String> mapData = new LinkedHashMap<>();
				for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
					mapData.put(rsMetaData.getColumnName(i), rs.getObject(i).toString());

				}
				listData.add(mapData);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return listData;

	}
	/**
	 * this method will close the connection with data base
	 */

	public static List<Map<String, String>> jobTitlesFromDB(String sqlQuery){
		try {
			st=conn.createStatement(); 
			rs=st.executeQuery(sqlQuery);
			ResultSetMetaData rsMetaData=rs.getMetaData();
			jobListData=new ArrayList<Map<String,String>>();
			
			while(rs.next()) {
				Map<String,String>	mapData=new LinkedHashMap<String, String>();
				for(int i=1; i<=rsMetaData.getColumnCount();i++) {
					mapData.put(rsMetaData.getColumnName(i), rs.getObject(i).toString());
				}
				jobListData.add(mapData);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return jobListData;
		}
		
	public static List<Map<String, String>> nationalitiesFromDB(String sqlQuery){
		try {
			st=conn.createStatement(); 
			rs=st.executeQuery(sqlQuery);
			ResultSetMetaData rsMetaData=rs.getMetaData();
			nationalityData=new ArrayList<Map<String,String>>();
			
			while(rs.next()) {
				Map<String,String>	mapNationality=new LinkedHashMap<String, String>();
				for(int i=1; i<=rsMetaData.getColumnCount();i++) {
					mapNationality.put(rsMetaData.getColumnName(i), rs.getObject(i).toString());
				}
				nationalityData.add(mapNationality);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return nationalityData;
		}
		
	public static List<Map<String, String>> locationsFromDB(String sqlQuery){
		try {
			st=conn.createStatement(); 
			rs=st.executeQuery(sqlQuery);
			ResultSetMetaData rsMetaData=rs.getMetaData();
			locationData=new ArrayList<Map<String,String>>();
			
			while(rs.next()) {
				Map<String,String>	mapLocation=new LinkedHashMap<String, String>();
				for(int i=1; i<=rsMetaData.getColumnCount();i++) {
					mapLocation.put(rsMetaData.getColumnName(i), rs.getObject(i).toString());
				}
				locationData.add(mapLocation);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return locationData;
		}
	
	
	public static void closeConnection() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
