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
public class Dhruti_TravelReservation {
    
    Dhruti_Flight dFlight;
    Dhruti_Passenger dPass;
    Dhruti_FrequentFlyer_Passenger dFreqPass;
    
    public Dhruti_TravelReservation(Dhruti_Flight dFlight, Dhruti_Passenger dPass ){
        this.dPass = dPass;
        this.dFlight = dFlight;         
    }
     
    public Dhruti_TravelReservation(Dhruti_Flight dFlight, Dhruti_FrequentFlyer_Passenger dFreqPass ){
        this.dFlight = dFlight;
        this.dFreqPass = dFreqPass;         
    }
     
    public Dhruti_Flight GetDFlight(){
        return dFlight;
    }
          
    public Dhruti_Passenger GetDPass(){
        return  dPass;
    }
    
    public Dhruti_FrequentFlyer_Passenger GetDFreqPass(){
        return dFreqPass;
    }
}
