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

public class FME_msg_test_DEP<Bookinfo> extends Thread {
	private static Log logger = LogFactory.getLog(FME_msg_test_DEP.class);
	private static String atot_mfg = null;
	private static String atot_test = null;
	private static String flightid_mfg = null;
	private static String flightid_test = null;

	private static Connection ct_mfg = null;
	private static PreparedStatement ps_mfg = null;
	private static ResultSet rs_mfg = null;

	private static Connection ct_test = null;
	private static PreparedStatement ps_test = null;
	private static ResultSet rs_test = null;

	// SQL statement
	private static String sql_mfg = null;
	private static String sql_test = null;
	private static long time_interval = 0;

	public FME_msg_test_DEP() throws IOException, ClassNotFoundException, InterruptedException, SQLException {

		try {

			InputStream is_db = Connection.class.getResourceAsStream("/config/db.properties");// db.properties
																								// 是一个用户配置文件传用户名密码
			Properties prop_db = new Properties();

			prop_db.load(is_db);

			InputStream is_sql = Connection.class.getResourceAsStream("/config/sql.properties");// sql.properties
																								// 是一个用户配置文件传sql
			Properties prop_sql = new Properties();
			prop_sql.load(is_sql);

			sql_mfg = prop_sql.getProperty("sql_mfg");
			sql_test = prop_sql.getProperty("sql_test");
			prop_sql.getProperty("key_word_atot");
			prop_sql.getProperty("key_word_flightid");

			String time_interval_s = prop_sql.getProperty("time_interval");

			/* String to int conversion */
			time_interval = Integer.parseInt(time_interval_s);
			// logger.info("Sleep time_interval: " + time_interval + " s");

			// 加载驱动

			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.info("Oracle Database loaded successfully!");

			// 返回连接成功信息

			ct_mfg = DriverManager.getConnection(prop_db.getProperty("url_mfg"), prop_db.getProperty("user_mfg"),
					prop_db.getProperty("password_mfg"));
			logger.info("Oracle Database_mfg connection were created successfully"); // 返回连接成功信息

			ct_test = DriverManager.getConnection(prop_db.getProperty("url_test"), prop_db.getProperty("user_test"),
					prop_db.getProperty("password_test"));
			logger.info("Oracle Database_test connection were created successfully"); // 返回连接成功信息

			// 创建PreparedStatement
			ps_mfg = ct_mfg.prepareStatement(sql_mfg);
			// 创建PreparedStatement
			ps_test = ct_test.prepareStatement(sql_test);
			// 执行SQL

			// 保存上一次查询报文结果，便于比较

			while (!isInterrupted()) {

				Thread.sleep(time_interval * 1000);//

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				logger.info(" \n\n########################### " + df.format(new Date())
						+ " FME DEP msg(ATOT)  comparision between production and test databases: " + time_interval
						+ "s time interval");// new
				// Date()为获取当前系统时间
				rs_mfg = ps_mfg.executeQuery();
				logger.info("executeQuery_mfg done!");
				// 对获取的数据进行操作
				// ArrayList<Bookinfo> books=new ArrayList<Bookinfo>();
				while (rs_mfg.next()) {
					logger.info("begin read the result_mfg!");
					atot_mfg = rs_mfg.getString(2);
					flightid_mfg = rs_mfg.getString(1);
					logger.info("Latest ATOT in production databases: " + flightid_mfg + ": " + atot_mfg);
				}

				rs_test = ps_test.executeQuery();
				// 对获取的数据进行操作
				while (rs_test.next()) {
					atot_test = rs_test.getString(2);
					flightid_test = rs_test.getString(1);
					logger.info("Latest ATOT in test databases: " + flightid_test + ": " + atot_test);
				}

				// 比较正式库和测试库报文增量
				if (!atot_test.equals(atot_mfg)) {
					logger.error("WARNING!!!! DEP msg  between  production and test databases are different!!!");
					logger.error(flightid_mfg + ": " + atot_mfg);
					logger.error(flightid_test + ": " + atot_test);
				} else {
					logger.info("Latest ATOT  between  production and test databases are Same!!!");
					logger.info(flightid_mfg + ": " + atot_mfg);
					logger.info(flightid_test + ": " + atot_test);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			logger.error(e.fillInStackTrace());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			logger.error(e.fillInStackTrace());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			logger.error(e.fillInStackTrace());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			logger.error(e.fillInStackTrace());
		}

		finally {
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
			logger.info("Connection,ResultSet,Statement  were closed in sucess!");

		}
	}

	public static void main(String[] args)
			throws ClassNotFoundException, IOException, InterruptedException, SQLException {
		new FME_msg_test_DEP<Object>();
	}
}