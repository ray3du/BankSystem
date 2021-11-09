package Config;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Borders {
    public static Border border(){
        Border border = BorderFactory.createLineBorder(Colors.getSecondaryColor(), 4);
        return border;
    }
}
