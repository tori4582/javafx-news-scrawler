package com.fxnews.common;

import com.fxnews.models.Article;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class XmlMapperUtils {

    private static final XmlMapperUtils mapper = new XmlMapperUtils();

    public static final Article parseArticle(Node itemNode) {
        Article article = new Article();
        article.setTitle(removeCData(getChildNode(itemNode, "title").getTextContent()));
        article.setUrl(removeCData(getChildNode(itemNode, "link").getTextContent()));
        article.setImageUrl(removeCData(getChildNode(itemNode, "image").getTextContent()));

        return article;
    }

    public static final Node getChildNode(Node parentNode, String childNodeName) {
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node childNode = parentNode.getChildNodes().item(i);
            if (childNode.getNodeName().equals(childNodeName)) {
                return childNode;
            }
        }
        return null;
    }

    public static final LocalDate toLocalDate(String rssDate) {
        return LocalDate.parse(rssDate, DateTimeFormatter.ofPattern("EEE, dd LLL uuuu HH:mm:ss Z", Locale.ENGLISH));
    }

    public static final String removeCData(String str) {
        return str.replaceAll("<!\\[CDATA\\[", "").replaceAll("\\]\\]>", "");
    }

    public static final List<Node> toNodeArrayList(NodeList nodeList) {

        List<Node> result = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            result.add(nodeList.item(i));
        }

        return result;
    }

    public static final Stream<Node> toNodeStream(NodeList nodeList) {
        return toNodeArrayList(nodeList).stream();
    }

    public static final List<Node> selectByTag(NodeList nodeList, String tagName) {
        return toNodeArrayList(nodeList).stream()
                .filter(node -> node.getNodeName().equals(tagName))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

}
