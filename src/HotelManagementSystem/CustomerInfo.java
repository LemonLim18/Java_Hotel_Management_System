package HotelManagementSystem;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import java.sql.*;	
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class CustomerInfo extends JFrame {
	Connection conn = null;
	private JPanel contentPane;
	private JLabel lblId;
	private JLabel lblNewLabel;
	private JLabel lblGender;
	private JTable table;
	private JLabel lblCountry;
	private JLabel lblRoom;
	private JLabel lblStatus;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerInfo frame = new CustomerInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void close()
	{
		this.dispose();
	}

	private void loadData() {
		try{
			conn c  = new conn();
			String displayCustomersql = "select * from Customer";
			ResultSet rs = c.s.executeQuery(displayCustomersql);

			// Get the ResultSetMetaData
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			// Get the column names
			Vector<String> columnNames = new Vector<String>();
			for (int column = 1; column <= columnCount; column++) {
				columnNames.add(metaData.getColumnName(column));
			}

			// Get the row data
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					vector.add(rs.getObject(columnIndex));
				}
				data.add(vector);
			}

			// Create a DefaultTableModel with the data and column names
			DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// This causes all cells to be uneditable
					return false;
				}
			};

			// Set the table model
			table.setModel(dtm);

			// Hide the table header
			table.setTableHeader(null);

			// Create a JPanel and add the table to it
			JPanel tablePanel = new JPanel(new BorderLayout());
			tablePanel.add(table);

			// Create a JScrollPane for the JPanel
			JScrollPane scrollPane = new JScrollPane(tablePanel);

			// tableDimension
			scrollPane.setBounds(0, 85, 902, 370);

			// Add the JScrollPane to the content pane
			contentPane.add(scrollPane);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public CustomerInfo() throws SQLException {
		//conn = Javaconnect.getDBConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(530, 200, 900, 600); // DIMENSION OF WINDOW
		setResizable(false); // Make the JFrame non-resizable

		// Load the image
		ImageIcon photo  = new ImageIcon(ClassLoader.getSystemResource("icons/beach 3.jpg"));
		Image resizedPhoto = photo.getImage().getScaledInstance(900, 630, Image.SCALE_DEFAULT); // Scale it to the size of the frame
		ImageIcon bgPic = new ImageIcon(resizedPhoto);

		// Create a new JPanel with overridden paintComponent method
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Draw the image on the panel
				g.drawImage(bgPic.getImage(), 0, 0, this);
			}
		};

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// TABLE INITIALIZATION
		table = new JTable(); //initialize the table for the first use
		loadData();

		// END OF INITIALIZATION METHOD/CONSTRUCTOR

		// RETURN BUTTON
		JButton btnExit = new JButton("Back");
		btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(Color.WHITE); // WHITE BG when mouse hovers over
				btnExit.setForeground(Color.BLACK); // BLACK FONT when mouse hovers over
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(Color.BLACK); // Original color of background
				btnExit.setForeground(Color.WHITE); // Original color of font
			}
		});

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setBounds(375, 500, 120, 30);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
		// END OF RETURN BUTTON

		lblId = new JLabel("ID");
		lblId.setBounds(53, 56, 44, 14);
		lblId.setForeground(Color.WHITE);
		contentPane.add(lblId);
                
		JLabel l1 = new JLabel("Number");
		l1.setBounds(150, 56, 46, 14);
		l1.setForeground(Color.WHITE);
		contentPane.add(l1);
		
		lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(265, 56, 65, 14);
		lblNewLabel.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel);
		
		lblGender = new JLabel("Gender");
		lblGender.setBounds(360, 56, 46, 14);
		lblGender.setForeground(Color.WHITE);
		contentPane.add(lblGender);
		
		lblCountry = new JLabel("Country");
		lblCountry.setBounds(480, 56, 46, 14);
		lblCountry.setForeground(Color.WHITE);
		contentPane.add(lblCountry);
		
		lblRoom = new JLabel("Room");
		lblRoom.setBounds(600, 56, 46, 14);
		lblRoom.setForeground(Color.WHITE);
		contentPane.add(lblRoom);
		
		lblStatus = new JLabel("Check-in Status");
		lblStatus.setBounds(685, 56, 100, 14);
		lblStatus.setForeground(Color.WHITE);
		contentPane.add(lblStatus);
		
		lblNewLabel_1 = new JLabel("Deposit");
		lblNewLabel_1.setBounds(810, 56, 100, 14);
		lblNewLabel_1.setForeground(Color.WHITE);
		contentPane.add(lblNewLabel_1);
                
		getContentPane().setBackground(Color.WHITE);
	}
}

