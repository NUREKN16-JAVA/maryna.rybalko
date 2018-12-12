package nure_ua_kn16_rybalko.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nure_ua_kn16_rybalko.User;
import nure_ua_kn16_rybalko.db.DatabaseException;
import nure_ua_kn16_rybalko.util.Messages;

public class BrowsePanel extends JPanel implements ActionListener {
	
	private MainFrame parent;
	private JPanel buttonPanel;
	private JButton addButton;
	private JButton detailsButton;
	private JButton deleteButton;
	private JButton editButton;
	private JScrollPane tablePanel;
	private JTable userTable;

	public BrowsePanel(MainFrame frame) {
	parent = frame;
	initialize();
	}

	private void initialize() {
		this.setName("browsePanel"); 
		this.setLayout(new BorderLayout());
		this.add(getTablePanel(),BorderLayout.CENTER);
		this.add(getButtonsPanel(),BorderLayout.SOUTH);
		
	}

	private JPanel getButtonsPanel() {
		if (buttonPanel==null) {
			buttonPanel= new JPanel();
			buttonPanel.add(getAddButton(), null);
			buttonPanel.add(getEditButton(), null);
			buttonPanel.add(getDeleteButton(), null);
			buttonPanel.add(getDetailsButton(), null);
		}
		return buttonPanel;
	}

	private JButton getDetailsButton() {
		if (detailsButton==null) {
			detailsButton = new JButton();
			detailsButton.setText(Messages.getString("BrowsePanel.details")); 
			detailsButton.setName("detailsButton");
			detailsButton.setActionCommand("details"); 
			detailsButton.addActionListener(this);
		}
				return detailsButton;
		
	}

	private JButton getDeleteButton() {
		if (deleteButton==null) {
			deleteButton = new JButton();
			deleteButton.setText(Messages.getString("BrowsePanel.delete")); 
			deleteButton.setName("deleteButton"); 
			deleteButton.setActionCommand("delete"); 
			deleteButton.addActionListener(this);
		}
				return deleteButton;
	}

	private JButton getEditButton() {
		if (editButton==null) {
			editButton = new JButton();
			editButton.setText(Messages.getString("BrowsePanel.edit")); 
			editButton.setName("editButton"); 
			editButton.setActionCommand("edit");
			editButton.addActionListener(this);
		}
				return editButton;
		
	}

	private JButton getAddButton() {
		if (addButton==null) {
			addButton = new JButton();
			addButton.setText(Messages.getString("BrowsePanel.add")); 
			addButton.setName("addButton"); 
			addButton.setActionCommand("add");
			addButton.addActionListener(this);
		}
				return addButton;
	}

	private JScrollPane getTablePanel() {
		if (tablePanel==null) {
			tablePanel= new JScrollPane(getUserTable());
		}
	
		return tablePanel;
	}

	private JTable getUserTable() {
		if (userTable==null) {
			userTable=new JTable();
			userTable.setName("userTable"); 
			
		}		
		return userTable;
	}

	public void initTable() {
		UserTableModel model;
		
		try {
			model = new UserTableModel(parent.getDao().findAll());
		} catch (DatabaseException e) {
			model = new UserTableModel(new ArrayList());
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		getUserTable().setModel(model);
	}

	
	 public void actionPerformed(ActionEvent e) {
	        String actionCommand = e.getActionCommand();
	        if ("add".equalsIgnoreCase(actionCommand)) { 
	            this.setVisible(false);
	            parent.showAddPanel();
	        } else if ("edit".equalsIgnoreCase(actionCommand)) { 
	            int selectedRow = userTable.getSelectedRow();
	            if (selectedRow == -1) {
	                JOptionPane.showMessageDialog(this, "Select a user, please",
	                        "Edit user", JOptionPane.INFORMATION_MESSAGE);
	                return;
	            }
	            User user = ((UserTableModel) userTable.getModel())
	                    .getUser(selectedRow);
	            this.setVisible(false);
	            parent.showEditPanel(user);
	        } else if ("delete".equalsIgnoreCase(actionCommand)) {
	            int selectedRow = userTable.getSelectedRow();
	            if (selectedRow == -1) {
	                JOptionPane.showMessageDialog(this, "Select a user, please",
	                        "Delete user", JOptionPane.INFORMATION_MESSAGE);
	                return;
	            }
	            try {
	                parent.getDao().delete(
	                        ((UserTableModel) userTable.getModel())
	                                .getUser(selectedRow));
	            } catch (DatabaseException e1) {
	                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",
	                        JOptionPane.ERROR_MESSAGE);
	            }
	            initTable();
	            return;
	        }
	        
	        else if ("details".equalsIgnoreCase(actionCommand)) { 
	            int selectedRow = userTable.getSelectedRow();
	            if (selectedRow == -1) {
	                JOptionPane.showMessageDialog(this, "Select a user, please",
	                        "Detail user", JOptionPane.INFORMATION_MESSAGE);
	                return;
	            }
	            User user = ((UserTableModel) userTable.getModel())
	                    .getUser(selectedRow);
	            this.setVisible(false);
	            parent.showDetailsPanel(user);
	        }

	    }

}
