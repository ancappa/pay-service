package it.tim.pay.service;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

public class IdsGenerator {


    public static String generateId10(){
        return RandomStringUtils.randomAlphanumeric(10);
    }

    public static String generateId24() {
    	return RandomStringUtils.randomAlphanumeric(24);
    }
    
    public static String generateId30() {
    	return RandomStringUtils.randomAlphanumeric(30);
    }
    
    public static String generateId(int lenght) {
    	return RandomStringUtils.randomAlphanumeric(lenght);
    }
    
    public static String generateUUID() throws UnsupportedEncodingException {
    	String source = RandomStringUtils.randomAlphanumeric(30);
    	byte[] bytes = source.getBytes("UTF-8");
    	return UUID.nameUUIDFromBytes(bytes).toString();
    }
    
    public static String generateTransactionId(int numchars) {
    	
    	SecureRandom sr = new SecureRandom();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(sr.nextInt()));
        }

        String tid =  sb.toString().substring(0, numchars);
    	
    	return tid;
    }
    
}
