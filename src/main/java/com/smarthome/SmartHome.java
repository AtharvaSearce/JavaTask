package com.smarthome;
// Abstraction: Base class for all smart devices
abstract class SmartDevice {
    protected String name;
    protected boolean powerStatus;

    public SmartDevice(String name) {
        try{
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Device name cannot be null or empty.");
            }
            this.name = name;
            this.powerStatus = false; // Default: Off
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
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
        try{
            if (brightness < 0 || brightness > 100) {
                throw new IllegalArgumentException("Brightness must be between 0 and 100.");
            }
            this.brightness = brightness;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void setBrightness(int level) {
        try {
            if (!powerStatus) {
                throw new IllegalStateException(name + " is OFF. Turn it on first.");
            }
            if (level < 0 || level > 100) {
                throw new IllegalArgumentException("Brightness must be between 0 and 100.");
            }
            brightness = level;
            System.out.println(name + " brightness set to " + brightness + "%.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
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
        try{
            if (speed < 0 || speed > 5) {
                throw new IllegalArgumentException("Speed must be between 0 and 5.");
            }
            this.speed = speed;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void setSpeed(int level) {
        try {
            if (!powerStatus) {
                throw new IllegalStateException(name + " is OFF. Turn it on first.");
            }
            if (level < 0 || level > 5) {
                throw new IllegalArgumentException("Speed must be between 0 and 5.");
            }
            speed = level;
            System.out.println(name + " speed set to level " + speed);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
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
        try{
            if (channel == null || channel.isEmpty()) {
                throw new IllegalArgumentException("Channel name cannot be null or empty.");
            }
            this.channel = channel;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void changeChannel(String newChannel) {
        try {
            if (!powerStatus) {
                throw new IllegalStateException(name + " is OFF. Turn it on first.");
            }
            if (newChannel == null || newChannel.isEmpty()) {
                throw new IllegalArgumentException("Channel name cannot be null or empty.");
            }
            channel = newChannel;
            System.out.println(name + " changed to channel: " + channel);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
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

        // Attempting invalid operations
        Light invalidLight = new Light("", 500); //Invalid Light object
        Fan invalidFan = new Fan(null, 100); //Invalid Fan object
        SmartTV invalidTV = new SmartTV("", null); //Invalid Tv object
        livingRoomLight.setBrightness(150); // Invalid brightness
        bedroomFan.setSpeed(7); // Invalid speed
        tv.changeChannel(""); // Invalid channel
    }
}
