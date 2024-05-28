package org.client.app;

import org.protocol.ChatProtocolException;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;

public class SignUpPanel extends JPanel {
    private ClientApp clientApp;
    private JPanel mainPanel;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton signUpBut;
    private JButton backBut;

    public SignUpPanel(ClientApp clientApp) {
        this.clientApp = clientApp;

        add(mainPanel);
        signUpBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clientApp.getClient().signup(textField.getText(), Arrays.toString(passwordField.getPassword()));

                    clientApp.getClient().getFromDatabase();

                    clientApp.setCard("appPanel");
                } catch (IOException | SQLException | ChatProtocolException ex) {
                    clientApp.getErrLabel().setText("Error: " + ex.getMessage());
                }
            }
        });
        backBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientApp.setCard("loginPanel");
            }
        });
    }

}
