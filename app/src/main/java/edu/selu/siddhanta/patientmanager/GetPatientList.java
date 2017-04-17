package edu.selu.siddhanta.patientmanager;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.net.URL;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import android.os.StrictMode;

/**
 * Created by Siddhanta on 4/2/2017.
 */

public class GetPatientList {
    ArrayList<Patient> the_patient = new ArrayList<>();

    ArrayList<Patient>  getPateintList(){

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document document = null;
        String xmlDocument = "http://www2.southeastern.edu/Academics/Faculty/jburris/emr.xml";
        try {
            document = builder.parse(new URL(xmlDocument).openStream());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NodeList emrRoot = document.getElementsByTagName("emr");
        Node emrNode = emrRoot.item(0);

        Element emrElement = (Element) emrNode;
        NodeList patient_list = emrElement.getElementsByTagName("patient_info");


        for (int i = 0; i < patient_list.getLength(); i++) {
            Node p = patient_list.item(i);

            // create new patient
            the_patient.add(new Patient());
            // create new list of orders for current patient
            ArrayList<Order> order_list = new ArrayList<>();

            if (p.getNodeType() == Node.ELEMENT_NODE) {

                Element patient = (Element) p;
                the_patient.get(i).setId(patient.getAttribute("id"));

                NodeList nameList = patient.getElementsByTagName("name");
                Element name = (Element) nameList.item(0);
                the_patient.get(i).setName(name.getTextContent());

                NodeList address1List = patient.getElementsByTagName("address1");
                Element addr1 = (Element) address1List.item(0);
                the_patient.get(i).setAddress1(addr1.getTextContent());


                NodeList address2List = patient.getElementsByTagName("address2");
                Element addr2 = (Element) address2List.item(0);
                the_patient.get(i).setAddress2(addr2.getTextContent());


                NodeList dobList = patient.getElementsByTagName("dob");
                Element dob = (Element) dobList.item(0);
                the_patient.get(i).setDOB(dob.getTextContent());


                NodeList providerList = patient.getElementsByTagName("provider");
                Element provider = (Element) providerList.item(0);
                the_patient.get(i).setProvider(provider.getTextContent());


                NodeList orderList = patient.getElementsByTagName("patient_order");

                for (int j = 0; j < orderList.getLength(); j++) {

                    order_list.add(new Order());

                    Element orders = (Element) orderList.item(j);// each list of order inside patient_order as Element order
                    Node type = orders.getElementsByTagName("type").item(0);
                    Node medicine = orders.getElementsByTagName("medicine").item(0);
                    Node dosage = orders.getElementsByTagName("dosage").item(0);
                    Node refill_rem = orders.getElementsByTagName("refillsRemaining").item(0);
                    Node lastRefill = orders.getElementsByTagName("lastRefill").item(0);
                    Node orderId = orders.getElementsByTagName("patientID").item(0);
                    //  set each variable value for Order
                    order_list.get(j).setOrder_type(type.getTextContent()); // set order_type
                    order_list.get(j).setMedicine(medicine.getTextContent()); // set medicine
                    order_list.get(j).setDosage(dosage.getTextContent()); // set dosage
                    order_list.get(j).setRefill_rem(refill_rem.getTextContent()); // set refill remaining
                    order_list.get(j).setLast_refill(lastRefill.getTextContent()); // set last refill time
                    order_list.get(j).setId(orderId.getTextContent()); // set orderID

                }

                // set order for the current patient
                the_patient.get(i).setOrders(order_list);
            } //if
        }// for

        return the_patient;
    }




}
