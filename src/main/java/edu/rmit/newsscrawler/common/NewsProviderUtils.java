package edu.rmit.newsscrawler.common;

import lombok.NonNull;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Log
public class NewsProviderUtils {

    /*
    Create a method that takes a URL from a XML file and read then return the XML object representation of the file's content
     */
    public static Document fetchRssDocument(String url) {

        try (InputStream is = connectXmlInputStream(url)) {

            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

        } catch (ParserConfigurationException | SAXException | IOException e) {

            log.severe("Error while parsing the XML file: " + e.getMessage());

            return null;
        }
    }

    public static InputStream connectXmlInputStream(final String url) throws IOException {
        return new URL(url).openStream();
    }

}
