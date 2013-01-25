package carrental.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLHelper {

    public XMLHelper() {
        super();
    }

    public static void saveRunningState(String _running) {
        CarRentalLogger carLogger = new CarRentalLogger();
        File myFile = new File("simulateLoad-settings.xml");
        NodeList nodeOperations;
        NodeList nodeInterval;
        NodeList nodeLength;
        NodeList nodeRunning;
        String running = _running;


        try {
            if (!myFile.exists()) {
                CreateFile("simulateLoad-settings");
                carLogger.Logger("Creating file simulateLoad-Settings.xml");
            } else {
                carLogger.Logger("File simulateLoad-Settings.xml already exists ..");
            }


            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource("simulateLoad-settings.xml"));


            // locate the node(s)
            XPath xpath = XPathFactory.newInstance().newXPath();
            //
            //            carLogger.Logger("XMLHelper ops value is: " + operations);
            //            XMLHelper ops value is: 5

            nodeRunning = (NodeList) xpath.evaluate("//running", doc, XPathConstants.NODESET);
            nodeRunning.item(0).setTextContent(running);

            //NodeList nodes =
            //  (NodeList)xpath.evaluate("//dbtype[text()='sqlserver']", doc,
            //         XPathConstants.NODESET);

            //make the change
            //            for (int i = 0; i < nodes.getLength(); i++) {
            //                nodes.item(i).setTextContent(dbType);
            //            }
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(new DOMSource(doc), new StreamResult(new File("simulateLoad-settings.xml")));
            carLogger.Logger("Successfully saved simulateLoad-settings.xml");

        } catch (ParserConfigurationException e) {
            carLogger.Logger("ParserConfigurationException: " + e, e);
        } catch (IOException e) {
            carLogger.Logger("IOException: " + e, e);
        } catch (SAXException e) {
            carLogger.Logger("SAXException: " + e, e);
        } catch (IllegalArgumentException e) {
            carLogger.Logger("IllegalArgumentException: " + e, e);
        } catch (XPathExpressionException e) {
            carLogger.Logger("XPathExpressionException: " + e, e);
        } catch (TransformerException e) {
            carLogger.Logger("TransformerException: " + e, e);
        } catch (Exception e) {
            carLogger.Logger("Exception : " + e, e);
        }

    }

    public static void saveOperationsSettings(String _running, String _operations, String _interval, String _length) {
        CarRentalLogger carLogger = new CarRentalLogger();
        File myFile = new File("simulateLoad-settings.xml");
        NodeList nodeOperations;
        NodeList nodeInterval;
        NodeList nodeLength;
        NodeList nodeRunning;
        String operations = _operations;
        String interval = _interval;
        String length = _length;
        String running = _running;


        try {
            if (!myFile.exists()) {
                CreateFile("simulateLoad-settings");
                carLogger.Logger("Creating file simulateLoad-Settings.xml");
            } else {
                carLogger.Logger("File simulateLoad-Settings.xml already exists ..");
            }


            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource("simulateLoad-settings.xml"));


            // locate the node(s)
            XPath xpath = XPathFactory.newInstance().newXPath();
            //
            //            carLogger.Logger("XMLHelper ops value is: " + operations);
            //            XMLHelper ops value is: 5

            nodeRunning = (NodeList) xpath.evaluate("//running", doc, XPathConstants.NODESET);
            nodeRunning.item(0).setTextContent(running);

            nodeOperations = (NodeList) xpath.evaluate("//operations", doc, XPathConstants.NODESET);
            nodeOperations.item(0).setTextContent(operations);

            nodeInterval = (NodeList) xpath.evaluate("//interval", doc, XPathConstants.NODESET);
            nodeInterval.item(0).setTextContent(interval);

            nodeLength = (NodeList) xpath.evaluate("//length", doc, XPathConstants.NODESET);
            nodeLength.item(0).setTextContent(length);


            //NodeList nodes =
            //  (NodeList)xpath.evaluate("//dbtype[text()='sqlserver']", doc,
            //         XPathConstants.NODESET);

            //make the change
            //            for (int i = 0; i < nodes.getLength(); i++) {
            //                nodes.item(i).setTextContent(dbType);
            //            }
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(new DOMSource(doc), new StreamResult(new File("simulateLoad-settings.xml")));
            carLogger.Logger("Successfully saved simulateLoad-settings.xml");

        } catch (ParserConfigurationException e) {
            carLogger.Logger("ParserConfigurationException: " + e, e);
        } catch (IOException e) {
            carLogger.Logger("IOException: " + e, e);
        } catch (SAXException e) {
            carLogger.Logger("SAXException: " + e, e);
        } catch (IllegalArgumentException e) {
            carLogger.Logger("IllegalArgumentException: " + e, e);
        } catch (XPathExpressionException e) {
            carLogger.Logger("XPathExpressionException: " + e, e);
        } catch (TransformerException e) {
            carLogger.Logger("TransformerException: " + e, e);
        } catch (Exception e) {
            carLogger.Logger("Exception : " + e, e);
        }

    }

    public static String[] getOperationSettingParamsFromXML() {
        String[] myOps = new String[6];
        CarRentalLogger carLogger = new CarRentalLogger();
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("simulateLoad-settings.xml"));

            // normalize text representation
            doc.getDocumentElement().normalize();
            //  System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

            NodeList listOfOps = doc.getElementsByTagName("simulateLoad-settings");

            for (int s = 0; s < listOfOps.getLength(); s++) {
                Node firstServerNode = listOfOps.item(s);

                if (firstServerNode.getNodeType() == Node.ELEMENT_NODE) {


                    Element firstServerElement = (Element) firstServerNode;

                    //------- load running?
                    NodeList opRunning = firstServerElement.getElementsByTagName("running");
                    Element opRunningElement = (Element) opRunning.item(0);
                    NodeList textRunning = opRunningElement.getChildNodes();
                    myOps[0] = (String) textRunning.item(0).getNodeValue().trim();

                    //------- Number of ops
                    NodeList opNumOperations = firstServerElement.getElementsByTagName("operations");
                    Element opNumOpsElement = (Element) opNumOperations.item(0);
                    NodeList textNumOps = opNumOpsElement.getChildNodes();
                    myOps[1] = (String) textNumOps.item(0).getNodeValue().trim();

                    //------- operations interval
                    NodeList opInterval = firstServerElement.getElementsByTagName("interval");
                    Element opIntervalElement = (Element) opInterval.item(0);
                    NodeList textInterval = opIntervalElement.getChildNodes();
                    myOps[2] = (String) textInterval.item(0).getNodeValue().trim();

                    // strips interval letter - Ex: 10s, 1h
                    myOps[3] = myOps[2].substring((myOps[2].length() - 1));
                    //        String myInterval = interval.substring(0, (interval.length() - 1));

                    //---- operations length
                    NodeList opLength = firstServerElement.getElementsByTagName("length");
                    Element opLengthElement = (Element) opLength.item(0);
                    NodeList textLength = opLengthElement.getChildNodes();
                    myOps[4] = (String) textLength.item(0).getNodeValue().trim();
                    // System.out.println("Port : " + strPort);
                    //  ((Node)textPortElement.item(0)).getNodeValue().trim());


                    // strips period letter - Ex: 10s, 1h
                    myOps[5] = myOps[4].substring((myOps[4].length() - 1));
                    //String myPeriod = period.substring(period.length() - 1)

                    return myOps;
                }
            }
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        } catch (Exception e) {
            carLogger.Logger("Exception: " + e, e);
            //        } catch (Throwable t) {
            //            t.printStackTrace();
            //        }
        }
        return myOps;


    }

    public static void saveSimulateSettings(String _insert, String _update, String _delete) {
        CarRentalLogger carLogger = new CarRentalLogger();
        File myFile = new File("simulateLoad-settings.xml");
        NodeList nodeInsert;
        NodeList nodeUpdate;
        NodeList nodeDelete;
        String insert = _insert;
        String update = _update;
        String delete = _delete;

        try {
            if (!myFile.exists()) {
                CreateFile("simulateLoad-settings");
                carLogger.Logger("Creating file simulateLoad-Settings.xml");
            } else {
                carLogger.Logger("File simulateLoad-Settings.xml already exists ..");
            }


            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource("simulateLoad-settings.xml"));


            // locate the node(s)
            XPath xpath = XPathFactory.newInstance().newXPath();
            //
            nodeInsert = (NodeList) xpath.evaluate("//insert", doc, XPathConstants.NODESET);
            nodeInsert.item(0).setTextContent(insert);

            nodeUpdate = (NodeList) xpath.evaluate("//update", doc, XPathConstants.NODESET);
            nodeUpdate.item(0).setTextContent(update);

            nodeDelete = (NodeList) xpath.evaluate("//delete", doc, XPathConstants.NODESET);
            nodeDelete.item(0).setTextContent(delete);


            //NodeList nodes =
            //  (NodeList)xpath.evaluate("//dbtype[text()='sqlserver']", doc,
            //         XPathConstants.NODESET);

            //make the change
            //            for (int i = 0; i < nodes.getLength(); i++) {
            //                nodes.item(i).setTextContent(dbType);
            //            }
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(new DOMSource(doc), new StreamResult(new File("simulateLoad-settings.xml")));
            carLogger.Logger("Successfully saved simulateLoad-settings.xml");

        } catch (ParserConfigurationException e) {
            carLogger.Logger("ParserConfigurationException: " + e, e);
        } catch (IOException e) {
            carLogger.Logger("IOException: " + e, e);
        } catch (SAXException e) {
            carLogger.Logger("SAXException: " + e, e);
        } catch (IllegalArgumentException e) {
            carLogger.Logger("IllegalArgumentException: " + e, e);
        } catch (XPathExpressionException e) {
            carLogger.Logger("XPathExpressionException: " + e, e);
        } catch (TransformerException e) {
            carLogger.Logger("TransformerException: " + e, e);
        } catch (Exception e) {
            carLogger.Logger("Exception : " + e, e);
        }
    }

    public static String[] getSimulateSettingParamsFromXML() {
        String[] mySettings = new String[3];
        CarRentalLogger carLogger = new CarRentalLogger();
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("simulateLoad-settings.xml"));

            // normalize text representation
            doc.getDocumentElement().normalize();
            //  System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

            NodeList listOfOps = doc.getElementsByTagName("simulateLoad-settings");

            for (int s = 0; s < listOfOps.getLength(); s++) {
                Node firstServerNode = listOfOps.item(s);

                if (firstServerNode.getNodeType() == Node.ELEMENT_NODE) {


                    Element firstServerElement = (Element) firstServerNode;

                    //------- insert settings (always true ...)
                    NodeList settingInsert = firstServerElement.getElementsByTagName("insert");
                    Element settingInsertElement = (Element) settingInsert.item(0);
                    NodeList textSettingInsert = settingInsertElement.getChildNodes();
                    mySettings[0] = (String) textSettingInsert.item(0).getNodeValue().trim();

                    //------- update setting
                    NodeList settingUpdate = firstServerElement.getElementsByTagName("update");
                    Element settingUpdateElement = (Element) settingUpdate.item(0);
                    NodeList textSettingUpdate = settingUpdateElement.getChildNodes();
                    mySettings[1] = (String) textSettingUpdate.item(0).getNodeValue().trim();

                    //---- delete setting
                    NodeList settingDeleete = firstServerElement.getElementsByTagName("delete");
                    Element settingDeleteElement = (Element) settingDeleete.item(0);
                    NodeList textSettingDelete = settingDeleteElement.getChildNodes();
                    mySettings[2] = (String) textSettingDelete.item(0).getNodeValue().trim();

                    return mySettings;
                }
            }
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        } catch (Exception e) {
            carLogger.Logger("Exception: " + e, e);
            //        } catch (Throwable t) {
            //            t.printStackTrace();
            //        }
        }
        return mySettings;
    }

    public static String[] getConnectionParamsFromXML() {
        String[] myParams = new String[6];
        CarRentalLogger carLogger = new CarRentalLogger();
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("jdbc-settings.xml"));

            // normalize text representation
            doc.getDocumentElement().normalize();
            //  System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

            NodeList listOfJDBCSettings = doc.getElementsByTagName("jdbc-settings");

            for (int s = 0; s < listOfJDBCSettings.getLength(); s++) {
                Node firstServerNode = listOfJDBCSettings.item(s);

                if (firstServerNode.getNodeType() == Node.ELEMENT_NODE) {


                    Element firstServerElement = (Element) firstServerNode;

                    //-------
                    NodeList dbType = firstServerElement.getElementsByTagName("dbtype");
                    Element dbTypeElement = (Element) dbType.item(0);
                    NodeList textDbType = dbTypeElement.getChildNodes();
                    myParams[0] = (String) textDbType.item(0).getNodeValue().trim();
                    // System.out.println("DB Type : " + strDbType);
                    //((Node)textDbType.item(0)).getNodeValue().trim());

                    //-------
                    NodeList host = firstServerElement.getElementsByTagName("host");
                    Element hostElement = (Element) host.item(0);
                    NodeList textHost = hostElement.getChildNodes();
                    myParams[1] = (String) textHost.item(0).getNodeValue().trim();
                    //System.out.println("Host : " + strHost);
                    // ((Node)textHost.item(0)).getNodeValue().trim());

                    //----
                    NodeList port = firstServerElement.getElementsByTagName("port");
                    Element portElement = (Element) port.item(0);
                    NodeList textPortElement = portElement.getChildNodes();
                    myParams[2] = (String) textPortElement.item(0).getNodeValue().trim();
                    // System.out.println("Port : " + strPort);
                    //  ((Node)textPortElement.item(0)).getNodeValue().trim());

                    //------

                    //----
                    NodeList user = firstServerElement.getElementsByTagName("user");
                    Element userElement = (Element) user.item(0);
                    NodeList textUserElement = userElement.getChildNodes();
                    myParams[3] = (String) textUserElement.item(0).getNodeValue().trim();
                    //System.out.println("Username : " + strUser);
                    //   ((Node)textUserElement.item(0)).getNodeValue().trim());

                    //----

                    //----
                    NodeList password = firstServerElement.getElementsByTagName("password");
                    Element passwordElement = (Element) password.item(0);
                    NodeList textPasswordElement = passwordElement.getChildNodes();
                    myParams[4] = (String) textPasswordElement.item(0).getNodeValue().trim();

                    // Oracle Sid
                    NodeList sid = firstServerElement.getElementsByTagName("sid");
                    Element sidElement = (Element) sid.item(0);
                    NodeList textSidElement = sidElement.getChildNodes();
                    myParams[5] = (String) textSidElement.item(0).getNodeValue().trim();


                    return myParams;
                }
            }
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        } catch (Exception e) {
            carLogger.Logger("Exception: " + e, e);
            //        } catch (Throwable t) {
            //            t.printStackTrace();
            //        }
        }
        return myParams;
    }

    public static void saveConnectionStringToXML(String _dbType, String _host, String _port, String _user, String _pass, String _sid) {
        CarRentalLogger carLogger = new CarRentalLogger();
        String dbType = _dbType;
        String host = _host;
        String port = _port;
        String user = _user;
        String pass = _pass;
        String sid = _sid;
        NodeList nodeDbType;
        NodeList nodeHost;
        NodeList nodePort;
        NodeList nodeUser;
        NodeList nodePass;
        NodeList nodeSid;
        File myFile = new File("jdbc-settings.xml");
        try {
            if (!myFile.exists()) {
                CreateFile("jdbc-settings");
                carLogger.Logger("Creating file jdbc-settings.xml");
            } else {
                carLogger.Logger("File jdbc-settings.xml already exists ..");
            }
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource("jdbc-settings.xml"));


            // locate the node(s)
            XPath xpath = XPathFactory.newInstance().newXPath();
            //
            nodeDbType = (NodeList) xpath.evaluate("//dbtype", doc, XPathConstants.NODESET);
            nodeDbType.item(0).setTextContent(dbType);

            nodeHost = (NodeList) xpath.evaluate("//host", doc, XPathConstants.NODESET);
            nodeHost.item(0).setTextContent(host);

            nodePort = (NodeList) xpath.evaluate("//port", doc, XPathConstants.NODESET);
            nodePort.item(0).setTextContent(port);

            nodeUser = (NodeList) xpath.evaluate("//user", doc, XPathConstants.NODESET);
            nodeUser.item(0).setTextContent(user);

            nodePass = (NodeList) xpath.evaluate("//password", doc, XPathConstants.NODESET);
            nodePass.item(0).setTextContent(pass);

            nodeSid = (NodeList) xpath.evaluate("//sid", doc, XPathConstants.NODESET);
            nodeSid.item(0).setTextContent(sid);


            //NodeList nodes =
            //  (NodeList)xpath.evaluate("//dbtype[text()='sqlserver']", doc,
            //         XPathConstants.NODESET);

            //make the change
            //            for (int i = 0; i < nodes.getLength(); i++) {
            //                nodes.item(i).setTextContent(dbType);
            //            }
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(new DOMSource(doc), new StreamResult(new File("jdbc-settings.xml")));
            carLogger.Logger("Successfully saved jdbc-settings.xml");

        } catch (ParserConfigurationException e) {
            carLogger.Logger("ParserConfigurationException: " + e, e);
        } catch (IOException e) {
            carLogger.Logger("IOException: " + e, e);
        } catch (SAXException e) {
            carLogger.Logger("SAXException: " + e, e);
        } catch (IllegalArgumentException e) {
            carLogger.Logger("IllegalArgumentException: " + e, e);
        } catch (XPathExpressionException e) {
            carLogger.Logger("XPathExpressionException: " + e, e);
        } catch (TransformerException e) {
            carLogger.Logger("TransformerException: " + e, e);
        } catch (Exception e) {
            carLogger.Logger("Exception : " + e, e);
        }
    }

    public static String getConnectionStringFromXMLNoLogging() {
        String conUrl = null;
        CarRentalLogger carLogger = new CarRentalLogger();
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse("jdbc-settings.xml"); // was new File("..")
            // normalize text representation
            doc.getDocumentElement().normalize();
            //  System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

            NodeList listOfJDBCSettings = doc.getElementsByTagName("jdbc-settings");

            for (int s = 0; s < listOfJDBCSettings.getLength(); s++) {
                Node firstServerNode = listOfJDBCSettings.item(s);

                if (firstServerNode.getNodeType() == Node.ELEMENT_NODE) {


                    Element firstServerElement = (Element) firstServerNode;

                    //-------
                    NodeList dbType = firstServerElement.getElementsByTagName("dbtype");
                    Element dbTypeElement = (Element) dbType.item(0);

                    NodeList textDbType = dbTypeElement.getChildNodes();
                    String strDbType = (String) textDbType.item(0).getNodeValue().trim();
                    //carLogger.Logger("DB Type : " + strDbType);
                    //((Node)textDbType.item(0)).getNodeValue().trim());

                    //-------
                    NodeList host = firstServerElement.getElementsByTagName("host");
                    Element hostElement = (Element) host.item(0);

                    NodeList textHost = hostElement.getChildNodes();
                    String strHost = (String) textHost.item(0).getNodeValue().trim();
                    //carLogger.Logger("Host : " + strHost);
                    // ((Node)textHost.item(0)).getNodeValue().trim());

                    //----
                    NodeList port = firstServerElement.getElementsByTagName("port");
                    Element portElement = (Element) port.item(0);

                    NodeList textPortElement = portElement.getChildNodes();
                    String strPort = (String) textPortElement.item(0).getNodeValue().trim();
                    //carLogger.Logger("Port : " + strPort);
                    //  ((Node)textPortElement.item(0)).getNodeValue().trim());

                    //------

                    //----
                    NodeList user = firstServerElement.getElementsByTagName("user");
                    Element userElement = (Element) user.item(0);

                    NodeList textUserElement = userElement.getChildNodes();
                    String strUser = (String) textUserElement.item(0).getNodeValue().trim();
                    // carLogger.Logger("Username : " + strUser);
                    //   ((Node)textUserElement.item(0)).getNodeValue().trim());

                    //----

                    //----
                    NodeList password = firstServerElement.getElementsByTagName("password");
                    Element passwordElement = (Element) password.item(0);

                    NodeList textPasswordElement = passwordElement.getChildNodes();
                    String strPassword = (String) textPasswordElement.item(0).getNodeValue().trim();
                    // carLogger.Logger("Password : " + strPassword);
                    //    ((Node)textPasswordElement.item(0)).getNodeValue().trim());

                    //------


                    NodeList sid = firstServerElement.getElementsByTagName("sid");
                    Element sidElement = (Element) sid.item(0);

                    NodeList textSidElement = sidElement.getChildNodes();
                    String strSid = (String) textSidElement.item(0).getNodeValue().trim();
                    // carLogger.Logger("Sid : " + strSid);

                    if (strDbType.trim().toLowerCase().equals("sqlserver")) {
                        conUrl = ("jdbc:sqlserver://" + strHost + ":" + strPort + ";databaseName=CarRental;user=" + strUser + ";password=" + strPassword + ";");
                        //databaseName=CarRental;user=sa;password=admin;
                    } else if (strDbType.trim().toLowerCase().equals("oracle")) {
                        conUrl = ("jdbc:oracle:thin:@" + strHost + ":" + strPort + ":" + strSid);
                        //jdbc:oracle:thin:@myhost:1521:orcl
                    } else {
                        //   carLogger.Logger("Unknown database type!");
                    }

                }
            }
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        } catch (Exception e) {
            //  carLogger.Logger("Exception: " + e, e);
            //        } catch (Throwable t) {
            //            t.printStackTrace();
            //        }
        }
        return conUrl;
    }

    public static String getConnectionStringFromXML() {
        String conUrl = null;
        CarRentalLogger carLogger = new CarRentalLogger();
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse("jdbc-settings.xml"); // was new File("..")
            // normalize text representation
            doc.getDocumentElement().normalize();
            //  System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

            NodeList listOfJDBCSettings = doc.getElementsByTagName("jdbc-settings");

            for (int s = 0; s < listOfJDBCSettings.getLength(); s++) {
                Node firstServerNode = listOfJDBCSettings.item(s);

                if (firstServerNode.getNodeType() == Node.ELEMENT_NODE) {


                    Element firstServerElement = (Element) firstServerNode;

                    //-------
                    NodeList dbType = firstServerElement.getElementsByTagName("dbtype");
                    Element dbTypeElement = (Element) dbType.item(0);

                    NodeList textDbType = dbTypeElement.getChildNodes();
                    String strDbType = (String) textDbType.item(0).getNodeValue().trim();
                    carLogger.Logger("DB Type : " + strDbType);
                    //((Node)textDbType.item(0)).getNodeValue().trim());

                    //-------
                    NodeList host = firstServerElement.getElementsByTagName("host");
                    Element hostElement = (Element) host.item(0);

                    NodeList textHost = hostElement.getChildNodes();
                    String strHost = (String) textHost.item(0).getNodeValue().trim();
                    carLogger.Logger("Host : " + strHost);
                    // ((Node)textHost.item(0)).getNodeValue().trim());

                    //----
                    NodeList port = firstServerElement.getElementsByTagName("port");
                    Element portElement = (Element) port.item(0);

                    NodeList textPortElement = portElement.getChildNodes();
                    String strPort = (String) textPortElement.item(0).getNodeValue().trim();
                    carLogger.Logger("Port : " + strPort);
                    //  ((Node)textPortElement.item(0)).getNodeValue().trim());

                    //------

                    //----
                    NodeList user = firstServerElement.getElementsByTagName("user");
                    Element userElement = (Element) user.item(0);

                    NodeList textUserElement = userElement.getChildNodes();
                    String strUser = (String) textUserElement.item(0).getNodeValue().trim();
                    carLogger.Logger("Username : " + strUser);
                    //   ((Node)textUserElement.item(0)).getNodeValue().trim());

                    //----

                    //----
                    NodeList password = firstServerElement.getElementsByTagName("password");
                    Element passwordElement = (Element) password.item(0);

                    NodeList textPasswordElement = passwordElement.getChildNodes();
                    String strPassword = (String) textPasswordElement.item(0).getNodeValue().trim();
                    carLogger.Logger("Password : " + strPassword);
                    //    ((Node)textPasswordElement.item(0)).getNodeValue().trim());

                    //------


                    NodeList sid = firstServerElement.getElementsByTagName("sid");
                    Element sidElement = (Element) sid.item(0);

                    NodeList textSidElement = sidElement.getChildNodes();
                    String strSid = (String) textSidElement.item(0).getNodeValue().trim();
                    carLogger.Logger("Sid : " + strSid);

                    if (strDbType.trim().toLowerCase().equals("sqlserver")) {
                        conUrl = ("jdbc:sqlserver://" + strHost + ":" + strPort + ";databaseName=CarRental;user=" + strUser + ";password=" + strPassword + ";");
                        //databaseName=CarRental;user=sa;password=admin;
                    } else if (strDbType.trim().toLowerCase().equals("oracle")) {
                        conUrl = ("jdbc:oracle:thin:@" + strHost + ":" + strPort + ":" + strSid);
                        //jdbc:oracle:thin:@myhost:1521:orcl
                    } else {
                        carLogger.Logger("Unknown database type!");
                    }

                }
            }
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        } catch (Exception e) {
            carLogger.Logger("Exception: " + e, e);
            //        } catch (Throwable t) {
            //            t.printStackTrace();
            //        }
        }
        return conUrl;
    }

    public static void CreateFile(String _type) throws IOException {
        String type = _type;
        if (type == "jdbc-settings" || type == "jdbc-settings.xml") {
            File myFile = new File("jdbc-settings.xml");
            myFile.createNewFile();
            BufferedWriter writer = null;
            CarRentalLogger carLogger = new CarRentalLogger();
            String blankFile = "<?xml version=\"1.0\" encoding=\"windows-1252\" standalone=\"no\"?>\n"
                    + "<jdbc-settings>\n"
                    + "   <dbtype></dbtype>\n"
                    + "   <host></host>\n"
                    + "   <port></port>\n"
                    + "   <user></user>\n"
                    + "   <password></password>\n"
                    + "   <sid></sid>\n"
                    + "</jdbc-settings>";
            try {
                writer = new BufferedWriter(new FileWriter("jdbc-settings.xml"));
                writer.write(blankFile);
                carLogger.Logger("Created file jdbc-settings.xml in app server context root.");
            } catch (IOException e) {
                carLogger.Logger("IOException: " + e, e);
            } finally {
                if (writer != null) {
                    writer.close();
                }

            }
        } else if (type == "simulateLoad-settings" || type == "simulateLoad-settings.xml") {
            File myFile = new File("simulateLoad-settings.xml");
            myFile.createNewFile();
            BufferedWriter writer = null;
            CarRentalLogger carLogger = new CarRentalLogger();
            String blankFile = "<?xml version=\"1.0\" encoding=\"windows-1252\" standalone=\"no\"?>\n"
                    + "<simulateLoad-settings>\n"
                    + "   <running></running>\n"
                    + "   <insert>true</insert>\n"
                    + "   <update>true</update>\n"
                    + "   <delete>true</delete>\n"
                    + "   <operations></operations>\n"
                    + "   <interval></interval>\n"
                    + "   <length></length>\n"
                    + "</simulateLoad-settings>";
            try {
                writer = new BufferedWriter(new FileWriter("simulateLoad-settings.xml"));
                writer.write(blankFile);
                carLogger.Logger("Created file simulateLoad-settings.xml in app server context root.");
            } catch (IOException e) {
                carLogger.Logger("IOException: " + e, e);
            } finally {
                if (writer != null) {
                    writer.close();
                }

            }
        } else if (type == "carFleet-info" || type == "carFleet-info.xml") {
            File myFile = new File("carFleet-info.xml");
            myFile.createNewFile();
            BufferedWriter writer = null;
            CarRentalLogger carLogger = new CarRentalLogger();
            String blankFile = "<?xml version=\"1.0\" encoding=\"windows-1252\" standalone=\"no\"?>\n"
                    + "<carFleet-info>\n"
                    + "   <contextNumber></contextNumber>\n"
                    + "   <jdbcNumber></jdbcNumber>\n"
                    + "</carFleet-info>";
            try {
                writer = new BufferedWriter(new FileWriter("carFleet-info.xml"));
                writer.write(blankFile);
                carLogger.Logger("Created file carFleet-info.xml in app server context root.");
            } catch (IOException e) {
                carLogger.Logger("IOException: " + e, e);
            } finally {
                if (writer != null) {
                    writer.close();
                }

            }
        }
    }
}
