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
	private JTextField txt_ID;
	private JTextField txt_Room;
	private JTextField txt_Status;
	private JTextField txt_Date;
	private JTextField txt_Time;
	private JTextField txt_Payment;
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

		// Beginning of the background image
		setBounds(530, 200, 850, 630);

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
				g2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
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
		lblUpdateCheckStatus.setBounds(105, 31, 300, 53);
		componentsPane.add(lblUpdateCheckStatus);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(80, 115, 200, 14);
		componentsPane.add(lblNewLabel);
                
		c1 = new Choice();
		try{
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			while(rs.next()){
				c1.add(rs.getString("number"));
			}
		}catch(Exception e){ }
		c1.setBounds(280, 115, 140, 20);
		componentsPane.add(c1);
		
		JLabel lblNewLabel_1 = new JLabel("Room Number :");
		lblNewLabel_1.setBounds(80, 159, 107, 14);
		componentsPane.add(lblNewLabel_1);
                
		txt_ID = new JTextField();
		txt_ID.setBounds(280, 156, 140, 20);
		txt_ID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		componentsPane.add(txt_ID);
		
		JLabel lblNewLabel_2 = new JLabel("Name : ");
		lblNewLabel_2.setBounds(80, 204, 97, 14);
		componentsPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Checked-in Date :");
		lblNewLabel_3.setBounds(80, 246, 107, 14);
		componentsPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Amount Paid (RM) : ");
		lblNewLabel_4.setBounds(80, 291, 115, 14);
		componentsPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Pending Amount (RM) : ");
		lblNewLabel_5.setBounds(80, 332, 150, 14);
		componentsPane.add(lblNewLabel_5);

		txt_Status = new JTextField();
		txt_Status.setBounds(280, 201, 140, 20);
		componentsPane.add(txt_Status);
		txt_Status.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txt_Status.setColumns(10);
		
		txt_Date = new JTextField();
		txt_Date.setBounds(280, 246, 140, 20);
		componentsPane.add(txt_Date);
		txt_Date.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txt_Date.setColumns(10);
		
		txt_Time = new JTextField();
		txt_Time.setBounds(280, 288, 140, 20);
		componentsPane.add(txt_Time);
		txt_Time.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txt_Time.setColumns(10);
		
		txt_Payment = new JTextField();
		txt_Payment.setBounds(280, 329, 140, 20);
		componentsPane.add(txt_Payment);
		txt_Payment.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txt_Payment.setColumns(10);


		// Beginning of UPDATE BUTTON
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { 
				try{
					conn c = new conn();
                                
					String s1 = c1.getSelectedItem();
					String s2 = txt_ID.getText(); //room_number;
					String s3 = txt_Status.getText(); //name
					String s4 = txt_Date.getText(); //status;
					String s5 = txt_Time.getText(); //deposit
				
					c.s.executeUpdate("update customer set room_number = '"+s2+"', name = '"+s3+"', status = '"+s4+"', deposit = '"+s5+"' where number = '"+s1+"'");
                                
					JOptionPane.showMessageDialog(null, "Data Updated Successfully");
					new Reception().setVisible(true);
					setVisible(false);
				}catch(Exception ee){
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
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String s1 = c1.getSelectedItem();
					conn c = new conn();
					ResultSet rs1 = c.s.executeQuery("select * from customer where number = "+s1);

					while(rs1.next()){
						txt_ID.setText(rs1.getString("room_number"));
						txt_Status.setText(rs1.getString("name"));
						txt_Date.setText(rs1.getString("status"));
						txt_Time.setText(rs1.getString("deposit"));
					}
				}catch(Exception ignored){}

				try{
					String total = "";
					conn c  = new conn();
					ResultSet rs2 = c.s.executeQuery("select * from room where room_number = " + txt_ID.getText());
					while(rs2.next()){
						total = rs2.getString("price");
					}
					String paid = txt_Time.getText();
					int pending = Integer.parseInt(total)- Integer.parseInt(paid);

					txt_Payment.setText(Integer.toString(pending));

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
                
		contentPane.add(componentsPane);
	}

}