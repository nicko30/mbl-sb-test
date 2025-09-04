package demo.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static demo.data.Constants.PATH_ELEMENTS;

public class Utils {
    public static Properties ELEMENTS;
    public static String errorMessage;

    public static void printError(String errorMessage) {
        Utils.errorMessage = errorMessage;
        throw new AssertionError(errorMessage);
    }

    /**
     * Lists files matching a specific pattern in the given base path.
     *
     * @param basePath The base path where to start searching for files.
     * @param pattern  The pattern to match against file names.
     * @return A list of paths to files that match the specified pattern.
     * @throws FileNotFoundException If no files matching the pattern were found.
     * @throws RuntimeException      If an I/O error occurs during file listing.
     */
    public static List<Path> listFilesMatchingPattern(String basePath, String pattern) {
        try (Stream<Path> stream = Files.walk(Paths.get(basePath), FileVisitOption.FOLLOW_LINKS)) {
            // Filter the stream to include only regular files
            List<Path> matchedFiles = stream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().matches(pattern)) // Filter paths based on whether they match the given pattern
                    .collect(Collectors.toList()); // Collect the filtered paths into a list

            // Check if the list of matched files is empty
            if (matchedFiles.isEmpty()) {
                throw new FileNotFoundException("No files matching the pattern were found.");
            }

            // Return the list of matched files
            return matchedFiles;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadElementProperties() {
        List<Path> listOfFiles = listFilesMatchingPattern(PATH_ELEMENTS, ".*/*.properties");
        ELEMENTS = new Properties();

        for (Path file : listOfFiles) {
            try {
                ELEMENTS.load(Files.newInputStream(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String removeDollarSign(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Input value cannot be null");
        }
        return value.replaceAll("\\$\\s?", "").trim();
    }
}
