import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FME_query extends Thread {// ����test�࣬��֤�ļ�����������ͬ

	private static Log logger = LogFactory.getLog(FME_query.class);
	private static int num_mfg = 0;
	private static int num_training = 0;

	/** 
	 * 
	 * 
	 * 
	*/
	/* Define relating functions for manufacture database */
	public static Connection getConnection_mfg() throws IOException { // ��������ֵΪConnection�ķ���
		Connection conn = null;
		String url;
		String user;
		String pwd;
		try { // �������ݿ�������
			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.info("Oracle Database driver_mfg loaded successfully"); // ���ؼ��������ɹ���Ϣ
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("Oracle Database driver_mfg loaded unsuccessfully", e);
		}
		try {
			InputStream is = Connection.class.getResourceAsStream("/db.properties");// db.properties ��һ���û������ļ����û�������
			Properties prop = new Properties();
			prop.load(is);
			url = prop.getProperty("url_mfg");
			user = prop.getProperty("user_mfg");
			pwd = prop.getProperty("password_mfg");
			conn = DriverManager.getConnection(url, user, pwd);
			logger.info("Oracle Database connection_mfg success"); // �������ӳɹ���Ϣ
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Oracle Database connection_mfg failure", e);
		}
		return conn;// ������Ҫ�󷵻�һ��Connection����
	}

	/** 
	 * 
	 * 
	 * 
	*/
	/* Define relating functions for training database */
	public static Connection getConnection_training() throws IOException { // ��������ֵΪConnection�ķ���
		Connection conn = null;
		String url;
		String user;
		String pwd;
		try { // �������ݿ�������
			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.info("Oracle Database driver_training loaded successfully"); // ���ؼ��������ɹ���Ϣ
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("Oracle Database driver_training loaded unsuccessfully", e);
		}
		try {
			InputStream is = Connection.class.getResourceAsStream("/db.properties");// db.properties ��һ���û������ļ����û�������
			Properties prop = new Properties();
			prop.load(is);
			url = prop.getProperty("url_training");
			user = prop.getProperty("user_training");
			pwd = prop.getProperty("password_training");
			conn = DriverManager.getConnection(url, user, pwd);
			logger.info("Oracle Database connection_training success"); // �������ӳɹ���Ϣ
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Oracle Database connection_training failure", e);
		}
		return conn;// ������Ҫ�󷵻�һ��Connection����
	}

	/* Ending */

	public static ResultSet find(String sql, Connection conn) throws IOException, SQLException {
		Statement smt = null;
		ResultSet rs = null;
		try {
			smt = conn.createStatement();
			rs = smt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error appeared when executing SQL��\n" + smt + e);
			return null;
		} finally {
			//smt.close();
			//rs.close();
			//conn.close();
			logger.info("smt close");
		}
	}

	public static ResultSet find(String sql, Connection conn, Object... pram) throws IOException {// ...pram����
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			smt = conn.prepareStatement(sql);
			for (int i = 0; i < pram.length; i++) {
				smt.setObject(i + 1, pram[i]);
			}
			rs = smt.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error appeared when executing SQL��\n" + rs + e);
			return null;
		} finally {
			try {
				smt.close();
				rs.close();
				conn.close();
				logger.info("smt close");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e);
			}
		}
	}

	public static void insert(String sql, Connection conn, Object... pram) throws IOException {// ...pram����
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			smt = conn.prepareStatement(sql);
			for (int i = 0; i < pram.length; i++) {
				smt.setObject(i + 1, pram[i]);
			}
			smt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error appeared when executing SQL��\n" + smt + e);
		} finally {
			try {
				smt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e);
			}
		}
	}
	/* Ending */

	/** 
	 * 
	 * 
	 * 
	*/
	// All the actions in run()
	public void run() {
		// SQL statement
		String sql_mfg = null;
		String sql_training = null;
		String key_word = null;
		int time_interval = 0;

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
		sql_training = prop.getProperty("sql_training");
		key_word = prop.getProperty("key_word");
		String time_interval_s = prop.getProperty("time_interval");

		// Start database connection of mfg.

		/**/
		try {
			time_interval = Integer.parseInt(time_interval_s);
			logger.info("Sleep time_interval: " + time_interval + " ms");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error(e);
		}

		try {

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
			logger.info("\n" + df.format(new Date()) + " run FME compareing:");// new Date()Ϊ��ȡ��ǰϵͳʱ��

			while (!isInterrupted()) //
			{
				// Time interval for FME msg comparing
				Thread.sleep(time_interval);

				/* FME_manufaturing */
				ResultSet rs_mfg = FME_query.find(sql_mfg, getConnection_mfg());

				ArrayList<String> list = new ArrayList<String>();
				while (rs_mfg.next()) {
					list.add(rs_mfg.getString(key_word));// sql���������б���
					// list.add(result.getString("FLIGHTID"));
				}

				for (String str : list) {
					try {
						num_mfg = Integer.parseInt(str);
					} catch (NumberFormatException e) {
						e.printStackTrace();
						logger.error(e);
					}
					logger.info("FME_mfg: " + num_mfg);
				}

				/**
				 * #######
				 */
				/* FME_training */
				ResultSet rs_training = FME_query.find(sql_training, getConnection_training());
				list = new ArrayList<String>();
				while (rs_training.next()) {
					list.add(rs_training.getString(key_word));// sql���������б���
				}

				for (String str : list) {
					try {
						num_training = Integer.parseInt(str);
					} catch (NumberFormatException e) {
						e.printStackTrace();
						logger.error(e);
					}
					logger.info("FME_mfg: " + num_training);
				}

				// �Ƚ���ʽ��Ͳ��Կⱨ������
				logger.info("FME msg comparing result: " + (num_mfg - num_training));

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.error(e);
			logger.info("Thread closed");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			
		}
	}

	/** 
	 * 
	 * 
	 * 
	*/
	public static void main(String[] args) { // ������
		new FME_query().start();

	}
}
