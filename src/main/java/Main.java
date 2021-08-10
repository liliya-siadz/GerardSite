import com.gerard.GerardSite.connection.ConnectionException;
import com.gerard.GerardSite.connection.ConnectionPool;
import com.gerard.GerardSite.service.tag.InitLocaleAndBundleCookies;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class Main {
        private static final Logger LOGGER =
                LogManager.getLogger(InitLocaleAndBundleCookies.class);
    public static void main(String[] args) throws ConnectionException {
        ConnectionPool connectionPool  = ConnectionPool.getInstance();
        Connection connection  =  connectionPool.giveOutConnection();
        connectionPool.getBackConnection(connection);

    }
}
