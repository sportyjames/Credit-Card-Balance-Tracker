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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author wujiayu
 */
    
    public class DeleteCardHolderInfo extends JFrame implements ActionListener{
    //declare
    private JLabel CardHolderID ;
    private JTextField CardHolderIDTextfield;
    private JButton deleteButton;
    private JButton exitButton;
    private CardHolderInfoTable gCardHolderInfo;
    
    private final String[] COLUMN_TITLES = {"cardholderID", "cardholderName", "cardholderJob", "amountOfMoneyOverDraft","amountOfDaysDelayPayment"};
    private Object[][] CardHolderData;
    private JTable CardHolderTable;
    private JScrollPane CardHolderScrollPanel;
    private MySQLdbAccess dbObj;
    
    //construction
    public DeleteCardHolderInfo(CardHolderInfoTable pCardHolderInfo)
    {
       super();
       this.setBounds(300,200,300,150);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       this.setLayout(new FlowLayout());
       
       gCardHolderInfo = pCardHolderInfo;
       
       CardHolderID = new JLabel("CardHolderID");
       
       deleteButton = new JButton("Delete");
       deleteButton.addActionListener(this);
       
       exitButton = new JButton("Exit");
       exitButton.addActionListener(this);
       
       CardHolderIDTextfield = new JTextField(10);
       
       this.add(CardHolderID);
       this.add(CardHolderIDTextfield);
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
                String IDString = CardHolderIDTextfield.getText();
                IDNumber = Integer.parseInt(IDString);
            }
            catch(Exception numberException)
            {
               System.out.println("Error"); 
            }
            
            Connection myDbConn;
            MySQLdbAccess objMySQLdbAccess = new MySQLdbAccess("Bank");
            myDbConn = objMySQLdbAccess.getDbConn();  
            
            String dbQuery1 = "DELETE FROM CardHolderInfoTable " + " WHERE cardholderID = ? ";
            
            try{
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1, IDNumber); 
            ps.executeUpdate();
            }
            
            catch(Exception e1){
            System.out.println(e1);
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

    

