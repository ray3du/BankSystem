package Config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class StringPath {
    public static String getAbsolutePath(){
        Path currenPath = Paths.get("");
        return currenPath.toAbsolutePath().toString();
    }
}
