package com.javarush.cryptanalyzer.maisuradze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;
    public class HelloController implements Initializable {
        @FXML
        private Label welcomeText;
        @FXML
        private TextField textField;
        @FXML
        private VBox resultPane;
        @FXML
        private TextField keyInput;
        @FXML
        private TextField KeyON;
        @FXML
        private Label resultLabel;
        private String text;
        private String cipherText;
        File file;

        @FXML
        protected void onHelloButtonClick() throws IOException {
            text = textField.getText();
            if (text == null || text.isEmpty()){
                File file = new File("input.txt");
                text = getChars(file);
            }
            cipherText = Cryptanalyzer.encrypt(
                    text,
                    Integer.parseInt(keyInput.getText()));
            welcomeText.setText(cipherText);
            writeToFile(cipherText, "encoded.txt");
            resultPane.setVisible(true);
        }

        public void decipherByFreeqan() throws IOException {
            String out = Cryptanalyzer.decrypt(cipherText, text);
            resultLabel.setText(out);

            writeToFile(out, "output.txt");
        }

        public void deChipherToKey() throws IOException {
            String out = Cryptanalyzer.decrypt(cipherText, Integer.parseInt(KeyON.getText()));
            resultLabel.setText(out);
            writeToFile(out, "output.txt");
        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            textField.setOnKeyPressed(ke -> {
                if (ke.getCode() == KeyCode.ENTER) {
                    try {
                        onHelloButtonClick();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            KeyON.setOnKeyPressed(ke -> {
                if (ke.getCode() == KeyCode.ENTER) {
                    try {
                        deChipherToKey();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        public void chooseFile() throws IOException {

            FileChooser fileChooser = new FileChooser();
            file = fileChooser.showOpenDialog(null);
            String text = getChars(file);
            cipherText = Cryptanalyzer.encrypt(valueOf(text), Integer.parseInt(keyInput.getText()));
            welcomeText.setText(cipherText);
            resultPane.setVisible(true);
        }

        private String getChars(File file) throws IOException {
            FileReader reader = new FileReader(file);

            int c;
            List<Integer> data = new ArrayList<>();
            while ((c = reader.read()) != -1) {
                if (c != -1) {
                    data.add(c);
                }
            }
            char[] word = new char[data.size()];
            for (Integer i = 0; i < word.length; i++) {
                word[i] = (char) ((int) data.get(i));
            }

            text = valueOf(word);
            return text;
        }

        private static void writeToFile(String out, String filename) throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(out);
            writer.close();
        }
}