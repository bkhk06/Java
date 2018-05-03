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
public class FME_query {//创建test类，保证文件名与类名相同    
	private static Connection con;
    private static String url;
    private static String user;
    private static String pwd;
    
    Statement sql;     
    ResultSet res;    
    public static Connection getConnection() throws IOException {  //建立返回值为Connection的方法    
    	try {        //加载数据库驱动类    
    		Class.forName("oracle.jdbc.driver.OracleDriver"); 
    		System.out.println("Oracle Database driver loaded successfully");  //返回加载驱动成功信息    
    		}catch(ClassNotFoundException e){
    			e.printStackTrace();   
    			System.out.println("Oracle Database driver loaded unsuccessfully"); 
    			}    
    	try {    
    		InputStream is = Connection.class.getResourceAsStream("/db.properties");//db.properties 是一个用户配置文件传用户名密码
    		Properties prop=new Properties();
            prop.load(is);
            url=prop.getProperty("url");
            user=prop.getProperty("user");
            pwd=prop.getProperty("password");
            con = DriverManager.getConnection(url, user, pwd);
    		System.out.println("Oracle Database connection success");  //返回连接成功信息  
            }catch(SQLException e) {  
            	  e.printStackTrace();  
                  System.out.println("Oracle Database connection failure");
                  }    
    	return con;//按方法要求返回一个Connection对象    
    	}
    
    public static ResultSet find(String sql) throws IOException{
    	con=getConnection();
        try {
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ResultSet find(String sql,Object ...pram) throws IOException{//...pram数组
        con=getConnection();
        try {
            PreparedStatement smt=con.prepareStatement(sql);
            for (int i=0;i<pram.length;i++){
                smt.setObject(i+1,pram[i]);
            }
            ResultSet rs=smt.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void insert(String sql,Object ...pram) throws IOException{//...pram数组
        con=getConnection();
        try {
            PreparedStatement smt=con.prepareStatement(sql);
            for (int i=0;i<pram.length;i++){
                smt.setObject(i+1,pram[i]);
            }
            smt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
     
	public static void main(String[] args) throws IOException, SQLException {   //主方法    
		FME_query.getConnection();//调用连接数据库的方法    
		String sql="SELECT count(*) FROM FME.FME_TODAY WHERE MISSION_DATE='20170103'" ;
		           
        ResultSet result=FME_query.find(sql);
        System.out.println(result);
        
        ArrayList<String> list = new ArrayList<String>();
        while(result.next()){
          list.add(result.getString("COUNT(*)"));//sql输出结果的列表名
        }
        for(String str : list){
        	System.out.println(str);
        	} 
        }    
} 


//db.properties
url=jdbc:oracle:thin:@192.168.11.68:1521:atfmsw
user=fme
password=fme

