package com.develogical.camera;

public class Camera {
    private Sensor sensor;
    private boolean isCameraOn = false;

    public void pressShutter() {
        // not implemented
    }

    public void powerOn() {
        sensor.powerUp();
        isCameraOn = true;
    }

    public void powerOff() {
        if(isCameraOn){
            sensor.powerDown();
            isCameraOn = false;
        }
    }
}

