package com.epam;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Stream;

public class Main {

    private static final PrintStream OUT = System.out;

    public static void main(String[] args) throws Exception {
        OUT.println("JSON helper");

        if (args.length > 0) {
            OUT.println("Chosen path: " + args[0]);
        } else {
            OUT.println("No path was chosen!  Use \"java -jar <jar-file> <path>\" ");
            return;
        }

        try (Stream<Path> paths = Files.walk(Paths.get(args[0]))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(".json"))
                    .forEach(Main::processPath);
        }
    }

    private static void processPath(Path path) {
        try {
            JsonElement jsonElement = JsonParser.parseReader(Files.newBufferedReader(path));
            processJson(jsonElement);
            Gson gson = new Gson();
            JsonWriter jsonWriter = gson.newJsonWriter(Files.newBufferedWriter(path));
            gson.toJson(jsonElement, jsonWriter);
            jsonWriter.flush();
            jsonWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processJson(JsonElement jsonElement) {
        if (jsonElement.isJsonArray()) {
            jsonElement.getAsJsonArray().forEach(Main::processJson);
        } else {
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                Set<String> keys = jsonObject.deepCopy().keySet();
                for (String key : keys) {
                    JsonElement nextElement = jsonObject.get(key);
                    if (isKeyFits(key)) {
                        transform(jsonElement, key, nextElement);
                    }
                    processJson(nextElement);
                }
            }
        }
    }

    private static boolean isKeyFits(String key) {
        return "_id123".equals(key);
    }

    private static void transform(JsonElement jsonElement, String key, JsonElement value) {
        OUT.println("KEY FOUND");
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        jsonObject.remove(key);
        jsonObject.add(key + "FOUND", value);
    }
}
