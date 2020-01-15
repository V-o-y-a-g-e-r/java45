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
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Flight test class
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
public class FlightTest {
    
    private static EJBContainer container;
    private static FlightBean flightBean;
    
    @BeforeClass
    public static void initOnce() throws NamingException {
        Properties properties = new Properties();
        
        properties.put("xyz", "new://Resource?type=DataSource");
        properties.put("xyz.UserName", "root");
        properties.put("xyz.Password", "root");
        properties.put("xyz.JdbcUrl", 
                "jdbc:mysql://localhost:3307/lab_tj");
        properties.put("xyz.JdbcDriver", "com.mysql.cj.jdbc.Driver");
        properties.put("xyz.JtaManaged", "true");
        properties.put("xyz.ConnectionProperties", 
                "useSSL=false;allowPublicKeyRetrieval=true");
        container = EJBContainer.createEJBContainer(properties);
        flightBean = (FlightBean)
                container.getContext().lookup("java:global/Lab45R-ejb/FlightBean");
    }
    
    @Test
    public void testCreate() {
//        Student student = new Student("Tomasz", 4.5);
//        assertNull("Should be null", student.getId());
//        studentBean.createOrUpdateStudent(student);
//        assertNotNull("Should be not null", student.getId());
    }
    
    @AfterClass
    public static void cleanUpOnce() {
        container.close();
    }
}
