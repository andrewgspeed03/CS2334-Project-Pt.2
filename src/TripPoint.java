import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * This class is used to create TripPoint objects that contain the time interval, latitude, and longitude of a
 * point. It also contains methods to calculate the distance between two points, the average speed
 * between two points, the total time of the trip, and the total distance of the trip
 * @author good0161
 * @version 1.0.0
 * 
 */

public class TripPoint {
    
    private double lat;
    private double lon;
    private int time;
    private static ArrayList<TripPoint> trip = new ArrayList<TripPoint>();

    //constructor method
    /**
     * Initializes variables of a new TripPoint object 
     * @param time
     * time interval of the point
     * @param lat
     * lattitude of the point
     * @param lon
     * longitude of the point
    */
    public TripPoint(int time, double lat, double lon){
        this.lat = lat;
        this.lon = lon;
        this.time = time;
    }

    //assessor methods

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
     * The previous point
     * @param b
     * point that was travel to
     * @return distance
     */
    public static double haversineDistance(TripPoint a, TripPoint b){
        final int R = 6371; // Earth's radius in km
        double lata = Math.toRadians(a.getLat());
        double latb = Math.toRadians(b.getLat());
        double lona = Math.toRadians(a.getLon());
        double lonb = Math.toRadians(b.getLon());

        double dLat = latb - lata;
        double dLon = lonb - lona;

        //Haversine formula
        double aVal = Math.sin(dLat / 2) * Math.sin(dLat / 2) + 
                   Math.cos(lata) * Math.cos(latb) * 
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double cVal = 2 * Math.atan2(Math.sqrt(aVal), Math.sqrt(1 - aVal));
        double dist = R * cVal;

        return dist;
    }
    /**
     *Calculates the average speed between two points in kilometers per hour.
     * Order does not matter
     * @param a
     * first Trippoint
     * @param b
     * second Trippoint
     * @see haversineDistance
     * @return avgSpeed
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
        for(int i = 1; i < trip.size(); i++)
            tot+=5;
        double totHrs = tot / 60.0;
        return totHrs;
    }
    /**
     * Computes and returns the total distance of the trip in kilometers.
     * @see haversineDistance
     * @return total distance
     */
    public static double totalDistance(){
        double tot = 0;
        for(int i = 1; i < trip.size(); i++)
            tot+= haversineDistance(trip.get(i-1),trip.get(i));
        return tot;
    }
    /**
     * Initialize each line of data in the file as a TripPoint object
     * adding them to the trip ArrayList 
     * @param filename
     * name of CSV file
     * @throws IOException
     */
    public static void readFile(String filename) throws IOException{
        try{
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String raw;
            while((raw = br.readLine()) != null){
                String[] data = raw.split(",");
                int time = Integer.parseInt(data[0]);
                double lat = Double.parseDouble(data[1]);
                double lon = Double.parseDouble(data[2]);
                trip.add(new TripPoint(time, lat, lon));
            }
            br.close();
            fr.close();
        }
        catch (InputMismatchException e){

        }
    }
}
