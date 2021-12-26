package org.selenium.pom.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.selenium.pom.constants.EnvType;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        String env = System.getProperty("env", EnvType.STAGE.toString());

        switch (EnvType.valueOf(env)){

            case  STAGE:
                properties = PropertyUtils.propertyLoader("src/test/resources/stage_config.properties");
                break;
            case PROD:
                properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
                break;
            default:
                throw new IllegalStateException("Invalid environment provided : "+ env);
        }


    }

    public static ConfigLoader getInstance(){   // this method is used anywhere which make sure only 1 instance should be there throughout execution.
        if(configLoader== null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getPropertyValue(String propName){

        String propValue = properties.getProperty(propName);
        if(propValue !=null) return propValue;
        else throw new RuntimeException("Property Name = "+propName+ " is not found in fig file. ");
    }

}
