import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class FormPanel extends JPanel implements ActionListener {
	
	private static final boolean True = false;
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	private JList ageList;
	private JComboBox empCombo;
	private JCheckBox citizenCheck;
	private JTextField taxField;
	private JLabel taxLabel;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;

	@SuppressWarnings("unchecked")
	public FormPanel(){
		
		//set Form Dimensions
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		//set Form Borders
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		//define Form elements
		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		okBtn = new JButton("OK");
		ageList = new JList();
		empCombo = new JComboBox();
		citizenCheck = new JCheckBox();
		taxField = new JTextField(10);
		taxLabel = new JLabel("Tax ID: ");
		
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		genderGroup = new ButtonGroup();
		
		//Setup mnemonics
		okBtn.setMnemonic(KeyEvent.VK_O);
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);
		
		//Setup tax field
		taxLabel.setEnabled(false);
		taxField.setEnabled(false);
		
		citizenCheck.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				boolean isTicked = citizenCheck.isSelected();
				taxLabel.setEnabled(isTicked);
				taxField.setEnabled(isTicked);				
			}
		});
		
		//Setup gender group
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		maleRadio.setSelected(true);
		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");
		
		//Define the values in the list
		DefaultListModel ageModel = new DefaultListModel();
		ageModel.addElement(new AgeCategory(0, "Under 65"));
		ageModel.addElement(new AgeCategory(1, "18 to 65"));
		ageModel.addElement(new AgeCategory(2, "65 or over"));
		ageList.setModel(ageModel);
		ageList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		ageList.setPreferredSize(new Dimension(110,60));
		ageList.setSelectedIndex(1);
		
		//Setup Combo box
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("employed");
		empModel.addElement("self-employed");
		empModel.addElement("unemployed");
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0);
		empCombo.setEditable(True);
		
		layoutComponents();
				
		
		okBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//capturing entered text
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue();
				String empCat = (String)empCombo.getSelectedItem();
				String taxID = taxField.getText();
				boolean usCitizen = citizenCheck.isSelected();
				String genderCommand = genderGroup.getSelection().getActionCommand();
				
				//submitting the entered text to a data layer
				FormEvent ev = new FormEvent(this, name, occupation, ageCat.getID(), empCat, taxID, usCitizen, genderCommand);
				
				if(formListener!= null){
					formListener.formEventOccurred(ev);
				}
			}
			
		});
		
		

	}
	
	public void layoutComponents(){
		//setting Layout
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		/***************FIRST ROW*******************/
		
		gc.gridy = 0;		//setting the gridy separately so that we can easily add more components to subsequent rows without always editing the gridy values in all the rows
		
		gc.weightx = 1;
		gc.weighty = 0.1;		
		
		//add name label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;	
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);		
		add(nameLabel, gc);
		
		//add name field
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;
		add(nameField, gc);
		
		/***************NEXT ROW*******************/		
		
		gc.gridy++;			//setting the gridy separately so that we can easily add more components to subsequent rows without always editing the gridy values in all the rows
		
		gc.weightx = 1;
		gc.weighty = 0.1;	
		
		//add occupation label
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);
		gc.fill = GridBagConstraints.NONE;		
		add(occupationLabel, gc);
		
		//add occupation field
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.NONE;		
		add(occupationField, gc);	

		/***************NEXT ROW*******************/
		
		gc.gridy++;			//setting the gridy separately so that we can easily add more components to subsequent rows without always editing the gridy values in all the rows		
		
		gc.weightx = 1;
		gc.weighty = 0.1;	
		
		//add Age label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;	
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0,0,0,5);		
		add(new JLabel("Age: "), gc);		
		
		//add Age List
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.NONE;		
		add(ageList, gc);		
		
		/***************NEXT ROW*******************/
		
		gc.gridy++;			//setting the gridy separately so that we can easily add more components to subsequent rows without always editing the gridy values in all the rows		
		
		gc.weightx = 1;
		gc.weighty = 0.1;	
		
		//add name label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;	
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0,0,0,5);		
		add(new JLabel("Employment: "), gc);			
		
		//add Employee combo list
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.NONE;		
		add(empCombo, gc);		
		
		/***************NEXT ROW*******************/
		
		gc.gridy++;			//setting the gridy separately so that we can easily add more components to subsequent rows without always editing the gridy values in all the rows		
		
		gc.weightx = 1;
		gc.weighty = 0.1;	
		
		//add name label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;	
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0,0,0,5);		
		add(new JLabel("US Citizen: "), gc);			
		
		//add Employee combo list
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.NONE;		
		add(citizenCheck, gc);		
		
		/***************NEXT ROW*******************/
		
		gc.gridy++;			//setting the gridy separately so that we can easily add more components to subsequent rows without always editing the gridy values in all the rows		
		
		gc.weightx = 1;
		gc.weighty = 0.1;	
		
		//add name label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;	
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0,0,0,5);		
		add(taxLabel, gc);			
		
		//add Employee combo list
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.NONE;		
		add(taxField, gc);		
		
		/***************NEXT ROW*******************/
		
		gc.gridy++;			//setting the gridy separately so that we can easily add more components to subsequent rows without always editing the gridy values in all the rows		
		
		gc.weightx = 1;
		gc.weighty = 0.05;	
		
		//add name label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;	
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);		
		add(new JLabel("Gender: "), gc);			
		
		//add Employee combo list
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.NONE;		
		add(maleRadio, gc);	
		
		/***************NEXT ROW*******************/
		
		gc.gridy++;			//setting the gridy separately so that we can easily add more components to subsequent rows without always editing the gridy values in all the rows		
		
		gc.weightx = 1;
		gc.weighty = 0.05;			
		
		//add Employee combo list
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.NONE;		
		add(femaleRadio, gc);			
		
		/***************NEXT ROW*******************/
		
		gc.gridy++;			//setting the gridy separately so that we can easily add more components to subsequent rows without always editing the gridy values in all the rows
		
		gc.weightx = 1;
		gc.weighty = 0.8;	
		
		//add OK button
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.NONE;		
		add(okBtn, gc);			
	}
	
	public void setFormListener(FormListener formListener){
		this.formListener = formListener;
		
	}	
	
	@Override
	public void actionPerformed(ActionEvent e){
		JButton clicked = (JButton) e.getSource();
		if(clicked==okBtn){
			okBtn.setVisible(false);
		}
	}
	
}



class AgeCategory{
	
	private int id;
	private String text;
	
	public AgeCategory(int id, String text){
		this.id = id;
		this.text = text;
	}
	
	public String toString(){
		return text;
	}
	
	public int getID(){
		return id;
	}
}