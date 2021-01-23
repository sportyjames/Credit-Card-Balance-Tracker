//Jiayu Wu
//10/24/18
//This class outputs the new balance 
package dbclass;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author wujiayu
 */
public class outputCardHolderBalance extends JFrame {
    //declare
    private JLabel balance;
    private JButton exitButton;
    
    //construction
    public outputCardHolderBalance(String balanceresultAsString){
     super();
      this.setBounds(300,0,400,400);
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      
      balance = new JLabel("This person needs to pay " + balanceresultAsString);
      
      exitButton = new JButton("GOT IT");
      
      this.add(balance,BorderLayout.CENTER);
      this.add(exitButton,BorderLayout.SOUTH);
      
      
      this.setVisible(true);
    
}
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if(command.equals("GOT IT"))
        {
            this.dispose();
        }
}
}
