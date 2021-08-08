import com.gerard.GerardSite.custom_tag.InitLocaleAndBundleCookies;
import com.gerard.GerardSite.util.CustomDocumentUtil;
import com.gerard.GerardSite.util.OneOffSingleXmlDocDomParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(InitLocaleAndBundleCookies.class);
    public static void main(String[] args) {
        //ConnectionPool connectionPool  = ConnectionPool.getInstance();
     //  Connection connection  =  connectionPool.giveOutConnection()
        String url = "http://localhost:8080/GerardSite/description.xml";

        OneOffSingleXmlDocDomParser oneOffSingleXmlDocDomParser =
                new OneOffSingleXmlDocDomParser(url);
        System.out.println(oneOffSingleXmlDocDomParser
                .getChildElementContent("phone"));
    //    System.out.println(CustomDocumentUtil.isUrlValid(url));
    }
}
