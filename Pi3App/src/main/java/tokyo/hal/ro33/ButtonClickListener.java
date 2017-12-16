/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokyo.hal.ro33;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 *
 * @author pi
 */
public class ButtonClickListener implements GpioPinListenerDigital {

    private GpioPinDigitalOutput leftButtonLED;
    private UDPSend udpSend;

    public ButtonClickListener(GpioPinDigitalOutput leftButtonLED, UDPSend udpSend) {
        this.leftButtonLED = leftButtonLED;
        this.udpSend = udpSend;
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent arg0) {
        System.out.println("GPIO Status:" + arg0.getPin() + " = " + arg0.getState());
        leftButtonLED.toggle();
        try {
            udpSend.Send(arg0.getState().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
