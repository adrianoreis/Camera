package com.develogical.camera;

public class Camera {
    private Sensor sensor;
    private boolean isCameraOn = false;
    private MemoryCard memory;
    private WriteCompleteListener listener;
    private boolean isCopying = false;

    public void pressShutter() {
        if(isCameraOn){
            memory.write(sensor.readData(),listener);
            isCopying = true;
        }
    }

    public void powerOn() {
        sensor.powerUp();
        isCameraOn = true;
    }

    public void powerOff() {
        if(isCameraOn && !isCopying){
            sensor.powerDown();
            isCameraOn = false;
        }
    }
}

