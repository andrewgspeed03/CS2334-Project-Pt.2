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

    /**
     * 
     * @param time
     * @param lat
     * @param lon
     * Initilizes varibles of a new TripPoint object
     */
    public TripPoint(int time, double lat, double lon){
        this.lat = lat;
        this.lon = lon;
        this.time = time;
    }

    //accessor methods

    /**
     * 
     * @return time
     * gets the time
     */
    public int getTime(){
        return time;
    }
    /**
     * 
     * @return lat
     * gets Latitude
     */
    public double getLat(){
        return lat;
    }
    /**
     * 
     * @return lon
     * gets Longitude
     */
    public double getLon(){
        return lon;
    }
    /**
     * 
     * @return copy of trip
     * Makes a copy of the trips ArrayList to prevent mutation and returns it
     */
    public static ArrayList<TripPoint> getTrip(){
        ArrayList<TripPoint> copy = new ArrayList<>(trip);
        return copy;
    }

    //Helper methods
    /**
     * 
     * @param a
     * @param b
     * @return distance
     * Computes and return the Haversine distance between two points in kilometers.
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

}
