package pl.polsl.tulczyjew.lukasz;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class for performing view tests.
 *
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
public class ViewTest {

    @Test
    public void testView() throws IOException, InterruptedException {
        // create the browser "simulator"
        try (WebClient webClient = new WebClient()) {
            // access the page you want to test
            HtmlPage page = webClient.getPage("http://localhost:8080/Lab45R-web/faces/editflight.xhtml");
            // make sure the page is accessed by checking its title
            assertEquals("Should be ...", "Edit Flight", page.getTitleText());

            // get the form and check whether it exists
            HtmlForm form = page.getFormByName("flight_form");
            assertNotNull("Should be non-null", form);
            
            form.getInputByName("aircraft_type").setValueAttribute("boeing");
            form.getInputByName("departure_location").setValueAttribute("toszek");
            form.getInputByName("arrival_location").setValueAttribute("paris");

            // Click the button and wait 3s for processing
            HtmlButton button = page.getHtmlElementById("edit_btn");
            button.click();
            Thread.sleep(3000);
            
     
        }
    }
    
    @Test
    public void testFlight() throws IOException, InterruptedException{
        try (WebClient webClient = new WebClient()) {
            // access the page you want to test
            HtmlPage page = webClient.getPage("http://localhost:8080/Lab45R-web/faces/flight.xhtml");
               // Get all the buttons within the form (Add button should always be there)
                   // get the form and check whether it exists
            assertEquals("Should be ...", "Flight menu:", page.getTitleText());

            HtmlForm form = page.getFormByName("flights_form");
            assertNotNull("Should be non-null", form);

            List<HtmlButton> buttons = form.getByXPath(".//button");
            assertFalse("Should not be empty", buttons.isEmpty());
              
            // Get the Add button - since it is in the table prepend table id to button id
            HtmlButton button = page.getHtmlElementById("flights_table:add_btn");
            assertNotNull("Should be non-null", button);
    }
       
}
    
    
    
    @Test
    public void testIndex() throws IOException, InterruptedException{
        try (WebClient webClient = new WebClient()) {
            // access the page you want to test
            HtmlPage page = webClient.getPage("http://localhost:8080/Lab45R-web");
               // Get all the buttons within the form (Add button should always be there)
                   // get the form and check whether it exists
            assertEquals("Should be ...", "Flight and passenger menu:", page.getTitleText());

            HtmlForm form = page.getFormByName("menu_form");
            assertNotNull("Should be non-null", form);

            HtmlButton button1 = page.getHtmlElementById("menu_form:flight_button");
            assertNotNull("Should be non-null", button1);
            
            HtmlButton button2 = page.getHtmlElementById("menu_form:passenger_button");
            assertNotNull("Should be non-null", button2);
            
            HtmlButton buttonToGo = page.getHtmlElementById("menu_form:passenger_button");
            buttonToGo.click();
            Thread.sleep(3000);
            HtmlPage passengerView = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
            assertEquals ("Should be ...",  "Passenger menu:",  passengerView.getTitleText());
            
            page = webClient.getPage("http://localhost:8080/Lab45R-web/faces/passenger.xhtml");
            passengerView = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
            
            assertEquals ("Should be ...",  "Passenger menu:",  passengerView.getTitleText());
            HtmlButton passengerToMenu = page.getHtmlElementById("passengers_table:menu_btn");
            passengerToMenu.click();
            Thread.sleep(3000);
            HtmlPage indexView = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
            assertEquals ("Should be ...",  "Flight and passenger menu:",  indexView.getTitleText());
            
    }
    }  
    
}
