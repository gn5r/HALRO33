/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokyo.hal.ro33;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 *
 * @author pi
 */
public class LeverListtener implements GpioPinListenerDigital {

    private UDPSend udpSend;

    public LeverListtener(UDPSend udpSend) {
        this.udpSend = udpSend;
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpdsce) {
        try {
            udpSend.Send("lever");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
