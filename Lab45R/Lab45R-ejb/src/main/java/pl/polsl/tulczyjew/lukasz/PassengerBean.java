package pl.polsl.tulczyjew.lukasz;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.polsl.tulczyjew.lukasz.model.Passenger;

/**
 * Class for performing CRUD operation on Passenger entity through EJBs.
 *
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
@Stateless
@LocalBean
public class PassengerBean {

    /**
     * Default constructor.
     */
    public PassengerBean() {

    }
    
     /**
     * Create or update passenger method.
     * @param passenger Passenger passed.
     * @return Updated or Created passenger.
     */
     public Passenger createOrUpdatePassenger(Passenger passenger) {
        if (passenger.getId() == null) {
            this.entityManager.persist(passenger);
        } else {
            this.entityManager.merge(passenger);
        }
        return passenger;
    }


    /**
     * Manager designed to perform CRUD operations.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Create passenger.
     *
     * @param passenger Passenger to create.
     */
    public void createPassenger(Passenger passenger) {
        this.entityManager.persist(passenger);
    }

    /**
     * Read passenger.
     *
     * @param id Id of the passenger.
     * @return Instance of passenger.
     */
    public Passenger readPassenger(int id) {
        Passenger passenger;
        passenger = this.entityManager.find(Passenger.class, id);
        return passenger;
    }

    /**
     * Update passenger.
     *
     * @param passenger Passenger to update.
     */
    public void updatePassenger(Passenger passenger) {
        this.entityManager.merge(passenger);
    }

    /**
     * Delete passenger.
     *
     * @param id Id of passenger.
     */
    public void deletePassenger(int id) {
        Passenger passenger;
        passenger = this.entityManager.find(Passenger.class, id);
        if (passenger != null) {
            this.entityManager.remove(passenger);
        }
    }

    /**
     * Read all passengers.
     *
     * @return List of passengers.
     */
    public List<Passenger> readAllPassengers() {
        return this.entityManager.createNamedQuery(
                "Passenger.findAll").getResultList();
    }
}
