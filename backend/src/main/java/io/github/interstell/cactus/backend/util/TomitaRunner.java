package io.github.interstell.cactus.backend.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Grigoriy on 5/15/2016.
 */
public class TomitaRunner {
    public TomitaRunner(){
    }
    private String pathToExecutable;

    public String getPathToExecutable() {
        return pathToExecutable;
    }

    public void setPathToExecutable(String pathToExecutable) {
        this.pathToExecutable = pathToExecutable;
    }

    public String parse(String text) throws IOException {
        ProcessBuilder p = new ProcessBuilder(getPathToExecutable());
        InputStream tomita_in = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        OutputStream tomita_out = new ByteArrayOutputStream();
        return tomita_out.toString();
    }
}
