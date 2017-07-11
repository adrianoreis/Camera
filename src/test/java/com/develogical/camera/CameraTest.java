package com.develogical.camera;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CameraTest {
    @InjectMocks
    private Camera camera;
    @Mock
    private Sensor sensor;
    @Mock
    private MemoryCard memoryCard;
    @Captor
    private ArgumentCaptor<WriteCompleteListener> captor;

    @Before
    public void setUp() {
        when(sensor.readData()).thenReturn(new byte[]{1, 2});
    }

    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {
        camera.powerOn();
        verify(sensor).powerUp();
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {
        camera.powerOn();
        camera.powerOff();
        verify(sensor).powerDown();
    }

    @Test
    public void pressingShutterWhenCameraIsOffDoesNothing() {
        camera.powerOff();
        verifyZeroInteractions(sensor);
    }

    @Test
    public void pressingShutterThePowerOnCopiesDataFromSensorToMemory() {
        camera.powerOn();
        camera.pressShutter();
        verify(memoryCard).write(eq(new byte[]{1, 2}), any());
    }

    @Test
    public void cameraDoesNotSwitchOffWhenDataIsBeingWritten() {
        camera.powerOn();
        camera.pressShutter();
        camera.powerOff();
        verify(sensor, never()).powerDown();
    }

    @Test
    public void powerDownTheSensorWhenWritingDataIsComplete() {
        camera.powerOn();
        camera.pressShutter();
        verify(memoryCard).write(eq(new byte[]{1, 2}), captor.capture());
        captor.getValue().writeComplete();
        camera.powerOff();
        verify(sensor).powerDown();
    }
}
