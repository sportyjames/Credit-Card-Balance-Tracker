//Jiayu Wu
//10/24/18
//This frame delete the record from database
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author wujiayu
 */
public class DeleteBankCardInfo extends JFrame implements ActionListener{
    //declare
    private JLabel bankCardID ;
    private JTextField bankCardIDTextfield;
    private JButton deleteButton;
    private JButton exitButton;
    private BankCardGeneralInfoTable gFrame;
    
    private final String[] COLUMN_TITLES = {"ID","CardBalance", "IssuerName", "CardNetworkLevel"};
    private Object[][] bankCardData;
    private JTable bankCardTable;
    private JScrollPane bankCardScrollPanel;
    
    //construction
    public DeleteBankCardInfo(BankCardGeneralInfoTable pGeneralInfo)
    {
       super();
       this.setBounds(300,200,300,150);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       this.setLayout(new FlowLayout());
       
       gFrame = pGeneralInfo;
       
       bankCardID = new JLabel("BankCardID");
       
       deleteButton = new JButton("Delete");
       deleteButton.addActionListener(this);
       
       exitButton = new JButton("Exit");
       exitButton.addActionListener(this);
       
       bankCardIDTextfield = new JTextField(10);
       
       this.add(bankCardID);
       this.add(bankCardIDTextfield);
       this.add(deleteButton);
       
       this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        int IDNumber = 0;
        if(command.equals("Delete"))
        {
            try{
                String IDString = bankCardIDTextfield.getText();
                IDNumber = Integer.parseInt(IDString);
            }
            catch(Exception numberException)
            {
               System.out.println("Error"); 
            }
            
            Connection myDbConn;
            MySQLdbAccess objMySQLdbAccess = new MySQLdbAccess("Bank");
            myDbConn = objMySQLdbAccess.getDbConn();  
            
            String dbQuery1 = "DELETE FROM BankCardGeneralInfoTable " + " WHERE ID = ? ";
            
            try{
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1, IDNumber); 
            ps.executeUpdate();
            }
            
            catch(Exception e1){
            System.out.println("don't Delete");
            }
            
            gFrame.getContentPane().removeAll();
            bankCardData = objMySQLdbAccess.getData("BankCardGeneralInfoTable",COLUMN_TITLES);
            bankCardTable = new JTable(bankCardData,COLUMN_TITLES);
            bankCardScrollPanel = new JScrollPane();
            bankCardScrollPanel.getViewport().add(bankCardTable);
            bankCardTable.setFillsViewportHeight(true);
            gFrame.add(bankCardScrollPanel,BorderLayout.CENTER);
            gFrame.revalidate();
            gFrame.repaint();
        }
        
        else if(command.equals("Exit"))
        {
            this.dispose();
        }  
    }
    
    
}
