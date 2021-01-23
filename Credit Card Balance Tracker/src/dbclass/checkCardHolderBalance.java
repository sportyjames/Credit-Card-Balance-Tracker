//Jiayu Wu
//10/24/18
//This class helps to check the cardholder's balance after overdraft
package dbclass;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author wujiayu
 */
public class checkCardHolderBalance extends JFrame implements ActionListener{
    
    //declare
    private JLabel bankCardID ;
    private JTextField bankCardIDTextfield;
    private JLabel CardHolderID ;
    private JTextField CardHolderIDTextfield;
    private JButton checkButton;
    private JButton exitButton;
    int cardBalance;
    int overdraftAmount;
    
    //construction
    public checkCardHolderBalance()
    {
       super();
       this.setBounds(900,200,400,350);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       this.setLayout(new FlowLayout());
       
       bankCardID = new JLabel("bankCardID");
       
       CardHolderID = new JLabel("CardHolderID");
       
       checkButton = new JButton("check");
       checkButton.addActionListener(this);
       
       exitButton = new JButton("Exit");
       exitButton.addActionListener(this);
       
       bankCardIDTextfield = new JTextField(10);
       CardHolderIDTextfield = new JTextField(10);
       
       this.add(bankCardID);
       this.add(bankCardIDTextfield);
       this.add(CardHolderID);
       this.add(CardHolderIDTextfield);
       this.add(checkButton);
       this.add(exitButton);
       
       this.setVisible(true);
}
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        
         int BankCardIDNumber = 0;
         int CardHolderIDNumber= 0;
         int balanceResult;
         
        
        if(command.equals("check"))
        {
            cardBalance = 0;
            overdraftAmount=0;
            try{
            String BankCardIDString = bankCardIDTextfield.getText();
            BankCardIDNumber = Integer.parseInt(BankCardIDString);
            String CardHolderIDString = CardHolderIDTextfield.getText();
            CardHolderIDNumber = Integer.parseInt(CardHolderIDString);
            }
            catch(Exception numberException)
            {
               System.out.println("1"); 
            }
            
            Connection myDbConn;
            MySQLdbAccess objMySQLdbAccess = new MySQLdbAccess("Bank");
            myDbConn = objMySQLdbAccess.getDbConn();
            
            String dbQuery1 = "SELECT CardBalance FROM BankCardGeneralInfoTable WHERE ID = ?";
            String dbQuery2 = "SELECT amountOfMoneyOverDraft FROM CardHolderInfoTable WHERE cardholderID = ?";
            
            
            try{
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1,BankCardIDNumber); 
            ResultSet rs = ps.executeQuery();
            
            PreparedStatement ps2 = myDbConn.prepareStatement(dbQuery2);
            ps2.setInt(1,CardHolderIDNumber);
            ResultSet rs2 = ps2.executeQuery();
            
            
            
            while (rs.next()) {
                                String CardBalance = rs.getString("CardBalance");
                                cardBalance = Integer.parseInt(CardBalance);
				
				System.out.println("CardBalance : " + CardBalance);
                          
			}
            while(rs2.next()){
            String amountOfMoneyOverDraft = rs2.getString("amountOfMoneyOverDraft");
                                overdraftAmount = Integer.parseInt(amountOfMoneyOverDraft);
                                
                                System.out.println("overdraftAmount : " + amountOfMoneyOverDraft);
        }
            balanceCalculation objcalculation = new balanceCalculation();
            balanceResult= objcalculation.balanceCalculation(cardBalance,overdraftAmount);
            String balanceresultAsString = Integer.toString(balanceResult);
            
            outputCardHolderBalance objoutputCardHolderBalance = new outputCardHolderBalance(balanceresultAsString);
        }
            catch(Exception e1){
            System.out.println("don't get data");
            System.out.println(e1);
            }
        }
        
        
        
        else if(command.equals("Exit"))
        {
            this.dispose();
        }  
    }
}
    
    
