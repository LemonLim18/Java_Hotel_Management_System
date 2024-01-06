package HotelManagementSystem;

import java.awt.*;
import java.awt.EventQueue;


import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.*;
import java.awt.event.ActionEvent;



public class CheckOut extends JFrame{
	Connection conn = null;
	PreparedStatement pst = null;
	private JPanel contentPane;
	private JTextField t1;
	Choice c1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckOut frame = new CheckOut();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void close(){
		this.dispose();
	}

	// CheckOut MAIN FUNCTION
	public CheckOut() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Window Dimension
		setBounds(355, 150, 800, 545);

		// Make the JFrame non-resizable
		setResizable(false);

		// Load the background image
		ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/sunset.jpg"));
		Image i3 = i1.getImage().getScaledInstance(800, 550,Image.SCALE_DEFAULT);
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
// End of the background image

		// WHITE CONTAINER
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
		componentsPane.setBounds(150, 85, 500, 320);
		// END OF WHITE CONTAINER
		// END OF WHITE CONTAINER

		JLabel lblCheckOut = new JLabel("CUSTOMER CHECK-OUT");
		lblCheckOut.setFont(new Font("Rambla", Font.BOLD, 20));
		lblCheckOut.setBounds(135, 35, 250, 35);
		componentsPane.add(lblCheckOut);
		
		JLabel lblName = new JLabel("<html>Customer ID <span style='color:red;'>*</span></html>");
		lblName.setBounds(80, 105, 80, 14);
		componentsPane.add(lblName);
                
		c1 = new Choice();
		try{
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			while(rs.next()){
				c1.add(rs.getString("number"));
			}
		}catch(Exception e){ }
		c1.setBounds(250, 105, 156, 20);
		componentsPane.add(c1);

		JButton l2 = new JButton("🔍");
		l2.setBounds(416,150,46,20);
		componentsPane.add(l2);

		l2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent ae){
				try{
					conn c = new conn();
					String number = c1.getSelectedItem();
					ResultSet rs = c.s.executeQuery("select * from customer where number = "+number);

					if(rs.next()){
						System.out.println("clicked");
						t1.setText(rs.getString("room"));
					}
				}catch(Exception e){ }
			}
		});

		JLabel lblRoomNumber = new JLabel("<html>Room Number <span style='color:red;'>*</span></html>");
		lblRoomNumber.setBounds(80, 150, 100, 20);
		componentsPane.add(lblRoomNumber);


		t1 = new JTextField();
		t1.setBounds(250, 150, 156, 20);
		componentsPane.add(t1);
		t1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		t1.setColumns(10);

		// CHECK OUT
		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = c1.getSelectedItem(); //Customer ID
				String roomNum = t1.getText(); //Room Number

				if (roomNum.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please click the search button to get the customer's room number.");
					return;
				}

				// Confirmation dialog
				JPanel message = new JPanel();
				message.setPreferredSize(new Dimension(280, 40)); // Set your preferred dimensions
				message.setLayout(new BorderLayout());

				JLabel text = new JLabel("<html><center>Are you sure to check out the customer?<br> This process cannot be undo.</center></html>", JLabel.CENTER);
				message.add(text);

				int dialogResult = JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.NO_OPTION){
					// If user choose NO, then return and do nothing
					return;
				}

				conn c = new conn();
				String deposit = "";

				// Retrieve the pending amount of the selected Customer ID
				try {
					ResultSet rs5 = c.s.executeQuery("select * from customer where room='"+roomNum+"'");
					if(rs5.next()) {
						deposit = rs5.getString("deposit");
					}
				} catch(SQLException ee) {
					ee.printStackTrace();
				}
				System.out.println(deposit);

				// Remove the customer from the CUSTOMER TABLE
				String deleteSQL = "Delete from customer where number = '"+id+"'";
				// Update the room status back to AVAILABLE
				String q2 = "update room set availability = 'Available' where roomnumber = '"+roomNum+"'";


				try{
					c.s.executeUpdate(deleteSQL);
					c.s.executeUpdate(q2);
					JOptionPane.showMessageDialog(null, "Please return the customer with deposit: RM"+deposit);
					JOptionPane.showMessageDialog(null, "Check Out Successful");
					new Reception().setVisible(true);
					setVisible(false);
				}catch(SQLException e1){
					System.out.println(e1.getMessage());
				}
			}
		});
		btnCheckOut.setBounds(110, 223, 123, 30);
		btnCheckOut.setBackground(Color.BLACK);
		btnCheckOut.setForeground(Color.WHITE);
		componentsPane.add(btnCheckOut);

		// Hover Effect for Button
		btnCheckOut.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCheckOut.setBackground(Color.WHITE);
				btnCheckOut.setForeground(new Color(0x964404));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCheckOut.setBackground(Color.BLACK); // Original color of background
				btnCheckOut.setForeground(Color.WHITE); // Original color of font
			}
		});
		// Hover Effect for Button

		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setBounds(270, 223, 123, 30);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		componentsPane.add(btnExit);

		// Hover Effect for Button
		btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(Color.WHITE);
				btnExit.setForeground(new Color(0x964404));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(Color.BLACK); // Original color of background
				btnExit.setForeground(Color.WHITE); // Original color of font
			}
		});
		// Hover Effect for Button

		// Add the componentsPane(that contains all the components) to the contentPane
		contentPane.add(componentsPane);
	}

}