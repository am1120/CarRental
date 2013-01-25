package carrental.helper;

import carrental.model.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.ServletContext;

import carrental.AddCarServlet;


public class CarStatHelper {
    public CarStatHelper() {
    }

    public static String median(List someList) {
        Iterator iter = someList.iterator();
        ArrayList anArray = new ArrayList();
        while (iter.hasNext()) {
            Car aCar = (Car)iter.next();
            //double cost = aCar.getGallons();
            //anArray.add(new Double(cost));
        }

        // This code sorts the array of costs from highest to lowest
        //  and provides how to find the median for arrays with an odd
        //  number of elements.
        //  *******************************

        Collections.sort(anArray); // the array is now in order
        String median = "not finished  -- doesn't work for even number array";
        if (anArray.size() % 2 == 1) {
            int middleOne = anArray.size() / 2;
            double value = (Double)anArray.get(middleOne);
            median = Double.toString(value);
        } else {
            int firstMiddle = (anArray.size() / 2);
            double firstValue = (Double)anArray.get(firstMiddle);
            int secondMiddle = (anArray.size() / 2) + 1;
            double secondValue = (Double)anArray.get(secondMiddle);
            double evenMedian = (firstValue + secondValue) / 2;
            median = Double.toString(evenMedian);
        }
        System.out.println("median = " + median);
        return median;
    }

    public static Double averageMPG(List someList) {
        Iterator iter = someList.iterator();
        ArrayList anArray = new ArrayList();
        //Populate Array with MPG values of all cars.
        while (iter.hasNext()) {
            Car aCar = (Car)iter.next();
            //   double mpg = aCar.miles;
            //   anArray.add(new Double(mpg));
        }
        Collections.sort(anArray); // Sort the Array.
        //Determine total MPG for entire fleet
        Iterator aIter = anArray.iterator();
        double total = 0;
        while (aIter.hasNext()) {
            Double aCost = (Double)aIter.next();
            total += aCost.doubleValue();
        }
        //Return the value of the average MPG for the fleet.
        return (total / anArray.size());
    }

    public static double sumMiles(List someList) {
        Iterator iter = someList.iterator();
        ArrayList anArray = new ArrayList();
        //Populate Array with MPG values of all cars.
        while (iter.hasNext()) {
            Car aCar = (Car)iter.next();
            // double mpg = aCar.miles;
            // anArray.add(new Double(mpg));
        }
        Collections.sort(anArray); // Sort the Array.
        //Determine total MPG for entire fleet
        Iterator aIter = anArray.iterator();
        double total = 0;
        while (aIter.hasNext()) {
            Double aCost = (Double)aIter.next();
            total += aCost.doubleValue();
        }
        //Return the value of the average MPG for the fleet.
        return total;
    }
    /*   public static double sumGasUsed(List someList) {
            {
                Iterator iter = someList.iterator();
                ArrayList anArray = new ArrayList();
                //Populate Array with MPG values of all cars.
                while (iter.hasNext()) {
                    Car aCar = (Car)iter.next();
                    double mpg = aCar.gallons;
                    anArray.add(new Double(mpg));
                }
                Collections.sort(anArray); // Sort the Array.
                //Determine total MPG for entire fleet
                Iterator aIter = anArray.iterator();
                double total = 0;
                while (aIter.hasNext()) {
                    Double aCost = (Double)aIter.next();
                    total += aCost.doubleValue();
                }
                return total;
            }
    } */

    /*  public static String bestMPG(List someList){
        Iterator iter = someList.iterator();
        ArrayList nameArray = new ArrayList();//Create an array of the names
        ArrayList mpgArray = new ArrayList();//Create an array of the mpg for those names
        while (iter.hasNext()) {//Populate the above arrays
            Car aCar = (Car)iter.next();
            nameArray.add(aCar.getName());
            double mpg = aCar.miles / aCar.gallons; //Double.valueOf(aCar.reportMPG());
            mpgArray.add(new Double(mpg));
        }

        TreeMap hmap = new TreeMap();//Create a treemap because we can sort with it
        List mapKeys = new ArrayList(nameArray);//Create a list to sort the names
        List mapValues = new ArrayList(mpgArray);//And a list to sort the mpg's
        hmap.clear();//Just keepin it clean
        TreeSet sortedSet = new TreeSet(mapValues);//A treset so we can sort.
        Object[] sortedArray = sortedSet.toArray();//Bleh bleh bleh
        int size = sortedArray.length;
        for (int i=0; i<size; i++)//And now we repopulate the treemap
        {
            hmap.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i]);
        }
        //Below returns everything needed to just print out, yay!
        return ("The vehicle with the best MPG is: " + hmap.lastKey() + "<br>");
    } */

    /*  public static String worstMPG(List someList) {
        Iterator iter = someList.iterator();
        ArrayList nameArray = new ArrayList();//Create an array of the names
        ArrayList mpgArray = new ArrayList();//Create an array of the mpg for those names
        while (iter.hasNext()) {//Populate the above arrays
            Car aCar = (Car)iter.next();
            nameArray.add(aCar.getName());
            double mpg = aCar.miles / aCar.gallons; //Double.valueOf(aCar.reportMPG());
            mpgArray.add(new Double(mpg));
        }

        TreeMap hmap = new TreeMap();//Create a treemap because we can sort with it
        List mapKeys = new ArrayList(nameArray);//Create a list to sort the names
        List mapValues = new ArrayList(mpgArray);//And a list to sort the mpg's
        hmap.clear();
        TreeSet sortedSet = new TreeSet(mapValues);
        Object[] sortedArray = sortedSet.toArray();
        int size = sortedArray.length;
        for (int i=0; i<size; i++)//And now we repopulate the treemap
        {
            hmap.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i]);
        }
        return ("The vehicle with the worst MPG is: " + hmap.firstKey() + "<br>");
    } */

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
    }

    // test code

    //    public static void main(String[] args) {
    //        List<Car> carsList = new ArrayList<Car>();
    //        carsList.add(new RentalCar("2006", "Jeep Wrangler", 1000.00, 25.00));
    //        carsList.add(new RentalCar("2004", "Honda Accord", 23000.00, 15.00));
    //        carsList.add(new RentalCar("2005", "Honda Civic", 14000.00, 10.00));
    //        carsList.add(new RentalCar("2002", "Nissan Altima", 35000.00, 25.00));
    //        carsList.add(new RentalCar("2000", "Ford F150", 100000.00, 25.00));
    //        carsList.add(new RentalCar("1990", "Mercury Cougar", 145000.00, 25.00));
    //        System.out.println("average is " +
    //                           CarStatHelper.averageMPG(carsList));
    //        Car aCar = carsList.get(0);
    //        String selectionString = aCar.toString();
    //        String rentalFeeString = "3.50";
    //
    //        CarStatHelper.rentACAR(selectionString, rentalFeeString, carsList);
    //        System.out.println(aCar.id + " " + aCar.reportRentalFees());
    //    }

}


