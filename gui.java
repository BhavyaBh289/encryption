import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class gui {
    private JFrame frame;
    private JTextField inputPathField;
    private JTextField outputPathField;
    private JPasswordField secretKeyField;
    private JRadioButton encryptRadio;
    private JRadioButton decryptRadio;

    public gui() {
        frame = new JFrame("Encryption/Decryption Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 2));

        JLabel inputLabel = new JLabel("Input Path:");
        inputPathField = new JTextField();
        JLabel outputLabel = new JLabel("Output Path:");
        outputPathField = new JTextField();
        JLabel secretKeyLabel = new JLabel("Secret Key:");
        secretKeyField = new JPasswordField();
        JLabel modeLabel = new JLabel("Mode:");
        encryptRadio = new JRadioButton("Encrypt");
        decryptRadio = new JRadioButton("Decrypt");

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(encryptRadio);
        modeGroup.add(decryptRadio);
        encryptRadio.setSelected(true);

        JButton executeButton = new JButton("Execute");

        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputPath = inputPathField.getText();
                String outputPath = outputPathField.getText();
                String secretKey = new String(secretKeyField.getPassword());
                boolean encryptt = encryptRadio.isSelected();

                if (encryptt) {
                    try {
                        encrypt.encryptfolder(new File(inputPath), new File(outputPath), secretKey);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        decrypt.decryptfolder(new File(inputPath), new File(outputPath), secretKey);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        frame.add(inputLabel);
        frame.add(inputPathField);
        frame.add(outputLabel);
        frame.add(outputPathField);
        frame.add(secretKeyLabel);
        frame.add(secretKeyField);
        frame.add(modeLabel);
        frame.add(encryptRadio);
        frame.add(new JPanel()); // Placeholder for alignment
        frame.add(decryptRadio);
        frame.add(executeButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new gui();
            }
        });
    }
}
