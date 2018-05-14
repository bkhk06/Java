import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 
 
import java.sql.*; 
import java.util.List; 
import java.util.Properties; 
 
/** 
* ͨ�����ݿ�������ߣ��ṩ���ݿ����ӻ�ȡ��SQLִ�С���Դ�رյȹ��ܣ�֧�ֵ����ݿ�ΪOracle10g��MySQL5.x��</P> 
* 
* @author leizhimin 2012-03-05 11:22 
*/
public class FME_query { 
  private static Log log = LogFactory.getLog(FME_query.class); 
 
  static { 
    try { 
      Class.forName("oracle.jdbc.driver.OracleDriver"); 
      //Class.forName("com.mysql.jdbc.Driver"); 
    } catch (ClassNotFoundException e) { 
      log.error("�������ݿ�������������"); 
      e.printStackTrace(); 
    } 
  } 
 
  /** 
   * ����һ�����ݿ����� 
   * 
   * @param url    ���ݿ�����URL�� 
   * @param properties ��Ϊ���Ӳ����������ַ������/ֵ�Ե��б�ͨ������Ӧ�ð��� "user" �� "password" ���� 
   * @return һ��JDBC�����ݿ����� 
   * @throws SQLException ��ȡ����ʧ��ʱ���׳� 
   */
  public static Connection makeConnection(String url, Properties properties) throws SQLException { 
    Connection conn = null; 
    try { 
      conn = DriverManager.getConnection(url, properties); 
    } catch (SQLException e) { 
      log.error("��ȡ���ݿ����ӷ����쳣", e); 
      throw e; 
    } 
    return conn; 
  } 
 
  /** 
   * ��һ�����ݿ�������ִ��һ����̬SQL����ѯ 
   * 
   * @param conn   ���ݿ����� 
   * @param staticSql ��̬SQL����ַ��� 
   * @return ���ز�ѯ�����ResultSet���� 
   * @throws SQLException ִ���쳣ʱ���׳� 
   */
  public static ResultSet executeQuery(Connection conn, String staticSql) throws SQLException { 
    ResultSet rs = null; 
    try { 
      //����ִ��SQL�Ķ��� 
      Statement stmt = conn.createStatement(); 
      //ִ��SQL������ȡ���ؽ�� 
      rs = stmt.executeQuery(staticSql); 
    } catch (SQLException e) { 
      log.error("ִ��SQL���������飡\n" + staticSql); 
      throw e; 
    } 
    return rs; 
  } 
 
  /** 
   * ��һ�����ݿ�������ִ��һ����̬SQL��� 
   * 
   * @param conn   ���ݿ����� 
   * @param staticSql ��̬SQL����ַ��� 
   * @throws SQLException ִ���쳣ʱ���׳� 
   */
  public static void executeSQL(Connection conn, String staticSql) throws SQLException { 
    Statement stmt = null; 
    try { 
      //����ִ��SQL�Ķ��� 
      stmt = conn.createStatement(); 
      //ִ��SQL������ȡ���ؽ�� 
      stmt.execute(staticSql); 
    } catch (SQLException e) { 
      log.error("ִ��SQL���������飡\n" + staticSql); 
      throw e; 
    } finally { 
      close(stmt); 
    } 
  } 
 
  /** 
   * ��һ�����ݿ�������ִ��һ����̬SQL��� 
   * 
   * @param conn  ���ݿ����� 
   * @param sqlList ��̬SQL����ַ������� 
   * @throws SQLException ִ���쳣ʱ���׳� 
   */
  public static void executeBatchSQL(Connection conn, List<String> sqlList) throws SQLException { 
    try { 
      //����ִ��SQL�Ķ��� 
      Statement stmt = conn.createStatement(); 
      for (String sql : sqlList) { 
        stmt.addBatch(sql); 
      } 
      //ִ��SQL������ȡ���ؽ�� 
      stmt.executeBatch(); 
    } catch (SQLException e) { 
      log.error("ִ������SQL���������飡"); 
      throw e; 
    } 
  } 
 
  /** 
   * ��ȡOracle����һ��ָ����Sequence��һ��ֵ 
   * 
   * @param conn   ���ݿ����� 
   * @param seq_name Sequence���� 
   * @return Sequence��һ��ֵ 
   */
  public static long sequenceNextval(Connection conn, String seq_name) { 
    long val = -1L; 
    Statement stmt = null; 
    ResultSet rs = null; 
    try { 
      //����ִ��SQL�Ķ��� 
      stmt = conn.createStatement(); 
      //ִ��SQL������ȡ���ؽ�� 
      rs = stmt.executeQuery("select " + seq_name + ".nextval from dual"); 
      if (rs.next()) val = rs.getLong(1); 
    } catch (SQLException e) { 
      log.error("#ERROR# :��ȡSequenceֵ�������飡\n" + seq_name); 
      e.printStackTrace(); 
      throw new RuntimeException(e); 
    } finally { 
      close(rs); 
      close(stmt); 
    } 
    return val; 
  } 
 
  /** 
   * �ر����пɹرյ�JDBC��Դ�������Ⱥ�˳����������ȷ��˳��ִ�� 
   * 
   * @param objs �ɹرյ���Դ������Connection��Statement��ResultSet�����������Դ�Զ����� 
   */
  public static void closeAll(Object... objs) { 
    for (Object obj : objs) 
      if (obj instanceof ResultSet) close((ResultSet) obj); 
    for (Object obj : objs) 
      if (obj instanceof Statement) close((Statement) obj); 
    for (Object obj : objs) 
      if (obj instanceof Connection) close((Connection) obj); 
  } 
 
  private static void close(Connection conn) { 
    if (conn != null) 
      try { 
        conn.close(); 
        log.info("�ر����ݿ����ӳɹ�");
      } catch (SQLException e) { 
        log.error("�ر����ݿ����ӷ����쳣��"); 
      } 
  } 
 
  private static void close(ResultSet rs) { 
    if (rs != null) 
      try { 
        rs.close(); 
      } catch (SQLException e) { 
        log.error("�رս���������쳣��"); 
      } 
  } 
 
  private static void close(Statement stmt) { 
    if (stmt != null) 
      try { 
        stmt.close(); 
      } catch (SQLException e) { 
        log.error("�ر�SQL��䷢���쳣��"); 
      } 
  } 
 
  /** 
   * ���Դ��룬û�� 
   * 
   * @param args 
   * @throws SQLException 
   */
  public static void main(String[] args) throws SQLException { 
    String tns = "jdbc:oracle:thin:@192.168.11.68:1521:atfmsw"; 
    Properties p_ora = new Properties(); 
    p_ora.put("user", "fme"); 
    p_ora.put("password", "fme"); 
    p_ora.put("internal_logon", "normal"); 
 
    Connection ora_conn = makeConnection(tns, p_ora); 
    ResultSet rs1 = ora_conn.createStatement().executeQuery("SELECT COUNT(*) FROM FME.FME_TODAY WHERE MISSION_DATE='20170103'"); 
    rs1.next(); 
    System.out.println(rs1.getInt(1)); 
    rs1.close(); 
    ora_conn.close(); 
 
  } 
}