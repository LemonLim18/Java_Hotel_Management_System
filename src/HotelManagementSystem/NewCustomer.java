package HotelManagementSystem;

import java.awt.*;
import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Image;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;


public class NewCustomer extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	private JPanel contentPane;
	private JTextField t1,t2,t3,t4,t5,t6;
	JComboBox comboBox;
	JRadioButton r1,r2;
	Choice c1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCustomer frame = new NewCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewCustomer() throws SQLException {
		//	Beginning of the background image
		setBounds(340, 115, 850, 630);

		// Make the JFrame non-resizable
		setResizable(false);

		// Load the image
		ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/beach 3.jpg"));
		Image i3 = i1.getImage().getScaledInstance(850, 630, Image.SCALE_DEFAULT); // Scale it to the size of the frame
		ImageIcon i2 = new ImageIcon(i3);

		// Create a new JPanel with overridden paintComponent method
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Draw the image on the panel
				g.drawImage(i2.getImage(), 0, 0, this);
			}
		};

		setContentPane(contentPane);
		contentPane.setLayout(null);
//	End of the Background Image

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
		componentsPane.setBounds(170, 70, 500, 470);

		// Add the components to the componentsPane instead of the contentPane
		JLabel lblName = new JLabel("NEW CUSTOMER");
		lblName.setFont(new Font("Rambla", Font.BOLD, 20));
		lblName.setBounds(170, 11, 280, 53);
		componentsPane.add(lblName);

		JLabel lblId = new JLabel("<html>ID Document <span style='color:red;'>*</span></html>");
		lblId.setBounds(80, 76, 200, 14);
		componentsPane.add(lblId);

		comboBox = new JComboBox(new String[] {"Passport", "Identification Card", "Driving License", "Voter ID"});
		comboBox.setBounds(280, 73, 150, 20);
		comboBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		componentsPane.add(comboBox);

		JLabel l2 = new JLabel("<html>Customer ID <span style='color:red;'>*</span></html>");
		l2.setBounds(80, 115, 200, 14);
		componentsPane.add(l2);

		//	ID NUMBER
//		try{
//			conn c = new conn();
//			ResultSet rs3 = c.s.executeQuery("SELECT MAX(number) as max_number FROM customer");
//			int nextNumber=0;
//			// If there is a row inside the table
//			if(rs3.next()) {
//				// get the maximum number from the data
//				int maxNumber = rs3.getInt("max_number");
//				// say if there is no number or null or 0 in the data
//				if (maxNumber == 0) {
//					nextNumber = 1000; // Default value will be 1000 if no customers exist yet
//				} else {
//					// else the number will continue after the last number
//					nextNumber = rs3.getInt("max_number") + 1;
//				}
//			}
//			System.out.println(nextNumber);
//			t1 = new JTextField(String.valueOf(nextNumber));
//			t1.setBounds(280, 111, 150, 20);
//			componentsPane.add(t1);
//			t1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
//			t1.setColumns(10);
//		}catch(Exception ignored){ }

		t1 = new JTextField();
		t1.setBounds(280, 111, 150, 20);
		componentsPane.add(t1);
		t1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		t1.setColumns(10);

		JLabel lblName_1 = new JLabel("<html>Name <span style='color:red;'>*</span></html>");
		lblName_1.setBounds(80, 151, 200, 14);
		componentsPane.add(lblName_1);

		//	NAME
		t2 = new JTextField();
		t2.setBounds(280, 151, 150, 20);
		componentsPane.add(t2);
		t2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		t2.setColumns(10);

		JLabel lblGender = new JLabel("<html>Gender <span style='color:red;'>*</span></html>");
		lblGender.setBounds(80, 191, 200, 14);
		componentsPane.add(lblGender);

		r1 = new JRadioButton("Male");
		r1.setFont(new Font("Raleway", Font.BOLD, 14));
		// r1.setBackground(Color.WHITE);
		// Remove the white background and make the background transparent
		r1.setOpaque(false);
		r1.setBounds(280, 191, 70, 20);
		componentsPane.add(r1);

		r2 = new JRadioButton("Female");
		r2.setFont(new Font("Raleway", Font.BOLD, 14));
		// r2.setBackground(Color.WHITE);
		// Remove the white background and make the background transparent
		r2.setOpaque(false);
		r2.setBounds(350, 191, 80, 20);
		componentsPane.add(r2);

		// Create a ButtonGroup instance to impose click exclusion
		ButtonGroup bg = new ButtonGroup();

		// Add the radio buttons to the ButtonGroup
		bg.add(r1);
		bg.add(r2);

		JLabel lblCountry = new JLabel("<html>Country <span style='color:red;'>*</span></html>");
		lblCountry.setBounds(80, 231, 200, 14);
		componentsPane.add(lblCountry);

		JLabel lblReserveRoomNumber = new JLabel("<html>Allocated Room Number <span style='color:red;'>*</span></html>");
		lblReserveRoomNumber.setBounds(80, 274, 200, 14);
		lblReserveRoomNumber.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		componentsPane.add(lblReserveRoomNumber);

		c1 = new Choice();
		try{
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from room where availability='Available'");
			while(rs.next()){
				c1.add(rs.getString("roomnumber"));
			}
		}catch(Exception ignored){ }

		c1.setBounds(280, 274, 150, 20);
		componentsPane.add(c1);

		JLabel lblCheckInStatus = new JLabel("<html>Check-In Date <span style='color:red;'>*</span></html>");
		lblCheckInStatus.setBounds(80, 316, 200, 14);
		componentsPane.add(lblCheckInStatus);

		JLabel lblDeposit = new JLabel("<html>Deposit (RM) <span style='color:red;'>*</span></html>");
		lblDeposit.setBounds(80, 359, 200, 14);
		componentsPane.add(lblDeposit);

		//	Country
		t3 = new JTextField();
		t3.setBounds(280, 231, 150, 20);
		componentsPane.add(t3);
		t3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		t3.setColumns(10);

		//	Check-In
		t5 = new JTextField();
		t5.setBounds(280, 316, 150, 20);
		componentsPane.add(t5);
		t5.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		t5.setColumns(10);

		//	Deposit
		t6 = new JTextField();
		t6.setBounds(280, 359, 150, 20);
		componentsPane.add(t6);
		t6.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		t6.setColumns(10);

		// Upon the submission event, the input data are collected and submitted to the database
		// Add Customer Button
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Validation
				// Check if the ID method is selected
				if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().toString().isEmpty()) {
					JOptionPane.showMessageDialog(null, "ID method is required");
					return;
				}

				// Check if ID Number is empty
				if (t1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "ID number is required");
					return;
				}
				// Validate ID Number
				if (!t1.getText().matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "Please enter a valid ID number");
					return;
				}

				// Check if Name is empty
				if (t2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Name is required");
					return;
				}
				// Validate Name
				if (!t2.getText().matches("^[a-zA-Z\\s]+$")) {
					JOptionPane.showMessageDialog(null, "Please enter a valid name");
					return;
				}

				// Check if the Gender is selected
				if (!r1.isSelected() && !r2.isSelected()) {
					JOptionPane.showMessageDialog(null, "Gender is required");
					return;
				}

				// Check if Country is empty
				if (t3.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Country is required");
					return;
				}
				// Validate Country
				if (!t3.getText().matches("^[a-zA-Z\\s]+$")) {
					JOptionPane.showMessageDialog(null, "Please enter a valid country name");
					return;
				}

				// Check if the room number is allocated
				if (c1.getSelectedItem() == null || c1.getSelectedItem().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Allocated room number is required");
					return;
				}

				// Check if Check-In Date is empty
				if (t5.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Check-In Date is required");
					return;
				}
				// Validate Check-In Date
				if (!t5.getText().matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((19|20)\\d\\d)$")) {
					JOptionPane.showMessageDialog(null, "Please enter a valid date in the format dd/mm/yyyy");
					return;
				}

				// Check if Deposit is empty
				if (t6.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Deposit is required");
					return;
				}
				// Validate Deposit
				if (!t6.getText().matches("^\\d*(\\.\\d+)?$")) {
					JOptionPane.showMessageDialog(null, "Please enter a valid deposit amount");
					return;
				}

				// Establish connection with the database server
				conn c = new conn();
				String radio = null;

				if(r1.isSelected()){
					radio = "Male";
				} else if(r2.isSelected()){
					radio = "Female";
				}
				String s6 = c1.getSelectedItem();

				try{
					String s1 = (String)comboBox.getSelectedItem();
					String s2 =  t1.getText();
					String s3 =  t2.getText();
					String s4 =  radio;
					String s5 =  t3.getText();
					String s7 =  t5.getText(); // Check-In Date
					String s8 =  t6.getText();
					ResultSet rs5 = c.s.executeQuery("select * from room where roomnumber='"+s6+"'");


					String q1 = "insert into customer values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"')";
					String q2 = "update room set availability = 'Occupied' where roomnumber = "+s6;
					c.s.executeUpdate(q1);
					c.s.executeUpdate(q2);

					JOptionPane.showMessageDialog(null, "New Customer added Successfully.");

					new Reception().setVisible(true);
					setVisible(false);

				}catch(SQLException e1){
					System.out.println(e1.getMessage());
					if(e1.getErrorCode() == 1062) {
						JOptionPane.showMessageDialog(null, "The Customer ID entered already existed. Please enter another ID");
					}else {
						System.out.println(e1.getMessage());
					}
				}
				catch(NumberFormatException s){
					JOptionPane.showMessageDialog(null, "Please enter a valid Number");
				}
			}

		});

		btnNewButton.setBounds(110, 415, 125, 30);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.WHITE);
		componentsPane.add(btnNewButton);

		btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnNewButton.setBackground(Color.WHITE); // WHITE BG when mouse hovers over
				btnNewButton.setForeground(Color.BLACK); // BLACK FONT when mouse hovers over
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnNewButton.setBackground(Color.BLACK); // Original color of background
				btnNewButton.setForeground(Color.WHITE); // Original color of font
			}
		});
		// End of Add Customer Button


		// Exit or Return Button
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setBounds(270, 415, 125, 30);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		btnExit.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(Color.WHITE); //WHITE BG when mouse hovers over
				btnExit.setForeground(Color.BLACK); //BLACK FONT when mouse hovers over
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(Color.BLACK); // Original color of background
				btnExit.setForeground(Color.WHITE); // Original color of font
			}
		});
		componentsPane.add(btnExit);
		// End of Exit Button

		// Add the componentsPane(that contains all the components) to the contentPane
		contentPane.add(componentsPane);
	}
}