//Jiayu Wu
//10/24/18
//This class displays the bank account stats
package dbclass;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author wujiayu
 */
    
    public class  bankCardHolderTbInvisible extends JFrame {
    //declare    
    private final String[] COLUMN_TITLES = { "cardHolderID","bankCardID",};
    private Object[][] bankCardHolderData;
    private JTable bankCardHolderTable;
    private JScrollPane bankCardHolderScrollPanel;
    private MySQLdbAccess dbObj;
    
    //construction
    public  bankCardHolderTbInvisible() {
        super("bank account stats");
        this.setBounds(500, 150, 250, 250);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        dbObj = new MySQLdbAccess("Bank");
        bankCardHolderData = dbObj.getData("bankCardHolderTbInvisible",COLUMN_TITLES);
        bankCardHolderTable = new JTable(bankCardHolderData,COLUMN_TITLES);
        bankCardHolderScrollPanel = new JScrollPane();
        bankCardHolderScrollPanel.getViewport().add(bankCardHolderTable);
        bankCardHolderTable.setFillsViewportHeight(true);
        this.add(bankCardHolderScrollPanel,BorderLayout.CENTER);
        this.setVisible(true);
        
        
    }
    
}
    
