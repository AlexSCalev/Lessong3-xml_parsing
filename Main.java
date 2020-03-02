package Parser;

import javax.xml.xpath.XPath;

public class Main {
    public static void main(String[] args) {

        // Input awayToFile example {"C:\\Users\\Alexandr\\Desktop\\CSV Generate File\\input.txt"}
        // For dinamic Dom parsing start element to end n element neccesary create file in mod

        // <?xml version = "1.0"?>
        //<class>
        //<student test1="1">
        //hello world1
        //<firstname soup="1.1">Content1</firstname>
        // all tags and content
        //</student>
        //<student test2="2.1">Content2
        //<firstname>Half life</firstname>
        // all tags and content
        //</student>
        //<student test3="3.1">Content3
        //<firstname>Life Cicle</firstname>
        // all tags and content to n element
        //</student>
        //</class>

//        Uncoment for start parsing DOM_Parser
        Dom_Parser test = new Dom_Parser();
        test.parsingDOM("C:\\Users\\Alexandr\\Desktop\\CSV Generate File\\input.txt");
        test.showAllDater();

//        Uncoment for start parsing Sax_Parser document
//        Format is static on start element to end element

        //<?xml version = "1.0"?>
        //<class>
        //   <student rollno = "393">soup
        //   ssssss
        //      <firstname test1="111">drakar</firstname>
        //      <lastname test2="222">kad</lastname>
        //      <nickname test3="333">dinkar</nickname>
        //      <marks test4="444">85</marks>
        //	  <soup test5="333">Jojo</soup>
        //   </student>
        //
        //   <student rollno = "493"> misterCrabs
        //      <firstname test6="222">Vaneet</firstname>
        //      <lastname test7="333">Gupta</lastname>
        //      <nickname test8="444">vinni</nickname>
        //      <marks test9="555">95</marks>
        //   </student>
        //
        //   <student zarra = "593">
        //	mister Bob
        //      <firstname test10="">jasvir</firstname>
        //      <lastname test11="">singn</lastname>
        //      <nickname test12="">jazz</nickname>
        //      <marks test13="">90</marks>
        //      <marks1 test14="98">CONTENT</marks1>
        //   </student>
        //</class>

//        Sax_Parser testing = new Sax_Parser();
//        testing.BublicParsingSAX("C:\\Users\\Alexandr\\Desktop\\CSV Generate File\\input.txt");

//        XPath_Parser testing = new XPath_Parser();
//        testing.BublicXpathParser("C:\\Users\\Alexandr\\Desktop\\CSV Generate File\\input.txt");
    }
}
