import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author good0161
 * @version 1.0.0
 * 
 */

public class TripPoint {
    
    private double lat;
    private double lon;
    private int time;
    private static ArrayList<TripPoint> trip;

    //constructor methods
    /**
     * Default constructor
     */
    public TripPoint(){
        lat = 0;
        lon = 0;
        time = 0;
    }
    /**
     * Initilizes varibles of a new TripPoint object 
     * @param time
     * @param lat
     * @param lon
    */
    public TripPoint(int time, double lat, double lon){
        this.lat = lat;
        this.lon = lon;
        this.time = time;
    }

    //accessor methods

    /**
     * gets the time
     * @return time
     */
    public int getTime(){
        return time;
    }
    /**
     * gets Latitude
     * @return lat
     */
    public double getLat(){
        return lat;
    }
    /**
     * gets Longitude
     * @return lon
     */
    public double getLon(){
        return lon;
    }
    /**
     * Makes a copy of the trips ArrayList to prevent mutation and returns it
     * @return copy of trip
     */
    public static ArrayList<TripPoint> getTrip(){
        ArrayList<TripPoint> copy = new ArrayList<>(trip);
        return copy;
    }

    //Helper methods
    /**
     * Computes and return the Haversine distance between two points in kilometers.
     * @param a
     * @param b
     * @return distance
     */
    public static double haversineDistance(TripPoint a, TripPoint b){
        final double EARTHR = 6372.8; //Earth's radius in Km
        double dLat = Math.toRadians(b.getLat() - a.getLat()); // change in lat
        double dLon = Math.toRadians(b.getLon() - a.getLon()); // change in lon

        //Haversine Formula
        double circle = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
               Math.cos(Math.toRadians(a.getLat())) * Math.cos(Math.toRadians(b.getLat())) *
               Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double angle = 2 * Math.asin(Math.sqrt(circle));
        double distance = EARTHR * angle;
        return distance;
    }
    /**
     * 
     * @param a
     * @param b
     * @return avgSpeed
     * Calculates the average speed between two points in kilometers per hour.
     * Order does not matter
     */
    public static double avgSpeed(TripPoint a, TripPoint b){
        double distance = haversineDistance(a,b);
        double time = Math.abs(b.getTime() - a.getTime()) / 60.0; // Convert minutes to hours
        double speed = distance / time;
        return speed;
    }
    /**
     * Calculates and returns the total trip time in hours
     * @return total time
     */
    public static double totalTime(){
        double tot = 0;
        for(TripPoint a : trip){
            tot+= a.getTime();
        }
        double totHrs = tot/60;
        return totHrs;
    }
    public static double totalDistance(){

    }
}
