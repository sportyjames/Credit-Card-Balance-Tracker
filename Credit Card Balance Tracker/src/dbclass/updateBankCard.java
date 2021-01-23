//Jiayu Wu
//10/24/18
//This frame updates the record in database
package dbclass;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author wujiayu
 */
public class updateBankCard extends JFrame implements ActionListener{
    //declare
    private JLabel primaryKeyLabel;
    private JLabel nonKeyLabel;
    private JLabel newDataLabel;
    private JTextField primaryKeyTextField;
    private JTextField newDataTextField;
    private JButton doneButton;
    private JButton exitButton;
    private JRadioButton IssuerNameButton;
    private JRadioButton CardBalanceButton;
    private ButtonGroup colorGroup; 
    private JPanel inputPanel;
    private JPanel radioButtonPanel;
    private JPanel commandPanel;
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
    public updateBankCard(BankCardGeneralInfoTable pGeneralInfo,networkTable pNetwork){
    super();
    this.setBounds(800,100,400,500);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setLayout(new BorderLayout());
    
    gGeneralInfo = pGeneralInfo;
    gNetwork = pNetwork;
    
    doneButton = new JButton("Done");
    doneButton.addActionListener(this);
    
    exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    
    primaryKeyLabel = new JLabel("Bank ID Number");
    nonKeyLabel = new JLabel("Item to change");
    newDataLabel = new JLabel("New Data");
    
    primaryKeyTextField = new JTextField(5);
    newDataTextField = new JTextField(5);
    
    IssuerNameButton = new JRadioButton("Issuer Name");
    CardBalanceButton = new JRadioButton("Card Balance");
    colorGroup = new ButtonGroup();
    colorGroup.add(IssuerNameButton);
    colorGroup.add(CardBalanceButton);
    
    inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(primaryKeyLabel);
    inputPanel.add(primaryKeyTextField);
    inputPanel.add(newDataLabel);
    inputPanel.add(newDataTextField);
    
    radioButtonPanel= new JPanel(new FlowLayout());
    radioButtonPanel.add(nonKeyLabel);
    radioButtonPanel.add(IssuerNameButton);
    radioButtonPanel.add(CardBalanceButton);
    
    commandPanel = new JPanel(new FlowLayout());
    commandPanel.add(doneButton);
    commandPanel.add(exitButton);
    
    this.add(inputPanel,BorderLayout.NORTH);
    this.add(radioButtonPanel,BorderLayout.CENTER);
    this.add(commandPanel,BorderLayout.SOUTH);
    
    this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        
        int IDNumber = 0;
        int newBalance = 0;
        String newDataString = null;
        
        
        String command = e.getActionCommand();
        if(command.equals("Done"))
        {
            try{
                String IDString = primaryKeyTextField.getText();
                IDNumber = Integer.parseInt(IDString);
                newDataString = newDataTextField.getText();
                newBalance = Integer.parseInt(newDataString);
            }
            catch(Exception numberException)
            {
               System.out.println("Error"); 
            }
            Connection myDbConn;
            MySQLdbAccess objMySQLdbAccess = new MySQLdbAccess("Bank");
            myDbConn = objMySQLdbAccess.getDbConn();
            
            if(IssuerNameButton.isSelected())
            {
             String dbQuery1 = "UPDATE BankCardGeneralInfoTable SET IssuerName = ? WHERE ID = ? ";
            
            try{
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setString(1, newDataString); 
            ps.setInt(2,IDNumber);
            ps.executeUpdate();
            }
            
            catch(Exception e1){
            System.out.println("don't Update");
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
            
            

            }
            else if(CardBalanceButton.isSelected())
            {
             String dbQuery3 = "UPDATE BankCardGeneralInfoTable SET CardBalance = ? WHERE ID = ? ";
             
            try{
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery3);
            ps.setInt(1, newBalance); 
            ps.setInt(2,IDNumber);
            ps.executeUpdate();
            }
            
            catch(Exception e2){
            System.out.println("don't Update!");
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
            

            }
        }
        else if(command.equals("Exit"))
        {
            this.dispose();
        }
        

    }
}
