import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {

    public static StringBuilder installationLog = new StringBuilder();

    public static void writeLog(StringBuilder logName, File fileName, boolean result) {
        logName.append(LocalDateTime.now());
        if (fileName.exists()) {
            if (fileName.isDirectory()) {
                logName.append(" directory");
            } else {
                logName.append(" file");
            }
            logName.append(" \"");
            logName.append(fileName.getName());
            logName.append("\" ");

        }
        if (result) {
            logName.append("created successfully in ");
            logName.append(fileName.getParent());
            logName.append('\n');
        } else {
            logName.append("creation failed\n");
        }
    }

    public static void createNewDir(String pathName, String dirName) {
        File newDir = new File(pathName, dirName);
        if (newDir.mkdir()) {
            writeLog(installationLog, newDir, true);
        } else {
            writeLog(installationLog, newDir, false);
        }
    }

    public static void createNewFile(String pathName, String fileName) {
        File newFile = new File(pathName, fileName);
        try {
            if (newFile.createNewFile()) {
                writeLog(installationLog, newFile, true);
            } else {
                writeLog(installationLog, newFile, false);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        createNewDir("/Users/egor_m/Games/", "src");
        createNewDir("/Users/egor_m/Games/", "res");
        createNewDir("/Users/egor_m/Games/", "savegames");
        createNewDir("/Users/egor_m/Games/", "temp");
        createNewDir("/Users/egor_m/Games/src/", "main");
        createNewDir("/Users/egor_m/Games/src/", "test");
        createNewFile("/Users/egor_m/Games/src/main/", "Main.java");
        createNewFile("/Users/egor_m/Games/src/main/", "Utils.java");
        createNewDir("/Users/egor_m/Games/res/", "drawables");
        createNewDir("/Users/egor_m/Games/res/", "vectors");
        createNewDir("/Users/egor_m/Games/res/", "icons");
        createNewFile("/Users/egor_m/Games/temp/", "temp.txt");
        try (FileWriter writer = new FileWriter("/Users/egor_m/Games/temp/temp.txt", false)) {
            writer.write(installationLog.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}