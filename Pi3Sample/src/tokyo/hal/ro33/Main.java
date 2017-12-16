package tokyo.hal.ro33;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Main {
	private static UDPGet udpGet;
	private static UDPSend udpSend;
	private static int comPort;
	private static GpioPinDigitalOutput leftButtonLED;
	
	public static void main(String[] args) throws Exception {
		System.out.println("Starting Pi4j\n" + " comPort:" + args[0]);
		comPort = Integer.parseInt(args[0]);
		udpGet = new UDPGet(comPort);
		udpGet.start();

		final GpioController gpio = GpioFactory.getInstance();

		final GpioPinDigitalInput leftButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_UP);
		leftButtonLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "LED", PinState.HIGH);

		leftButton.setShutdownOptions(true);
		leftButtonLED.setShutdownOptions(true, PinState.LOW);

		leftButtonLED.high();

		leftButton.addListener(new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent arg0) {
				System.out.println("GPIO Status :  " + arg0.getPin() + " = " + arg0.getState());
				leftButtonLED.toggle();
				if (leftButtonLED.getState().equals(PinState.HIGH)) {
					try {
						udpSend.send("lever");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		while (true) {
			Thread.sleep(500);
		}

		// gpio.shutdown();

	}

	public void Receive(String text) throws Exception {
		if (!text.isEmpty()) {
			switch (text) {
			case "connect":
				System.out.println("送信元:" + udpGet.getConnectIP());
				udpSend = new UDPSend(comPort, udpGet.getConnectIP());
				udpSend.send("connecting start!");
				break;
				
				case "replay":
					leftButtonLED.toggle();
					break;

			default:
				System.out.println("受信:" + text);
				leftButtonLED.toggle();
				break;
			}
		}
	}

}
