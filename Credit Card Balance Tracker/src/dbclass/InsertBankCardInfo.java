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
public class InsertBankCardInfo extends JFrame implements ActionListener{
    
    //declare
    private JLabel IDLabel;
    private JLabel IssuerNameLabel;
    private JLabel CardNetworkLevelLabel;
    private JLabel CardNetworkLabel;
    private JLabel CardInterestRateLabel;
    private JTextField IDTextField;
    private JTextField IssuerNameTextField;
    private JTextField CardNetworkLevelTextField;
    private JTextField CardNetworkTextField;
    private JTextField CardInterestRateTextField;
    private JPanel IDPanel;
    private JPanel IssuerNamePanel;
    private JPanel CardNetworkLevelPanel;
    private JPanel CardNetworkPanel;
    private JPanel CardInterestRatePanel;
    private JButton doneButton;
    private JButton exitButton;
    private BankCardGeneralInfoTable gGeneralInfo;
    private networkTable gNetwork;
    
    
    private final String[] COLUMN_TITLES = {"ID","CardBalance", "IssuerName", "CardNetworkLevel"};
    private Object[][] bankCardData;
    private JTable bankCardTable;
    private JScrollPane bankCardScrollPanel;
    
    private final String[] COLUMN_TITLES2 = {"CardNetworkLevel","CardNetwork"};
    private Object[][] networkData;
    private JTable networkTable;
    private JScrollPane networkScrollPanel;
    
    //construction 
    public InsertBankCardInfo(BankCardGeneralInfoTable pGeneralInfo,networkTable pNetwork){
    super();
    this.setBounds(400,100,400,500);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setLayout(new FlowLayout());
    
    gGeneralInfo = pGeneralInfo;
    gNetwork = pNetwork;
    
    doneButton = new JButton("Done");
    doneButton.addActionListener(this);
    
    exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    
    IDLabel = new JLabel("Card ID");
    IssuerNameLabel = new JLabel("Issuer Name");
    CardNetworkLevelLabel = new JLabel("Card Network Level");
    CardNetworkLabel = new JLabel("Card Network");
    CardInterestRateLabel = new JLabel("Card Balance");
    
    IDTextField = new JTextField(5);
    IssuerNameTextField = new JTextField(5);
    CardNetworkLevelTextField = new JTextField(5);
    CardNetworkTextField = new JTextField(5);
    CardInterestRateTextField = new JTextField(5);
    
    IDPanel = new JPanel(new FlowLayout());
    IDPanel.add(IDLabel);
    IDPanel.add(IDTextField);
   
    IssuerNamePanel = new JPanel(new FlowLayout());
    IssuerNamePanel.add(IssuerNameLabel);
    IssuerNamePanel.add(IssuerNameTextField);
   
    CardNetworkLevelPanel = new JPanel(new FlowLayout());
    CardNetworkLevelPanel.add(CardNetworkLevelLabel);
    CardNetworkLevelPanel.add(CardNetworkLevelTextField);
   
    CardNetworkPanel = new JPanel(new FlowLayout());
    CardNetworkPanel.add(CardNetworkLabel);
    CardNetworkPanel.add(CardNetworkTextField);
   
    CardInterestRatePanel = new JPanel(new FlowLayout());
    CardInterestRatePanel.add(CardInterestRateLabel);
    CardInterestRatePanel.add(CardInterestRateTextField);
   
    this.add(IDPanel,BorderLayout.NORTH);
    this.add(IssuerNamePanel);
    this.add(CardNetworkLevelPanel);
    this.add(CardNetworkPanel);
    this.add(CardInterestRatePanel);
    this.add(doneButton);
    this.add(exitButton);
    
    this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        int IDnumber = 0;
        int CardBalance = 0;
        String IssuerNameString = null;
        String CardNetworkLevelString = null;
        String CardNetworkString = null;
        String CardInterestRateString = null;
        if(command.equals("Done"))
        {
            try{
                String IDString = IDTextField.getText();
                IDnumber = Integer.parseInt(IDString);
                IssuerNameString = IssuerNameTextField.getText();
                CardNetworkLevelString = CardNetworkLevelTextField.getText();
                CardNetworkString = CardNetworkTextField.getText();
                CardInterestRateString = CardInterestRateTextField.getText();  
                CardBalance = Integer.parseInt(CardInterestRateString);
            }
            catch(Exception numberException)
            {
                System.out.println("Error");
            }
            Connection myDbConn;
            MySQLdbAccess objMySQLdbAccess = new MySQLdbAccess("Bank");
            myDbConn = objMySQLdbAccess.getDbConn();
            
            
          String dbQuery1 = "INSERT INTO BankCardGeneralInfoTable VALUES " +
                          "(?,?,?,?) ";
        try{
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1, IDnumber); 
            ps.setInt(2,CardBalance);
            ps.setString(3,IssuerNameString);
            ps.setString(4,CardNetworkLevelString);
            ps.executeUpdate();
            
    }
        catch(Exception e1){
            System.out.println("do not insert");
        }
        
        gGeneralInfo.getContentPane().removeAll();
        bankCardData = objMySQLdbAccess.getData("BankCardGeneralInfoTable",COLUMN_TITLES);
        bankCardTable = new JTable(bankCardData,COLUMN_TITLES);
        bankCardScrollPanel = new JScrollPane();
        bankCardScrollPanel.getViewport().add(bankCardTable);
        bankCardTable.setFillsViewportHeight(true);
        gGeneralInfo.add(bankCardScrollPanel,BorderLayout.CENTER);
        gGeneralInfo.revalidate();
        gGeneralInfo.repaint();
        gGeneralInfo.setVisible(true);
        

        String dbQuery2 = "INSERT INTO networkTable VALUES " +
                          "(?,?) ";
        try{
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery2);
            ps.setString(1,CardNetworkLevelString);
            ps.setString(2,CardNetworkString);
            ps.executeUpdate();
        }
        catch(Exception e2){
             System.out.println("don't insert");
        }
        
        gNetwork.getContentPane().removeAll();
        networkData = objMySQLdbAccess.getData("networkTable",COLUMN_TITLES2);
        networkTable = new JTable(networkData,COLUMN_TITLES2);
        networkScrollPanel = new JScrollPane();
        networkScrollPanel.getViewport().add(networkTable);
        networkTable.setFillsViewportHeight(true);
        gNetwork.add(networkScrollPanel,BorderLayout.CENTER);
        gNetwork.revalidate();
        gNetwork.repaint();
        gNetwork.setVisible(true);
        
        
       
        
        }
        else if(command.equals("Exit"))
        {
            this.dispose();
    }
    }
    
}
