/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.tulczyjew.lukasz.flight;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pl.polsl.tulczyjew.lukasz.FlightBean;
import pl.polsl.tulczyjew.lukasz.model.Flight;

/**
 * Controller of the flights.
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */


@ManagedBean
@ViewScoped
public class FlightsController implements Serializable{
    
    /**
     * Bean for operations on the database
     */
    @EJB
    private FlightBean flightBean;
    
    /**
     * Get all flights
     * @return List of all flights.
     */
    public List<Flight> getFlights() {
        return this.flightBean.readAllFlights();
    }

    /**
     * Move to edit flight menu.
     * @param flight Flight to be edited.
     * @return name of the redirecting face
     */
    public String actionEdit(Flight flight) {
        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("flight", flight);
        return "editflight";
    }

    /**
     * Delete flight action
     * @param flight Flight to be deleted
     */
    public void actionListenerRemove(Flight flight) {
        this.flightBean.deleteFlight(flight.getId());
    }
}
