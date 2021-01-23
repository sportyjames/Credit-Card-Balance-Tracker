//Jiayu Wu
//10/24/18
//This is CardHolderInfoTable
package dbclass;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class CardHolderInfoTable extends JFrame {
    
    //declare
    private final String[] COLUMN_TITLES = {"cardholderID", "cardholderName", "cardholderJob", "amountOfMoneyOverDraft","amountOfDaysDelayPayment"};
    private Object[][] CardHolderData;
    private JTable CardHolderTable;
    private JScrollPane CardHolderScrollPanel;
    private MySQLdbAccess dbObj;
    
        //construction
        public CardHolderInfoTable() {
        super("CardHolderInfoTable");
        this.setBounds(500, 150, 250, 250);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        dbObj = new MySQLdbAccess("Bank");
        CardHolderData = dbObj.getData("CardHolderInfoTable",COLUMN_TITLES);
        CardHolderTable = new JTable(CardHolderData,COLUMN_TITLES);
        CardHolderScrollPanel = new JScrollPane();
        CardHolderScrollPanel.getViewport().add(CardHolderTable);
        CardHolderTable.setFillsViewportHeight(true);
        this.add(CardHolderScrollPanel,BorderLayout.CENTER);
        this.setVisible(true);
        
        
    }
}
