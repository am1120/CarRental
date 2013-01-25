package carrental.model;

import java.util.Iterator;
import java.util.List;

public class RentalCar extends Car {

    private int copy;

    public RentalCar(String name, double miles) {
        super(name, miles);
        //this.setCopy(copy);
    }

    // public int getCopy() {
    //     return copy;
    //}

    //public void setCopy(int copy) {
    //    this.copy = copy;
    //}
    // new methods to handle rentals

    // I've been rented for an amount

    public void rentCar(double amount) {
        myCars.add(amount);
    }

    // sum up and report my rental fees

    public static void updateMiles(String selection, String miles, List carList) {
        // find the car to rent
        Iterator iter = carList.iterator();
        while (iter.hasNext()) {
            Car aCar = (Car)iter.next();
            if (aCar.equals(selection)) {
                //aCar.getMiles(Double.parseDouble(miles));
                aCar.updateMiles(Double.parseDouble(miles));
                break;
            }
        }


        //        Iterator myIter = myCars.iterator();
        //        double sum = 0;
        //        while (myIter.hasNext()) {
        //            Double rentalAmount = (Double)myIter.next();
        //            sum += rentalAmount.doubleValue();
        //        }
        //        return ("-- total rental fees sum to  $" + sum);
    }

    // override object methods

    public String toString() {
        return (name);

    }

    public boolean equals(Object o) {
        if (!(o instanceof String))
            return false;
        String d = (String)o;
        return (this.toString().equals(d));
    }

}

