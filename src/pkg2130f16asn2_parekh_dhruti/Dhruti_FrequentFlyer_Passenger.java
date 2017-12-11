/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2130f16asn2_parekh_dhruti;

import java.util.Random;

/* Course Code : COMP2130
   Student ID  : 101039706
   Dhruti Parekh
*/
public class Dhruti_FrequentFlyer_Passenger extends Dhruti_Passenger{
    
    private int miles;
    private String freqFlyerNumber;
    
    public Dhruti_FrequentFlyer_Passenger(String fn, String ln, String age, String ffNum)
    {   
        super(fn, ln, age);
        this.freqFlyerNumber = ffNum;
        Random rand = new Random();
        this.miles = rand.nextInt(20000) + 1;
    }
    
    public String GetFreqFlyerNum(){
        return freqFlyerNumber;
    }
    
    
    public int GetMiles(){
        return miles;
    }
}
