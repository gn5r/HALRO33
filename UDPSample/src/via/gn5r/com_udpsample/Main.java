package via.gn5r.com_udpsample;

import java.net.InetAddress;

public class Main {

	public static void main(String[] args) throws Exception {
		UDPSend udpSend = new UDPSend();
		UDPGet udpGet = new UDPGet();
		
		System.out.println("送信先:" + args[0]);
		udpSend.inetAddress = InetAddress.getByName(args[0]);
		
		if(args.length != 2) {
			udpSend.setPort(5555);
			udpGet.setSocketPort(5555);
		}else {
			udpSend.setPort(Integer.parseInt(args[1]));
			udpGet.setSocketPort(Integer.parseInt(args[1]));
		}

		while (udpSend.send() == true) {
			if (udpGet.receive() == true)
				;
		}
		udpSend.close();
	}
}
