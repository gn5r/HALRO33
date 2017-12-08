package via.gn5r.com_udpsample;

public class Main {

	public static void main(String[] args) throws Exception {
		// UDPSend udpSend = new UDPSend();
		UDPGet udpGet = new UDPGet();

		System.out.print("UDPポート:を入力してください");
		udpGet.setSocketPort(Integer.parseInt(args[0]));
		// udpSend.inetAddress = InetAddress.getByName(args[0]);

		if (args.length != 1) {
			udpGet.setSocketPort(5555);
		}

		// while (udpSend.send() == true) {
		// if (udpGet.receive() == true)
		// ;
		// }
		while (udpGet.receive()) {
			;
		}
	}
}
