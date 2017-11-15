package udpsend_test;

import java.net.InetAddress;

public class Main {

	public static void main(String[] args) throws Exception {
		UDPSend udpSend = new UDPSend();
		UDPGet udpGet = new UDPGet();
		udpSend.inetAddress = InetAddress.getByName(args[0]);
		udpSend.setPort(Integer.parseInt(args[1]));
		udpGet.setSocketPort(args[1]);
		while (udpSend.send() == true) ;
		udpSend.close();
	}

}
