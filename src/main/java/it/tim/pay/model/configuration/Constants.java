package it.tim.pay.model.configuration;

import java.util.Arrays;

public class Constants {

    public enum  Subsystems{
        MYTIMAPP, MYTIMWEB;

        public static boolean contains(String str) {
            return str != null
                    && Arrays.stream(values())
                    .map(Enum::toString)
                    .anyMatch(sub -> sub.equalsIgnoreCase(str));
        }
    }

    public enum  DeviceType{
        IPHONE,JAVA,ANDROID,SMARTPHONE,TABLET,IPAD;

        public static boolean contains(String str) {
            return str != null
                    && Arrays.stream(values())
                    .map(Enum::toString)
                    .anyMatch(sub -> sub.equalsIgnoreCase(str));
        }
    }
}
