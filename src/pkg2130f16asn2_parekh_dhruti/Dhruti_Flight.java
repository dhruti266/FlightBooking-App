/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2130f16asn2_parekh_dhruti;

/* Course Code : COMP2130
   Student ID  : 101039706
   Dhruti Parekh
*/


public class Dhruti_Flight{
    private String flightNumber;
    private String source;
    private String destination;
    private double flightFare;
    private String travelDate;
    

    
     public Dhruti_Flight(String fn, String src, String dest, double ff, String date){
         this.flightNumber = fn;
         this.source = src;
         this.destination = dest;
         this.flightFare = ff;
         this.travelDate = date;
    
     }
     
    public String GetFlightNumber(){
        return flightNumber;
    }
    
    public String GetSource(){
        return source;
    }
    
    public String GetDestination(){
        return destination;
    }
    
    public double GetFlightFare(){
        return flightFare;
    }
    
    public String GetTravelDate(){
        return travelDate;
    }
}
