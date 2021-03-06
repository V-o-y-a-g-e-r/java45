/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.tulczyjew.lukasz;

import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.polsl.tulczyjew.lukasz.model.Flight;

/**
 * Flight test class
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
public class FlightTest {
    
    private static EJBContainer container;
    private static FlightBean flightBean;
    private static PassengerBean passengerBean;
    @BeforeClass
    public static void initOnce() throws NamingException {
        Properties properties = new Properties();
        properties.put("xyz", "new://Resource?type=DataSource");
        properties.put("xyz.UserName", "root");
        properties.put("xyz.Password", "root");
        properties.put("xyz.JdbcUrl", 
                "jdbc:mysql://localhost:3306/lab");
        properties.put("xyz.JdbcDriver", "com.mysql.cj.jdbc.Driver");
        properties.put("xyz.JtaManaged", "true");
        properties.put("xyz.ConnectionProperties", 
                "useSSL=false;allowPublicKeyRetrieval=true");
        container = EJBContainer.createEJBContainer(properties);
        flightBean = (FlightBean)
                container.getContext().lookup("java:global/Lab45R-ejb/FlightBean");
        passengerBean = (PassengerBean)
                container.getContext().lookup("java:global/Lab45R-ejb/PassengerBean");
    }
    
    @Test
    public void testCreate() {
        Flight flight = new Flight("boweing", "sydney", "toszek");
        flight = flightBean.createOrUpdateFlight(flight);
        assertNotNull("Should be not null", flight.getId());
    }
    
    @Test
    public void testDelete() {
        Flight flight = new Flight("boweing", "sydney", "toszek");
        flight = flightBean.createOrUpdateFlight(flight);
        flightBean.deleteFlight(flight.getId());
        System.out.println("sffewffeww");
        Flight flight2 = flightBean.readFlight(flight.getId());
        assertNull("Should be null", flight2);
    }
    
    @Test
     public void testFind() {
        try {
        Flight flight = new Flight("boweing", "sydney", "toszek");
        flight = flightBean.createOrUpdateFlight(flight);
        Flight flight2 = flightBean.readFlight(flight.getId());
        assertNotNull("Should be not null", flight2);
        } catch (Exception ex) {
            {System.out.println(ex.toString());}
        }
    }
    
     @AfterClass
    public static void cleanUpOnce() {
        container.close();
    }
}
