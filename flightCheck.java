import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class flightCheck {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@192.168.11.69:15261/atfmsw";
    private static final String ORACLE_USERNAME = "fme";
    private static final String ORACLE_PASSWORD = "atfmserver";
    private static final String SQL_QUERY = "select ft.id,ft.flightid,ft.s_deptime,ft.p_deptime,ft.p_depap,ft.p_arrap,ft.r_deptime from fme.fme_today ft where  ft.p_deptime is not null and ft.s_deptime is null   and ft.r_deptime is null for update order by ft.p_deptime ;";
    private static final Logger logger = Logger.getLogger(OracleConnection.class);

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            try (Connection connection = DriverManager.getConnection(ORACLE_URL, ORACLE_USERNAME, ORACLE_PASSWORD);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(SQL_QUERY)) {

                while (resultSet.next()) {
                    // 处理查询结果
                    logger.info("Column value: " + resultSet.getString("column_name"));
                }
            } catch (Exception e) {
                logger.error("Error executing SQL query", e);
            }
        }, 0, 5, TimeUnit.MINUTES);
    }

    
}
