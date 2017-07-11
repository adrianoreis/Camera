package com.develogical.camera;

public class Camera {
    private Sensor sensor;
    private boolean isCameraOn = false;
    private MemoryCard memory;
    private WriteCompleteListener listener;

    public void pressShutter() {
        if(isCameraOn){
            memory.write(sensor.readData(),listener);
        }
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

