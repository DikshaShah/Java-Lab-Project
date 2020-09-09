package login;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection_package.ConnectionClass;
import dashboard.DashBoard;

import javax.swing.*;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener{
	JLabel  usernameL,passwordL,notification; 
	JTextField usernameT, passwordT;
	JButton submit,cancel;
	Connection con;
	@SuppressWarnings("static-access")
	public Login()
	{
		con = ConnectionClass.doConnect();
		this.setVisible(true);
		this.setSize(300, 500);
		this.setLocation(100, 150);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		//setBackground(Color.LIGHT_GRAY);
		Image logoI = new ImageIcon("F:\\eclipse workspace\\JavaLabProject\\src\\login\\logo.png").getImage();
		Image logoII = logoI.getScaledInstance(200,300,Image.SCALE_SMOOTH);
		JLabel logoL = new JLabel("");
		logoL.setIcon(new ImageIcon(logoII));
		this.add(logoL);
		usernameL = new JLabel("Enter Username"); 
		passwordL = new JLabel("Enter Password");
		notification = new JLabel("");
		usernameT = new JTextField(30);
		passwordT = new JTextField(30);
		submit = new JButton("Submit");
		cancel = new JButton("Cancel");
		submit.addActionListener(this);
		cancel.addActionListener(this);
		this.add(usernameL);
		this.add(usernameT);
		this.add(passwordL);
		this.add(passwordT);
		this.add(submit);
		this.add(cancel);
		this.add(notification);
		this.usernameL.setLocation(25, 100);
        this.passwordL.setLocation(25, 150);
        this.usernameT.setLocation(45, 100);
        this.passwordT.setLocation(45, 150);
        this.submit.setLocation(45,200);
        this.cancel.setLocation(120,200);
	}
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("Cancel"))
		System.exit(1);
		else
		{
			PreparedStatement pst;
			if(usernameT.getText().equals("") || passwordT.getText().equals(""))
			{
				notification.setText("Please Enter Information");
			}
			else
			{
			  try {
					pst = con.prepareStatement("select password from logininfo where username = ?");
					pst.setString(1, usernameT.getText());
					ResultSet res = pst.executeQuery();
					String cresult_pass = "notFound", cresult_capacity = "notFound";
					while(res.next())
					{
						cresult_pass = res.getString("password");
					}
					if(cresult_pass.equals(passwordT.getText()))
					{
						this.setVisible(false);
						new DashBoard();
					}
					else
						notification.setText("Incorrect Details");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}	
		}
	  public static void main(String args[]) throws Exception
	   {
		  SwingUtilities.invokeAndWait(new Runnable(){
			        public void run()
			        {
			        	new Login();
			        }
				  } ); 
	   }

}
