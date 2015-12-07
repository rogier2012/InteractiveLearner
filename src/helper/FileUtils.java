package helper;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUtils {
    public static final int ZIPFILE = 0x504b0304;
    public static String fileToString(ZipFile zipFile, ZipEntry file){
        String result = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                result = result + line;
            }
            br.close();
        } catch (IOException e){
            e.getMessage();
        }
        return result;
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

    public static File getFile(){
        File selectedFile = null;
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

        }
        return selectedFile;
    }
}
