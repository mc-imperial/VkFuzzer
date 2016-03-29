package tool.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by constantinos on 29/03/2016.
 * Saves files to a specified location
 */
public class FileGenerator {
    private final String DEFAULT_FOLDER = "";
    private final String outputFolder;

    public FileGenerator() {
        outputFolder = DEFAULT_FOLDER;
    }

    public FileGenerator(final String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public void writeFile(final Writer writer, final String path)
            throws IOException {
        writeFile(writer.toString(), path);
    }

    public void writeFile(final String content, final String path)
            throws IOException {
        FileWriter fileWriter = new FileWriter(new File(outputFolder + path));
        fileWriter.write(content);
        fileWriter.close();
    }
}
