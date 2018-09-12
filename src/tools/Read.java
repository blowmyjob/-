package tools;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public  class Read {

	public static String getImageBinary(String g){   
        File f = new File(g);          
        BufferedImage bi;   
        try {   
            bi = ImageIO.read(f);   
            ByteArrayOutputStream baos = new ByteArrayOutputStream();   
            ImageIO.write(bi, "jpg", baos);   
            byte[] bytes = baos.toByteArray();   
            return Base64Util.encode(bytes);
              
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
        return null;   
    }   
}
