package HotelManagementSystem;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


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
import net.proteanit.sql.DbUtils;

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

	public CustomerInfo() throws SQLException {
		//conn = Javaconnect.getDBConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(200, 100, 1100, 660); // DIMENSION OF WINDOW
		setResizable(false); // Make the JFrame non-resizable

		// Load the image
		ImageIcon photo  = new ImageIcon(ClassLoader.getSystemResource("icons/purple sea.png"));
		Image resizedPhoto = photo.getImage().getScaledInstance(1100, 650, Image.SCALE_DEFAULT); // Scale it to the size of the frame
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
		//End of background image

		// WHITE CONTAINER(Components)
		// Set the border radius to the white container
		JPanel componentsPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				if (!(g instanceof Graphics2D)) {
					return;
				}
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// Set the white background to translucent
				g2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
				g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 45, 45);
				super.paintComponent(g);
			}
		};
		componentsPane.setOpaque(false);

		componentsPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0, true));
		componentsPane.setLayout(null);
		componentsPane.setBounds(105, 65, 870, 505);
		// END OF WHITE CONTAINER

		// TABLE INITIALIZATION
		table = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// This causes all cells to be not editable
				return false;
			}
		}; //initialize the table for the first use//initialize the table for the first use
		table.setBounds(25, 70, 820, 340);
		table.setShowGrid(false);
		table.setRowHeight(25);
		Color semiTransparentColor = new Color(252, 252, 252, 132);
		table.setBackground(semiTransparentColor);
		table.setOpaque(false);
		Font tableFont = new Font("Sans Serif", Font.PLAIN, 12);
		table.setFont(tableFont);
		componentsPane.add(table);

		conn c = new conn();
		String displayCustomersql = "select * from customer";
		ResultSet rs = c.s.executeQuery(displayCustomersql);
		table.setModel(DbUtils.resultSetToTableModel(rs));




		// RETURN BUTTON
		JButton btnExit = new JButton("Back");
		btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(Color.WHITE); // WHITE BG when mouse hovers over
				btnExit.setForeground(new Color(64, 26, 241)); // BLACK FONT when mouse hovers over
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
		btnExit.setBounds(360, 448, 120, 30);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		componentsPane.add(btnExit);
		// END OF RETURN BUTTON

		JButton btnId = new JButton("ID Document");
		btnId.setBounds(3, 31, 150, 30);
		btnId.setForeground(new Color(72, 41, 245));
		btnId.setBorder(null);
		btnId.setContentAreaFilled(false);
		componentsPane.add(btnId);
		btnId.setToolTipText("Sort according to ID Document");

		// Add ActionListener to the button
		btnId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Execute a SQL query to fetch the data sorted by ID
					String sql = "SELECT * FROM customer ORDER BY document";
					ResultSet rs = c.s.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		btnId.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnId.setForeground(new Color(173, 42, 250)); // Change the font color when the mouse hovers over
				btnId.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnId.setForeground(new Color(72, 41, 245)); // Change the font color back when the mouse leaves
				btnId.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		JButton customerId = new JButton("Customer ID");
		customerId.setBounds(105, 40, 150, 14);
		customerId.setForeground(new Color(72, 41, 245));
		customerId.setBorder(null);
		customerId.setContentAreaFilled(false);
		customerId.setToolTipText("Sort according to Customer ID");
		componentsPane.add(customerId);

		customerId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Execute a SQL query to fetch the data sorted by ID
					String sql = "SELECT * FROM customer ORDER BY number";
					ResultSet rs = c.s.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		customerId.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				customerId.setForeground(new Color(173, 42, 250)); // Change the font color when the mouse hovers over
				customerId.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				customerId.setForeground(new Color(72, 41, 245)); // Change the font color back when the mouse leaves
				customerId.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		JButton name = new JButton("Name");
		name.setBounds(249, 40, 65, 14);
		name.setForeground(new Color(72, 41, 245));
		name.setBorder(null);
		name.setContentAreaFilled(false);
		name.setToolTipText("Sort according to Customer Name");
		componentsPane.add(name);

		name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Execute a SQL query to fetch the data sorted by ID
					String sql = "SELECT * FROM customer ORDER BY name";
					ResultSet rs = c.s.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		name.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				name.setForeground(new Color(173, 42, 250)); // Change the font color when the mouse hovers over
				name.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				name.setForeground(new Color(72, 41, 245)); // Change the font color back when the mouse leaves
				name.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		JButton gender = new JButton("Gender");
		gender.setBounds( 360, 40, 46, 14);
		gender.setForeground(new Color(72, 41, 245));
		gender.setBorder(null);
		gender.setContentAreaFilled(false);
		gender.setToolTipText("Sort according to Gender");
		componentsPane.add(gender);

		gender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Execute a SQL query to fetch the data sorted by ID
					String sql = "SELECT * FROM customer ORDER BY gender";
					ResultSet rs = c.s.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		gender.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				gender.setForeground(new Color(173, 42, 250)); // Change the font color when the mouse hovers over
				gender.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				gender.setForeground(new Color(72, 41, 245)); // Change the font color back when the mouse leaves
				gender.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		JButton country = new JButton("Country");
		country.setBounds(460, 40, 46, 14);
		country.setForeground(new Color(72, 41, 245));
		country.setBorder(null);
		country.setContentAreaFilled(false);
		country.setToolTipText("Sort according to Country");
		componentsPane.add(country);

		country.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Execute a SQL query to fetch the data sorted by ID
					String sql = "SELECT * FROM customer ORDER BY country";
					ResultSet rs = c.s.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		country.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				country.setForeground(new Color(173, 42, 250)); // Change the font color when the mouse hovers over
				country.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				country.setForeground(new Color(72, 41, 245)); // Change the font color back when the mouse leaves
				country.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		JButton lblRoom = new JButton("Room");
		lblRoom.setBounds(564, 40, 46, 14);
		lblRoom.setForeground(new Color(72, 41, 245));
		lblRoom.setBorder(null);
		lblRoom.setContentAreaFilled(false);
		lblRoom.setToolTipText("Sort according to Room Number");
		componentsPane.add(lblRoom);

		lblRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Execute a SQL query to fetch the data sorted by ID
					String sql = "SELECT * FROM customer ORDER BY room";
					ResultSet rs = c.s.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		// Add a hover effect
		lblRoom.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblRoom.setForeground(new Color(173, 42, 250)); // Change the font color when the mouse hovers over
				lblRoom.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblRoom.setForeground(new Color(72, 41, 245)); // Change the font color back when the mouse leaves
				lblRoom.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		JButton lblStatus = new JButton("Check-In Date");
		lblStatus.setBounds(640, 40, 100, 14);
		lblStatus.setForeground(new Color(72, 41, 245));
		lblStatus.setToolTipText("Sort according to Check-In Date");
		lblStatus.setBorder(null);
		lblStatus.setContentAreaFilled(false);
		componentsPane.add(lblStatus);

		lblStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Execute a SQL query to fetch the data sorted by ID
					String sql = "SELECT * FROM customer ORDER BY checkintime";
					ResultSet rs = c.s.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		lblStatus.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblStatus.setForeground(new Color(173, 42, 250)); // Change the font color when the mouse hovers over
				lblStatus.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblStatus.setForeground(new Color(72, 41, 245)); // Change the font color back when the mouse leaves
				lblStatus.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});


		JButton lblNewLabel_1 = new JButton("Deposit Amt.");
		lblNewLabel_1.setBounds(742, 40, 100, 14);
		lblNewLabel_1.setForeground(new Color(72, 41, 245));
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setContentAreaFilled(false);
		lblNewLabel_1.setToolTipText("Sort according to Deposit Amount");
		componentsPane.add(lblNewLabel_1);

		lblNewLabel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Execute a SQL query to fetch the data sorted by ID
					String sql = "SELECT * FROM customer ORDER BY deposit";
					ResultSet rs = c.s.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		lblNewLabel_1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblNewLabel_1.setForeground(new Color(173, 42, 250)); // Change the font color when the mouse hovers over
				lblNewLabel_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				lblNewLabel_1.setForeground(new Color(72, 41, 245)); // Change the font color back when the mouse leaves
				lblNewLabel_1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		// Add all the components that we insert into the componentsPane into the contentPane container
		contentPane.add(componentsPane);
	}
}

