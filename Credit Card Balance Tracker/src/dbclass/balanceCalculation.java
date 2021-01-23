//Jiayu Wu
//10/24/18
//This class calculates the balance left
package dbclass;


public class balanceCalculation {
    //method
    public int balanceCalculation(int balance, int overdraft)
    {
        int result;
          
        result = balance-overdraft; 
                
        return result;
    
    }
    
}
