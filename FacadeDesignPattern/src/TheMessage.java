import java.net.InetAddress;
import java.net.UnknownHostException;

//Eric McCreath 2015 GPL 


public class TheMessage {

	// TheMessage is made up of the text of the message sent and the IP address from 
	// which the message was originally sent from. 
	
	InetAddress fromIP;
	String text;

	public TheMessage(String ip, String t) {
		try {
			fromIP = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			fromIP = null;
		}
		text = t;
	}

	static TheMessage decode(byte data[], int messagelen) {
		String str = "";
		for (int i = 0; i < messagelen; i++)
			str += (char) data[i];
		System.out.println("Decoding : " + str);
		if (str.contains(":")) {
			String split[] = str.split(":");
			return new TheMessage(split[1], split[0]);
		} else {
			return null;
		}
	}

	byte[] encode() {
		String str = text + ":" + fromIP.getHostAddress();
		System.out.println("Encoded message : " + str);
		byte res[] = new byte[str.length()];
		for (int i = 0; i < str.length(); i++)
			res[i] = (byte) str.charAt(i);
		return res;
	}

	public String show() {
		return "message : " + (text == null ? "" : text) + " from : "
				+ (fromIP == null ? "null" : fromIP.getHostAddress());
	}

}
