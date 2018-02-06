/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokyo.hal.ro33;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListener;
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
    private static boolean maxbetPushed, leverPushed;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
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
                                maxbetPushed = true;
                                maxbetLED.toggle();
                                udpSend.Send("maxbet");
                            } catch (Exception e) {
                            }

                        }
                    });

                    lever.addListener(new GpioPinListenerDigital() {
                        @Override
                        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpdsce) {
                            
                            try {
                                if (maxbetPushed == true) {
                                    udpSend.Send("lever");
                                    leverPushed = true;
                                    leftButtonLED.toggle();
                                    centerButtonLED.toggle();
                                    rightButtonLED.toggle();
                                    maxbetPushed = false;
                                }
                            } catch (Exception e) {
                            }
                            
                        }
                    });

                    leftButton.addListener(new StopButtonListener(leftButtonLED, "left"));
                    centerButton.addListener(new StopButtonListener(centerButtonLED, "center"));
                    rightButton.addListener(new StopButtonListener(rightButtonLED, "right"));

                    maxbetLED.high();
                    leftButtonLED.low();
                    centerButtonLED.low();
                    rightButtonLED.low();

                    break;

                case "replay":
                    break;

                default:
                    break;

            }
        }
    }

    class StopButtonListener implements GpioPinListenerDigital {

        private final GpioPinDigitalOutput ButtonLED;
        private final String text;

        public StopButtonListener(GpioPinDigitalOutput ButtonLED, String text) {
            this.ButtonLED = ButtonLED;
            this.text = text;
        }

        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpdsce) {
            try {

                if (maxbetPushed == true && leverPushed == true) {
                    ButtonLED.toggle();
                    udpSend.Send(text);
                    leverPushed = false;
                }

            } catch (Exception e) {
            }

        }
    }

}
