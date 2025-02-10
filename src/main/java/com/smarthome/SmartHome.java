package com.smarthome;
// Abstraction: Base class for all smart devices
abstract class SmartDevice {
    protected String name;
    protected boolean powerStatus;

    public SmartDevice(String name) {
        this.name = name;
        this.powerStatus = false; // Default: Off
    }

    // Encapsulation: Control power status
    public void turnOn() {
        powerStatus = true;
        System.out.println(name + " is now ON.");
    }

    public void turnOff() {
        powerStatus = false;
        System.out.println(name + " is now OFF.");
    }

    // Abstract method for device status
    public abstract void deviceStatus();
}

// Inheritance: Light extends SmartDevice
class Light extends SmartDevice {
    private int brightness;

    public Light(String name, int brightness) {
        super(name);
        this.brightness = brightness;
    }

    public void setBrightness(int level) {
        if (powerStatus) {
            brightness = level;
            System.out.println(name + " brightness set to " + brightness + "%.");
        } else {
            System.out.println(name + " is OFF. Turn it on first.");
        }
    }

    // Polymorphism: Overriding deviceStatus()
    @Override
    public void deviceStatus() {
        System.out.println("Light [" + name + "] - Power: " + (powerStatus ? "ON" : "OFF") + ", Brightness: " + brightness + "%");
    }
}

// Inheritance: Fan extends SmartDevice
class Fan extends SmartDevice {
    private int speed;

    public Fan(String name, int speed) {
        super(name);
        this.speed = speed;
    }

    public void setSpeed(int level) {
        if (powerStatus) {
            speed = level;
            System.out.println(name + " speed set to level " + speed);
        } else {
            System.out.println(name + " is OFF. Turn it on first.");
        }
    }

    @Override
    public void deviceStatus() {
        System.out.println("Fan [" + name + "] - Power: " + (powerStatus ? "ON" : "OFF") + ", Speed: " + speed);
    }
}

// Inheritance: SmartTV extends SmartDevice
class SmartTV extends SmartDevice {
    private String channel;

    public SmartTV(String name, String channel) {
        super(name);
        this.channel = channel;
    }

    public void changeChannel(String newChannel) {
        if (powerStatus) {
            channel = newChannel;
            System.out.println(name + " changed to channel: " + channel);
        } else {
            System.out.println(name + " is OFF. Turn it on first.");
        }
    }

    @Override
    public void deviceStatus() {
        System.out.println("Smart TV [" + name + "] - Power: " + (powerStatus ? "ON" : "OFF") + ", Channel: " + channel);
    }
}

// Main class
public class SmartHome {
    public static void main(String[] args) {
        Light livingRoomLight = new Light("Living Room Light", 50);
        Fan bedroomFan = new Fan("Bedroom Fan", 3);
        SmartTV tv = new SmartTV("Samsung TV", "Netflix");

        // Turning on devices
        livingRoomLight.turnOn();
        bedroomFan.turnOn();
        tv.turnOn();

        // Using devices
        livingRoomLight.setBrightness(80);
        bedroomFan.setSpeed(5);
        tv.changeChannel("StarSports");

        // Display device statuses
        livingRoomLight.deviceStatus();
        bedroomFan.deviceStatus();
        tv.deviceStatus();

        // Turning off devices
        livingRoomLight.turnOff();
        bedroomFan.turnOff();
        tv.turnOff();
    }
}
