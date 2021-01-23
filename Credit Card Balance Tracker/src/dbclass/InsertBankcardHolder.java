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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author wujiayu
 */
public class InsertBankcardHolder extends JFrame implements ActionListener{
    
    //declare
    private JLabel bankCardIDLabel;
    private JLabel cardHolderIDLabel;
    private JTextField bankCardIDTextField;
    private JTextField cardHolderIDTextField;
    private JPanel IDPanel;
    private JPanel commandPanel;
    private JButton doneButton;
    private JButton exitButton;
    private bankCardHolderTbInvisible gFrame;
    
    private final String[] COLUMN_TITLES = {"cardHolderID","bankCardID"};
    private Object[][] bankCardHolderData;
    private JTable bankCardHolderTable;
    private JScrollPane bankCardHolderScrollPanel;
    
    //construction
    public InsertBankcardHolder(bankCardHolderTbInvisible pFrame){
    super();
    this.setBounds(400,100,400,500);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    gFrame = pFrame;
    
    bankCardIDLabel = new JLabel("BankCardID");
    cardHolderIDLabel = new JLabel("CardHolderID");
    
    bankCardIDTextField = new JTextField(5);
    cardHolderIDTextField = new JTextField(5);
    
    IDPanel = new JPanel(new FlowLayout());
    IDPanel.add(bankCardIDLabel);
    IDPanel.add(bankCardIDTextField);
    IDPanel.add(cardHolderIDLabel);
    IDPanel.add(cardHolderIDTextField);
    
    
    
    doneButton = new JButton("Done");
    doneButton.addActionListener(this);
    
    exitButton = new JButton("Exit");
    exitButton.addActionListener(this);

    commandPanel = new JPanel(new FlowLayout());
    commandPanel.add(doneButton);
    commandPanel.add(exitButton);
    
    this.add(IDPanel,BorderLayout.CENTER);
    this.add(commandPanel,BorderLayout.SOUTH);
    
    this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        int BankCardID = 0;
        int CardHolderID = 0;
        
        if(command.equals("Done"))
        {
            try{
                String ID1String = bankCardIDTextField.getText();
                BankCardID = Integer.parseInt(ID1String);
                String ID2String = cardHolderIDTextField.getText();
                CardHolderID = Integer.parseInt(ID2String);
            }
            catch(Exception numberException)
            {
                System.out.println("Error");
            }
            Connection myDbConn;
            MySQLdbAccess objMySQLdbAccess = new MySQLdbAccess("Bank");
            myDbConn = objMySQLdbAccess.getDbConn();
            
            String dbQuery1 = "INSERT INTO bankCardHolderTbInvisible VALUES " +
                          "(?,?) ";
            try{
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery1);
            ps.setInt(1, CardHolderID); 
            ps.setInt(2,BankCardID);
            ps.executeUpdate();
            
    }
            
        catch(Exception e1){
            System.out.println("don't insert");
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
