//Jiayu Wu
//10/24/18
//This frame Insert information into database
package dbclass;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author wujiayu
 */
public class InsertCardHolderInfo extends JFrame implements ActionListener {
    //declare
    private JLabel CardholderIDLabel;
    private JLabel NameLabel;
    private JLabel CardholderJobLabel;
    private JLabel MoneyAmountLabel;
    private JLabel DelayDaysLabel;
    private JLabel doneLabel;
    private JTextField CardholderIDTextField;
    private JTextField NameTextField;
    private JTextField CardholderJobTextField;
    private JTextField MoneyAmountTextField;
    private JTextField DelayDaysTextField;
    private JPanel CardholderIDPanel;
    private JPanel NamePanel;
    private JPanel CardholderJobPanel;
    private JPanel MoneyAmountPanel;
    private JPanel DelayDaysPanel;
    private JButton insertButton;
    private JButton doneButton;
    private JButton exitButton;
    private CardHolderInfoTable gCardHolderInfo;
    
    private final String[] COLUMN_TITLES = {"cardholderID", "cardholderName", "cardholderJob", "amountOfMoneyOverDraft","amountOfDaysDelayPayment"};
    private Object[][] CardHolderData;
    private JTable CardHolderTable;
    private JScrollPane CardHolderScrollPanel;
    private MySQLdbAccess dbObj;
   
    //construction 
    public InsertCardHolderInfo(CardHolderInfoTable pFrame){
    super();
    this.setBounds(400,100,400,500);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setLayout(new FlowLayout());
    
    gCardHolderInfo=pFrame;;
    
    doneButton = new JButton("Done");
    doneButton.addActionListener(this);
    
    exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    
    
    CardholderIDLabel = new JLabel("Card ID");
    NameLabel = new JLabel("Cardholder Name");
    CardholderJobLabel = new JLabel("Cardholder Job");
    MoneyAmountLabel = new JLabel("Amount Of Money Overdraft");
    DelayDaysLabel = new JLabel("Amount Of Days Delay");
    doneLabel= new JLabel("Done");
    
    CardholderIDTextField = new JTextField(5);
    NameTextField = new JTextField(5);
    CardholderJobTextField = new JTextField(5);
    MoneyAmountTextField = new JTextField(5);
    DelayDaysTextField = new JTextField(5);
    
    CardholderIDPanel = new JPanel(new FlowLayout());
    CardholderIDPanel.add(CardholderIDLabel);
    CardholderIDPanel.add(CardholderIDTextField);
   
    NamePanel = new JPanel(new FlowLayout());
    NamePanel.add(NameLabel);
    NamePanel.add(NameTextField);
   
    CardholderJobPanel = new JPanel(new FlowLayout());
    CardholderJobPanel.add(CardholderJobLabel);
    CardholderJobPanel.add(CardholderJobTextField);
   
    MoneyAmountPanel = new JPanel(new FlowLayout());
    MoneyAmountPanel.add(MoneyAmountLabel);
    MoneyAmountPanel.add(MoneyAmountTextField);
    
    DelayDaysPanel = new JPanel(new FlowLayout());
    DelayDaysPanel.add(DelayDaysLabel);
    DelayDaysPanel.add(DelayDaysTextField);
   
    this.add(CardholderIDPanel);
    this.add(NamePanel);
    this.add(CardholderJobPanel);
    this.add(MoneyAmountPanel);
    this.add(DelayDaysPanel);
    this.add(doneButton);
    this.add(exitButton);
    
    
    this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        int CardholderIDnumber = 0;
        String NameString = null;
        String CardholderJobString = null;
        int MoneyAmount= 0;
        int DelayDays= 0;  
        if(command.equals("Done"))
        {
            try{
                String IDString = CardholderIDTextField.getText();
                CardholderIDnumber = Integer.parseInt(IDString);
                NameString = NameTextField.getText();
                CardholderJobString = CardholderJobTextField.getText();
                String MoneyString = MoneyAmountTextField.getText();
                MoneyAmount = Integer.parseInt(MoneyString);
                String DelayDaysString = DelayDaysTextField.getText();
                DelayDays = Integer.parseInt(DelayDaysString); 
            }
            catch(Exception numberException)
            {
                System.out.println("Error");
            }

            Connection myDbConn;
            MySQLdbAccess objMySQLdbAccess = new MySQLdbAccess("Bank");
            myDbConn = objMySQLdbAccess.getDbConn();
            
          String dbQuery1 = "INSERT INTO CardHolderInfoTable VALUES " +
                          "(?,?,?,?,?) ";
        try{
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1, CardholderIDnumber); 
            ps.setString(2,NameString);
            ps.setString(3,CardholderJobString);
            ps.setInt(4, MoneyAmount); 
            ps.setInt(5, DelayDays); 
            ps.executeUpdate();
            
    }
        catch(Exception e2){
            System.out.println("Don't insert");
        }
        
        gCardHolderInfo.getContentPane().removeAll();
        CardHolderData = objMySQLdbAccess.getData("CardHolderInfoTable",COLUMN_TITLES);
        CardHolderTable = new JTable(CardHolderData,COLUMN_TITLES);
        CardHolderScrollPanel = new JScrollPane();
        CardHolderScrollPanel.getViewport().add(CardHolderTable);
        CardHolderTable.setFillsViewportHeight(true);
        gCardHolderInfo.add(CardHolderScrollPanel,BorderLayout.CENTER);
        gCardHolderInfo.revalidate();
        gCardHolderInfo.repaint();
        gCardHolderInfo.setVisible(true);
        
        }
        else if(command.equals("Exit"))
        {
          this.dispose();  
        }
        
        }
                    
        
    }


