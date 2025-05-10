package MyApp.runner;

import MyApp.CommandType;
import MyApp.Constants;
import MyApp.cipher.BruteForce;
import MyApp.cipher.CesarCipherAction;
import MyApp.cipher.Decryption;
import MyApp.cipher.Encryption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApplicationRunner {
    public void run (String[] args) {
        if (args.length < Constants.MIN_ALLOWED_ARGS_COUNT){
            System.out.println("Please provide at least the command and the file path.");
            return;
        }

        CommandType command = CommandType.fromString(args[Constants.COMMAND_TYPE_ARG_POSITION]);
        Path filePath = Path.of(args[Constants.FILE_ARG_POSITION]);

        String inputText;
        try{
            inputText = Files.readString(filePath);
        } catch (IOException e) {
            System.out.println("Failed to read a file: " + filePath);
            return;
        }

        CesarCipherAction action;

        if (command == CommandType.ENCRYPT){
            if (args.length < Constants.MAX_ALLOWED_ARGS_COUNT){
                System.out.println("Key is needed for encryption");
                return;
            }
            int key;

            try {
                key = Integer.parseInt(args[Constants.KEY_ARG_POSITION]);
            } catch (NumberFormatException e) {
                System.out.println("The key must be an integer.");
                return;
            }
            action = new Encryption(key);
        } else if (command == CommandType.DECRYPT) {
            if (args.length < Constants.MAX_ALLOWED_ARGS_COUNT){
                System.out.println("Key is needed for decryption");
                return;
            }
            int key = Integer.parseInt(args[Constants.KEY_ARG_POSITION]);
            action = new Decryption(key);
        } else if (command == CommandType.BRUTE_FORCE) {
            action = new BruteForce();
        } else {
            System.out.println("Unknown command");
            return;
        }

        String result = action.execute(inputText);
        String newFileName = createNewFileName(filePath, command);

        try {
            Files.writeString(Path.of(newFileName), result);
            System.out.println("The result is saved to a file: " + newFileName);
        } catch (IOException e) {
            System.out.println("The file could not be saved.");
        }
    }

    private String createNewFileName(Path originalPath, CommandType command) {
        String fileName = originalPath.getFileName().toString();
        String baseName;
        String extension;

        if (fileName.contains(".")) {
            int dotIndex = fileName.lastIndexOf(".");
            baseName = fileName.substring(0, dotIndex);
            extension = fileName.substring(dotIndex);
        } else {
            baseName = fileName;
            extension = "";
        }

        return originalPath.getParent().resolve(baseName + "[" + command + "]" + extension).toString();
    }
}
