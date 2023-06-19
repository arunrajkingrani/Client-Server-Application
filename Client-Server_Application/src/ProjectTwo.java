
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import com.mysql.cj.xdevapi.Statement;

import net.proteanit.sql.DbUtils;
import net.proteanit.sql.DbUtils;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Label;
import java.awt.Cursor;

public class ProjectTwo {
	
	String filepath = "C:\\Users\\MG\\Desktop\\root.properties.txt";
	String tempuser = "";
	String temppass = "";
	
	

	private JFrame frame;
	private JTextField usernamebox;
	private JTable table;
	public static Scanner x;
	private JTextField sqlbox;
	private JTextField textField;
	private JPasswordField passbox;
	
	void verifyLogin() {
		try {
			x= new Scanner(new File(filepath));
			x.useDelimiter("[,\n]");
			
			while(x.hasNext()) {
				tempuser = x.next();
				temppass = x.next();
				
				if(tempuser.trim().equals(usernamebox.getText()) && temppass.trim().equals(passbox.getText())) {
				    try {
						try {
							String load = "use project2;";
							PreparedStatement pst = DBConnection.getConnection().prepareStatement(load);
							textField.setText("Connected to jdbc:mysql://localhost:3306/project2\",\"root\",\"pentforac1@1\"");
							
						} catch (Exception f) {
							f.printStackTrace();
							
						}
						
				} catch(Exception e) {
				    	System.out.println("Error!"); }
		}
				else {JOptionPane.showMessageDialog(null, "Username or Password Incorrect");
				textField.setText("Connection Error");
					} 
				} }catch(Exception e) {
					e.printStackTrace();
				}
				
	}
	public void OperationsLog() {
		try {
			String query = "insert into operationscount values(1,1)";
			PreparedStatement pst = DBConnection.getConnection1().prepareStatement(query);
			pst.executeUpdate();			
		} catch (SQLException f) {
			if(sqlbox.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Enter SQL Command");}
				else if(f.getErrorCode() == 1054 ) {
				JOptionPane.showMessageDialog(null, "Unkown Column");
			}
		}
	}
	public void ExecutionWithOutput() {
		try {
			String query = sqlbox.getText();
			PreparedStatement pst = DBConnection.getConnection().prepareStatement(query);
			ResultSet rs1 = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs1));
			
		} catch (SQLException f) {
			if(sqlbox.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Enter SQL Command");}
				else if(f.getErrorCode() == 1054 ) {
				JOptionPane.showMessageDialog(null, "Unkown Column");
				}
				else if(f.getErrorCode() == 1050 ) {
					JOptionPane.showMessageDialog(null, "Table already exists");
			f.printStackTrace();}
				else if(f.getErrorCode() == 1007 ) {
					JOptionPane.showMessageDialog(null, "Database already exists");
			f.printStackTrace();}
			}
		
		}
	
	
	public void ExecutionWithoutOutput() {
		try {
			String query = sqlbox.getText();
			java.sql.Statement st = DBConnection.getConnection().createStatement();
			st.executeUpdate(query);
			st.executeQuery(query);
		
		} catch (SQLException f) {
			if(sqlbox.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Enter SQL Command");}
				else if(f.getErrorCode() == 1054 ) {
					JOptionPane.showMessageDialog(null, "Unkown Column");
			}
				else if(f.getErrorCode() == 1050 ) {
					JOptionPane.showMessageDialog(null, "Table already exists");
			f.printStackTrace();}
				else if(f.getErrorCode() == 1007 ) {
					JOptionPane.showMessageDialog(null, "Database already exists");
			f.printStackTrace();}
		}
	} public void RemoveTable() {
	DefaultTableModel modelrow = (DefaultTableModel)table.getModel();
	DefaultTableModel modelcolumn = (DefaultTableModel)table.getModel();
	while(modelrow.getRowCount() >0) {
		for(int i = 0; i< modelrow.getRowCount(); i++) {
			modelrow.removeRow(i);
		}
	}
	while(modelcolumn.getColumnCount() >0) {
		for(int j = 0; j< modelcolumn.getColumnCount(); j++) {
			modelrow.removeRow(j);
		}
	}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectTwo window = new ProjectTwo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProjectTwo() {
		initialize();
		textField.setText("No Connection Now");
		
		passbox = new JPasswordField();
		passbox.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		passbox.setBounds(202, 188, 283, 63);
		frame.getContentPane().add(passbox);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1364, 880);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Connection Details");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 199, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEnterSqlCommand = new JLabel("Enter SQL Command");
		lblEnterSqlCommand.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterSqlCommand.setForeground(Color.BLUE);
		lblEnterSqlCommand.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblEnterSqlCommand.setBounds(704, 0, 199, 39);
		frame.getContentPane().add(lblEnterSqlCommand);
		
		JLabel lblPropertiesFile = new JLabel("Properties File");
		lblPropertiesFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblPropertiesFile.setForeground(Color.BLUE);
		lblPropertiesFile.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblPropertiesFile.setBounds(0, 50, 199, 58);
		frame.getContentPane().add(lblPropertiesFile);
		
		JLabel lblUsernma = new JLabel("Username");
		lblUsernma.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsernma.setForeground(Color.BLUE);
		lblUsernma.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblUsernma.setBounds(0, 119, 199, 58);
		frame.getContentPane().add(lblUsernma);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblPassword.setBounds(0, 188, 199, 58);
		frame.getContentPane().add(lblPassword);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"root.properties"}));
		comboBox.setBounds(202, 50, 283, 58);
		frame.getContentPane().add(comboBox);
		
		usernamebox = new JTextField();
		usernamebox.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		usernamebox.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		usernamebox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		usernamebox.setAutoscrolls(false);
		usernamebox.setHorizontalAlignment(SwingConstants.LEFT);
		usernamebox.setBounds(202, 119, 283, 63);
		frame.getContentPane().add(usernamebox);
		usernamebox.setColumns(10);
		
		JButton btnNewButton = new JButton("Connect To Database");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verifyLogin();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setBounds(10, 292, 230, 39);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnClear = new JButton("Clear SQL Command");
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sqlbox.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnClear.setBackground(Color.WHITE);
		btnClear.setForeground(Color.RED);
		btnClear.setBounds(704, 263, 199, 39);
		frame.getContentPane().add(btnClear);
		
		JButton btnExecuteSqlCommand = new JButton("Execute Sql Command");
		btnExecuteSqlCommand.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExecuteSqlCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExecutionWithOutput();
				ExecutionWithoutOutput();
				OperationsLog();
				
				
				}
		});
		btnExecuteSqlCommand.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExecuteSqlCommand.setForeground(new Color(0, 0, 0));
		btnExecuteSqlCommand.setBackground(Color.GREEN);
		btnExecuteSqlCommand.setBounds(1120, 263, 218, 39);
		frame.getContentPane().add(btnExecuteSqlCommand);
		
		JLabel lblSqlExecutionResult = new JLabel("SQL Execution Result Window");
		lblSqlExecutionResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblSqlExecutionResult.setForeground(Color.BLUE);
		lblSqlExecutionResult.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblSqlExecutionResult.setBounds(28, 415, 296, 39);
		frame.getContentPane().add(lblSqlExecutionResult);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
		scrollPane.setBounds(133, 458, 1115, 333);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnClearResultWindow = new JButton("Clear Result Window");
		btnClearResultWindow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClearResultWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			RemoveTable();
				}
		});
		btnClearResultWindow.setForeground(Color.BLACK);
		btnClearResultWindow.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnClearResultWindow.setBackground(Color.YELLOW);
		btnClearResultWindow.setBounds(152, 802, 207, 39);
		frame.getContentPane().add(btnClearResultWindow);
		sqlbox = new JTextField();
		sqlbox.setFont(new Font("Malgun Gothic", Font.BOLD, 25));
		sqlbox.setBounds(704, 50, 634, 196);
		frame.getContentPane().add(sqlbox);
		sqlbox.setColumns(10);
		
		textField = new JTextField();
		textField.setFont(new Font("Malgun Gothic", Font.BOLD, 22));
		textField.setForeground(Color.RED);
		textField.setBackground(Color.BLACK);
		textField.setBounds(10, 342, 1328, 73);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
