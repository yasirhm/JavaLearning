/**
 * Created by Yasi on 10/23/2016.
 */

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.io.File;
import javax.swing.text.html.parser.Parser;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) throws Exception {

        List<Deposit> deposits = new ArrayList<Deposit>();
        try {
            parseXML(deposits);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.exit(1);
        }
        Collections.sort(deposits, new Comparator() {
            @Override
            public int compare(Object depositOne, Object depositTwo) {
                //ascending order
                // return ((Deposit)depositOne).getPayedInterest().compareTo(((Deposit)depositTwo).getPayedInterest());
                //descending order
                return ((Deposit) depositTwo).getPayedInterest().compareTo(((Deposit) depositOne).getPayedInterest());
            }
        });
        System.out.println("Sorted");
        for (Deposit dp : deposits) {
            System.out.println(dp.getCustomerNumber() + " - " + dp.getPayedInterest());
        }
        writeIntoAccessFile(deposits);
    }

    private static void parseXML(List<Deposit> deposits) throws ParserConfigurationException, SAXException, IOException {

        File inputFile;
        inputFile = new File("src/input1.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("deposit");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                try {
                    Class depositTypeClass = Class.forName(eElement.getElementsByTagName("depositType").item(0).getTextContent());
                    DepositType depositType = (DepositType) depositTypeClass.newInstance();

                    Deposit deposit = new Deposit(eElement.getElementsByTagName("customerNumber").item(0).getTextContent(),
                            parseInt(eElement.getElementsByTagName("durationInDays").item(0).getTextContent()),
                            new BigDecimal(eElement.getElementsByTagName("depositBalance").item(0).getTextContent().replaceAll(",", "")),
                            depositType
                    );
                    deposit.calculatePayedInterest();
                    deposits.add(deposit);
                } catch (DurationDaysException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (BalanceException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (ClassNotFoundException e) {
                    System.out.println("Error: " + e.getMessage() + " is unknown deposit type!");
                } catch (ReflectiveOperationException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

    private static void writeIntoAccessFile(List<Deposit> deposits) {
        try {
            File file = new File("Project1RandomAccessFile.out");
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            // Read a character
            //byte ch = raf.readByte();
            //System.out.println("Read first character of file: " + (char)ch);
            //System.out.println("Read full line: " + raf.readLine());
            // Seek to the end of file
            raf.seek(file.length());
            raf.writeBytes("\nThis will complete the Project.");
            for (Deposit dp : deposits) {
                raf.writeBytes("\n" + dp.getCustomerNumber() + " # " + dp.getPayedInterest());
            }
            raf.close();
        } catch (IOException e) {
            System.out.println("IOException:");
        }
    }

}
