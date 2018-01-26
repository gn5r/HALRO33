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

    private static GpioPinDigitalInput leftButton, centerButton, rightButton, lever;
    private static GpioPinDigitalOutput leftButtonLED, centerButtonLED, rightButtonLED;
    private static UDPReceive udpReceive;
    private static UDPSend udpSend;
    private static int comPort;

    public static void main(String[] args) throws Exception {
        System.out.println("Start GPIO Settings...");

        final GpioController gpio = GpioFactory.getInstance();

        leftButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_15, PinPullResistance.PULL_UP);
        leftButtonLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "LED", PinState.HIGH);

        centerButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_UP);
        centerButtonLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "cLED", PinState.HIGH);

        rightButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04, PinPullResistance.PULL_UP);
        rightButtonLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "rLED", PinState.HIGH);

        lever = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06, PinPullResistance.PULL_UP);

        leftButton.setShutdownOptions(true);
        leftButtonLED.setShutdownOptions(true, PinState.LOW);
        centerButton.setShutdownOptions(true);
        centerButtonLED.setShutdownOptions(true, PinState.LOW);
        rightButton.setShutdownOptions(true);
        rightButtonLED.setShutdownOptions(true, PinState.LOW);

        System.out.println("OK\nStart UDP Settings...");

        /*  jar実行時に引数がなければcomPort5555にする  */
        if (args.length < 1) {
            comPort = 5555;
        } else {
            comPort = Integer.parseInt(args[0]);
        }

        udpReceive = new UDPReceive(comPort);
        udpReceive.start();

        System.out.println("OK\nUDPPort:" + String.valueOf(comPort) + "\nwaiting connect devices...");

        while (true) {
            Thread.sleep(500);
        }
        
    }

    public void Receive(String text) throws Exception {
        if (!text.isEmpty()) {
            
            switch (text) {
                case "connect":
                    System.out.println("OK,connected IPAddress:" + udpReceive.getConnectIP());
                    udpSend = new UDPSend(udpReceive.getConnectIP(), comPort);
                    udpSend.Send("Success!");

                    leftButton.addListener(new ButtonClickListener(leftButtonLED, udpSend));
                    centerButton.addListener(new ButtonClickListener(centerButtonLED, udpSend));
                    rightButton.addListener(new ButtonClickListener(rightButtonLED, udpSend));
                    lever.addListener(new LeverListener(udpSend));

                    leftButtonLED.high();
                    centerButtonLED.high();
                    rightButtonLED.high();

                    break;

                case "replay":
                    break;

                default:
                    break;
                    
            }
        }
    }
}
