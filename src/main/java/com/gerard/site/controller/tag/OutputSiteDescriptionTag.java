package com.gerard.site.controller.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.util.Optional;

/**
 * Custom JSP-tag to display information from
 * description XML file that must be located at
 * application root level and must corresponds
 * requirements of using custom tool for XML parsing
 * {@code SingleDocDomParser} {@link SingleDocDomParser} .
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
 * Tag has 2 attributes:
 * <ol>
 *     <li>applicationUrl (url of application,
 *     that stores description file</li>
 *     <li>tag name in description file,
 *     that is used for finding content to display</li>
 * </ol>
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

    /**
     * Default content, is used if content specified to tag
     * is corrupted or not found .
     */
    private static final String DEFAULT_CONTENT = "NO CONTENT WAS FOUND";

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
        Optional<String> specifiedContent =
                singleDocDomParser.getChildElementContent(elementTagName);
        String content = specifiedContent.isEmpty()
                ? DEFAULT_CONTENT
                : specifiedContent.get();
        try {
            pageContext.getOut().write(content);
        } catch (IOException exception) {
            throw new JspException(exception.getMessage());
        }
        return SKIP_BODY;
    }
}
