package me.TheL0w3R.SkypePlus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("rawtypes")
public class MainForm {

	/**
	 * Custom Variables
	 */
	ArrayList listIndexes = new ArrayList();
	DefaultListModel tempListModel = new DefaultListModel();
	DefaultListModel groupListModel = new DefaultListModel();
	DefaultListModel blankModel = new DefaultListModel();
	ArrayList users = new ArrayList();
	ArrayList names = new ArrayList();

	/**
	 * Auto-generated Variables
	 */
	private JFrame frame;
	private JComboBox groupListCB;
	private JButton editGroupBtn;
	private JButton sendBtn;
	private JTextPane msgField;
	private JComboBox botTypeCB;
	private JLabel botStatusLabel;
	private JButton disableBotBtn;
	private JButton enableBotBtn;
	private JComboBox botSingleGroup;
	private JRadioButton botGroup;
	private JRadioButton botSingle;
	private JList groupList;
	private JList tempList;
	private JComboBox editGroupCB;
	private JButton remGroupBtn;
	private JButton newGroupBtn;
	private JPanel msgPanel;
	private JTabbedPane JTabbedPane1;
	private JButton selectAllTemp;
	private JButton selectAllGroup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm form = new MainForm();
					form.frame.setTitle("SkypePlus v" + Utils.getVersion());
					form.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
		startProc();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainForm.class.getResource("/Resources/icon.png")));
		frame.setBounds(100, 100, 579, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JTabbedPane1 = new JTabbedPane(JTabbedPane.TOP);
		JTabbedPane1.setName("JTabbedPane1");
		JTabbedPane1.setBounds(0, 0, 573, 582);
		frame.getContentPane().add(JTabbedPane1);

		msgPanel = new JPanel();
		msgPanel.setName("msgPanel");
		JTabbedPane1.addTab("Message", null, msgPanel, null);

		JLabel lblNewLabel = new JLabel("Destination Group:");

		groupListCB = new JComboBox();
		groupListCB.setName("groupListCB");
		groupListCB.setModel(new DefaultComboBoxModel(new String[] { "All Skype Contacts" }));

		JLabel lblCreateAndEdit = new JLabel("Create and edit groups here:");

		editGroupBtn = new JButton("Edit Group");
		editGroupBtn.setName("editGroupBtn");
		editGroupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTabbedPane1.setSelectedIndex(3);
				;
			}
		});

		JSeparator separator = new JSeparator();

		JLabel lblWriteYourMessage = new JLabel("Write your message before:");

		JLabel lblTheText = new JLabel("The text \"<NAME>\" will be replaced with the full contact name.");

		JScrollPane scrollPane = new JScrollPane();

		sendBtn = new JButton("Send Message");
		sendBtn.setName("sendBtn");

		GroupLayout gl_msgPanel = new GroupLayout(msgPanel);
		gl_msgPanel.setHorizontalGroup(gl_msgPanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_msgPanel.createSequentialGroup().addContainerGap().addGroup(gl_msgPanel.createParallelGroup(Alignment.TRAILING).addComponent(sendBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE).addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE).addGroup(Alignment.LEADING, gl_msgPanel.createSequentialGroup().addComponent(lblNewLabel).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(groupListCB, 0, 203, Short.MAX_VALUE).addGap(18).addComponent(lblCreateAndEdit).addPreferredGap(ComponentPlacement.RELATED).addComponent(editGroupBtn)).addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE).addComponent(lblWriteYourMessage, Alignment.LEADING).addComponent(lblTheText, Alignment.LEADING)).addContainerGap()));
		gl_msgPanel.setVerticalGroup(gl_msgPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_msgPanel.createSequentialGroup().addContainerGap().addGroup(gl_msgPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(groupListCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(editGroupBtn).addComponent(lblCreateAndEdit)).addGap(18).addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE).addGap(18).addComponent(lblWriteYourMessage).addPreferredGap(ComponentPlacement.RELATED).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(lblTheText).addPreferredGap(ComponentPlacement.RELATED).addComponent(sendBtn, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE).addContainerGap()));

		msgField = new JTextPane();
		msgField.setName("msgField");
		scrollPane.setViewportView(msgField);
		msgPanel.setLayout(gl_msgPanel);

		JPanel botPanel = new JPanel();
		JTabbedPane1.addTab("Skype Bot", null, botPanel, null);

		JLabel lblNewLabel_1 = new JLabel("Select yuour target type : ");

		botSingle = new JRadioButton("Single Person");
		botSingle.setName("botSingle");

		botGroup = new JRadioButton("SkypePlus Group");
		botGroup.setName("botGroup");

		botSingleGroup = new JComboBox();
		botSingleGroup.setName("botSingleGroup");

		JLabel lblBotType = new JLabel("Bot type: ");

		botTypeCB = new JComboBox();
		botTypeCB.setName("botTypeCB");
		botTypeCB.setModel(new DefaultComboBoxModel(new String[] { "Cleverbot", "JabberWacky" }));

		JLabel lblTheBotType = new JLabel("The bot type will change automatically when someone talk to you.");

		JLabel lblMessageHistory = new JLabel("Message History:");

		JScrollPane scrollPane_1 = new JScrollPane();

		JSeparator separator_1 = new JSeparator();

		enableBotBtn = new JButton("Enable Bot");
		enableBotBtn.addActionListener(new EnableBotBtnActionListener());
		enableBotBtn.setName("enableBotBtn");

		disableBotBtn = new JButton("Disable Bot");
		disableBotBtn.addActionListener(new DisableBotBtnActionListener());
		disableBotBtn.setName("disableBotBtn");

		JLabel lblBotStatus = new JLabel("Bot Status:");

		botStatusLabel = new JLabel("DISABLED");
		botStatusLabel.setName("botStatusLabel");
		botStatusLabel.setForeground(Color.RED);
		GroupLayout gl_botPanel = new GroupLayout(botPanel);
		gl_botPanel.setHorizontalGroup(gl_botPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_botPanel.createSequentialGroup().addContainerGap().addGroup(gl_botPanel.createParallelGroup(Alignment.LEADING).addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE).addGroup(gl_botPanel.createSequentialGroup().addComponent(lblNewLabel_1).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(botSingle).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(botGroup).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(botSingleGroup, 0, 212, Short.MAX_VALUE)).addComponent(lblMessageHistory).addGroup(gl_botPanel.createSequentialGroup().addComponent(lblBotType).addPreferredGap(ComponentPlacement.RELATED).addComponent(botTypeCB, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(lblTheBotType, GroupLayout.PREFERRED_SIZE, 339, Short.MAX_VALUE)).addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE).addGroup(gl_botPanel.createSequentialGroup().addComponent(enableBotBtn, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE).addGap(42).addComponent(lblBotStatus).addPreferredGap(ComponentPlacement.RELATED).addComponent(botStatusLabel).addGap(102).addComponent(disableBotBtn, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))).addContainerGap()));
		gl_botPanel.setVerticalGroup(gl_botPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_botPanel.createSequentialGroup().addContainerGap().addGroup(gl_botPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1).addComponent(botSingle).addComponent(botGroup).addComponent(botSingleGroup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(gl_botPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblBotType).addComponent(botTypeCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(lblTheBotType)).addGap(18).addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE).addGap(11).addComponent(lblMessageHistory).addGap(8).addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(gl_botPanel.createParallelGroup(Alignment.BASELINE).addComponent(enableBotBtn).addComponent(disableBotBtn).addComponent(lblBotStatus).addComponent(botStatusLabel)).addContainerGap()));

		JTextPane textPane = new JTextPane();
		textPane.setName("textPane");
		scrollPane_1.setViewportView(textPane);
		botPanel.setLayout(gl_botPanel);

		JPanel smartArtPanel = new JPanel();
		JTabbedPane1.addTab("SmartArt", null, smartArtPanel, null);
		GroupLayout gl_smartArtPanel = new GroupLayout(smartArtPanel);
		gl_smartArtPanel.setHorizontalGroup(gl_smartArtPanel.createParallelGroup(Alignment.LEADING).addGap(0, 568, Short.MAX_VALUE));
		gl_smartArtPanel.setVerticalGroup(gl_smartArtPanel.createParallelGroup(Alignment.LEADING).addGap(0, 554, Short.MAX_VALUE));
		smartArtPanel.setLayout(gl_smartArtPanel);

		JPanel groupPanel = new JPanel();
		JTabbedPane1.addTab("Groups", null, groupPanel, null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setName("scrollPane_2");

		editGroupCB = new JComboBox();
		editGroupCB.addItemListener(new EditGroupCBItemListener());
		editGroupCB.setModel(new DefaultComboBoxModel(new String[] { "All Skype Contacts" }));
		editGroupCB.setName("editGroupCB");

		JButton addBtn = new JButton(">");
		addBtn.addActionListener(new addBtnActionListener());
		addBtn.setName("addBtn");

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setName("scrollPane_2");

		JButton remBtn = new JButton("<");
		remBtn.addActionListener(new RemBtnActionListener());
		remBtn.setName("remBtn");

		newGroupBtn = new JButton("Save Group");
		newGroupBtn.addActionListener(new NewGroupBtnActionListener());
		newGroupBtn.setName("newGroupBtn");

		remGroupBtn = new JButton("Remove Group");
		remGroupBtn.setName("remGroupBtn");

		JLabel lblNewLabel_2 = new JLabel("Group Editor");
		lblNewLabel_2.setName("lblNewLabel_2");

		JLabel lblGroupSelector = new JLabel("Group Selector");
		lblGroupSelector.setName("lblGroupSelector");

		selectAllTemp = new JButton("Select All");
		selectAllTemp.addActionListener(new SelectAllTempActionListener());
		selectAllTemp.setName("selectAllTemp");

		selectAllGroup = new JButton("Select All");
		selectAllGroup.addActionListener(new SelectAllGroupActionListener());
		selectAllGroup.setName("selectAllGroup");
		GroupLayout gl_groupPanel = new GroupLayout(groupPanel);
		gl_groupPanel.setHorizontalGroup(gl_groupPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_groupPanel.createSequentialGroup().addContainerGap().addGroup(gl_groupPanel.createParallelGroup(Alignment.LEADING).addComponent(lblGroupSelector).addGroup(gl_groupPanel.createSequentialGroup().addGroup(gl_groupPanel.createParallelGroup(Alignment.TRAILING).addComponent(selectAllGroup, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE).addComponent(scrollPane_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE).addComponent(editGroupCB, Alignment.LEADING, 0, 248, Short.MAX_VALUE).addComponent(remGroupBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_groupPanel.createParallelGroup(Alignment.LEADING).addComponent(addBtn, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE).addComponent(remBtn, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_groupPanel.createParallelGroup(Alignment.LEADING).addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE).addComponent(newGroupBtn, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE).addComponent(lblNewLabel_2).addComponent(selectAllTemp, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)).addContainerGap()));
		gl_groupPanel.setVerticalGroup(gl_groupPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_groupPanel.createSequentialGroup().addGroup(gl_groupPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_groupPanel.createSequentialGroup().addGap(17).addGroup(gl_groupPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2).addComponent(lblGroupSelector)).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_groupPanel.createParallelGroup(Alignment.LEADING, false).addGroup(gl_groupPanel.createSequentialGroup().addComponent(editGroupCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(scrollPane_2)).addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_groupPanel.createParallelGroup(Alignment.BASELINE).addComponent(selectAllTemp).addComponent(selectAllGroup)).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_groupPanel.createParallelGroup(Alignment.BASELINE).addComponent(remGroupBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE).addComponent(newGroupBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))).addGroup(gl_groupPanel.createSequentialGroup().addGap(212).addComponent(addBtn).addGap(30).addComponent(remBtn))).addContainerGap()));

		tempList = new JList();
		tempList.setName("tempList");
		scrollPane_3.setViewportView(tempList);

		groupList = new JList();
		groupList.setName("groupList");
		scrollPane_2.setViewportView(groupList);
		groupPanel.setLayout(gl_groupPanel);

		JPanel settingPanel = new JPanel();
		JTabbedPane1.addTab("Settings", null, settingPanel, null);
		GroupLayout gl_settingPanel = new GroupLayout(settingPanel);
		gl_settingPanel.setHorizontalGroup(gl_settingPanel.createParallelGroup(Alignment.LEADING).addGap(0, 568, Short.MAX_VALUE));
		gl_settingPanel.setVerticalGroup(gl_settingPanel.createParallelGroup(Alignment.LEADING).addGap(0, 554, Short.MAX_VALUE));
		settingPanel.setLayout(gl_settingPanel);

		JPanel aboutPanel = new JPanel();
		JTabbedPane1.addTab("About", null, aboutPanel, null);
		GroupLayout gl_aboutPanel = new GroupLayout(aboutPanel);
		gl_aboutPanel.setHorizontalGroup(gl_aboutPanel.createParallelGroup(Alignment.LEADING).addGap(0, 568, Short.MAX_VALUE));
		gl_aboutPanel.setVerticalGroup(gl_aboutPanel.createParallelGroup(Alignment.LEADING).addGap(0, 554, Short.MAX_VALUE));
		aboutPanel.setLayout(gl_aboutPanel);
	}

	public void startProc() {
		try {
			// groupList.setModel(Utils.getFriends(0));
			for (int i = 0; i < Utils.countLines(Utils.groupFile); i++) {
				editGroupCB.addItem(Utils.getIndexValues(i, 0));
				groupListCB.addItem(Utils.getIndexValues(i, 0));
				botSingleGroup.addItem(Utils.getIndexValues(i, 0));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class EnableBotBtnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			botStatusLabel.setText("ENABLED");
			botStatusLabel.setForeground(Color.GREEN);
		}
	}

	private class DisableBotBtnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			botStatusLabel.setText("DISABLED");
			botStatusLabel.setForeground(Color.RED);
		}
	}

	private class addBtnActionListener implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			Object[] options = { "OK" };
			try {
				if (groupList.getSelectedValuesList().size() == 1) {
					if (editGroupCB.getSelectedItem().equals("All Skype Contacts")) {
						if (listIndexes.isEmpty()) {
							System.out.println("The listIndexes Array is empty. All Contacts Selected.");
							listIndexes.add(groupList.getSelectedIndex());
							tempListModel.addElement(groupList.getSelectedValue());
							tempList.setModel(tempListModel);
							groupList.clearSelection();
							System.out.println("listIndexes content: " + listIndexes);
						} else {
							System.out.println("The listIndexes Array is NOT empty. May contain a single contact added before or a complete array of id's (Group List)");
							listIndexes.add(groupList.getSelectedIndex());
							tempListModel.addElement(groupList.getSelectedValue());
							tempList.setModel(tempListModel);
							groupList.clearSelection();
							System.out.println("listIndexes content: " + listIndexes);
						}
					} else {
						if (listIndexes.isEmpty()) {
							System.out.println("The listIndexes Array is empty. All Contacts Selected.");
							listIndexes.add(Arrays.asList(Utils.getGroup(editGroupCB.getSelectedIndex() - 1, 2)).get(groupList.getSelectedIndex()));
							tempListModel.addElement(groupList.getSelectedValue());
							tempList.setModel(tempListModel);
							groupList.clearSelection();
							System.out.println("listIndexes content: " + listIndexes);
						} else {
							System.out.println("The listIndexes Array is NOT empty. May contain a single contact added before or a complete array of id's (Group List)");
							listIndexes.add(Arrays.asList(Utils.getGroup(editGroupCB.getSelectedIndex() - 1, 2)).get(groupList.getSelectedIndex()));
							tempListModel.addElement(groupList.getSelectedValue());
							tempList.setModel(tempListModel);
							groupList.clearSelection();
							System.out.println("listIndexes content: " + listIndexes);
						}
					}
				} else if (groupList.getSelectedValuesList().size() > 1) {
					if (editGroupCB.getSelectedItem().equals("All Skype Contacts")) {
						if (listIndexes.isEmpty()) {
							System.out.println("The listIndexes Array is empty. All Contacts Selected.");
							for (int id : groupList.getSelectedIndices()) {
								listIndexes.add(id);
							}
							for (Object element : groupList.getSelectedValuesList()) {
								tempListModel.addElement(element);
							}
							tempList.setModel(tempListModel);
							groupList.clearSelection();
							System.out.println("listIndexes content: " + listIndexes);
						} else {
							System.out.println("The listIndexes Array is NOT empty. May contain a single contact added before or a complete array of id's (Group List)");
							for (int id : groupList.getSelectedIndices()) {
								listIndexes.add(id);
							}
							for (Object element : groupList.getSelectedValuesList()) {
								tempListModel.addElement(element);
							}
							tempList.setModel(tempListModel);
							groupList.clearSelection();
							System.out.println("listIndexes content: " + listIndexes);
						}
					} else {
						if (listIndexes.isEmpty()) {
							System.out.println("The listIndexes Array is empty. All Contacts Selected.");
							for (int id : groupList.getSelectedIndices()) {
								listIndexes.add(Arrays.asList(Utils.getGroup(editGroupCB.getSelectedIndex() - 1, 2)).get(id));
							}
							for (Object element : groupList.getSelectedValuesList()) {
								tempListModel.addElement(element);
							}
							tempList.setModel(tempListModel);
							groupList.clearSelection();
							System.out.println("listIndexes content: " + listIndexes);
						} else {
							System.out.println("The listIndexes Array is NOT empty. May contain a single contact added before or a complete array of id's (Group List)");
							for (int id : groupList.getSelectedIndices()) {
								listIndexes.add(Arrays.asList(Utils.getGroup(editGroupCB.getSelectedIndex() - 1, 2)).get(id));
							}
							for (Object element : groupList.getSelectedValuesList()) {
								tempListModel.addElement(element);
							}
							tempList.setModel(tempListModel);
							groupList.clearSelection();
							System.out.println("listIndexes content: " + listIndexes);
						}
					}
				} else if (groupList.getSelectedValuesList().size() < 1) {
					JOptionPane.showOptionDialog(null, "Please, select a contact first.", "SkypePlus Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	private class RemBtnActionListener implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			// Object[] options = { "OK" };
			// try {
			// if (listIndexes.isEmpty()) {
			// System.out.println("Pre-Check is needed.");
			// if
			// (!(editGroupCB.getSelectedItem().equals("All Skype Contacts"))) {
			// listIndexes.addAll(Arrays.asList(Utils.getGroup(editGroupCB.getSelectedIndex()
			// - 1, 2)));
			// }
			// } else {
			// System.out.println("Pre-Check is not needed.");
			// }
			// if (tempList.isSelectionEmpty() == false) {
			// System.out.println("Indexes: " + listIndexes);
			// System.out.println("Removed: " +
			// listIndexes.get(tempList.getSelectedIndex()));
			// listIndexes.remove(tempList.getSelectedIndex());
			// tempListModel.removeElement(tempList.getSelectedValue());
			// tempList.setModel(tempListModel);
			// } else if (tempList.isSelectionEmpty() == true) {
			// int n = JOptionPane.showOptionDialog(null,
			// "Please, select an Item from the group list first.",
			// "SkypePlus Error", JOptionPane.PLAIN_MESSAGE,
			// JOptionPane.ERROR_MESSAGE, null, options, options[0]);
			// if (n == JOptionPane.OK_OPTION) {
			// }
			// }
			// } catch (IOException ex) {
			// Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE,
			// null, ex);
			// }
		}
	}

	private class NewGroupBtnActionListener implements ActionListener {
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			String groupName = JOptionPane.showInputDialog(null, "Group Name:", "Save group", JOptionPane.QUESTION_MESSAGE);
			DefaultListModel refresher = new DefaultListModel();
			if (!(new File(Utils.groupFolder + "\\" + groupName + ".txt").exists())) {
				for (Object id : listIndexes) {
					users.add(Utils.getUserNames(Integer.parseInt(id.toString()), false));
					names.add(Utils.getFriends(0).get(Integer.parseInt(id.toString())));
					refresher.addElement(Utils.getFriends(0).get(Integer.parseInt(id.toString())));
				}

				Utils.saveGroup(groupName, users, names, listIndexes, true);
				groupListCB.addItem(groupName);
				groupListCB.setSelectedItem(groupName);
				editGroupCB.addItem(groupName);
				editGroupCB.setSelectedItem(groupName);
				groupList.setModel(refresher);
				tempList.setModel(blankModel);
			} else {
				int yesno = JOptionPane.YES_NO_OPTION;
				int dialog = 0;
				dialog = JOptionPane.showConfirmDialog(null, "The group " + editGroupCB.getSelectedItem().toString() + " already exists.\n Do you want to overwrite it?", "SkypePlus", yesno);
				if (dialog == JOptionPane.YES_OPTION) {
					System.out.println("OverWriting an existing group.");
					for (Object id : listIndexes) {
						users.add(Utils.getUserNames(Integer.parseInt(id.toString()), false));
						names.add(Utils.getFriends(0).get(Integer.parseInt(id.toString())));
						refresher.addElement(Utils.getFriends(0).get(Integer.parseInt(id.toString())));
					}
					Utils.saveGroup(groupName, users, names, listIndexes, false);
					groupListCB.setSelectedItem(groupName);
					editGroupCB.setSelectedItem(groupName);
					groupList.setModel(refresher);
					tempList.setModel(blankModel);
				} else {
					System.out.println("The group wasn't OverWritten.");
				}
			}
			users.clear();
			names.clear();
			listIndexes.clear();
			refresher.clear();
		}
	}

	private class EditGroupCBItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {
			groupListModel.clear();
			if (editGroupCB.getSelectedItem().equals("All Skype Contacts")) {
				groupList.setModel(Utils.getFriends(0));
			} else {
				try {
					for (String name : Utils.getGroup(editGroupCB.getSelectedIndex() - 1, 0)) {
						groupListModel.addElement(name);
					}
				} catch (IOException ex) {
					Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
				}
				groupList.setModel(groupListModel);
			}
		}
	}

	private class SelectAllGroupActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int start = 0;
			int end = groupList.getModel().getSize() - 1;

			if (end >= 0) {
				groupList.setSelectionInterval(start, end);
			}
		}
	}

	private class SelectAllTempActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int start = 0;
			int end = tempList.getModel().getSize() - 1;

			if (end >= 0) {
				tempList.setSelectionInterval(start, end);
			}
		}
	}
}
