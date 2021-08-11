import com.gerard.site.connection.ConnectionException;
import com.gerard.site.service.tag.localization.DefineLanguageAttributesTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
        private static final Logger LOGGER =
                LogManager.getLogger(DefineLanguageAttributesTag.class);
    public static void main(String[] args) throws ConnectionException {
//        ConnectionPool connectionPool  = ConnectionPool.getInstance();
//        Connection connection  =  connectionPool.giveOutConnection();
//        connectionPool.getBackConnection(connection);
//
//        Optional<Cookie> locale = Optional.of(new Cookie("locale", "ru"));
//        if (locale.isPresent()) {
//            try {
//                ProvidedLanguage languageFromCookie =
//                        ProvidedLanguage.valueOf(locale.get().getValue().toUpperCase());
//            } catch (IllegalArgumentException exception) {
//
//            }
//        }
    }
}
