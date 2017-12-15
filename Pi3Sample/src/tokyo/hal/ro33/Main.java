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
	private static String receive;

	public static void main(String[] args) throws Exception {
		System.out.println("Starting Pi4j\n" + "connectIP:" + args[0] + " comPort:" + args[1]);
		udpGet = new UDPGet(Integer.parseInt(args[1]));
		udpGet.start();
		
		udpSend = new UDPSend(Integer.parseInt(args[1]), udpGet.getConnectIP());

		System.out.println("送信元:" + udpGet.getConnectIP());

		final GpioController gpio = GpioFactory.getInstance();

		final GpioPinDigitalInput gpio00 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_UP);
		final GpioPinDigitalOutput gpio02 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "LED", PinState.HIGH);

		gpio00.setShutdownOptions(true);
		gpio02.setShutdownOptions(true, PinState.LOW);

		gpio02.high();

		gpio00.addListener(new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent arg0) {
				System.out.println("GPIO Status :  " + arg0.getPin() + " = " + arg0.getState());
				gpio02.toggle();
				try {
					udpSend.send(arg0.getState().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		while (true) {
			Thread.sleep(500);
		}

		// gpio.shutdown();

	}

}
