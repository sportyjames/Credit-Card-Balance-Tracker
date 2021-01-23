//Jiayu Wu
//10/24/18
//This is generalinfoTable
package dbclass;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class BankCardGeneralInfoTable extends JFrame {

    //declare
    private final String[] COLUMN_TITLES = {"ID","CardBalance","IssuerName", "CardNetworkLevel"};
    private Object[][] bankCardData;
    private JTable bankCardTable;
    private JScrollPane bankCardScrollPanel;
    private MySQLdbAccess dbObj;
    
    //construction
    public BankCardGeneralInfoTable() {
        super("BankCardGeneralInfoTable");
        this.setBounds(500, 150, 250, 250);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        dbObj = new MySQLdbAccess("Bank");
        bankCardData = dbObj.getData("BankCardGeneralInfoTable",COLUMN_TITLES);
        bankCardTable = new JTable(bankCardData,COLUMN_TITLES);
        bankCardScrollPanel = new JScrollPane();
        bankCardScrollPanel.getViewport().add(bankCardTable);
        bankCardTable.setFillsViewportHeight(true);
        this.add(bankCardScrollPanel,BorderLayout.CENTER);
        this.setVisible(true);
        
        
    }
    
}
