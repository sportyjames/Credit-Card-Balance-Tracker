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
public class updateCardHolder extends JFrame implements ActionListener{
    
    private JLabel primaryKeyLabel;
    private JLabel nonKeyLabel;
    private JLabel newDataLabel;
    private JTextField primaryKeyTextField;
    private JTextField newDataTextField;
    private JButton doneButton;
    private JButton exitButton;
    private JRadioButton OverdraftButton;
    private JRadioButton DelayButton;
    private ButtonGroup colorGroup; 
    private JPanel inputPanel;
    private JPanel radioButtonPanel;
    private JPanel commandPanel;
    private CardHolderInfoTable gCardHolderInfo;
    
    private final String[] COLUMN_TITLES = {"cardholderID", "cardholderName", "cardholderJob", "amountOfMoneyOverDraft","amountOfDaysDelayPayment"};
    private Object[][] CardHolderData;
    private JTable CardHolderTable;
    private JScrollPane CardHolderScrollPanel;
    private MySQLdbAccess dbObj;
    
    
    
    
    public updateCardHolder(CardHolderInfoTable pCardHolderInfo){
    super("updateCardHolder");
    this.setBounds(800,100,400,500);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setLayout(new BorderLayout());
    
    gCardHolderInfo = pCardHolderInfo;
    
    doneButton = new JButton("Done");
    doneButton.addActionListener(this);
    
    exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    
    primaryKeyLabel = new JLabel("CardHolder ID Number");
    nonKeyLabel = new JLabel("Item to change");
    newDataLabel = new JLabel("New Data");
    
    primaryKeyTextField = new JTextField(5);
    newDataTextField = new JTextField(5);
    
    OverdraftButton = new JRadioButton("amount of money overdraft");
    DelayButton = new JRadioButton("amount of days delay");
    colorGroup = new ButtonGroup();
    colorGroup.add(OverdraftButton);
    colorGroup.add(DelayButton);
    
    inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(primaryKeyLabel);
    inputPanel.add(primaryKeyTextField);
    inputPanel.add(newDataLabel);
    inputPanel.add(newDataTextField);
    
    radioButtonPanel= new JPanel(new FlowLayout());
    radioButtonPanel.add(nonKeyLabel); 
    radioButtonPanel.add(OverdraftButton);
    radioButtonPanel.add(DelayButton);
    
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
        String newDataString = null;
        
        String command = e.getActionCommand();
        if(command.equals("Done"))
        {
            try{
                String IDString = primaryKeyTextField.getText();
                IDNumber = Integer.parseInt(IDString);
                newDataString = newDataTextField.getText();
            }
            catch(Exception numberException)
            {
               System.out.println("Error"); 
            }
            Connection myDbConn;
            MySQLdbAccess objMySQLdbAccess = new MySQLdbAccess("Bank");
            myDbConn = objMySQLdbAccess.getDbConn();
            
            if(OverdraftButton.isSelected())
            {
             String dbQuery1 = "UPDATE CardHolderInfoTable SET amountOfMoneyOverDraft = ? WHERE cardholderID = ? ";
            
            try{
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setString(1, newDataString); 
            ps.setInt(2,IDNumber);
            ps.executeUpdate();
            }
            
            catch(Exception e1){
            System.out.println("don't Update");
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
            else if(DelayButton.isSelected())
            {
             String dbQuery2 = "UPDATE CardHolderInfoTable SET amountOfDaysDelayPayment = ? WHERE cardholderID = ? ";
             
            try{
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery2);
            ps.setString(1, newDataString); 
            ps.setInt(2,IDNumber);
            ps.executeUpdate();
            }
            
            catch(Exception e2){
            System.out.println("don't Update");
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
        }
        else if(command.equals("Exit"))
        {
            this.dispose();
        }
        

    }
}