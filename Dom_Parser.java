package Parser;

import java.io.File;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Dom_Parser {
    // Arrays for save date
    ArrayList<String[]> saveNameAttribute = new ArrayList<>();
    ArrayList<String[]> saveValueAttribute = new ArrayList<>();
    ArrayList<String> saveTagName = new ArrayList<>();
    ArrayList<String> saveTagContent = new ArrayList<>();
    ArrayList<String> saveNameAttribte = new ArrayList<>();
    ArrayList<String> saveValueAttribte = new ArrayList<>();

    // Clear arrays unnecessary Tag
    private void clearTagName() {
        int counter = 0;
        for (int i = 0; i < saveTagName.size(); i++) {
            String temp0 = saveTagName.get(i);
            String temp1 = saveTagName.get(i + 1);
            if (temp0.equals("#text")) {
                if (temp1.equals("#text")) {
                    counter = i;
                    break;
                }
            }
        }
//        Save need date
        String[] temp = new String[counter];
        for (int i = 0; i < counter; i++) {
            temp[i] = saveTagName.get(i);
        }
//        Destruction old array and save
        saveTagName.removeAll(saveTagName);
        for (int i = 0; i < counter; i++) {
            saveTagName.add(temp[i]);
        }
//      Delete #text
        for (int i = 0; i < saveTagName.size(); i++) {
            String temp3 = saveTagName.get(i);
            if (temp3.equals("#text")) {
                saveTagName.remove(i);
            }
        }
    }

    // Clear all unnecesary Content
    private void clearContent() {
        int counter = 0;
        final String point = saveTagContent.get(1);
        for (int i = saveTagContent.size() - 1; i > 0; i--) {
            if (saveTagContent.get(i).equals(point)) {
                counter++;
                break;
            } else {
                counter++;
            }
        }
        int saveNumberElement = saveTagContent.size() - 1 - counter;
        String[] saveElement = new String[saveNumberElement];
        for (int i = 0; i < saveNumberElement; i++) {
            saveElement[i] = saveTagContent.get(i);
        }
        saveTagContent.removeAll(saveTagContent);
        for (int i = 0; i < saveElement.length; i++) {
            saveTagContent.add(saveElement[i]);
        }
        for (int i = 0; i < saveTagContent.size(); i++) {
            if (saveTagContent.get(i).trim().length() == 0) {
                saveTagContent.remove(i);
            }
        }
        for (int i = 0; i < saveTagContent.size(); i++) {
            if (saveTagContent.get(i).trim().length() == 0) {
                saveTagContent.remove(i);
            }
        }

    }

    // Count all Text and Tag Name
    private boolean list1(NodeList listNode) {
        if (listNode.getLength() > 0) {
            for (int i = 0; i < listNode.getLength(); i++) {
                setArrays1(listNode.item(i));
            }
        }
        for (int i = 0; i < listNode.getLength(); i++) {
            if (listNode.item(i).getChildNodes().getLength() > 0) {
                list1(listNode.item(i).getChildNodes());
            }
        }
        return false;
    }

    // Count all Value and Attribute
    private boolean list(NodeList listNode) {
        if (listNode.getLength() > 0) {
            for (int i = 0; i < listNode.getLength(); i++) {
                setArrays(listNode.item(i));
            }

            for (int i = 0; i < listNode.getLength(); i++) {
                if (listNode.item(i).getChildNodes().getLength() > 0) {
                    list(listNode.item(i).getChildNodes());
                }
            }
        }

        return false;
    }

    // Clear unnecesary  Date
    private void clear(ArrayList<String[]> arrays) {
        if (arrays.size() != 1) {
            for (int i = 0; i < arrays.size() - 1; i++) {
                arrays.remove(i);
            }
        }
    }

    // Show Date in saveAttribute and saveValueAttribute
    private void showATI(ArrayList<String[]> array, ArrayList<String> save) {
        clear(array);
        for (String[] value : array) {
            for (int i = 0; i < value.length; i++) {
                save.add(value[i]);
            }
        }
    }

    // Collect date Tag Name and Context Date
    private void setArrays1(Node element) {
        saveTagName.add(element.getNodeName());
        for (int i = 0; i < element.getChildNodes().getLength(); i++) {
            String temp = element.getChildNodes().item(i).getNodeName();
            if (!temp.equals("#text")) {
                saveTagName.add(element.getChildNodes().item(i).getNodeName());
            }
            saveTagContent.add(element.getChildNodes().item(i).getTextContent());
        }
    }

    // Collect date Attribute and collect Value
    private void setArrays(Node element) {
        if (element.getNodeType() == Node.ELEMENT_NODE) {
            if (element.getAttributes().getLength() >= 1) {
                String[] elementNameAttribite = new String[element.getAttributes().getLength()];
                String[] elementValueAttribute = new String[element.getAttributes().getLength()];
                for (int i = 0; i < element.getAttributes().getLength(); i++) {

                    elementNameAttribite[i] = element.getAttributes().item(i).getNodeName();
                    elementValueAttribute[i] = element.getAttributes().item(i).getTextContent();
                }
                saveNameAttribute.add(elementNameAttribite);
                saveValueAttribute.add(elementValueAttribute);
            } else {
                String[] str = {""};
                saveNameAttribute.add(str);
                saveValueAttribute.add(str);
            }
            showATI(saveNameAttribute, saveNameAttribte);
            showATI(saveValueAttribute, saveValueAttribte);
        }
    }

    // Show all date
    public void showAllDater() {
        String glav = saveTagName.get(0);
        int counter = 0;
        for (int i = 0; i < saveTagName.size(); i++) {
            if (saveTagName.get(i).equals(glav)) counter++;
        }
        String[] extrudeAttributeName = new String[counter];
        for (int i = 0; i < counter; i++) {
            extrudeAttributeName[i] = saveNameAttribte.get(i);
        }
        for (int i = 0; i < counter; i++) {
            saveNameAttribte.remove(0);
        }
        String[] exttrudeAttributs = new String[counter];
        for (int i = 0; i < counter; i++) {
            exttrudeAttributs[i] = saveValueAttribte.get(i);
        }
        for (int i = 0; i < counter; i++) {
            saveValueAttribte.remove(0);
        }
        String[] extrudeContent = new String[counter];
        for (int i = 0, s = 0; i < saveTagContent.size(); i++) {
            if (saveTagContent.get(i).length() > saveTagContent.get(i).trim().length()) {
                extrudeContent[s] = saveTagContent.get(i);
                saveTagContent.remove(i);
                s++;
            }
        }
        System.out.println("Tag name , Attribut Name , Attribut value , Content in Tag ");
        for (int i = 0, j = 0, s = 0; i < saveTagName.size(); i++) {
            if (saveTagName.get(i).equals(glav)) {
                System.out.println(saveTagName.get(i) + " " + extrudeAttributeName[j] + " " + exttrudeAttributs[j] + " "
                        + extrudeContent[j]);
                j++;
            } else {
                System.out.println(saveTagName.get(i) + " " + saveNameAttribte.get(s) + " " + saveValueAttribte.get(s) + " " +
                        saveTagContent.get(s));
                s++;
            }
        }
    }

    // Parsing date
    public void parsingDOM(String awayToFile) {
        try {
            File inputFile = new File(awayToFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println(doc.getDocumentElement().getTagName());
            Element body = doc.getDocumentElement();
            NodeList arrayElement = body.getChildNodes();
            list1(arrayElement);
            clearTagName();
            clearContent();
            System.out.println("--------------------------------------------");
            list(arrayElement);

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
