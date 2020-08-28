package com.chidiadi;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;


class Convert {

    static HashMap<String, String> MorseCodeLibrary = new HashMap<String, String>();

    public static HashMap<String, String> getMorseCodeLibrary() {
        MorseCodeLibrary.put("A", ".-");
        MorseCodeLibrary.put("B", "-...");
        MorseCodeLibrary.put("C", "-.-.");
        MorseCodeLibrary.put("D", "-..");
        MorseCodeLibrary.put("E", ".");
        MorseCodeLibrary.put("F", "..-.");
        MorseCodeLibrary.put("G", "--.");
        MorseCodeLibrary.put("H", "....");
        MorseCodeLibrary.put("I", "..");
        MorseCodeLibrary.put("J", ".---");
        MorseCodeLibrary.put("K", "-.-");
        MorseCodeLibrary.put("L", ".-..");
        MorseCodeLibrary.put("M", "--");
        MorseCodeLibrary.put("N", "-.");
        MorseCodeLibrary.put("O", "---");
        MorseCodeLibrary.put("P", ".--.");
        MorseCodeLibrary.put("Q", "--.-");
        MorseCodeLibrary.put("R", ".-.");
        MorseCodeLibrary.put("S", "...");
        MorseCodeLibrary.put("T", "-");
        MorseCodeLibrary.put("U", "..-");
        MorseCodeLibrary.put("V", "...-");
        MorseCodeLibrary.put("W", ".--");
        MorseCodeLibrary.put("X", "-..-");
        MorseCodeLibrary.put("Y", "-.--");
        MorseCodeLibrary.put("Z", "--..");
        MorseCodeLibrary.put(" ", "/");
        MorseCodeLibrary.put(".", ".-.-.-");
        MorseCodeLibrary.put(",", "..-..");
        MorseCodeLibrary.put(":", "---...");
        MorseCodeLibrary.put("?", "..--..");
        MorseCodeLibrary.put("'", ".----.");
        MorseCodeLibrary.put("/", "-..-.");
        MorseCodeLibrary.put("-", "-....-");
        MorseCodeLibrary.put("(", "-.--.");
        MorseCodeLibrary.put(")", "-.--.-");
        MorseCodeLibrary.put("1", ".----");
        MorseCodeLibrary.put("2", "..---");
        MorseCodeLibrary.put("3", "...--");
        MorseCodeLibrary.put("4", "....-");
        MorseCodeLibrary.put("5", ".....");
        MorseCodeLibrary.put("6", "-....");
        MorseCodeLibrary.put("7", "--...");
        MorseCodeLibrary.put("8", "---..");
        MorseCodeLibrary.put("9", "----.");
        MorseCodeLibrary.put("0", "-----");
        MorseCodeLibrary.put("\n", "***");
        return MorseCodeLibrary;
    }

    public static String EnglishToMorse(String entry) {

        char[] enteredText = entry.toUpperCase().toCharArray();
        StringBuffer outputText = new StringBuffer();

        for (char c : enteredText) {
            outputText.append(getMorseCodeLibrary().get(String.valueOf(c)));
            outputText.append(" ");
        }

        return outputText.toString();
    }

    public static String MorseToEnglish(String entry) {
        String[] letters = entry.split(" ");
        StringBuffer outputText = new StringBuffer();
        StringBuffer outputTextCapitalized = new StringBuffer();

        for (String s : letters) {
            for (Object o : getMorseCodeLibrary().keySet()) {
                if (getMorseCodeLibrary().get(o).equalsIgnoreCase(s)) {
                    outputText.append(o);
                }
            }

        }

        String[] sentences = outputText.toString().split("\\.");
        for (String sentence : sentences) {
            if (sentence.equalsIgnoreCase("") || sentence.equalsIgnoreCase(" ")) {
                outputTextCapitalized.append(".");
            } else if (!sentence.substring(0, 1).equalsIgnoreCase(" ")) {
                outputTextCapitalized.append(sentence.substring(0, 1).toUpperCase() +
                        sentence.substring(1).toLowerCase() + ". ");
            } else if (!sentence.substring(1, 2).equalsIgnoreCase(" ")) {
                outputTextCapitalized.append(sentence.substring(1, 2).toUpperCase() +
                        sentence.substring(2).toLowerCase() + ". ");
            }
            sentence.replaceAll("  ", " ");

        }


        return outputTextCapitalized.toString();
    }

}


public class Main {


    public static void main(String[] args) {
        String words = "Let's get started...this thing is written poorly.no caps and no spaces before first words.";

        String entry = words;
        String output1 = Convert.EnglishToMorse(entry);
        String output2 = Convert.MorseToEnglish(output1);

        System.out.println("The morse code is: " + output1 + "\nThe English is this: " + output2);

        Runnable myApp = new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                    JFrame app = new JFrame("Morse Code Converter By Chidiadi");
                    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //app.setExtendedState(JFrame.MAXIMIZED_BOTH);

                    JPanel wholeAppContainer = new JPanel(new BorderLayout());
                    //MENU BAR

                    JMenuBar menuBar = new JMenuBar();
                    JMenu file = new JMenu("File");
                    JMenu about = new JMenu("About");

                    JMenuItem printToTextFile = new JMenuItem("Save to text file");
                    KeyStroke ctrlSKeystroke = KeyStroke.getKeyStroke("control S");
                    printToTextFile.setAccelerator(ctrlSKeystroke);

                    JMenuItem translateFromTextFile = new JMenuItem("Translate from text file");
                    KeyStroke ctrlOKeystroke = KeyStroke.getKeyStroke("control O");
                    translateFromTextFile.setAccelerator(ctrlOKeystroke);

                    file.add(printToTextFile);
                    file.add(translateFromTextFile);

                    menuBar.add(file);
                    menuBar.add(about);

                    //ADDING SPACES BESIDE AND BELOW

                    JPanel rootPanel = new JPanel();
                    JPanel leftPanel = new JPanel();
                    JPanel rightPanel = new JPanel();


                    rootPanel.setSize(new Dimension(10000, 100));
                    leftPanel.setSize(50, 500);
                    rightPanel.setSize(50, 500);

                    //TEXT AREAS

                    JPanel titles = new JPanel(new BorderLayout());
                    Panel textAreas = new Panel(new BorderLayout());
                    JPanel buttons = new JPanel(new BorderLayout());
                    JPanel leftButtons = new JPanel(new FlowLayout());
                    JPanel rightButtons = new JPanel(new FlowLayout());
                    JPanel centreButton = new JPanel();


                    //I'm adding this space because the Morse Code seems to need the space to not be
                    //cut off from the left side when this textfield is set to it.
                    // I added it to the switch button's listener too.
                    JTextField LeftTitle = new JTextField("English", 10);
                    JTextField RightTitle = new JTextField("Morse Code", 10);


                    RightTitle.setHorizontalAlignment(JTextField.RIGHT);
                    //LeftTitle.setHorizontalAlignment(JTextField.LEFT);

                    LeftTitle.setFont(LeftTitle.getFont().deriveFont(Font.BOLD));
                    LeftTitle.setBorder(null);

                    RightTitle.setFont(RightTitle.getFont().deriveFont(Font.BOLD));
                    RightTitle.setBorder(null);

                    JTextArea LeftArea = new JTextArea();
                    JTextArea RightArea = new JTextArea();


                    JButton LeftSideCut = new JButton("Cut");
                    JButton LeftSideCopy = new JButton("Copy");
                    JButton LeftSidePaste = new JButton("Paste");

                    JButton RightSideCut = new JButton("Cut");
                    JButton RightSideCopy = new JButton("Copy");
                    JButton RightSidePaste = new JButton("Paste");

                    // JButton translate = new JButton(("Translate"));

                    JToggleButton switchButton = new JToggleButton("Switch", false);

                    //DIMENSIONING


                    wholeAppContainer.setPreferredSize(new Dimension(900, 700));
                    app.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    app.setMinimumSize(new Dimension(900, 500));


                    //SETTING UP TEXT AREAS TO SCROLL
                    LeftArea.setLineWrap(true);
                    LeftArea.setWrapStyleWord(true);
                    RightArea.setLineWrap(true);

                    JScrollPane scrollPane = new JScrollPane(LeftArea);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

                    JScrollPane scrollPane1 = new JScrollPane(RightArea);
                    scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

                    scrollPane.setPreferredSize(new Dimension(500, 300));
                    scrollPane1.setPreferredSize(new Dimension(500, 300));


                    //PIECING COMPONENTS TOGETHER


                    wholeAppContainer.add(titles, BorderLayout.NORTH);
                    wholeAppContainer.add(textAreas, BorderLayout.CENTER);
                    wholeAppContainer.add(buttons, BorderLayout.SOUTH);

                    titles.add(LeftTitle, BorderLayout.WEST);
                    titles.add(RightTitle, BorderLayout.EAST);

                    textAreas.add(scrollPane, BorderLayout.WEST);
                    textAreas.add(scrollPane1, BorderLayout.EAST);


                    leftButtons.add(LeftSidePaste, FlowLayout.LEFT);
                    leftButtons.add(LeftSideCopy, FlowLayout.LEFT);
                    leftButtons.add(LeftSideCut, FlowLayout.LEFT);

                    centreButton.add(switchButton, BoxLayout.X_AXIS);
                    //centreButton.add(translate, BoxLayout.X_AXIS);
                    switchButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                    buttons.add(leftButtons, BorderLayout.WEST);
                    buttons.add(centreButton, BorderLayout.CENTER);

                    rightButtons.add(RightSidePaste, FlowLayout.LEFT);
                    rightButtons.add(RightSideCopy, FlowLayout.LEFT);
                    rightButtons.add(RightSideCut, FlowLayout.LEFT);

                    buttons.add(rightButtons, BorderLayout.EAST);


                    //BORDERS FOR TEXT AREAS
                    Border border = BorderFactory.createEmptyBorder();

                    Border theTextAreaBorder = BorderFactory.createLineBorder(
                            Color.decode("#0080CC"), 1, true);

                    scrollPane.setBorder(border);
                    scrollPane1.setBorder(border);

                    //The left area has a focus listener with a thicker border that's triggered
                    //when on focuses on the text area.


                    LeftArea.setBorder(BorderFactory.createCompoundBorder(theTextAreaBorder,
                            BorderFactory.createEmptyBorder(5, 5, 0, 5)));

                    RightArea.setBorder(BorderFactory.createCompoundBorder(theTextAreaBorder,
                            BorderFactory.createEmptyBorder(5, 5, 0, 5)));

                    wholeAppContainer.setBorder(BorderFactory.createCompoundBorder
                            (BorderFactory.createEmptyBorder(40, 0, 0, 0), wholeAppContainer.getBorder()));


                    //LISTENERS FOR BUTTONS

                    ActionListener leftAreaCut = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            LeftArea.cut();
                        }
                    };
                    ActionListener leftAreaCopy = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            LeftArea.copy();
                        }
                    };
                    ActionListener leftAreaPaste = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            LeftArea.paste();
                        }
                    };

                    ActionListener rightAreaCut = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            RightArea.cut();
                        }
                    };
                    ActionListener rightAreaCopy = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            RightArea.copy();
                        }
                    };
                    ActionListener rightAreaPaste = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            RightArea.paste();
                        }
                    };


                    ItemListener switchButtonListener = new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {


                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                LeftArea.setText("");
                                LeftArea.setText(RightArea.getText());
                                RightArea.setText("");
                                RightArea.append(Convert.MorseToEnglish(LeftArea.getText()));
                                LeftTitle.setText("Morse Code");
                                LeftTitle.setEditable(false);

                                RightTitle.setEditable(true);
                                RightTitle.setText("English");
                                RightTitle.setEditable(false);


                            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                                LeftArea.setText("");
                                LeftArea.setText(RightArea.getText());
                                RightArea.setText("");
                                RightArea.append(Convert.EnglishToMorse(LeftArea.getText()));
                                LeftTitle.setEditable(true);
                                LeftTitle.setText("English");
                                LeftTitle.setEditable(false);

                                RightTitle.setEditable(true);
                                RightTitle.setText("Morse Code");
                                RightTitle.setEditable(false);

                            }

                        }
                    };


                    ActionListener printText = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFileChooser fileChooser = new JFileChooser();
                            FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(
                                    "TEXT FILE", ".txt", ".txt");
                            fileChooser.setFileFilter(txtFilter);

                            int option = fileChooser.showSaveDialog(app);
                            if (option == JFileChooser.APPROVE_OPTION) {
                                File file = fileChooser.getSelectedFile();

                                try (BufferedWriter bufferedWriter = new BufferedWriter(
                                        new FileWriter(
                                                new File(file.getAbsolutePath())))) {

                                    String[] textToPrint = RightArea.getText().split(" ");
                                    int count = 0;

                                    for (int i = 0; i < textToPrint.length; i++) {
                                        bufferedWriter.append(textToPrint[i] + " ");
                                        System.out.println("File should have been done by now.");
                                        count++;

                                        if (count == 20) {
                                            bufferedWriter.append("\n");
                                            count = 0;
                                        }
                                    }
                                    JOptionPane.showMessageDialog(app,
                                            "File saved successfully", " ", JOptionPane.PLAIN_MESSAGE);
                                } catch (Exception exception) {
                                    JOptionPane.showMessageDialog(app, "File not saved!",
                                            " ", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        }
                    };

                    ActionListener readText = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFileChooser fileChooser = new JFileChooser();
                            FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(
                                    "TEXT FILE", "txt");
                            fileChooser.setFileFilter(txtFilter);

                            int option = fileChooser.showOpenDialog(app);
                            if (option == JFileChooser.APPROVE_OPTION) {
                                File file = fileChooser.getSelectedFile();
                                StringBuffer textFromFile = new StringBuffer();

                                try {
                                    Scanner reader = new Scanner(file);
                                    switchButton.setSelected(true);

                                    while (reader.hasNext()) {
                                        textFromFile.append(reader.next() + " ");
                                    }
                                    LeftArea.setText(textFromFile.toString());

                                } catch (Exception exception) {

                                }
                            }
                        }
                    };

                    KeyListener translateText = new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e) {

                        }

                        @Override
                        public void keyPressed(KeyEvent e) {

                        }

                        @Override
                        public void keyReleased(KeyEvent e) {
                            if (switchButton.isSelected()) {
                                if (!RightArea.getText().equalsIgnoreCase(null)) {
                                    RightArea.setText("");
                                    RightArea.append(Convert.MorseToEnglish(LeftArea.getText()));
                                } else {
                                    RightArea.append(Convert.MorseToEnglish(LeftArea.getText()));
                                }
                            } else if (!switchButton.isSelected()) {
                                if (!RightArea.getText().equalsIgnoreCase(null)) {
                                    RightArea.setText("");
                                    RightArea.append(Convert.EnglishToMorse(LeftArea.getText()));
                                } else {
                                    RightArea.append(Convert.EnglishToMorse(LeftArea.getText()));
                                }
                            }
                        }
                    };

                    MenuListener aboutButtonListener = new MenuListener() {
                        @Override
                        public void menuSelected(MenuEvent e) {
                            JOptionPane.showMessageDialog(app, "This morse code translator was written by" +
                                    " Chidiadi Anyanwu. It is meant to translate from English to Morse Code and" +
                                    " vice versa.\nIt recognises sentences when translating, but still needs more work" +
                                    " to recognise websites as distinct from sentences" +
                                    "\n and to capitalise more stuff " +
                                    "for proper English grammar. Maybe in future, I'd be developing some autocorrect " +
                                    "feature.\nFeatures include:\n\n" +
                                    "- Translating the text from English to Morse Code & vice versa\n" +
                                    "- Writing the translated code or text to a text file\n" +
                                    "- Reading from a text file and translating", " ", JOptionPane.INFORMATION_MESSAGE);
                        }

                        @Override
                        public void menuDeselected(MenuEvent e) {

                        }

                        @Override
                        public void menuCanceled(MenuEvent e) {

                        }
                    };

                    FocusListener focusListener = new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            Border theTextAreaBorder1 = BorderFactory.createLineBorder(
                                    Color.decode("#0080CC"), 2, true);

                            LeftArea.setBorder(BorderFactory.createCompoundBorder(theTextAreaBorder1,
                                    BorderFactory.createEmptyBorder(5, 5, 0, 5)));
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            LeftArea.setBorder(BorderFactory.createCompoundBorder(theTextAreaBorder,
                                    BorderFactory.createEmptyBorder(5, 5, 0, 5)));
                        }
                    };


                    LeftSideCut.addActionListener(leftAreaCut);
                    LeftSideCopy.addActionListener(leftAreaCopy);
                    LeftSidePaste.addActionListener(leftAreaPaste);

                    RightSideCut.addActionListener(rightAreaCut);
                    RightSideCopy.addActionListener(rightAreaCopy);
                    RightSidePaste.addActionListener(rightAreaPaste);

                    switchButton.addItemListener(switchButtonListener);
                    // translate.addActionListener(translateListener);
                    LeftArea.addKeyListener(translateText);
                    LeftArea.addFocusListener(focusListener);

                    printToTextFile.addActionListener(printText);
                    translateFromTextFile.addActionListener(readText);


                    about.addMenuListener(aboutButtonListener);


                    RightArea.setEditable(false);
                    LeftTitle.setEditable(false);
                    RightTitle.setEditable(false);


                    app.add(menuBar, BorderLayout.NORTH);
                    app.add(rootPanel, BorderLayout.SOUTH);
                    app.add(leftPanel, BorderLayout.WEST);
                    app.add(rightPanel, BorderLayout.EAST);
                    app.add(wholeAppContainer, BorderLayout.CENTER);
                    app.setVisible(true);


                } catch (
                        ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (
                        InstantiationException e) {
                    e.printStackTrace();
                } catch (
                        IllegalAccessException e) {
                    e.printStackTrace();
                } catch (
                        UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            }
        };

        EventQueue.invokeLater(myApp);

    }
}
