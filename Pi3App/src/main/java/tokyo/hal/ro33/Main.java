/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokyo.hal.ro33;

import com.pi4j.io.gpio.*;

/**
 *
 * @author pi
 */
public class Main {

    private static GpioPinDigitalInput leftButton;
    private static GpioPinDigitalOutput leftButtonLED;
    private static UDPReceive udpReceive;
    private static UDPSend udpSend;
    private static int comPort;

    public static void main(String[] args) throws Exception {
        System.out.println("Start GPIO Settings...");

        final GpioController gpio = GpioFactory.getInstance();

        leftButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_UP);
        leftButtonLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "LED", PinState.HIGH);

        leftButton.setShutdownOptions(true);
        leftButtonLED.setShutdownOptions(true, PinState.LOW);

        System.out.println("Start UDP Settings...");
        comPort = Integer.parseInt(args[0]);
        udpReceive = new UDPReceive(comPort);
        udpReceive.start();

        leftButtonLED.high();

        while (true) {
            Thread.sleep(500);
        }
    }

    public void Receive(String text) throws Exception {
        if (!text.isEmpty()) {
            switch (text) {
                case "connect":
                    System.out.println("送信元:" + udpReceive.getConnectIP());
                    udpSend = new UDPSend(udpReceive.getConnectIP(), comPort);
                    udpSend.Send("Success Connect!");
                    leftButton.addListener(new ButtonClickListener(leftButtonLED, udpSend));
                    break;

                default:
                    break;
            }
        }
    }
}
