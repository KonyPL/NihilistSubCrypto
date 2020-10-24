package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    @FXML private Button encryptButton;
    @FXML private Button decryptButton;
    @FXML private TextField key1;
    @FXML private TextField key2;
    @FXML private TextArea encTxt;
    @FXML private TextArea decTxt;
    @FXML private TextField pathToDec;
    @FXML private TextField pathToEnc;
    @FXML private CheckBox useFileEnc;
    @FXML private CheckBox useFileDec;
    @FXML private Pane savedPane;
    @FXML private Pane errorPane;
    @FXML private RadioButton polish;
    @FXML private RadioButton english;
    @FXML private Label key1Label;
    @FXML private Label key2Label;
    @FXML private Label path1Label;
    @FXML private Label path2Label;
    @FXML private Label text1Label;
    @FXML private Label text2Label;
    @FXML private Label savedL;
    @FXML private Label errorL;
    @FXML private MenuItem about;
    @FXML private MenuItem author;
    @FXML private Menu help;
    private Cypher cypher = new Cypher();
    private String encryptedText;
    private String decryptedText;

    Alert authorAlert = new Alert(Alert.AlertType.INFORMATION);

    public Controller() {
    }

    @FXML
    void showAbout(ActionEvent event) throws IOException {
        Stage secondaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about.fxml"));
        Parent alertRoot = loader.load();
        if(polish.isSelected()) {
            secondaryStage.setTitle("O programie");
        } else {
            secondaryStage.setTitle("About");
        }
        secondaryStage.setScene(new Scene(alertRoot));
        AboutController aboutController = loader.getController();
        aboutController.setLang(polish.isSelected());
        secondaryStage.setResizable(false);
        secondaryStage.showAndWait();
    }

    @FXML void initialize() {
        pathToDec.setEditable(false);
        pathToDec.setMouseTransparent(true);
        pathToDec.setFocusTraversable(false);
        pathToDec.setOpacity(0.5);
        pathToEnc.setEditable(false);
        pathToEnc.setMouseTransparent(true);
        pathToEnc.setFocusTraversable(false);
        pathToEnc.setOpacity(0.5);
        authorAlert.setHeaderText("Konrad Trawiński\nIT student at Poznan University of Technology");
    }

    @FXML public void encrypt() {

        if(useFileDec.isSelected())
        {
            try {
                decryptedText = new String(Files.readAllBytes(Paths.get(pathToDec.getText())));
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                errorPane.setVisible(true);
                //Schedule the Visibility for 1000ms
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //Run on UI thread
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                errorPane.setVisible(false);
                            }
                        });
                    }
                }, 1000);
            }

        } else {
            decryptedText = decTxt.getText();
        }

        encryptedText = cypher.encrypt(key1.getText(), key2.getText(), decryptedText);

        if(useFileEnc.isSelected())
        {
            try {
                File file = new File(pathToEnc.getText());
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write(encryptedText);
                writer.flush();
                writer.close();
                savedPane.setVisible(true);
                //Schedule the Visibility for 1000ms
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //Run on UI thread
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                savedPane.setVisible(false);
                            }
                        });
                    }
                }, 1000);
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                errorPane.setVisible(true);
                //Schedule the Visibility for 1000ms
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //Run on UI thread
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                errorPane.setVisible(false);
                            }
                        });
                    }
                }, 1000);
            }
        } else {
            encTxt.setText(encryptedText);
        }

    }

    @FXML public void decrypt() {
        if(useFileEnc.isSelected())
        {
            try {
                encryptedText = new String(Files.readAllBytes(Paths.get(pathToEnc.getText())));
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                errorPane.setVisible(true);
                //Schedule the Visibility for 1000ms
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //Run on UI thread
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                errorPane.setVisible(false);
                            }
                        });
                    }
                }, 1000);
            }
        } else {
            encryptedText = encTxt.getText();
        }

        decryptedText = cypher.decrypt(key1.getText(), key2.getText(), encryptedText);

        if(useFileDec.isSelected())
        {
            try {
                File file = new File(pathToDec.getText());
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write(decryptedText);
                writer.flush();
                writer.close();
                savedPane.setVisible(true);
                //Schedule the Visibility for 1000ms
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //Run on UI thread
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                savedPane.setVisible(false);
                            }
                        });
                    }
                }, 1000);
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                errorPane.setVisible(true);
                //Schedule the Visibility for 1000ms
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //Run on UI thread
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                errorPane.setVisible(false);
                            }
                        });
                    }
                }, 1000);
            }
        } else {
            decTxt.setText(decryptedText);
        }

    }

    @FXML public void encFileAction() {
        if(useFileEnc.isSelected())
        {
            encTxt.setEditable(false);
            encTxt.setMouseTransparent(true);
            encTxt.setFocusTraversable(false);
            encTxt.setOpacity(0.5);
            pathToEnc.setEditable(true);
            pathToEnc.setMouseTransparent(false);
            pathToEnc.setFocusTraversable(true);
            pathToEnc.setOpacity(1);

        }else {
            encTxt.setEditable(true);
            encTxt.setMouseTransparent(false);
            encTxt.setFocusTraversable(true);
            encTxt.setOpacity(1);
            pathToEnc.setEditable(false);
            pathToEnc.setMouseTransparent(true);
            pathToEnc.setFocusTraversable(false);
            pathToEnc.setOpacity(0.5);
        }
    }

    @FXML public void decFileAction() {
        if(useFileDec.isSelected())
        {
            decTxt.setEditable(false);
            decTxt.setMouseTransparent(true);
            decTxt.setFocusTraversable(false);
            decTxt.setOpacity(0.5);
            pathToDec.setEditable(true);
            pathToDec.setMouseTransparent(false);
            pathToDec.setFocusTraversable(true);
            pathToDec.setOpacity(1);
        }else {
            decTxt.setEditable(true);
            decTxt.setMouseTransparent(false);
            decTxt.setFocusTraversable(true);
            decTxt.setOpacity(1);
            pathToDec.setEditable(false);
            pathToDec.setMouseTransparent(true);
            pathToDec.setFocusTraversable(false);
            pathToDec.setOpacity(0.5);
        }
    }

    @FXML public void lang(){
        if(polish.isSelected())
        {
            key1Label.setText("Klucz 1");
            key2Label.setText("Klucz 2");
            path1Label.setText("Ścieżka do pliku z jawnym tekstem");
            path2Label.setText("Ścieżka do pliku z zaszyfrowanym tekstem");
            text1Label.setText("Tekst jawny");
            text2Label.setText("Tekst zaszyfrowany");
            useFileEnc.setText("Użyj pliku");
            useFileDec.setText("Użyj pliku");
            encryptButton.setText("Zaszyfruj");
            decryptButton.setText("Deszyfruj");
            savedL.setText("Zapisano do pliku!");
            errorL.setText("Wystąpił błąd!");
            about.setText("O programie");
            author.setText("Autor");
            help.setText("Pomoc");
            authorAlert.setTitle("Autor");
            authorAlert.setHeaderText("Konrad Trawiński\nStudent Informatyki na Politechnice Poznańskiej");
        }else{
            key1Label.setText("Key 1");
            key2Label.setText("Key 2");
            path1Label.setText("Path to decrypted text file");
            path2Label.setText("Path to encrypted text file");
            text1Label.setText("Decrypted text");
            text2Label.setText("Encrypted text");
            useFileEnc.setText("Use file");
            useFileDec.setText("Use file");
            encryptButton.setText("Encrypt");
            decryptButton.setText("Decrypt");
            savedL.setText("Saved to file!");
            errorL.setText("An error occured!");
            about.setText("About");
            author.setText("Author");
            help.setText("Help");
            authorAlert.setTitle("Author");
            authorAlert.setHeaderText("Konrad Trawiński\nIT student at Poznan University of Technology");
        }
    }

    @FXML public void showAuthor() {
        authorAlert.showAndWait();
    }
}
