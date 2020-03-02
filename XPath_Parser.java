package Parser;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XPath_Parser {
    public void BublicXpathParser(String awayToFile) {
        try {
//            Initilize directory for file  and create file
            File inputFile = new File(awayToFile);
//            Create document use document ...txt
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
//            Indicate where name have doc and create reference for document
            Document doc = dBuilder.parse(inputFile);
//            bring type Document to normal for parsing
            doc.getDocumentElement().normalize();
//            Input framework for XpathFactory
            XPath xPath = XPathFactory.newInstance().newXPath();
// Indicate hierarchy
            String expression = "/class/student";
// Create List element use hierarchy document
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET
            );
//            get and show date of  node List
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                System.out.println("\nCurrent Element:" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Student roll no :" + eElement.getAttribute("rollno"));
                    System.out.println("First Name:"
                            + eElement.getElementsByTagName("firstname")
                            .item(0)
                            .getTextContent());

                    System.out.println("Last Name :" +
                            eElement.getElementsByTagName("lastname")
                                    .item(0)
                                    .getTextContent());
                    System.out.println("Nick Name :" + eElement
                            .getElementsByTagName("nickname")
                            .item(0)
                            .getTextContent());
                    System.out.println(
                            "Marks :" + eElement
                                    .getElementsByTagName("marks")
                                    .item(0)
                                    .getTextContent()
                    );
                }
            }
//            Check Error
        } catch (ParserConfigurationException e) {
            e.fillInStackTrace();
        } catch (SAXException e) {
            e.fillInStackTrace();
        } catch (IOException e) {
            e.fillInStackTrace();
        } catch (XPathExpressionException e) {
            e.fillInStackTrace();
        }
    }
}
