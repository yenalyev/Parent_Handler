import javax.swing.*;
import java.io.File;
import java.util.prefs.Preferences;

public class GUI_FileChooser {
    public static String path = "";

    public static void main (String[] args) {
        Preferences pref = Preferences.userRoot();
        String path = pref.get("DEFAULT_PATH", "");

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(path));

        int returnValue = chooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            chooser.setCurrentDirectory(selectedFile);
            /*try {
                //path = selectedFile.getAbsolutePath();
               HTMLParser.parser(selectedFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(selectedFile.getAbsolutePath());
            pref.put("DEFAULT_PATH", selectedFile.getAbsolutePath());
        }

    }
}
