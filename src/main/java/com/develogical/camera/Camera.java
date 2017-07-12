package com.develogical.camera;

public class Camera implements WriteCompleteListener {
    private Sensor sensor;
    private boolean isCameraOn = false;
    private MemoryCard memory;
    private boolean isCopying = false;

    public Camera(Sensor sensor, MemoryCard memory) {
        this.memory = memory;
        this.sensor = sensor;
    }

    public void pressShutter() {
        if (isCameraOn) {
            memory.write(sensor.readData(), this);
            isCopying = true;
        }
    }

    public void powerOn() {
        sensor.powerUp();
        isCameraOn = true;
    }

    public void powerOff() {
        if (isCameraOn && !isCopying) {
            sensor.powerDown();
            isCameraOn = false;
        }
    }

    @Override
    public void writeComplete() {
        this.isCopying = false;
    }
}

