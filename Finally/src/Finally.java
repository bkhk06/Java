import java.sql.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Finally extends Thread {
	private static Log logger = LogFactory.getLog(Finally.class);
	private static int num_mfg = 0;
	private static int num_test = 0;

	private static Connection ct_mfg = null;
	private static PreparedStatement ps_mfg = null;
	private static ResultSet rs_mfg = null;

	private static Connection ct_test = null;
	private static PreparedStatement ps_test = null;
	private static ResultSet rs_test = null;

	// SQL statement
	private static String sql_mfg = null;
	private static String sql_test = null;
	private static String key_word = null;
	private static long time_interval = 0;

	public Finally() {

		try {

			InputStream is_db = Connection.class.getResourceAsStream("/db.properties");// db.properties ��һ���û������ļ����û�������
			Properties prop_db = new Properties();
			try {
				prop_db.load(is_db);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e);
			}

			InputStream is_sql = Connection.class.getResourceAsStream("/sql.properties");// db.properties
																							// ��һ���û������ļ����û�������
			Properties prop_sql = new Properties();
			try {
				prop_db.load(is_sql);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e);
			}

			InputStream is = Connection.class.getResourceAsStream("/sql.properties");// db.properties ��һ���û������ļ����û�������
			Properties prop = new Properties();
			try {
				prop.load(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e);
			}
			sql_mfg = prop.getProperty("sql_mfg");
			sql_test = prop.getProperty("sql_test");
			key_word = prop.getProperty("key_word");
			String time_interval_s = prop.getProperty("time_interval");

			/**/
			try {
				time_interval = Integer.parseInt(time_interval_s);
				logger.info("Sleep time_interval: " + time_interval + " ms");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				logger.error(e);
			}

			// 1.��������
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				logger.info("Oracle Database loaded successfully!");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				logger.error("Oracle Database driver loaded unsuccessfully!", e);

			}
			// �������ӳɹ���Ϣ

			ct_mfg = DriverManager.getConnection(prop_db.getProperty("url_mfg"), prop_db.getProperty("user_mfg"),
					prop_db.getProperty("password_mfg"));
			// 3.����PreparedStatement
			ps_mfg = ct_mfg.prepareStatement(sql_mfg);
			// 4.ִ��SQL

			ct_test = DriverManager.getConnection(prop_db.getProperty("url_test"), prop_db.getProperty("user_test"),
					prop_db.getProperty("password_test"));
			// 3.����PreparedStatement
			ps_test = ct_mfg.prepareStatement(sql_test);
			// 4.ִ��SQL

			while (!isInterrupted()) {
				try {
					Thread.sleep(time_interval);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e);
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
				logger.info("\n" + df.format(new Date()) + " ########################### run FME compareing:");// new
																												// Date()Ϊ��ȡ��ǰϵͳʱ��
				rs_mfg = ps_mfg.executeQuery();
				// 5.�Ի�ȡ�����ݽ��в���
				while (rs_mfg.next()) {
					logger.info("mfg msg: " + rs_mfg.getString(key_word));
					num_mfg = Integer.parseInt(rs_mfg.getString(key_word));
				}

				rs_test = ps_test.executeQuery();
				// 5.�Ի�ȡ�����ݽ��в���
				while (rs_test.next()) {
					logger.info("test msg: " + rs_test.getString(key_word));
					num_test = Integer.parseInt(rs_test.getString(key_word));
				}

				// �Ƚ���ʽ��Ͳ��Կⱨ������
				logger.info("FME msg comparing result: " + (num_mfg - num_test));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		} finally {
			try {
				if (rs_mfg != null) {
					rs_mfg.close();
				}
				if (ps_mfg != null) {
					ps_mfg.close();
				}
				if (ct_mfg != null) {
					ct_mfg.close();
				}
				if (rs_test != null) {
					rs_test.close();
				}
				if (ps_test != null) {
					ps_test.close();
				}
				if (ct_test != null) {
					ct_test.close();
				}
				logger.info("Connections were closed in sucess!");
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
	}

	public static void main(String[] args) {
		new Finally();
	}
}