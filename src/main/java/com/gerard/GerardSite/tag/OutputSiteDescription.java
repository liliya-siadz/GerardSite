package com.gerard.GerardSite.custom_tag;

import com.gerard.GerardSite.util.OneOffSingleXmlDocDomParser;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

public class OutputSiteDescription extends TagSupport {
    private static final String DESCRIPTION_XML_FILE_URI = "/description.xml";
    private String elementTagName;
    private String applicationUrl;

    public void setElementTagName(String elementTagName) {
        this.elementTagName = elementTagName;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    @Override
    public int doStartTag() throws JspException {
        OneOffSingleXmlDocDomParser oneOffSingleXmlDocDomParser =
                new OneOffSingleXmlDocDomParser(applicationUrl + DESCRIPTION_XML_FILE_URI);
        String value = oneOffSingleXmlDocDomParser.getChildElementContent(elementTagName);
        try {
          //  pageContext.getOut().write("<p>" + value + "</p>");
            pageContext.getOut().write(value);
        } catch (IOException exception) {
            throw new JspException(exception.getMessage());
        }
        return SKIP_BODY;
    }
}
