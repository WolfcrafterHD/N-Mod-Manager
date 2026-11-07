/*
    Lupinus
    orig. 01.11.2026
    This Script handles every other function of which I can't be bothered to make a separate Script.
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Control {
    /***Attributes***/
    public static Control INSTANCE;
    private File _backgroundImagePath;
    private File _mcModPath; public String GetMcModPath() {return _mcModPath.toString();} public void SetMcModPath(File pFile) {_mcModPath = pFile;}
    private File _modProfilePath; public String GetModProfilePath() {return _modProfilePath.toString();} public void SetModProfilePath(File pFile) {_modProfilePath = pFile;}
    private BufferedImage _backgroundImage;

    /***Constructor***/
    Control() {
        if (INSTANCE == null) {
            INSTANCE = this;
        }
    }

    /***Public***/
    public void LoadImage(String pPathString) {
        _backgroundImagePath = new File(pPathString);
        FileSavingProtocol();
        try {
            _backgroundImage = ImageIO.read(_backgroundImagePath);
            if (_backgroundImage != null) {
                Display.INSTANCE.GetPanelMain().SetBackgroundImage(_backgroundImage);
                Display.INSTANCE.GetPanelSettings().SetBackgroundImage(_backgroundImage);
                Display.INSTANCE.GetFrameMain().repaint();
            }
            else {
                System.out.printf("File Format for Image at %s not supported!%n", pPathString);
            }
        } catch (IOException e) {
            System.out.printf("Path not usable \" %s \"!%n", pPathString);
        }
    }

    public void LoadImage(File pPath) {
        _backgroundImagePath = pPath;
        FileSavingProtocol();
        try {
            _backgroundImage = ImageIO.read(_backgroundImagePath);
            if (_backgroundImage != null) {
                Display.INSTANCE.GetPanelMain().SetBackgroundImage(_backgroundImage);
                Display.INSTANCE.GetPanelSettings().SetBackgroundImage(_backgroundImage);
                Display.INSTANCE.GetFrameMain().repaint();
            }
            else {
                System.out.printf("File Format for Image at %s not supported!%n", pPath);
            }
        } catch (IOException e) {
            System.out.printf("Path not usable \" %s \"!%n", pPath);
        }
    }

    public void FileLoadingProtocol() {
        try {
            File thisProgram = new File(Control.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            Path thisProgramPath = thisProgram.getParentFile().toPath();
            Path ConfigFilePath = thisProgramPath.resolve("config.txt");

            if (Files.exists(ConfigFilePath)) {
                System.out.println("Config file found. Loading content.");
                List<String> loadedCollins = Files.readAllLines(ConfigFilePath, StandardCharsets.UTF_8);
                _backgroundImagePath = new File(loadedCollins.get(0));
                _mcModPath = new File(loadedCollins.get(1));
                _modProfilePath = new File(loadedCollins.get(2));


                //load functions connected to File Paths
                LoadImage(_backgroundImagePath);
                GUI.INSTANCE.GetBackgroundPathTextField().setText(_backgroundImagePath.toString());

                GUI.INSTANCE.GetMcModPathTextField().setText(_mcModPath.toString());

                Control.INSTANCE.UpdateDropdown();
                GUI.INSTANCE.GetModManagerPathTextField().setText(_modProfilePath.toString());
            }
            else {
                System.out.println("No Config file found, creating new one.");
                String temp = "no background path";
                String temp1 = "no mc mod path";
                String temp2 = "no mod profile path";
                List<String> createdCollins = Arrays.asList(temp, temp1, temp2);
                Files.write(ConfigFilePath, createdCollins, StandardCharsets.UTF_8);
                System.out.printf("New Config File created under: %s .%n", ConfigFilePath.toAbsolutePath());
            }
        } catch (Exception e) {
            System.out.println("Error while loading!");
        }
    }

    public void FileSavingProtocol() {
        try {
            File thisProgram = new File(Control.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            Path thisProgramPath = thisProgram.getParentFile().toPath();
            Path ConfigFilePath = thisProgramPath.resolve("config.txt");

            if (Files.exists(ConfigFilePath)) {
                System.out.println("Saving Content.");
                String temp = _backgroundImagePath.toString();
                String temp1 = _mcModPath.toString();
                String temp2 = _modProfilePath.toString();
                List<String> savedCollins = Arrays.asList(temp, temp1, temp2);
                Files.write(ConfigFilePath, savedCollins, StandardCharsets.UTF_8);
            }
            else {
                System.out.println("No Config file found, was it deleted on runtime?");
            }
        } catch (Exception e) {
            System.out.println("Error while Saving!");
        }
    }

    public void OpenFolder(String pPathString) {
        File FolderPath = new File(pPathString);

        if (!FolderPath.exists() || !FolderPath.isDirectory()) {
            System.out.println("Path does not exist or is not a Folder!");
            return;
        }

        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("nix") || os.contains("nux")) {
                new ProcessBuilder("xdg-open", FolderPath.getAbsolutePath()).start();
            } else {
                System.err.println("Unsupported Operating System.");
            }
        } catch (IOException e) {
            System.out.println("Error while opening File Manager!");
        }
    }

    public void ClearMcModFolder() {
        Path mcModPath = Paths.get(GetMcModPath());
        try (Stream<Path> stream = Files.list(mcModPath)) {
            stream.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".jar")).forEach(path -> {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    System.out.println("Error while clearing you mc mod folder!");
                }
            });
        }
        catch (IOException e) {
            System.out.println("Path isn't a Folder or can not be read!");
        }
    }

    public void CreateFolder(String pFolderName) {
        try {
            Path parentFolder = Paths.get(GetModProfilePath());
            Path newFolderPath = parentFolder.resolve(pFolderName);
            Files.createDirectories(newFolderPath);
        } catch (Exception e)
        {
            System.out.println("Error while creating a Folder!");
        }
    }

    public void UpdateDropdown() {
        List<FolderNamePathPair> tempList = ReadModProfiles();
        JComboBox tempDropdown = GUI.INSTANCE.GetProfileDropdownComboBox();
        tempDropdown.removeAllItems();
        for (FolderNamePathPair pair : tempList) {
            tempDropdown.addItem(pair);
        }
    }

    public void SwitchModProfile() {
        ClearMcModFolder();
        JComboBox<FolderNamePathPair> tempDropdown = GUI.INSTANCE.GetProfileDropdownComboBox();
        FolderNamePathPair tempFolderPair = (FolderNamePathPair) tempDropdown.getSelectedItem();
        String tempPath= tempFolderPair.GetPath();
        Path modProfile = Paths.get(tempPath);
        Path mcModFolder = Paths.get(GetMcModPath());

        try (Stream<Path> stream = Files.list(modProfile)) {
            stream.filter(Files::isRegularFile).filter(path ->
                    path.toString().toLowerCase().endsWith(".jar")).forEach(modFile -> {
                        Path targetModFile = mcModFolder.resolve(modFile.getFileName());
                        try {
                            Files.copy(modFile, targetModFile, StandardCopyOption.REPLACE_EXISTING);
                        } catch (Exception e) {
                            System.out.printf("Error while copying %s %n", modFile.getFileName());
                        }
            });

        } catch (Exception e) {
            System.out.println("Error while trying to copy Mod Profile to Mc Mod Directory");
        }
    }

    /***Private***/
    private List<FolderNamePathPair> ReadModProfiles() {
        Path parent = Paths.get(GetModProfilePath());

        try (Stream<Path> stream = Files.list(parent)) {
            return stream.filter(Files::isDirectory).map(path ->
                new FolderNamePathPair(path.getFileName().toString(), path.toAbsolutePath().toString())
            ).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error while reading mod profiles, returning empty List");
            return List.of();
        }
    }

}
