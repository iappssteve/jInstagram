
package org.jinstagram.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;

/**
 * Generic utils
 * @author 
 *
 */
public class InstagramGenericUtil {
    
    /**
     * Generate UUID
     * @param dash If needs to keep dash
     * @return UUID
     */
    public static String generateUuid(boolean dash) {
        String uuid = UUID.randomUUID().toString();
        
        if (dash) {
            return uuid;
        }
        
        return uuid.replaceAll("-", "");
    }
    
    @SuppressWarnings("unchecked")
	public static <T> T deepClone(T object) {
    	   try {
    	     ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	     ObjectOutputStream oos = new ObjectOutputStream(baos);
    	     oos.writeObject(object);
    	     ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    	     ObjectInputStream ois = new ObjectInputStream(bais);
    	     return (T)ois.readObject();
    	   }
    	   catch (Exception e) {
    	     e.printStackTrace();
    	     return null;
    	   }
    	 }
}
