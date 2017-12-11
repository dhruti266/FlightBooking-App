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
public class Dhruti_Passenger {
    
    private String firstName;
    private String lastName;
    private String age;
    
    public Dhruti_Passenger(String fn, String ln, String age) {
        this.firstName = fn;
        this.lastName = ln;
        this.age = age;   
    }
    
    public String GetFirstName(){
        return firstName;
    }
    
    public String GetLastName(){
        return lastName;
    }
    public String GetAge(){
        return age;
    }
}
