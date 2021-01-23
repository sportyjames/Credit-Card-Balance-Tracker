//Jiayu Wu
//10/24/18
//This is newworkTable
package dbclass;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author wujiayu
 */
public class networkTable extends JFrame{
    
    private final String[] COLUMN_TITLES = {"CardNetworkLevel","CardNetwork"};
    private Object[][] networkData;
    private JTable networkTable;
    private JScrollPane networkScrollPanel;
    private MySQLdbAccess dbObj;
    
    public networkTable(){
     super("networkTable");
     this.setBounds(700, 150, 250, 250);
     this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     this.setLayout(new BorderLayout());
     
     dbObj = new MySQLdbAccess("Bank");
     networkData = dbObj.getData("networkTable",COLUMN_TITLES);
     networkTable = new JTable(networkData,COLUMN_TITLES);
     networkScrollPanel = new JScrollPane();
     networkScrollPanel.getViewport().add(networkTable);
     networkTable.setFillsViewportHeight(true);
     this.add(networkScrollPanel,BorderLayout.CENTER);
     this.setVisible(true);
     
     
    }
    
    
}
