package HotelManagementSystem;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

	public CustomerInfo() throws SQLException {
		//conn = Javaconnect.getDBConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(200, 100, 1100, 650); // DIMENSION OF WINDOW
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
		componentsPane.setBounds(90, 65, 932, 490);
		// END OF WHITE CONTAINER

		// TABLE INITIALIZATION
		table = new JTable(); //initialize the table for the first use
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
			scrollPane.setBounds(15, 55, 900, 370);

			// Add the JScrollPane to the content pane
			componentsPane.add(scrollPane);

		} catch(Exception e) {
			e.printStackTrace();
		}

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
		btnExit.setBounds(380, 443, 120, 30);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		componentsPane.add(btnExit);
		// END OF RETURN BUTTON

		lblId = new JLabel("ID");
		lblId.setBounds(65, 23, 44, 14);
		lblId.setForeground(new Color(72, 41, 245));
		componentsPane.add(lblId);
                
		JLabel l1 = new JLabel("Number");
		l1.setBounds(160, 23, 46, 14);
		l1.setForeground(new Color(72, 41, 245));
		componentsPane.add(l1);
		
		lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(275, 23, 65, 14);
		lblNewLabel.setForeground(new Color(72, 41, 245));
		componentsPane.add(lblNewLabel);
		
		lblGender = new JLabel("Gender");
		lblGender.setBounds( 380, 23, 46, 14);
		lblGender.setForeground(new Color(72, 41, 245));
		componentsPane.add(lblGender);
		
		lblCountry = new JLabel("Country");
		lblCountry.setBounds(485, 23, 46, 14);
		lblCountry.setForeground(new Color(72, 41, 245));
		componentsPane.add(lblCountry);
		
		lblRoom = new JLabel("Room");
		lblRoom.setBounds(605, 23, 46, 14);
		lblRoom.setForeground(new Color(72, 41, 245));
		componentsPane.add(lblRoom);
		
		lblStatus = new JLabel("Check-in Date");
		lblStatus.setBounds(695, 23, 100, 14);
		lblStatus.setForeground(new Color(72, 41, 245));
		componentsPane.add(lblStatus);
		
		lblNewLabel_1 = new JLabel("Deposit");
		lblNewLabel_1.setBounds(825, 23, 100, 14);
		lblNewLabel_1.setForeground(new Color(72, 41, 245));
		componentsPane.add(lblNewLabel_1);

		// Add all the components that we insert into the componentsPane into the contentPane container
		contentPane.add(componentsPane);
	}
}

