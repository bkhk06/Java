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

public class FME_query extends Thread {// 创建test类，保证文件名与类名相同

	private static Log logger = LogFactory.getLog(FME_query.class);
	private static int num_mfg = 0;
	private static int num_training = 0;

	/** 
	 * 
	 * 
	 * 
	*/
	/* Define relating functions for manufacture database */
	public static Connection getConnection_mfg() throws IOException { // 建立返回值为Connection的方法
		Connection conn = null;
		String url;
		String user;
		String pwd;
		try { // 加载数据库驱动类
			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.info("Oracle Database driver_mfg loaded successfully"); // 返回加载驱动成功信息
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("Oracle Database driver_mfg loaded unsuccessfully", e);
		}
		try {
			InputStream is = Connection.class.getResourceAsStream("/db.properties");// db.properties 是一个用户配置文件传用户名密码
			Properties prop = new Properties();
			prop.load(is);
			url = prop.getProperty("url_mfg");
			user = prop.getProperty("user_mfg");
			pwd = prop.getProperty("password_mfg");
			conn = DriverManager.getConnection(url, user, pwd);
			logger.info("Oracle Database connection_mfg success"); // 返回连接成功信息
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Oracle Database connection_mfg failure", e);
		}
		return conn;// 按方法要求返回一个Connection对象
	}

	/** 
	 * 
	 * 
	 * 
	*/
	/* Define relating functions for training database */
	public static Connection getConnection_training() throws IOException { // 建立返回值为Connection的方法
		Connection conn = null;
		String url;
		String user;
		String pwd;
		try { // 加载数据库驱动类
			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.info("Oracle Database driver_training loaded successfully"); // 返回加载驱动成功信息
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("Oracle Database driver_training loaded unsuccessfully", e);
		}
		try {
			InputStream is = Connection.class.getResourceAsStream("/db.properties");// db.properties 是一个用户配置文件传用户名密码
			Properties prop = new Properties();
			prop.load(is);
			url = prop.getProperty("url_training");
			user = prop.getProperty("user_training");
			pwd = prop.getProperty("password_training");
			conn = DriverManager.getConnection(url, user, pwd);
			logger.info("Oracle Database connection_training success"); // 返回连接成功信息
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Oracle Database connection_training failure", e);
		}
		return conn;// 按方法要求返回一个Connection对象
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
			logger.error("Error appeared when executing SQL！\n" + smt + e);
			return null;
		} finally {
			//smt.close();
			//rs.close();
			//conn.close();
			logger.info("smt close");
		}
	}

	public static ResultSet find(String sql, Connection conn, Object... pram) throws IOException {// ...pram数组
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
			logger.error("Error appeared when executing SQL！\n" + rs + e);
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

	public static void insert(String sql, Connection conn, Object... pram) throws IOException {// ...pram数组
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
			logger.error("Error appeared when executing SQL！\n" + smt + e);
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

		InputStream is = Connection.class.getResourceAsStream("/sql.properties");// db.properties 是一个用户配置文件传用户名密码
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

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			logger.info("\n" + df.format(new Date()) + " run FME compareing:");// new Date()为获取当前系统时间

			while (!isInterrupted()) //
			{
				// Time interval for FME msg comparing
				Thread.sleep(time_interval);

				/* FME_manufaturing */
				ResultSet rs_mfg = FME_query.find(sql_mfg, getConnection_mfg());

				ArrayList<String> list = new ArrayList<String>();
				while (rs_mfg.next()) {
					list.add(rs_mfg.getString(key_word));// sql输出结果的列表名
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
					list.add(rs_training.getString(key_word));// sql输出结果的列表名
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

				// 比较正式库和测试库报文增量
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
	public static void main(String[] args) { // 主方法
		new FME_query().start();

	}
}
