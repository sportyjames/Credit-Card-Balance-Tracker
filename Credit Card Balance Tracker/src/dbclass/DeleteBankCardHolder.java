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
public class DeleteBankCardHolder extends JFrame implements ActionListener{
    //declare
    private JLabel cardHolderID ;
    private JTextField cardHolderIDTextfield;
    private JButton deleteButton;
    private JButton exitButton;
    private bankCardHolderTbInvisible gFrame;
    
    private final String[] COLUMN_TITLES = {"cardHolderID","bankCardID"};
    private Object[][] bankCardHolderData;
    private JTable bankCardHolderTable;
    private JScrollPane bankCardHolderScrollPanel;
    
    //construction
    public DeleteBankCardHolder(bankCardHolderTbInvisible pFrame)
    {
       super();
       this.setBounds(300,200,300,150);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       this.setLayout(new FlowLayout());
       
       gFrame = pFrame;
       
       cardHolderID = new JLabel("CardHolderID");
       
       deleteButton = new JButton("Delete");
       deleteButton.addActionListener(this);
       
       exitButton = new JButton("Exit");
       exitButton.addActionListener(this);
       
       cardHolderIDTextfield = new JTextField(10);
       
       this.add(cardHolderID);
       this.add(cardHolderIDTextfield);
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
                String IDString = cardHolderIDTextfield.getText();
                IDNumber = Integer.parseInt(IDString);
            }
            catch(Exception numberException)
            {
               System.out.println("Error"); 
            }
            
            Connection myDbConn;
            MySQLdbAccess objMySQLdbAccess = new MySQLdbAccess("Bank");
            myDbConn = objMySQLdbAccess.getDbConn();  
            
            String dbQuery1 = "DELETE FROM bankCardHolderTbInvisible " + " WHERE cardHolderID = ? ";
            
            try{
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1, IDNumber); 
            ps.executeUpdate();
            }
            
            catch(Exception e1){
            System.out.println("don't Delete");
            }
            
            gFrame.getContentPane().removeAll();
            bankCardHolderData = objMySQLdbAccess.getData("bankCardHolderTbInvisible",COLUMN_TITLES);
            bankCardHolderTable = new JTable(bankCardHolderData,COLUMN_TITLES);
            bankCardHolderScrollPanel = new JScrollPane();
            bankCardHolderScrollPanel.getViewport().add(bankCardHolderTable);
            bankCardHolderTable.setFillsViewportHeight(true);
            gFrame.add(bankCardHolderScrollPanel,BorderLayout.CENTER);
            gFrame.revalidate();
            gFrame.repaint();
            
            
        }
        
        else if(command.equals("Exit"))
        {
            this.dispose();
        }  
    }
    
    
}
