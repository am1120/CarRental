package carrental.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Car {
    String name;
    double miles;
    ArrayList<Double> myCars = new ArrayList<Double>();

    public Car(String name, Double miles) {
        // this.setId(id);
        this.setName(name);
        this.setMiles(miles);
    }

    public void reportRentalFees(String selection, Double miles, List carList) {

    }

    public void updateMiles(double amount) {
        myCars.add(amount);
    }

    /*  public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    } */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMiles() {
        return miles;
    }

    public void setMiles(Double miles) {
        this.miles = miles;
    }
}
