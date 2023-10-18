
//GUI
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.border.EmptyBorder;

public class GUI {
    private JFrame frame;
    private JFileChooser fileChooser;
    private JProgressBar progressBar;
    private JButton encryptButton;
    private JButton decryptButton;

    public GUI() {
        frame = new JFrame("File Encryption Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");
        encryptButton.setEnabled(false);
        decryptButton.setEnabled(false);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(new EmptyBorder(10, 10, 50, 10)); // Add padding
        panel.add(fileChooser);
        panel.add(encryptButton);
        panel.add(decryptButton);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(progressBar, BorderLayout.CENTER);

        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableActionButtons(true);
            }
        });

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    progressBar.setVisible(true);
                    encryptFileOrFolder(selectedFile);
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    progressBar.setVisible(true);
                    decryptFileOrFolder(selectedFile);
                }
            }
        });

        frame.setVisible(true);
    }

    private void enableActionButtons(boolean enable) {
        encryptButton.setEnabled(enable);
        decryptButton.setEnabled(enable);
    }

    private void encryptFileOrFolder(File file) {
        if (file.isFile()) {
            // Encrypt the selected file

            System.out.println(file.getAbsolutePath());
        } else if (file.isDirectory()) {
            // Encrypt all files within the selected directory

        }
    }

    private void encryptFile(File file) {

        // encrypt file logic

    }

    private void encryptDirectory(File directory) {
        // Implement directory encryption logic here
        // Recursively process all files within the directory
    }

    private void decryptFileOrFolder(File file) {
        if (file.isFile()) {
            // Decrypt the selected file
            System.out.println(file.getAbsolutePath());

        } else if (file.isDirectory()) {
            // Decrypt all files within the selected directory
            decryptDirectory(file);
        }
    }

    private void decryptFile(File file) {
        // decrypt file logic

    }

    private void decryptDirectory(File directory) {
        // Implement directory decryption logic here
        // Recursively process all files within the directory
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
