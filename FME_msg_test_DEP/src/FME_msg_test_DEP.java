import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FME_msg_test_DEP<Bookinfo> extends Thread {
	private static Log logger = LogFactory.getLog(FME_msg_test_DEP.class);
	private static String atot_mfg = null;
	private static String atot_test = null;
	private static int count_mfg = 0;
	private static int count_test = 0;
	private static String flightid_mfg = null;
	private static String flightid_test = null;

	private static Connection ct_mfg = null;
	private static PreparedStatement ps_mfg = null;
	private static ResultSet rs_mfg = null;

	private static Connection ct_test = null;
	private static PreparedStatement ps_test = null;
	private static ResultSet rs_test = null;

	// SQL statement
	private static PreparedStatement ps_mfg_dep = null;
	private static PreparedStatement ps_test_dep = null;

	private static PreparedStatement ps_mfg_count = null;
	private static PreparedStatement ps_test_count = null;

	private static PreparedStatement ps_mfg_fc = null;
	private static PreparedStatement ps_test_fc = null;

	private static PreparedStatement ps_mfg_msg = null;
	private static PreparedStatement ps_test_msg = null;

	private static String sql_mfg = null;
	private static String sql_test = null;
	private static String sql_count = null;
	private static String sql_dep = null;
	private static String sql_fc_mfg = null;
	private static String sql_fc_test = null;
	private static String sql_msg = null;
	private static long time_interval = 0;
	private static int loop_times = 0;
	private static boolean loop_flag = true;
	private static String DEP_AP = null;
	private static int time_count = 0;

	@SuppressWarnings("rawtypes")
	private static Map<String, Comparable> map_mfg = new HashMap<String, Comparable>();
	@SuppressWarnings("rawtypes")
	private static Map<String, Comparable> map_test = new HashMap<String, Comparable>();

	public FME_msg_test_DEP() throws IOException, ClassNotFoundException, InterruptedException, SQLException {

		try {
			logger.info("\n\n");
			logger.info("================================================");
			logger.info("Begin FME messages test between MFG and TEST Databases:");
			logger.info("================================================");
		
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
			sql_count = prop_sql.getProperty("sql_count");
			sql_dep = prop_sql.getProperty("sql_dep");
			sql_fc_mfg = prop_sql.getProperty("sql_fc_mfg");
			sql_fc_test = prop_sql.getProperty("sql_fc_test");
			sql_msg = prop_sql.getProperty("sql_msg");
			DEP_AP = prop_sql.getProperty("DEP_AP");
			
			String time_count_s=prop_sql.getProperty("time_count");

			String time_interval_s = prop_sql.getProperty("time_interval");

			/* String to int conversion */
			time_interval = Integer.parseInt(time_interval_s);
			time_count=Integer.parseInt(time_count_s);

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
			ps_test = ct_test.prepareStatement(sql_test);

			ps_mfg_dep = ct_mfg.prepareStatement(sql_dep);
			ps_test_dep = ct_test.prepareStatement(sql_dep);

			ps_mfg_count = ct_mfg.prepareStatement(sql_count);
			ps_test_count = ct_test.prepareStatement(sql_count);

			ps_mfg_fc = ct_mfg.prepareStatement(sql_fc_mfg);
			ps_test_fc = ct_test.prepareStatement(sql_fc_test);
			
			ps_mfg_msg = ct_mfg.prepareStatement(sql_msg);
			ps_test_msg = ct_test.prepareStatement(sql_msg);
			
			/* Add Frame */
			JFrame frame = new JFrame(" FME_msg_test for " + DEP_AP);
			frame.setLocationRelativeTo(null);
			frame.setUndecorated(true); // 去掉窗口的装饰
			frame.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG); // 设置为简单搜索对话框风格
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 设置关闭按钮失效

			frame.setSize(350, 100);
			frame.setVisible(true);
			JLabel label = new JLabel("Press 'Ctrl+Q' to quit FME_msg_test for " + DEP_AP);
			frame.add(label);

            //begin the comparing
			while (loop_flag) {

				frame.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if ((e.isControlDown() == true) && (e.getKeyCode() == KeyEvent.VK_Q)) {
							logger.info("ctrl + Q has pressed,quit!");
							loop_flag = false;

						} else {
							System.out.println("other key  pressed");
						}

					}
				});

				Thread.sleep(time_interval * 1000);//

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				logger.info(" \n\n========================== " + df.format(new Date()) + " FME DEP msg test: "
						+ time_interval + "s time interval");// new
				// Date()为获取当前系统时间

				// 执行SQL

				rs_mfg = ps_mfg.executeQuery();
				// logger.info("executeQuery_mfg done!");
				// 对获取的数据进行操作
				// ArrayList<Bookinfo> books=new ArrayList<Bookinfo>();
				while (rs_mfg.next()) {
					// logger.info("begin read the result_mfg!");
					atot_mfg = rs_mfg.getString(2);
					flightid_mfg = rs_mfg.getString(1);
				}

				rs_test = ps_test.executeQuery();
				// 对获取的数据进行操作
				while (rs_test.next()) {
					atot_test = rs_test.getString(2);
					flightid_test = rs_test.getString(1);
				}
				
				if(atot_mfg==null || atot_test==null)
				{
					logger.error("ATOT result is invalid, please check!!!"+flightid_mfg+": "+atot_mfg+"||"+flightid_test+": "+atot_test);
					continue;
				}
				

				// 比较正式库和测试库报文增量
				if (!atot_test.equals(atot_mfg)) {
					logger.error("WARNING!!!! DEP msg  between  production and test databases are different!!!");
					logger.info("Latest ATOT in production databases: " + flightid_mfg + ": " + atot_mfg);
					logger.info("Latest ATOT in test databases: " + flightid_test + ": " + atot_test);
				} else {
					logger.info("Latest ATOT  between  production and test databases are Same!!!");
					logger.info("Latest ATOT in production databases: " + flightid_mfg + ": " + atot_mfg);
					logger.info("Latest ATOT in test databases: " + flightid_test + ": " + atot_test);
				}

				// 比较各个机场FME_TODAY起飞报报文数量-----------------------------------------------------------------------------------------------
				logger.info("\n-----Compare count num between test and mfg DEP msg:");
				rs_mfg = ps_mfg_dep.executeQuery();
				// logger.info("executeQuery_mfg done!");
				// 对获取的数据进行操作
				// ArrayList<Bookinfo> books=new ArrayList<Bookinfo>();
				while (rs_mfg.next()) {
					// logger.info("begin read the result_mfg!");
					count_mfg = rs_mfg.getInt(1);
				}

				rs_test = ps_test_dep.executeQuery();
				// 对获取的数据进行操作
				while (rs_test.next()) {
					count_test = rs_test.getInt(1);
				}
				logger.info("Count in FME_TODAY.DEP for " + DEP_AP + " in production: " + count_mfg);
				logger.info("Count in FME_TODAY.DEP for " + DEP_AP + " in test: " + count_test);
				logger.info("D-Value of DEP message between mfg and test database in " + DEP_AP + " : "
						+ (count_test - count_mfg));

				// 比较各个机场FME_TODAY报文数量-----------------------------------------------------------------------------------------------
				logger.info("\n-----Compare count num between test and mfg FME_TODAY msg:");

				rs_mfg = ps_mfg_count.executeQuery();
				// logger.info("executeQuery_mfg done!");
				// 对获取的数据进行操作
				// ArrayList<Bookinfo> books=new ArrayList<Bookinfo>();
				while (rs_mfg.next()) {
					// logger.info("begin read the result_mfg!");
					count_mfg = rs_mfg.getInt(1);
				}

				rs_test = ps_test_count.executeQuery();
				// 对获取的数据进行操作
				while (rs_test.next()) {
					count_test = rs_test.getInt(1);
				}
				logger.info("Count in FME_TODAY for " + DEP_AP + " in production: " + count_mfg);
				logger.info("Count in FME_TODAY for " + DEP_AP + " in test: " + count_test);
				logger.info("D-Value of FME_TODAY message between mfg and test database in " + DEP_AP + " : "
						+ (count_test - count_mfg));

				// 比较各个机场FLIGHT_COORDINATION报文数量-----------------------------------------------------------------------------------------------
				logger.info("\n-----Compare count num between test and mfg FLIGHT_COORDINATION msg:");

				rs_mfg = ps_mfg_fc.executeQuery();
				// logger.info("executeQuery_mfg done!");
				// 对获取的数据进行操作
				// ArrayList<Bookinfo> books=new ArrayList<Bookinfo>();
				while (rs_mfg.next()) {
					// logger.info("begin read the result_mfg!");
					count_mfg = rs_mfg.getInt(1);
				}

				rs_test = ps_test_fc.executeQuery();
				// 对获取的数据进行操作
				while (rs_test.next()) {
					count_test = rs_test.getInt(1);
				}
				logger.info("Count in FLIGHT_COORDINATION for " + DEP_AP + " in production: " + count_mfg);
				logger.info("Count in FLIGHT_COORDINATION for " + DEP_AP + " in test: " + count_test);
				logger.info("D-Value of FLIGHT_COORDINATION message between mfg and test database in " + DEP_AP + " : "
						+ (count_test - count_mfg));

				// 对比各个机场DEP报文，确定那个航班丢失报文-----------------------------------------------------------------------------------------------

				if (loop_times >= time_count) {// 创建PreparedStatement
					logger.info("\n-----Compare DEP message between test and mfg FME_TODAY:");
					
					// 执行SQL
					
					// 对获取的数据进行操作--测试库
					logger.info("Begin create HashMap");
					rs_test = ps_test_msg.executeQuery();
					while (rs_test.next()) {
						atot_test = rs_test.getString(2);
						flightid_test = rs_test.getString(1);
						map_test.put(flightid_test, atot_test);
					}
					
					// 对获取的数据进行操作--生产库
					rs_mfg = ps_mfg_msg.executeQuery();
					while (rs_mfg.next()) {
						atot_mfg = rs_mfg.getString(2);
						flightid_mfg = rs_mfg.getString(1);
						map_mfg.put(flightid_mfg, atot_mfg);
					}/*数据入HashMap，*/
					logger.info("End create HashMap");
									
					
					logger.info("Begin the DEP messages' comparison between mfg and test databases:");
					
					logger.info("------------------Compare HashMap:from K-V_mfg");
					for (@SuppressWarnings("rawtypes")
					Entry<String, Comparable> entry : map_mfg.entrySet()) {
						//logger.info(entry.getKey() + " : " + entry.getValue());
						String flightID= (String)entry.getKey();
						String atot=(String) entry.getValue();
						String atotOfflightTest=(String) map_test.get(flightID);
						if(atotOfflightTest==null)
							logger.error("This flight "+flightID+": "+atot+" in mfg database not exists in test database,pls double check that!");
						else
							if(atotOfflightTest.equals(atot)) {
								logger.info("-+-+-+-+-+-+-+This flight "+flightID+" in mfg database has same atot: "+atot+" ,as in test database ");
							}
							else
								logger.error("This flight "+flightid_mfg+" in mfg database has different atot: "+atot+"||"+atotOfflightTest+" in test database !");
					}
					
					logger.info("------------------Compare HashMap:from K-V_test");
					for (@SuppressWarnings("rawtypes")
					Entry<String, Comparable> entry : map_test.entrySet()) {
						//logger.info(entry.getKey() + " : " + entry.getValue());
						String flightID= (String)entry.getKey();
						String atot=(String) entry.getValue();
						String atotOfflightMfg=(String) map_mfg.get(flightID);
						if(atotOfflightMfg==null)
							logger.error("This flight "+flightID+": "+atot+" in test database not exists in mfg database,pls double check that!");
						else
							if(atotOfflightMfg.equals(atot)) {
								logger.info("*************This flight "+flightID+" in test database has same atot:"+atot+" ,as in mfg database ");
							}
							else
								logger.error("This flight "+flightid_mfg+" in mfg database has different atot: "+atot+"||"+atotOfflightMfg+" in test database !");	
					}
										
					logger.info("DEP message compare between test and mfg FME_TODAY done~");
              
                    //清空map，便于下一次循环
					map_mfg.clear();
					map_test.clear();
					
					loop_times = 0;// reset loop_times
				}

				loop_times++;// Timer for Array_List
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
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
			if (rs_test != null) {
				rs_test.close();
			}
			
			if (ps_mfg != null) {
				ps_mfg.close();
			}
			
			if (ps_test != null) {
				ps_test.close();
			}
			
			if (ps_mfg_dep != null) {
				ps_mfg_dep.close();
			}
			if (ps_test_dep != null) {
				ps_test_dep.close();
			}
			if (ps_mfg_count != null) {
				ps_mfg_count.close();
			}
			if (ps_test_count != null) {
				ps_test_count.close();
			}
			
			if (ps_mfg_fc != null) {
				ps_mfg_fc.close();
			}
			if (ps_test_fc != null) {
				ps_test_fc.close();
			}
			if (ps_mfg_msg != null) {
				ps_mfg_msg.close();
			}
			if (ps_test_msg != null) {
				ps_test_msg.close();
			}
			
			if (ct_mfg != null) {
				ct_mfg.close();
			}
			if (ct_test != null) {
				ct_test.close();
			}

			logger.info("\n\n");
			logger.info("================================================");
			logger.info("Connection,ResultSet,Statement  were closed successfully!");
			logger.info("================================================");
			System.exit(1);

		}
	}

	public static void main(String[] args)
			throws ClassNotFoundException, IOException, InterruptedException, SQLException {
		new FME_msg_test_DEP<Object>();
	}
}