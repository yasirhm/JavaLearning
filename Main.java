/**
 * Created by Dotinschool6 on 10/23/2016.
 */

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


import static java.lang.Integer.parseInt;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) throws Exception{

        List<Deposit> deposits = new ArrayList<Deposit>() ;
        XMLParser (deposits);
        Collections.sort(deposits, new Comparator() {
            @Override
            public int compare(Object depositOne, Object depositTwo) {
                //use instanceof to verify the references are indeed of the type in question
                //ascending order
               // return ((Deposit)depositOne).getPayedInterest()
                 //       .compareTo(((Deposit)depositTwo).getPayedInterest());
                //descending order
                 return ((Deposit)depositTwo).getPayedInterest()
                          .compareTo(((Deposit)depositOne).getPayedInterest());

            }
        });
        doAccess(deposits);

        System.out.println("Sorted");
        for (Deposit dp : deposits) {
            System.out.println(dp.getCustomerNumber() + " - " + dp.getPayedInterest() );
        }
    }

    private static void XMLParser( List<Deposit> deposits)
    {

        try{
            File inputFile;
            inputFile = new File("C:/Users/Dotinschool6/Desktop/Dotin School/Project1/src/input.xml");
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
                if (nNode.getNodeType() == Node.ELEMENT_NODE) { ////??
                    Element eElement = (Element) nNode;

                    /*try {
                        Class depositType = Class.forName(eElement.getElementsByTagName("depositType").item(0).getTextContent());
                        DepositType dType = (DepositType) depositType.newInstance();
                    }catch (ClassNotFoundException err){
                        System.out.println("Exception!");
                        err.println(
                                "Reflection error trying to invoke " + className + ".");
                    }*/

                    try {
                        Class depositType = Class.forName(eElement.getElementsByTagName("depositType").item(0).getTextContent());
                        DepositType dType = (DepositType) depositType.newInstance();

                        Class deposit = Class.forName("Deposit");
                        Deposit de = (Deposit) deposit.getConstructor(new Class[]{String.class, Integer.class, BigDecimal.class, DepositType.class}).newInstance(eElement.getElementsByTagName("customerNumber").item(0).getTextContent(),
                                parseInt(eElement.getElementsByTagName("durationInDays").item(0).getTextContent()),
                                new BigDecimal(eElement.getElementsByTagName("depositBalance").item(0).getTextContent().replaceAll(",", "")),
                                dType
                        );
                        de.calculatePayedInterest();
                        deposits.add(de);
                    }catch(Exception exp){
                        exp.printStackTrace();
                    }


                }

            }

        }catch(Exception e){
            System.out.println("Exception In parsing XML File!");
            e.printStackTrace();
        }
    }


    private static void doAccess( List<Deposit> deposits)
    {

        try {

            File file = new File("Project1RandomAccessFile.out");
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            // Read a character
            //byte ch = raf.readByte();
            //System.out.println("Read first character of file: " + (char)ch);

            // Now read the remaining portion of the line.
            // This will print out from where the file pointer is located
            // (just after the '+' character) and print all remaining characters
            // up until the end of line character.
            //System.out.println("Read full line: " + raf.readLine());

            // Seek to the end of file
            raf.seek(file.length());

            // Append to the end of the file
            raf.write(0x0A);
            raf.writeBytes("This will complete the Project.");

            for (Deposit dp : deposits) {
                raf.write(0x0A);
                raf.writeBytes(dp.getCustomerNumber() + " # " + dp.getPayedInterest());
            }

            raf.close();

        } catch (IOException e) {
            System.out.println("IOException:");
            e.printStackTrace();
        }
    }

}
