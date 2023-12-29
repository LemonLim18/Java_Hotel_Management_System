package HotelManagementSystem;

import java.awt.*;
import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Image;
import java.sql.*;	
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(530, 200, 850, 550);

		// Make the JFrame non-resizable
		setResizable(false);

		// Load the image
		ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/beach 2.jpg"));
		Image i3 = i1.getImage().getScaledInstance(850, 550, Image.SCALE_DEFAULT); // Scale it to the size of the frame
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
		componentsPane.setBounds(170, 30, 500, 470);

		// Add the components to the componentsPane instead of the contentPane
		JLabel lblName = new JLabel("NEW CUSTOMER");
		lblName.setFont(new Font("Rambla", Font.BOLD, 20));
		lblName.setBounds(170, 11, 280, 53);
		componentsPane.add(lblName);

		JLabel lblId = new JLabel("ID :");
		lblId.setBounds(80, 76, 200, 14);
		componentsPane.add(lblId);

		comboBox = new JComboBox(new String[] {"Passport", "Identification Card", "Driving License", "Voter ID"});
		comboBox.setBounds(280, 73, 150, 20);
		componentsPane.add(comboBox);

		JLabel l2 = new JLabel("Number :");
		l2.setBounds(80, 111, 200, 14);
		componentsPane.add(l2);

		//	Number
		t1 = new JTextField();
		t1.setBounds(280, 111, 150, 20);
		componentsPane.add(t1);
		t1.setColumns(10);

		JLabel lblName_1 = new JLabel("Name :");
		lblName_1.setBounds(80, 151, 200, 14);
		componentsPane.add(lblName_1);

		//	Name
		t2 = new JTextField();
		t2.setBounds(280, 151, 150, 20);
		componentsPane.add(t2);
		t2.setColumns(10);

		JLabel lblGender = new JLabel("Gender :");
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

		JLabel lblCountry = new JLabel("Country :");
		lblCountry.setBounds(80, 231, 200, 14);
		componentsPane.add(lblCountry);

		JLabel lblReserveRoomNumber = new JLabel("Allocated Room Number :");
		lblReserveRoomNumber.setBounds(80, 274, 200, 14);
		componentsPane.add(lblReserveRoomNumber);

		c1 = new Choice();
		try{
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from room");
			while(rs.next()){
				c1.add(rs.getString("room_number"));
			}
		}catch(Exception ignored){ }

		c1.setBounds(280, 274, 150, 20);
		componentsPane.add(c1);

		JLabel lblCheckInStatus = new JLabel("Check-In Date :");
		lblCheckInStatus.setBounds(80, 316, 200, 14);
		componentsPane.add(lblCheckInStatus);

		JLabel lblDeposite = new JLabel("Deposit :");
		lblDeposite.setBounds(80, 359, 200, 14);
		componentsPane.add(lblDeposite);

		//	Country
		t3 = new JTextField();
		t3.setBounds(280, 231, 150, 20);
		componentsPane.add(t3);
		t3.setColumns(10);

		//	Check-In
		t5 = new JTextField();
		t5.setBounds(280, 316, 150, 20);
		componentsPane.add(t5);
		t5.setColumns(10);

		//	Deposit
		t6 = new JTextField();
		t6.setBounds(280, 359, 150, 20);
		componentsPane.add(t6);
		t6.setColumns(10);

		// Upon the submission event, the input data are collected and submitted to the database
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					String s7 =  t5.getText();
					String s8 =  t6.getText();
					String q1 = "insert into customer values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"')";
					String q2 = "update room set availability = 'Occupied' where room_number = "+s6;
					c.s.executeUpdate(q1);
					c.s.executeUpdate(q2);

					JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
					new Reception().setVisible(true);
					setVisible(false);
				}catch(SQLException e1){
					System.out.println(e1.getMessage());
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

		// Return Button

		// Custom JButton class
		class RoundButton extends JButton {
			public RoundButton(String label) {
				super(label);
				setOpaque(false); // As the paintComponent method will handle the rendering
			}

			@Override
			protected void paintComponent(Graphics g) {
				int diameter = Math.min(getWidth(), getHeight());
				g.setColor(getBackground());
				g.fillOval(getWidth() / 2 - diameter / 2, getHeight() / 2 - diameter / 2, diameter, diameter);

				super.paintComponent(g);
			}
		}

		// Usage
		RoundButton btnExit = new RoundButton("X");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setBounds(270, 415, 125, 30);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		btnExit.setOpaque(false);
		componentsPane.add(btnExit);

		// Add the componentsPane to the contentPane
		contentPane.add(componentsPane);
	}
}