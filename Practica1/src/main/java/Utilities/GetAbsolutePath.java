package Utilities;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public final class GetAbsolutePath {
    private GetAbsolutePath(){}

    public static String getAbsolutePathFromResource(final String path){ //Get path from resource folder
        String absolutePath = "";
        try {
            URL resource = GetAbsolutePath.class.getResource(path);
            if (resource == null) throw new FileNotFoundException("File not found");
            absolutePath = Paths.get(resource.toURI()).toFile().getAbsolutePath();
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return absolutePath;
    }
}
