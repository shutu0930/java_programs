import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Eric McCreath 2015 GPL 

public class PassTheMessage {
	JTextField iptextfield, messagetextfield;
	JButton sendbutton;
	JFrame jframe;
	NetServer netserver;
	
	public PassTheMessage() {
		netserver = new NetServer(this);
		jframe = new JFrame("Pass The Message");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainpanel = new JPanel();
		mainpanel.add(new JLabel("This IP Adddress:"));
		mainpanel.add(new JLabel(NetServer.getThisIp()));
		mainpanel.add(new JLabel("Send to IP Address:"));
		mainpanel.add(iptextfield = new JTextField(10));
		mainpanel.add(new JLabel("The Message:"));
		mainpanel.add(messagetextfield = new JTextField(20));
		mainpanel.add(sendbutton = new JButton("SEND"));

		sendbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Press");
				TheMessage message = new TheMessage(NetServer.getThisIp(), messagetextfield.getText());
				try {
					NetServer.sendMessage(iptextfield.getText(),message);
				} catch (MessageProblem e) {
					JOptionPane.showMessageDialog(jframe,
						    "Problem Sending Message");

				}
				
			}
		});
		
		jframe.getContentPane().add(mainpanel);
		jframe.pack();
		jframe.setVisible(true);
	}
	
	
	public void showMessage(TheMessage message) {
		JOptionPane.showMessageDialog(jframe,
			    (message == null ? "Empty Message" : message.show()));
	}
	
	public static void main(String[] args) {
		PassTheMessage ptm = new PassTheMessage();

	}
}
