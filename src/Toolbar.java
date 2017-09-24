import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Toolbar extends JPanel implements ActionListener {
	private JButton helloButton;
	private JButton goodbyeButton;	
	private StringListener textListener;
	
	public Toolbar(){
		
		//set the Toolbar layout
		setLayout(new FlowLayout(FlowLayout.LEFT));		
		
		//define buttons for Toolbar
		helloButton = new JButton("Hello");
		goodbyeButton = new JButton("Goodbye");
		
		//add the buttons to the Toolbar
		add(helloButton);
		add(goodbyeButton);
				
		/*add action listener to the buttons and pass current instance of Toolbar class 
		*to indicate location of actionPerformed method description
		*/
		helloButton.addActionListener(this);
		goodbyeButton.addActionListener(this);

	}
	
	/*user defined function
	 * Communicates with TextArea on action listener event of buttons 
	 */
	public void setStringListener(StringListener listener){
		this.textListener = listener;
	}
 
	/*override method of ActionListener interface
	 * this method detects which button is clicked and communicates with TextArea panel accordingly
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		if(clicked==helloButton){
			if(textListener != null){
				textListener.textEmitted("Hello\n");
			}
		}
		else if(clicked==goodbyeButton){
			if(textListener != null){
				textListener.textEmitted("Goodbye\n");
			}
		}
	}	
}
 