import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

//Eric McCreath 2015 GPL 



public class NetServer implements Runnable {

	private static final int MAXDATASIZE = 1024;
	private static final int SERVERPORT = 5247;

	Thread thread;
	PassTheMessage ptm;

	public NetServer(PassTheMessage ptm) {
		this.ptm = ptm;
		thread = new Thread(this);
		thread.start();
	}

	public static String getThisIp() {
		try { 
			Enumeration<NetworkInterface> inter = NetworkInterface.getNetworkInterfaces();
			
			  while ( inter.hasMoreElements() ) {
 		            NetworkInterface face = (NetworkInterface) inter.nextElement();
 		            
		            Enumeration<InetAddress> inetAddrs = face.getInetAddresses();
		            while ( inetAddrs.hasMoreElements()) {
		                InetAddress address = inetAddrs.nextElement();
		                if (!address.isLoopbackAddress() && address.isSiteLocalAddress()) return address.getHostAddress();
		            }
			  }
			
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException | SocketException e) {
			return "problem obtaining local host IP address";
		}
	}

	public static void sendMessage(String ip, TheMessage message)
			throws MessageProblem {
		try {
			Socket sock = new Socket(InetAddress.getByName(ip), SERVERPORT);
			DataOutputStream stream = new DataOutputStream(
					sock.getOutputStream());
			stream.write(message.encode());
			stream.close();

		} catch (Exception e) {
			throw new MessageProblem();
		}
	}

	@Override
	public void run() {
		byte data[] = new byte[MAXDATASIZE];
		ServerSocket ssock;
		try {
			ssock = new ServerSocket(SERVERPORT);
			while (true) {
				Socket sock = ssock.accept();
				DataInputStream is = new DataInputStream(sock.getInputStream());
				int messagelen = is.read(data);
				is.close();
				TheMessage message = TheMessage.decode(data,messagelen);
				ptm.showMessage(message);
				try {
					// Only send the message down the line if it wasn't
					// originally from this server
					if (!message.fromIP.getHostAddress().equals(getThisIp()))
						sendMessage(ptm.iptextfield.getText(), message);

				} catch (MessageProblem e) {
					System.err.printf("send message problem");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
