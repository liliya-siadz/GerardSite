package com.gerard.site.util;

import com.gerard.site.exception.ServiceException;
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
import java.util.Objects;

/*
 * Tool for simple xml document, with child unique elements (quantity fixed=1)
 */
public class SingleDocDomParser {

    private static final Logger LOGGER = LogManager.getLogger(SingleDocDomParser.class);
    private DocumentBuilder documentBuilder;
    private String documentUrl;

    public SingleDocDomParser(String documentUrl) {
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException exception) {
            LOGGER.error("Unable to create parser. " +
                    exception.getLocalizedMessage(), exception);
        }
        try {
            setDocumentUrl(documentUrl);
        } catch (ServiceException exception) {
            LOGGER.error("Unable to set document url or root element tag name" +
                    ". Unable to create parser", exception);
        }
    }

    public final String getDocumentUrl() {
        return documentUrl;
    }

    public String getChildElementContent(String childElementTagName) {
        if (childElementTagName == null
                || childElementTagName.isBlank()) {
            LOGGER.warn("Element tag name: " + childElementTagName
                    + "is null or blank or empty");
            LOGGER.debug("Null will be returned");
            return null;
        }
        try {
            Document document = documentBuilder.parse(documentUrl);
            Element root = document.getDocumentElement();
            NodeList childNodeList = root.getElementsByTagName(childElementTagName);
            Node node = childNodeList.item(0);
            String text = node.getTextContent();
            return text;
        } catch (SAXException | IOException exception) {
            LOGGER.error("Unable to parse document: " + documentUrl, exception);
            LOGGER.trace("Null will be returned");
            return null;
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
        return Objects.hash(documentUrl);
    }

    @Override
    public String toString() {
        return "SingleDocDomParser{" +
                "documentUrl=" + documentUrl + '\''
                + '}';
    }

    private void setDocumentUrl(String documentUrl) throws ServiceException {
        if (CustomDocumentUtil.isUrlValid(documentUrl)) {
            this.documentUrl = documentUrl;
        } else {
            throw new ServiceException("Document is not readable. " +
                    "Document url string representation: " + documentUrl);
        }
    }

}
