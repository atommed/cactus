package io.github.interstell.cactus.backend.util;

import io.github.interstell.cactus.backend.models.Event;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Grigoriy on 5/15/2016.
 */
public class TomitaRunner {
    public TomitaRunner() throws IOException{

    }
    private String pathToExecutable;

    public String getPathToExecutable() {
        return pathToExecutable;
    }

    public void setPathToExecutable(String pathToExecutable) {
        this.pathToExecutable = pathToExecutable;
    }

    public String parse(String text, Event event) throws IOException, InterruptedException, ParserConfigurationException, SAXException {
        File input = new File("C:\\Users\\Grigoriy\\Documents\\cactus\\tomita\\input.txt");
        File output = new File("C:\\Users\\Grigoriy\\Documents\\cactus\\tomita\\output.xml");
        FileOutputStream of = new FileOutputStream(input, false);
        text = text.replaceAll("<br>"," ");
        text = text.replaceAll("ул.","ул");
        text = text.replaceAll("/","-");
        of.write(text.getBytes());
        of.close();
        ProcessBuilder tomita_b = new ProcessBuilder("C:\\Users\\Grigoriy\\Documents\\cactus\\tomita\\wrapper.exe")
                .directory(new File("C:\\Users\\Grigoriy\\Documents\\cactus\\tomita"));
        tomita_b.start();

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(output);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("facts");
        Node facts_h = nodeList.item(0);
        NodeList facts = facts_h.getChildNodes();
        for(int i = 0; i < facts.getLength(); i++){
            Node fact  = facts.item(i);
            System.out.println(fact.getNodeName());
            switch (fact.getNodeName()){
                case "Date":/*
                    //Node val = fact.getNodeValue();
                    NodeList s = fact.getChildNodes();
                    for(int j=0; j < s.getLength(); j++)
                        if(s.item(i).getNodeName()=="Data")
                            event.hack1 = s.item(i).getNodeName().g
                    System.out.println(" XXX"+s);*//*
                    Node val = fact.getFirstChild();
                    System.out.println(" XXX"+val.toString());*/
                    break;

                case "Address" :
                    Node val2 = fact.getFirstChild();
                    event.setAddress(val2.toString());
                    break;
                case "Price" :
                    Node val1 = fact.getFirstChild();
                    event.setPrice(val1.toString());
                    break;
            }
        }

        return "bad";
    }
}
