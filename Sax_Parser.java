package Parser;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Sax_Parser {
    public void BublicParsingSAX(String awayToFile) {
        try {
//            Initilize and create reference for file
            File inputFile = new File(awayToFile);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParsing = factory.newSAXParser();
//            Create object for return class Date
            UserHandler userhandle = new UserHandler();
//            Parsing input file user userhandle object
            saxParsing.parse(inputFile, userhandle);

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}

// Class User Handler find need tags and show date in this Tags
class UserHandler extends DefaultHandler {
    //    Initilize Tags in boolean type = false;
    boolean bFirstName = false;
    boolean bLastName = false;
    boolean bNickName = false;
    boolean bMarks = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
// Find tags "nameTag" and output date
        if (qName.equalsIgnoreCase("student")) {
            String rollno = attributes.getValue("rollno");
            System.out.println("Roll no :" + rollno);
        } else if (qName.equalsIgnoreCase("firstname")) {
            bFirstName = true;
        } else if (qName.equalsIgnoreCase("lastname")) {
            bLastName = true;
        } else if (qName.equalsIgnoreCase("nickname")) {
            bNickName = true;
        } else if (qName.equalsIgnoreCase("marks")) {
            bMarks = true;
        }

    }

    // Find over element in Document "student" and output read document over
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("student")) {
            System.out.println("End Element:" + qName);
        }
    }

    // When we show date in tags we make bFirstName=true because we find this tag and now we make they false because we cant
//    read Other Tags other Student
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (bFirstName) {
            System.out.println("First Name :" + new String(ch, start, length));
            bFirstName = false;
        }
        if (bLastName) {
            System.out.println("Last Name :" + new String(ch, start, length));
            bLastName = false;
        }
        if (bNickName) {
            System.out.println("Nick Name :" + new String(ch, start, length));
            bNickName = false;
        }
        if (bMarks) {
            System.out.println("Marks :" + new String(ch, start, length));
            bMarks = false;
        }
    }
}