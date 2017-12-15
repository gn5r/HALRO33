package tokyo.hal.ro33;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Main {

	public static void main(String[] args) throws Exception{
		System.out.println("Starting Pi4j");
		
		final GpioController gpio = GpioFactory.getInstance();
		
		final GpioPinDigitalOutput pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "LED", PinState.HIGH);
		pin1.setShutdownOptions(true, PinState.LOW);
		
		Thread.sleep(1000);
		
		pin1.low();
		Thread.sleep(1000);
		
		pin1.high();
		Thread.sleep(1000);
		
		pin1.toggle();;
		Thread.sleep(1000);
		
		pin1.low();
		Thread.sleep(1000);
		
		pin1.toggle();;
		Thread.sleep(1000);
		
		gpio.shutdown();
		
	}

}
