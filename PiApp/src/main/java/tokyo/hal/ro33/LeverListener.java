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
public class LeverListener implements GpioPinListenerDigital  {

    private final GpioPinDigitalOutput leftButtonLED, centerButtonLED, rightButtonLED;
    private final UDPSend udpSend;
    
    public LeverListener(GpioPinDigitalOutput leftButtonLED, GpioPinDigitalOutput centerButtonLED, GpioPinDigitalOutput rightButtonLED,UDPSend udpSend){
        this.leftButtonLED = leftButtonLED;
        this.centerButtonLED = centerButtonLED;
        this.rightButtonLED = rightButtonLED;
        this.udpSend = udpSend;
    }
    
    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpdsce) {
        
    }
    
}
