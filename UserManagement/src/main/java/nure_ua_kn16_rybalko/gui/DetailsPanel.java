package nure_ua_kn16_rybalko.gui;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nure_ua_kn16_rybalko.User;
import nure_ua_kn16_rybalko.util.Messages;

public class DetailsPanel extends JPanel implements ActionListener {

		private MainFrame parent;
		private JPanel buttonPanel;
		private JPanel fieldPanel;
		private JButton okButton;
		private JTextField AgeField;
		private JTextField lastNameField;
		private JTextField firstNameField;
		private User user;
		
		public DetailsPanel(MainFrame frame) {
			parent = frame;
			initialize();
		}

		private void initialize() {
			this.setName("detailsPanel"); 
			this.setLayout(new BorderLayout());
			this.add(getFieldPanel(), BorderLayout.NORTH);
			this.add(getButtonPanel(), BorderLayout.SOUTH);
		}

		private JPanel getButtonPanel() {
			if (buttonPanel == null) {
				buttonPanel = new JPanel();
				buttonPanel.add(getOkButton());
			}
			return buttonPanel;
		}

		private JButton getOkButton() {
			if (okButton == null) {
				okButton = new JButton();
				okButton.setText(Messages.getString("DetailsPanel.ok"));  
				okButton.setName("okButton"); 
				okButton.setActionCommand("ok"); 
				okButton.addActionListener(this);
			}
			return okButton;
		}

		private JPanel getFieldPanel() {
			if (fieldPanel == null) {
				fieldPanel = new JPanel();
				fieldPanel.setLayout(new GridLayout(3, 2));
				addLabeledField(fieldPanel,Messages.getString("DetailsPanel.first_name"), getFirstNameField());  
				addLabeledField(fieldPanel, Messages.getString("DetailsPanel.last_name"), getLastNameField());  
				addLabeledField(fieldPanel, Messages.getString("DetailsPanel.age"), getAgeField());  
			}
			return fieldPanel;
		}

		private JTextField getAgeField() {
			if (AgeField == null) {
				AgeField = new JTextField();
				AgeField.setName("ageField"); 
			}
			return AgeField;
		}

		private JTextField getLastNameField() {
			if (lastNameField == null) {
				lastNameField = new JTextField();
				lastNameField.setName("lastNameField");
			}
			return lastNameField;
		}

		private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
			JLabel label = new JLabel(labelText);
			label.setLabelFor(textField);
			panel.add(label);
			panel.add(textField);
			
		}

		private JTextField getFirstNameField() {
			if (firstNameField == null) {
				firstNameField = new JTextField();
				firstNameField.setName("firstNameField"); 
			}
			return firstNameField;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if ("".equalsIgnoreCase(e.getActionCommand())) 
				this.setVisible(false);
			this.setVisible(false);
			parent.showBrowsePanel();
		}

		  public void setUser(User user) {
		        this.user = user;
		        getFirstNameField().setText(user.getFirstName());
		        getLastNameField().setText(user.getLastName());
		        getAgeField().setText(String.valueOf(user.getAge()));
		    }
}
