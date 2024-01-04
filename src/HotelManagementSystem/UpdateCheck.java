package HotelManagementSystem;

import java.awt.*;
import java.awt.EventQueue;

import java.sql.*;	
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateCheck extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	private JPanel contentPane;
	private Choice choice_roomNum;
//	private JTextField txt_Room;
	private JTextField txt_Name;
	private JTextField txt_Date;
	private JTextField txt_Deposit;
	private JTextField txt_Pending;
	private JTextField txt_Pay;
	Choice c1, c2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCheck frame = new UpdateCheck();
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

	public UpdateCheck() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Window Dimension
		setBounds(375, 120, 850, 630);

		// Make the JFrame non-resizable
		setResizable(false);

		// Load the image
		ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/chalet.jpg"));
		Image image3 = image1.getImage().getScaledInstance(850, 630, Image.SCALE_DEFAULT);  // Scale it to the size of the frame
		ImageIcon image2 = new ImageIcon(image3);

		// Create a new JPanel with overridden paintComponent method
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Draw the image on the panel
				g.drawImage(image2.getImage(), 0, 0, this);
			}
		};

		setContentPane(contentPane);
		contentPane.setLayout(null);
// End of background Image

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
				g2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.7f));
				g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 45, 45);
				super.paintComponent(g);
			}
		};
		componentsPane.setOpaque(false);

		componentsPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0, true));
		componentsPane.setLayout(null);
		componentsPane.setBounds(170, 70, 500 , 470);

		// Add the components to the componentsPane instead of the contentPane
		JLabel lblUpdateCheckStatus = new JLabel("CUSTOMER STATUS UPDATE");
		lblUpdateCheckStatus.setFont(new Font("Rambla", Font.BOLD, 20));
		lblUpdateCheckStatus.setBounds(110, 31, 300, 53);
		componentsPane.add(lblUpdateCheckStatus);
		
		JLabel lblNewLabel = new JLabel("<html>Customer ID <span style='color:red;'>*</span></html>");
		lblNewLabel.setBounds(80, 115, 200, 14);
		componentsPane.add(lblNewLabel);
                
		c1 = new Choice();
		try{
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			// list of values
			while(rs.next()){
				c1.add(rs.getString("number"));  //Import all the values from the database into the list of values
			}
		}catch(Exception e){ }
		c1.setBounds(280, 115, 140, 20);
		componentsPane.add(c1);


		JLabel lblNewLabel_2 = new JLabel("<html>Name <span style='color:red;'>*</span></html>");
		lblNewLabel_2.setBounds(80, 159, 97, 14);
		componentsPane.add(lblNewLabel_2);

		// Customer Name Field
		txt_Name = new JTextField();
		txt_Name.setBounds(280, 159, 140, 20);
		componentsPane.add(txt_Name);
		txt_Name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txt_Name.setColumns(10);


		JLabel lblNewLabel_1 = new JLabel("<html>Room Number <span style='color:red;'>*</span></html>");
		lblNewLabel_1.setBounds(80, 201, 107, 14);
		componentsPane.add(lblNewLabel_1);

		// Room Number Field
		choice_roomNum = new Choice();
		choice_roomNum.add("");  // Add an empty string as the first item.
		try {
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from room where availability='Available'");
			while(rs.next()) {
				// Retrieve the roomnumber value from the table
				choice_roomNum.add(rs.getString("roomnumber"));
			}
		}catch(Exception ignored) { }

		choice_roomNum.setBounds(280, 201, 140, 20);
		componentsPane.add(choice_roomNum);


		JLabel lblNewLabel_3 = new JLabel("<html>Check-In Date <span style='color:red;'>*</span></html>");
		lblNewLabel_3.setBounds(80, 246, 107, 14);
		componentsPane.add(lblNewLabel_3);

		// Checked-In Date Field
		txt_Date = new JTextField();
		txt_Date.setBounds(280, 246, 140, 20);
		componentsPane.add(txt_Date);
		txt_Date.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txt_Date.setColumns(10);

		// original amount paid
		JLabel lblNewLabel_4 = new JLabel("<html>Pending Amt (RM) <span style='color:red;'>*</span></html>");
		lblNewLabel_4.setBounds(80, 291, 115, 14);
		componentsPane.add(lblNewLabel_4);

		// original Amount Paid Field
		txt_Pending = new JTextField();
		txt_Pending.setBounds(280, 288, 140, 20);
		componentsPane.add(txt_Pending);
		txt_Pending.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txt_Pending.setColumns(10);

		// original pending amount
		JLabel lblNewLabel_5 = new JLabel("<html>Current Pay Amt (RM) <span style='color:red;'>*</span></html>");
		lblNewLabel_5.setBounds(80, 332, 150, 14);
		componentsPane.add(lblNewLabel_5);

		// original Pending Amount Field
		txt_Pay = new JTextField();
		txt_Pay.setBounds(280, 329, 140, 20);
		componentsPane.add(txt_Pay);
		txt_Pay.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txt_Pay.setColumns(10);

		// Beginning of UPDATE BUTTON
		JButton btnUpdate = new JButton("Update");
		// UPDATE ACTION
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { 
				try{
					conn c = new conn();
                                
					String s1 = c1.getSelectedItem();
					String s2 = choice_roomNum.getSelectedItem(); //s2 is the current latest ROOM NUMBER;
					String s3 = txt_Name.getText(); //Name
					String s4 = txt_Date.getText(); //Date;
					String s5 = txt_Pending.getText(); //Pending
					String s6 = txt_Pay.getText(); //Current Pay Amount
					String oldRoomNumber="";

					int latestPending = Integer.parseInt(s5) - Integer.parseInt(s6);
					if(latestPending<0) {
						JOptionPane.showMessageDialog(null, "Customer paid excessive amount of RM "+ -latestPending + ". Please return the change.");
						latestPending = 0;  //in case the pending amount is negative, due to the customer pays too much

					}

					// Retrieve the old room number from the room table
					ResultSet rs = c.s.executeQuery("SELECT room FROM customer WHERE number = '"+s1+"'");
					if (rs.next()) {
						oldRoomNumber = rs.getString(1); // Get the old room number
					}

					// Customer's room: room
					// Room's room: roomnumber

					// UPDATE CUSTOMER BOOKING INFO
					c.s.executeUpdate("update customer set room = '"+s2+"', name = '"+s3+"', checkintime = '"+s4+"', pending = '"+latestPending+"' where number = '"+s1+"'");

					// If the room number has changed, set the old room to 'Available' and new room to 'Occupied'
					if (!oldRoomNumber.equals(s2)) {
						c.s.executeUpdate("update room set availability = 'Occupied' where roomnumber = '"+s2+"'");
						c.s.executeUpdate("update room set availability = 'Available' where roomnumber = '"+oldRoomNumber+"'");
						System.out.println(s2);
					}else{  //If it's still the same, remain that room status as 'Occupied'
						c.s.executeUpdate("update room set availability = 'Occupied' where roomnumber = '"+s2+"'");
					}

					JOptionPane.showMessageDialog(null, "Data Updated Successfully");
					new Reception().setVisible(true);
					setVisible(false);
				}catch(Exception ee){
					JOptionPane.showMessageDialog(null, "Please insert all the values completely.");
					System.out.println(ee);
				}
			}
		});
		btnUpdate.setBounds(207, 398, 89, 30);
		btnUpdate.setBackground(Color.BLACK);
		btnUpdate.setForeground(Color.WHITE);
		componentsPane.add(btnUpdate);

		btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnUpdate.setBackground(Color.WHITE); // WHITE BG when mouse hovers over
				btnUpdate.setForeground(Color.BLACK); // BLACK FONT when mouse hovers over
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnUpdate.setBackground(Color.BLACK); // Original color of background
				btnUpdate.setForeground(Color.WHITE); // Original color of font
			}
		});
		// End of UPDATE BUTTON


		// Beginning of EXIT BUTTON
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setBounds(319, 398, 89, 30);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		componentsPane.add(btnExit);

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
		// End of EXIT BUTTON


		// Beginning of CHECK BUTTON
		JButton btnAdd = new JButton("Check");
		// CHECK ACTION
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String s1 = c1.getSelectedItem();
					conn c = new conn();
					// Retrieve that one specific data point
					ResultSet rs1 = c.s.executeQuery("select * from customer where number = "+s1);
					System.out.println(rs1);

					if(rs1.next()){
						// Make sure the newly-added-item for the check purpose can be added in again once
						String room = rs1.getString("room");
						boolean exists = false;
						for (int i = 0; i < choice_roomNum.getItemCount(); i++) {
							if (choice_roomNum.getItem(i).equals(room)) {
								exists = true;
								break;
							}
						}
						// if the listed value never exists before, then add in the value
						if (!exists) {
							choice_roomNum.add(room);
						}
						choice_roomNum.select(room);
						txt_Name.setText(rs1.getString("name"));
						txt_Date.setText(rs1.getString("checkintime"));
//						txt_Deposit.setText(rs1.getString("deposit"));  // Disable it cuz the CURRENT PAY TEXT FIELD IS NOW BLANK
					}
				}catch(Exception ignored){}

				try{
					String pending = "";
					conn c  = new conn();
					ResultSet rs2 = c.s.executeQuery("select * from customer where room = " + choice_roomNum.getSelectedItem());
					while(rs2.next()){
						// Price to pay
						pending = rs2.getString("pending");
					}

  					int pendingInt = Integer.parseInt(pending);
					boolean fullPayStatus = false;
					if(pendingInt<=0) {
						fullPayStatus = true; //if there's no remaining amount, fullPay is true
						pendingInt = 0; // if the customer gives more than what they pay make it zero too
					} else {
						fullPayStatus = false; // if there's still remaining amount, fullPay is false
					}
					System.out.println("Pending Amount:"+pendingInt); // For testing purpose
					// Remaining to be paid
					txt_Pending.setText(Integer.toString(pendingInt));

				}catch(Exception ignored){}
			}
		});

		btnAdd.setBounds(95, 398, 89, 30);
		btnAdd.setBackground(Color.BLACK);
		btnAdd.setForeground(Color.WHITE);
		componentsPane.add(btnAdd);

		btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnAdd.setBackground(Color.WHITE); // WHITE BG when mouse hovers over
				btnAdd.setForeground(Color.BLACK); // BLACK FONT when mouse hovers over
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnAdd.setBackground(Color.BLACK); // Original color of background
				btnAdd.setForeground(Color.WHITE); // Original color of font
			}
		});
		// End of CHECK BUTTON

		// Transfer all the added components back to the contentPane
		contentPane.add(componentsPane);
	}

}