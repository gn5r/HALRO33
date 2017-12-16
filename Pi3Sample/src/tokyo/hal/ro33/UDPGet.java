package tokyo.hal.ro33;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPGet extends Thread {
	private DatagramSocket socket;
	private DatagramPacket packet;
	private Main main;

	public UDPGet(int comPort) throws Exception, SocketException {
		this.socket = new DatagramSocket(comPort);
		this.main = new Main();
	}

	@Override
	public void run() {
		try {
			byte[] buf = new byte[1024];
			this.packet = new DatagramPacket(buf, buf.length);
			while (true) {
				socket.receive(this.packet);
				String receive = new String(packet.getData(), 0, packet.getLength());
				main.Receive(receive);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPacket() {
		return new String(packet.getData(), 0, packet.getLength());
	}

	public String getConnectIP() {
		return packet.getAddress().toString().replaceFirst(".*" + "/", "");
	}
}
