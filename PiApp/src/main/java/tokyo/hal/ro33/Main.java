/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokyo.hal.ro33;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 *
 * @author pi
 */
public class Main {

    private static GpioPinDigitalInput maxbet, lever, leftButton, centerButton, rightButton;
    private static GpioPinDigitalOutput maxbetLED, leftButtonLED, centerButtonLED, rightButtonLED;

    private static UDPReceive udpReceive;
    private static UDPSend udpSend;
    private static int comPort;
    private static boolean maxbetPushed, leverPushed, leftPushed, centerPushed, rightPushed;

    public static void main(String[] args) throws Exception {
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
                    udpSend.Send("接続しました");

                    maxbet.addListener(new GpioPinListenerDigital() {
                        @Override
                        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpdsce) {

                            try {
                                if (gpdsce.getState() == PinState.LOW) {
                                    maxbetPushed = true;
                                }
                                if (maxbetPushed == true) {
                                    maxbetLED.low();
                                    udpSend.Send("maxbet");
                                } else {
                                    maxbetLED.high();
                                }

                            } catch (Exception e) {
                            }

                        }
                    });

                    lever.addListener(new GpioPinListenerDigital() {
                        @Override
                        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpdsce) {

                            try {
                                if (gpdsce.getState() == PinState.LOW && maxbetPushed == true) {
                                    leverPushed = true;
                                    leftButtonLED.high();
                                    centerButtonLED.high();
                                    rightButtonLED.high();
                                    udpSend.Send("lever");
                                }
                            } catch (Exception e) {
                            }

                        }
                    });

                    leftButton.addListener(new StopButtonListener(leftButtonLED, "left", leftPushed));
                    centerButton.addListener(new StopButtonListener(centerButtonLED, "center", centerPushed));
                    rightButton.addListener(new StopButtonListener(rightButtonLED, "right", rightPushed));

                    maxbetLED.high();
                    leftButtonLED.low();
                    centerButtonLED.low();
                    rightButtonLED.low();

                    break;

                case "replay":
                    maxbetPushed = true;
                    leverPushed = true;
                    maxbetLED.low();
                    leftButtonLED.high();
                    centerButtonLED.high();
                    rightButtonLED.high();
                    break;

                default:
                    break;

            }
        }
    }

    class StopButtonListener implements GpioPinListenerDigital {

        private final GpioPinDigitalOutput ButtonLED;
        private final String text;
        private boolean pushed;

        public StopButtonListener(GpioPinDigitalOutput ButtonLED, String text, boolean pushed) {
            this.ButtonLED = ButtonLED;
            this.text = text;
            this.pushed = pushed;
        }

        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpdsce) {
            try {

                if (gpdsce.getState() == PinState.LOW && leverPushed == true && pushed == false) {
                    pushed = true;
                    ButtonLED.low();
                    udpSend.Send(text);
                }

                if (leftPushed == true && centerPushed == true && rightPushed == true) {
                    maxbetPushed = true;
                    leverPushed = true;
                }

            } catch (Exception e) {
            }

        }
    }

}
