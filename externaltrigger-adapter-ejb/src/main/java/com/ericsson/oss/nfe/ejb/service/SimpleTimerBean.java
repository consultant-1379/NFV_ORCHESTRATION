/*package com.ericsson.oss.nfe.ejb.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import org.json.simple.JSONObject;

@Stateless
public class SimpleTimerBean {

	// @Resource(name = "jdbc/MyAppServerDataSourceJNDIName")
	// private DataSource ds;

	private String selectSQL = "select *  from FMA_alarm_list where Event_time > ? and Object_of_reference='SubNetwork=ONRM_RootMo,SubNetwork=RNC21,MeContext=RNC21RBS30' and Perceived_severity=1";

	@PostConstruct
	void onServiceStart() {
		System.out
				.println(" -----------------------~ SimpleTimerBean Starting service ~------------------------------------");

		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your SYbase JTDS JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("SYbase JTDS Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:jtds:sybase://10.45.19.163:5025/fmadb_1_1 ", "sa",
					"sybase11");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			releaseConnection(connection);
		} else {
			System.out.println("Failed to make connection!");
		}
	}

//	@Schedule(minute = "*/
  //3", hour = "*", info = "every minute", persistent = false)
	/*public void doWork() {

		System.out.println("timer bean called at  " + new Date());

		System.out.println("Alarms since last run --> " + getAlarmsSinceLastRun());

	}

	private List<JSONObject> getAlarmsSinceLastRun() {
		Connection dbConnection = null;

		PreparedStatement preparedStatement = null;

		List<JSONObject> alarms = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);

			Calendar now = Calendar.getInstance();
			now.add(Calendar.MINUTE, -3);

			preparedStatement.setDate(1, new java.sql.Date(now.getTime().getTime()));

			ResultSet rs = preparedStatement.executeQuery();

			System.out.println("rs obtained !!");
			while (rs.next()) {

				String alarmId = rs.getString("Log_record_id");
				String moName = rs.getString("Object_of_reference");
				String problemText = rs.getString("Object_of_reference");

				JSONObject alarm = new JSONObject();
				alarm.put("alarmId", alarmId);
				alarm.put("moName", moName);
				alarm.put("problemText", problemText);

				alarms.add(alarm);
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			releaseConnection(dbConnection);

		}

		return alarms;
	}

	private Connection getDBConnection() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:jtds:sybase://10.45.19.163:5025/fmadb_1_1 ", "sa",
					"sybase11");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			return null;
		}
		return connection;
	}

	private void releaseConnection(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("releaseConnection failed");
			e.printStackTrace();
		}
	}
}*/
