package helper;

import java.io.*;
import java.util.zip.ZipEntry;

/**
 * Created by Rogier on 06-12-15.
 */
public class FileUtils {
    public static final int ZIPFILE = 0x504b0304;
    public static String fileToString(ZipEntry file){
        //TODO ZIPENTRY TXT TO STRING
        return null;
    }

    public static boolean isZipFile(File file) throws IOException {
        if(file.isDirectory()) {
            return false;
        }
        if(!file.canRead()) {
            throw new IOException("Cannot read file "+file.getAbsolutePath());
        }
        if(file.length() < 4) {
            return false;
        }
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        int test = in.readInt();
        in.close();
        return test == ZIPFILE;
    }
}
