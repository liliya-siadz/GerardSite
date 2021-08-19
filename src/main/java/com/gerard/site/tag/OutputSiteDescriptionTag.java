package com.gerard.site.tag;

import com.gerard.site.exception.ServiceException;
import com.gerard.site.util.SingleDocDomParser;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Custom JSP-tag to display information from
 * description XML file that located at
 * application root level, works only with
 * 1-level nested xml file with unique elements inside,
 * it means that only root element of the file
 * can have child elements and they must have unique names .
 *
 * <p>Note, that description XML file must be named
 * as "description.xml" and be located at application
 * root level.
 * For ex.:
 * <b>if application URL: </b>
 * <i>http://localhost:8080/gerard </i>
 * <b>description file location should be: </b>
 * <i>http://localhost:8080/gerard/description.xml </i>
 * </p>
 * <p>Tag has 2 attributes:
 * <ol>
 *     <li>applicationUrl (url of application,
 *     that stores description file</li>
 *     <li>tag name in description file,
 *     that is used for finding content to display</li>
 * </ol>
 * </p>
 * For parsing uses custom single XML document DOM parser
 * {@link SingleDocDomParser}
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 * @see SingleDocDomParser
 */
public class OutputSiteDescriptionTag extends TagSupport {

    /**
     * URI for finding description file .
     */
    private static final String DESCRIPTION_XML_FILE_URI = "/description.xml";
    private static final Logger LOGGER =
            LogManager.getLogger(OutputSiteDescriptionTag.class);

    /**
     * Tag name to display content from,
     * must be a unique-named child of root element
     * in the description file .
     */
    private String elementTagName;

    /**
     * Application URL, is used for finding description file .
     */
    private String applicationUrl;

    public OutputSiteDescriptionTag() {
        super();
    }

    /**
     * Set element tag name field value .
     *
     * @param elementTagName element tag name
     *                       {@link OutputSiteDescriptionTag#elementTagName}
     */
    public void setElementTagName(String elementTagName) {
        this.elementTagName = elementTagName;
    }

    /**
     * Set application url field value .
     *
     * @param applicationUrl element tag name
     *                       {@link OutputSiteDescriptionTag#applicationUrl}
     */
    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    @Override
    public int doStartTag() throws JspException {
        SingleDocDomParser singleDocDomParser =
                new SingleDocDomParser(applicationUrl + DESCRIPTION_XML_FILE_URI);
        String value = singleDocDomParser.getChildElementContent(elementTagName);
        try {
            pageContext.getOut().write(value);
        } catch (IOException exception) {
            throw new JspException(exception.getMessage());
        }
        return SKIP_BODY;
    }
}
