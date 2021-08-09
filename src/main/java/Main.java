import com.gerard.GerardSite.connection.ConnectionPool;
import com.gerard.GerardSite.custom_tag.InitLocaleAndBundleCookies;
import com.gerard.GerardSite.util.CustomDocumentUtil;
import com.gerard.GerardSite.util.OneOffSingleXmlDocDomParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(InitLocaleAndBundleCookies.class);
    public static void main(String[] args) {
        ConnectionPool connectionPool  = ConnectionPool.getInstance();
        Connection connection  =  connectionPool.giveOutConnection();

    }
}
