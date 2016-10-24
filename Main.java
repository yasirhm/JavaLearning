/**
 * Created by Dotinschool6 on 10/23/2016.
 */

import java.io.IOException;
import java.util.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args){
        //ArrayList<Deposit> deposits = new ArrayList<Deposit>();

        try{
            File inputFile;
            inputFile = new File("C:/Users/Dotinschool6/Desktop/Project1/src/input.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("deposit");
            System.out.println(nList.getLength());

            for(int i=0; i<nList.getLength();i++){
                Node nNode = nList.item(i);
                System.out.println("\nCurrent Element :"
                        + nNode.getNodeName()+" $ "+Node.ELEMENT_NODE);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) { ////??
                    Element eElement = (Element) nNode;
                    System.out.println("customerNumber : "
                            + eElement.getElementsByTagName("customerNumber").item(0).getTextContent());
                    System.out.println("depositType : "
                            + eElement.getElementsByTagName("depositType").item(0).getTextContent());
                    if(eElement.getElementsByTagName("depositType").item(0).getTextContent() == "Loan"){
                        Loan L = new Loan(eElement.getElementsByTagName("customerNumber").item(0).getTextContent());
                    }
                    //String depositType = eElement.getElementsByTagName("depositType").item(0).getTextContent();

                    Class depositType=Class.forName(eElement.getElementsByTagName("depositType").item(0).getTextContent());
                    Deposit t = (Deposit) depositType.getConstructor(String.class).newInstance(eElement.getElementsByTagName("customerNumber").item(0).getTextContent());


                }

            }

        }catch(Exception e){
            System.out.println("Exception!");
            e.printStackTrace();
        }

        Integer customerID = 1;
        //LongTermDeposit a1 = new LongTermDeposit(customerID);
        customerID++;
        //ShortTermDeposit a2 = new ShortTermDeposit(customerID);
        customerID++;
        //Loan a3 = new Loan(customerID);
        /*System.out.println("Loan: "+a3.getInterestRate());
        System.out.println("Loan: "+a2.getInterestRate());
        System.out.println("Loan: "+a1.getInterestRate());*/
    }

}
