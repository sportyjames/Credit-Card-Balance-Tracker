//Jiayu Wu
//10/24/18
//This program is used to create bank accounts and allow bank to check balance for cardholders.
//Pseudocode: 1. launch main frame 
//            2. select a step
//            3. press a function(insert,delete,update)
//            5. if data is correct
//                  output table
//               else
//                  println "Error.Enter the data correctly"
//            6. When all steps done, select balance after overdraft
//            7. enter primary key(bankID, cardholderID)
//            8. if primary key exists
//                  output balance in JLabel

package dbclass;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

/**
 *
 * @author wujiayu
 */
public class DbServiceFrame extends JFrame implements ActionListener{
    
    
    //declare
    private final java.net.URL BANK_ACCOUNT = getClass().getResource("bankAccount.jpg");
    private final ImageIcon BANK_ACCOUNT_IMAGEICON= new ImageIcon(new ImageIcon(
            BANK_ACCOUNT).getImage().getScaledInstance(
                    400,400,Image.SCALE_DEFAULT));
    
    private JRadioButton BankCardTableButton;
    private JRadioButton CardHolderTableButton;
    private JRadioButton BankcardHolderButton;
    private ButtonGroup tableGroup; 
    private JButton InsertButton;
    private JButton UpdateButton;
    private JButton DeleteButton;
    private JButton checkCardHolderBalance;
    private JButton exitButton;
    private JButton showTableButton;
    private JLabel selectFunctionLabel;
    private JLabel imageLabel;
    private JPanel commandPanel;
    private JPanel tablePanel;
    private bankCardHolderTbInvisible objIV;
    private BankCardGeneralInfoTable objGeneralInfo;
    private networkTable objNetwork;
    private CardHolderInfoTable objCardHolder;
    
    
    //construction
    public DbServiceFrame(){
    super("CreditCardBalanceTracker");
    objIV = new bankCardHolderTbInvisible();
    objIV.setBounds(100,400,300,300);
    objGeneralInfo = new BankCardGeneralInfoTable();
    objGeneralInfo.setVisible(false);
    objNetwork = new networkTable();
    objNetwork.setVisible(false);
    objCardHolder = new CardHolderInfoTable();
    objCardHolder.setVisible(false);
    
    this.setBounds(200,100,1200,500);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
   
    
    InsertButton = new JButton("Insert");
    InsertButton.addActionListener(this);
    
    UpdateButton = new JButton("Update");
    UpdateButton.addActionListener(this);
    
    DeleteButton = new JButton("Delete");
    DeleteButton.addActionListener(this);
    
    checkCardHolderBalance = new JButton("balance after overdraft(Don't click here until you finish all the steps with function performed)");
    checkCardHolderBalance.addActionListener(this);
    
    exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    
    showTableButton = new JButton("Display table");
    showTableButton.addActionListener(this);
    
    imageLabel = new JLabel(BANK_ACCOUNT_IMAGEICON,SwingConstants.CENTER);
    
    selectFunctionLabel = new JLabel("which function do you wanna perform ->");
    
    commandPanel = new JPanel(new FlowLayout());
    
    tablePanel = new JPanel(new FlowLayout());
    
    BankCardTableButton = new JRadioButton("Step 2: bankcard Info ");
    CardHolderTableButton = new JRadioButton("Step 3: money overdraft Info");
    BankcardHolderButton = new JRadioButton("Step 1: Open Account");
        
    tableGroup = new ButtonGroup();
    tableGroup.add(BankCardTableButton);
    tableGroup.add(CardHolderTableButton);
    tableGroup.add(BankcardHolderButton);
    
    commandPanel.add(selectFunctionLabel);
    commandPanel.add(InsertButton);
    commandPanel.add(UpdateButton);
    commandPanel.add(DeleteButton);
    commandPanel.add(checkCardHolderBalance);
    
    tablePanel.add(BankcardHolderButton);
    tablePanel.add(BankCardTableButton);
    tablePanel.add(CardHolderTableButton);
    
    this.add(commandPanel,BorderLayout.NORTH);
    this.add(imageLabel,BorderLayout.CENTER);
    this.add(tablePanel,BorderLayout.SOUTH);
    
    this.setVisible(true);
    
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if(command.equals("Insert"))
        {
            if(BankCardTableButton.isSelected())
            {
                InsertBankCardInfo objInsertBankCardInfo = new InsertBankCardInfo(objGeneralInfo,objNetwork);
            }
            else if(CardHolderTableButton.isSelected())
            {
                InsertCardHolderInfo objInsertCardHolderInfo = new InsertCardHolderInfo(objCardHolder);  
            }
            else if(BankcardHolderButton.isSelected())
            {
                InsertBankcardHolder objInsertBankcardHolder  = new InsertBankcardHolder(objIV);
            }
        }
        else if(command.equals("Delete"))
        {
           if(BankCardTableButton.isSelected())
           {
               DeleteBankCardInfo objDeleteInfo = new DeleteBankCardInfo(objGeneralInfo);
           }
           else if(CardHolderTableButton.isSelected())
           {
               DeleteCardHolderInfo objDeleteCardHolderInfo = new DeleteCardHolderInfo(objCardHolder);
           }
           else if(BankcardHolderButton.isSelected())
           {
              DeleteBankCardHolder objDeleteBankcardHolder  = new DeleteBankCardHolder(objIV);
           }
        }
        else if(command.equals("Update"))
         {
           if(BankCardTableButton.isSelected())
           {
              updateBankCard objupdateBankCard = new updateBankCard(objGeneralInfo,objNetwork);
           }
           else if(CardHolderTableButton.isSelected())
           {
              updateCardHolder objupdateCardHolder  = new updateCardHolder(objCardHolder);
           }
           else if(BankcardHolderButton.isSelected())
           {
              updateBankcardHolder objupdateBankcardHolder  = new updateBankcardHolder(objIV);
           }
         }
        else if(command.equals("balance after overdraft(Don't click here until you finish all the steps with function performed)"))
        {
            checkCardHolderBalance objBalance = new checkCardHolderBalance();
        }
    }
        
        
    
    public static void main(String args[])
    {
        DbServiceFrame objDbServiceFrame = new DbServiceFrame();
        
    }
    
    
}
