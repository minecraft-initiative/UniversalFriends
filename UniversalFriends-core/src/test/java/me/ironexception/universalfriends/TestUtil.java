package me.ironexception.universalfriends;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestUtil {

    public static Path getPath(String resourceName) throws URISyntaxException {
        return Paths.get(TestUtil.class.getClassLoader().getResource(resourceName).toURI());
    }

}
