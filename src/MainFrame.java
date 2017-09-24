import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class MainFrame extends JFrame {
	
	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	
	public MainFrame(){
		
		//creating the parent Frame
		super("Hello World");
		
		//set Layout
		setLayout(new BorderLayout());
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
		
		//define the Frame elements
		textPanel = new TextPanel();
		toolbar = new Toolbar();
		formPanel = new FormPanel();
		
		setJMenuBar(createMenuBar());

		//add the Frame elements
		add(toolbar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);		
		
		//Setup communication between Toolbar and TextPanel elements
		toolbar.setStringListener(new StringListener(){
			@Override
			public void textEmitted(String text) {
				textPanel.appendText(text);				
			}			
		});
		
		//Setup communication between FormPanel and TextPanel elements
		formPanel.setFormListener(new FormListener(){
			@Override
			public void formEventOccurred(FormEvent e){
				//capturing text from the Form's data layer
				String name = e.getName();
				String occupation = e.getOccupation();
				int age = e.getAgeCategory();
				String empCat = e.getEmpCat();
				String taxID = e.getTaxID();
				String gender = e.getGender();
				
				//passing text to TextPanel to be printed
				textPanel.appendText(name + ": " + occupation + ": " + age + ": " + empCat + ": " + taxID + ": " + gender + "\n");
			}
		});

		
	}
	
	private JMenuBar createMenuBar(){
		
		JMenuBar menuBar = new JMenuBar();
		
		//create Menu Items
		JMenu fileMenu = new JMenu("File");
		JMenu windowMenu = new JMenu("Window");
		
		//create Sub Menus
		JMenu showMenu = new JMenu("Show");
		
		//create Sub Menu Items for File Menu
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		//Add Menu Items to File Menu
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		//create Sub Menu Items for Show Sub Menu
		//JMenuItem showFormItem = new JMenuItem("Person Form");
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		showFormItem.setSelected(true);
		showMenu.add(showFormItem);
		
		//set ActionListener for show Form Item 
		showFormItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();
				formPanel.setVisible(menuItem.isSelected());
			}
		});
		
		//Add Sub Menu to Window Menu
		windowMenu.add(showMenu);
		
		//Add Mnemonics to File Menu
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		//Set accelerators
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				System.exit(0);
			}
		});
		
		//Add Menus to Menu Bar
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);		
				
		return menuBar;
		
	}
}

