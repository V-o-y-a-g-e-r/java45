/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.tulczyjew.lukasz.passenger;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pl.polsl.tulczyjew.lukasz.PassengerBean;
import pl.polsl.tulczyjew.lukasz.model.Passenger;

/**
 * Controller of the passengers.
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */

@ManagedBean
@ViewScoped
public class PassengersController implements Serializable{
    
    /**
     * Bean for operations on the database
     */
    @EJB
    private PassengerBean passengerBean;
    
    /**
     * Get all passengers
     * @return List of all passengers.
     */
    public List<Passenger> getPassengers() {
        return this.passengerBean.readAllPassengers();
    }
    
    /**
     * Move to edit passenger menu.
     * @param passenger Passenger to be edited.
     * @return name of the redirecting face
     */
    public String actionEdit(Passenger passenger) {
        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("passenger", passenger);
        return "editpassenger";
    }

    /**
     * Delete passenger action
     * @param passenger Passenger to be deleted
     */
    public void actionListenerRemove(Passenger passenger) {
        this.passengerBean.deletePassenger(passenger.getId());
    }
}
