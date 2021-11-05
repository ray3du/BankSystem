package Config;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;

public class Icons {
    public static ImageIcon getIcon(){
        Path currentPath = Paths.get("");
        String pathTemp = currentPath.toAbsolutePath().toString();
        String path = pathTemp + "/public/img/icon.png";
        return new ImageIcon(path);
    }
}
