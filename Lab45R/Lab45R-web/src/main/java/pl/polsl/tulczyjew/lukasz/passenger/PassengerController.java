/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.tulczyjew.lukasz.passenger;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pl.polsl.tulczyjew.lukasz.FlightBean;
import pl.polsl.tulczyjew.lukasz.PassengerBean;
import pl.polsl.tulczyjew.lukasz.model.Flight;
import pl.polsl.tulczyjew.lukasz.model.Passenger;

/**
 * Controller of the passenger.
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
@ManagedBean
@ViewScoped
public class PassengerController implements Serializable{
    
    /**
     * Passenger field.
     */
    private Passenger passenger;
    
    /**
     * Chosen Flight of the passenger 
     */
    private Integer flight;
    
    /**
     * EJB to perform CRUD operations on database.
     */
    @EJB
    private PassengerBean passengerBean;
    
    @EJB
    private FlightBean flightBean;
    
    /**
     * Initialiser method.
     */
    @PostConstruct
    protected void init() {
        this.passenger = (Passenger) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("passenger");
        if (this.passenger == null) {
            this.passenger = new Passenger();
        }
    }
    
    /**
     * Get passenger method
     * @return passenger
     */
    public Passenger getPassenger() {
        return this.passenger;
    }
    
    public void setFlight(Integer flight) {
        this.flight = flight;
    }
    
    public Integer getFlight() {
        return this.flight;
    }
    
    /**
     * Action save method for updating or creating the passenger
     * @return String representing the destination face
     */
    public String actionSave() {
        this.passenger.setFlight(this.flightBean.readFlight(this.flight));
        this.passengerBean.createOrUpdatePassenger(passenger);
        return "passenger";
    } 
}

