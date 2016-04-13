package tool.fuzzer;

import tool.Main;
import tool.utils.UnzipUtility;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cvryo on 13/04/2016.
 */
public class LibraryDependencyGenerator {
    private final String SDL_PATH = "libraries/SDL2-2.0.4.zip";
    private final UnzipUtility unzipUtility;

    public LibraryDependencyGenerator() {
        unzipUtility = new UnzipUtility();
    }

    public void generateLibraryDependencies(final String output) {
        try {
            InputStream file = Main.class.getClassLoader().getResourceAsStream(SDL_PATH);
            unzipUtility.unzip(file, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
