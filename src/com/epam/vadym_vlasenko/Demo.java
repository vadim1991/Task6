package com.epam.vadym_vlasenko;

import com.epam.vadym_vlasenko.ui.ConsoleLauncher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by Vadym_Vlasenko on 01.04.2015.
 */
public class Demo {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("d:\\example.txt");
        byte[] array = Files.readAllBytes(path);
        System.out.println(Arrays.toString(array));

        ConsoleLauncher launcher = new ConsoleLauncher();
        launcher.run();
    }

}
