import com.gerard.GerardSite.custom_tag.InitLocaleAndBundleCookies;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mani {
    private static final Logger LOGGER = LogManager.getLogger(InitLocaleAndBundleCookies.class);
    private static final String TARGET_REQUEST_HEADER_NAME = "Accept-Language";
    private static final String LOCALE_CODE_SEPARATOR = ",";

    //docs: https://datatracker.ietf.org/doc/html/rfc2616#page-29
    //where any two-letter primary-tag is an ISO-639 language abbreviation
    private static final int LOCALE_CODE_LETTERS_QUANTITY = 2;


    public static void main(String[] args) {
        //ConnectionPool connectionPool  = ConnectionPool.getInstance();
     //  Connection connection  =  connectionPool.giveOutConnection()
//        SupportedLocale defaultLocale = SupportedLocale.EN;
//        String localeValue = defaultLocale.name().toLowerCase();
//        String bundleBaseName = defaultLocale.getBundleName();
//        String setBundle = "<c: set var=" + '"' + "bundleBasename" + '"'
//                + " value=" + '"' + bundleBaseName + '"'
//                + " scope=" + '"' + "application" + '"' + "/>";
//        String setLocale = "<c: set var=" + '"' + "localeValue" + '"'
//                + " value=" + '"' + localeValue + '"'
//                + " scope=" + '"' + "application" + '"' + "/>";
//
//        System.out.println(setBundle + setLocale);
    }
}
