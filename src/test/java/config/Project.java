package config;

import org.aeonbits.owner.ConfigFactory;

public class Project {
    public static DeviceConfig deviceConfig = ConfigFactory.create(DeviceConfig.class, System.getProperties());
}
