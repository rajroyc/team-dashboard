import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextPanel extends JPanel {
	
	private JTextArea textArea;
	private JTextField textField;
	
	public TextPanel(){
		
		//set Layout and Borders
		setLayout(new BorderLayout());	
		setBorder(BorderFactory.createLineBorder(Color.BLACK));		
		
		//define Panel elements
		textArea = new JTextArea();
		textField = new JTextField(10);

		//add Panel elements
		add(new JScrollPane(textArea), BorderLayout.CENTER); //wrapping the text area in a scroll pane
		add(textField, BorderLayout.NORTH);
		
	}
	/* user defined function
	 * this add texts to the text area
	 */
	public void appendText(String text){
		textArea.append(text);
		
	}
}
