package com.gerard.site.controller.tag;

import com.gerard.site.service.ServiceException;
import com.gerard.site.service.util.AppIdentifierUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Optional;

/**
 * XML parsing tool that works only with files,
 * that realized next requirements:
 * <ol>
 *     <li>Only root element has child elements</li>
 *     <li>Root element have unique child elements</li>
 *     <li>Root child elements have unique names</li>
 * </ol> .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 * @see SingleDocDomParser#getChildElementContent(String)
 */
public class SingleDocDomParser {
    private static final Logger LOGGER = LogManager.getLogger(SingleDocDomParser.class);

    /**
     * Document url that is used for parsing .
     */
    private String documentUrl;

     SingleDocDomParser(String documentUrl) {
        try {
            setDocumentUrl(documentUrl);
        } catch (ServiceException exception) {
            LOGGER.error("Unable to set parameter 'documentUrl' ! "
                    + exception.getMessage(), exception);
        }
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    /**
     * Finds tag content by tag name .
     *
     * @param tagName tag name to find attached content
     * @return if tag name is null or blank,
     * returns {@code Optional.empty()},
     * otherwise returns {@code Optional.ofNullable(tagName)}
     */
    Optional<String> getChildElementContent(String tagName) {
        if (tagName == null || tagName.isBlank()) {
            LOGGER.warn("Element tag name: " + tagName + " is null or blank");
            return Optional.empty();
        } else {
            String content = null;
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder documentBuilder
                        = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(documentUrl);
                Element root = document.getDocumentElement();
                NodeList childNodeList = root.getElementsByTagName(tagName);
                Node node = childNodeList.item(0);
                content = node.getTextContent();
            } catch (ParserConfigurationException
                    | SAXException
                    | IOException exception) {
                LOGGER.error("Unable to parse document! "
                        + "Document url: " + documentUrl, exception);
            }
            return Optional.ofNullable(content);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof SingleDocDomParser singleDocDomParser) {
            return (this.documentUrl.equals(singleDocDomParser.documentUrl));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 13;
        int hashcode = hash + 31 * (documentUrl != null ? documentUrl.hashCode() : 0);
        return hashcode;
    }

    @Override
    public String toString() {
        return "SingleDocDomParser{"
                + "documentUrl=" + documentUrl + '\n'
                + '}';
    }

    private void setDocumentUrl(String documentUrl) throws ServiceException {
        if (AppIdentifierUtil.isUrlValid(documentUrl)) {
            this.documentUrl = documentUrl;
        } else {
            throw new ServiceException("Document is not readable. !"
                    + "Document url: " + documentUrl);
        }
    }
}
