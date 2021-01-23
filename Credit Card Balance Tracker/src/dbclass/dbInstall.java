//Jiayu Wu
//10/24/18
//This class install database and create tables
package dbclass;

/**
 *
 * @author wujiayu
 */
public class dbInstall {
    public static void main(String[] args)
    {
        String generalInfoTable;
        String networkTable;
        String cardHolderInfoTable;
        String bankCardHolderTbInvisible;
        MySQLdbAccess objMySQLdb = new MySQLdbAccess();
        objMySQLdb.createDb("Bank");
        
        
        
        generalInfoTable = "CREATE TABLE BankCardGeneralInfoTable ( " +
                   "ID int," +
                   "CardBalance int," +
                   "IssuerName varchar(30),"+
                   "CardNetworkLevel varchar(30)" +
                   ");";
        objMySQLdb.createTable(generalInfoTable,"Bank");
        
        System.out.println(generalInfoTable);
         
        networkTable = "CREATE TABLE networkTable ( " +
                   "CardNetworkLevel varchar(30), " +
                   "CardNetwork varchar(30) " +
                   ");";
        objMySQLdb.createTable(networkTable,"Bank");
        
        System.out.println(networkTable);
        
        cardHolderInfoTable = "CREATE TABLE CardHolderInfoTable ( " +
                   "cardholderID int, " +
                   "cardholderName varchar(30), " +
                   "cardholderJob varchar(30), " +
                   "amountOfMoneyOverDraft int, "+
                   "amountOfDaysDelayPayment int "+
                   ");";
        objMySQLdb.createTable(cardHolderInfoTable,"Bank");
        
        System.out.println(cardHolderInfoTable);
      
        
        bankCardHolderTbInvisible = "CREATE TABLE bankCardHolderTbInvisible ( " +
                   "cardHolderID int, " +
                   "bankCardID int " +
                   ");";
        objMySQLdb.createTable(bankCardHolderTbInvisible,"Bank");
        
        System.out.println(bankCardHolderTbInvisible);
                
              
    }
    
}
