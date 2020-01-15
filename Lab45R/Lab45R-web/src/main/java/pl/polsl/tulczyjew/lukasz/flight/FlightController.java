/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.tulczyjew.lukasz.flight;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pl.polsl.tulczyjew.lukasz.FlightBean;
import pl.polsl.tulczyjew.lukasz.model.Flight;

/**
 * Controller of the flight.
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
@ManagedBean
@ViewScoped
public class FlightController implements Serializable {
    
    /**
     * Flight field.
     */
    private Flight flight;
    
    /**
     * EJB to perform CRUD operations on database.
     */
    @EJB
    private FlightBean flightBean;
    
    /**
     * Initialiser method.
     */
    @PostConstruct
    protected void init() {
        this.flight = (Flight) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("flight");
        if (this.flight == null) {
            this.flight = new Flight();
        }
    }
    
    /**
     * Get flight method
     * @return flight
     */
    public Flight getFlight() {
        return this.flight;
    }
    
    /**
     * Action save method for updating or creating the flight
     * @return String representing the destination face
     */
    public String actionSave() {
        this.flightBean.createOrUpdateFlight(flight);
        return "flight";
    } 
}
