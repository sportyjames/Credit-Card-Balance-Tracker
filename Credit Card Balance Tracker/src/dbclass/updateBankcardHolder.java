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
public class updateBankcardHolder extends JFrame implements ActionListener {
    
    //declare
    private JLabel primaryKeyLabel;
    private JLabel foreignKeyLabel;
    private JLabel newDataLabel;
    private JTextField primaryKeyTextField;
    private JTextField foreignKeyTextField;
    private JButton doneButton;
    private JButton exitButton;
    private JPanel inputPanel;
    private JPanel commandPanel;
    private bankCardHolderTbInvisible gFrame;

    private final String[] COLUMN_TITLES = {"cardHolderID","bankCardID"};
    private Object[][] bankCardHolderData;
    private JTable bankCardHolderTable;
    private JScrollPane bankCardHolderScrollPanel;
    
    //construction
    public updateBankcardHolder(bankCardHolderTbInvisible pFrame){
    super();
    this.setBounds(800,100,400,500);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setLayout(new BorderLayout());
    
    gFrame = pFrame;
    
    doneButton = new JButton("Done");
    doneButton.addActionListener(this);
    
    exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    
    primaryKeyLabel = new JLabel("cardHolder ID Number");
    foreignKeyLabel = new JLabel("Change my bank ID to");
    
    primaryKeyTextField = new JTextField(5);
    foreignKeyTextField = new JTextField(5);
    
    
    inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(primaryKeyLabel);
    inputPanel.add(primaryKeyTextField);
    inputPanel.add(foreignKeyLabel);
    inputPanel.add(foreignKeyTextField);
    
    
    commandPanel = new JPanel(new FlowLayout());
    commandPanel.add(doneButton);
    commandPanel.add(exitButton);
    
    this.add(inputPanel,BorderLayout.CENTER);
    this.add(commandPanel,BorderLayout.SOUTH);
    
    this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        
        int cardHolderID = 0;
        int bankCardID = 0;
        
        String command = e.getActionCommand();
        if(command.equals("Done"))
        {
            try{
                String cardHolderIDString = primaryKeyTextField.getText();
                cardHolderID = Integer.parseInt(cardHolderIDString);
                String bankCardIDString = foreignKeyTextField.getText();
                bankCardID = Integer.parseInt(bankCardIDString);
            }
            catch(Exception numberException)
            {
               System.out.println("Error"); 
            }
            Connection myDbConn;
            MySQLdbAccess objMySQLdbAccess = new MySQLdbAccess("Bank");
            myDbConn = objMySQLdbAccess.getDbConn();
            
            String dbQuery1 = "UPDATE bankCardHolderTbInvisible SET bankCardID = ? WHERE cardHolderID = ? ";
            
            try{
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1,bankCardID); 
            ps.setInt(2,cardHolderID);
            ps.executeUpdate();
            }
            
            catch(Exception e1){
            System.out.println("don't Update");
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
    }
    
}
