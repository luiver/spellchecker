package com.codecool;

import java.io.File;

public class Helper {
    public static String getPathForResourceFile(String fileName) {
        ClassLoader classLoader = SpellCheck.class.getClassLoader();
        File wordListFile = new File(classLoader.getResource(fileName).getFile());
        return wordListFile.getAbsolutePath();
    }
}
