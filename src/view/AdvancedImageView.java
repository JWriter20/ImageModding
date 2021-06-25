package view;

import model.imageclasses.Image;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

public class AdvancedImageView extends JFrame implements AdvancedView,
    ActionListener, ItemListener {


  private final JMenuItem[] editItems = {
      new JMenuItem("Sepia"), new JMenuItem("Grayscale"),
      new JMenuItem("Sharpen"), new JMenuItem("Blur"),
      new JMenuItem("Mosaic"), new JMenuItem("Shrink")};

  private final JMenuItem[] saveItems = {
      new JMenuItem("Save Layer as PPM"), new JMenuItem("Save Layer As PNG"),
      new JMenuItem("Save Layer as JPG"), new JMenuItem("Save All as PPM"),
      new JMenuItem("Save All as PNG"), new JMenuItem("Save all as JPG")};

  private final JMenuItem[] loadItems = {
      new JMenuItem("Load Script"), new JMenuItem("Load Layer"),
      new JMenuItem("Load multi-layer Image")
  };

  private final JMenu[] menuItems = {new JMenu("Save"), new JMenu("Load"), new JMenu("Edit")};
  private JSplitPane mainSplitPane;  // split the window in top and bottom
  private JPanel imagePanel;       // container panel for the top
  private JPanel textPanel;    // container panel for the bottom

  public AdvancedImageView(BufferedImage image) {
    setTitle("Image Modifier");
    mainSplitPane = new JSplitPane();
    imagePanel = new JPanel();
    textPanel = new JPanel();

    getContentPane().setLayout(new GridLayout());
    getContentPane().add(mainSplitPane);

    mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    mainSplitPane.setDividerLocation(500);
    mainSplitPane.setTopComponent(imagePanel);                  // at the top we want our "topPanel"
    mainSplitPane.setBottomComponent(textPanel);

    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
    imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));

    setJMenuBar(menuBar());

    imagePanel.add(ImagePane(image));
    textPanel.add(scriptInputBox());

    pack();

  }

  private String getOpenPath(String desc, String... types) throws IllegalArgumentException {
    final JFileChooser fileChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        desc, types);
    fileChooser.setFileFilter(filter);
    int resp = fileChooser.showOpenDialog(AdvancedImageView.this);
    if (resp == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    throw new IllegalArgumentException("File not Valid");
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Sepia":
        System.out.println(1);break;
      case "Grayscale": System.out.println(2); break;
      case "Sharpen": System.out.println(3); break;
      case "Blur": break;
      case "Mosaic": break;
      case "Shrink": break;
      case "Save Layer as PPM": break;
      case "Save Layer As PNG": break;
      case "Save Layer as JPG": break;
      case "Save All as PPM": break;
      case "Save All as PNG": break;
      case "Save all as JPG": break;
      case "Load Script":
        getOpenPath("Text file containing script","txt"); break;
      case "Load Layer":
        getOpenPath("JPG, PNG, and PPM images", "jpg", "png", "jpeg", "ppm"); break;
      case "Load multi-layer Image":
        getOpenPath("Text file containing layered image data", "txt"); break;
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {

  }

  private void initMenuBarField(int menuIndex, JMenuItem[] items) {
    for (JMenuItem curr: items) {
      menuItems[menuIndex].add(curr);
      curr.setActionCommand(curr.getText());
      curr.addActionListener(this);
    }
  }

  public JMenuBar menuBar() {
    JMenuBar menuBar = new JMenuBar();

    initMenuBarField(2, editItems);
    initMenuBarField(0, saveItems);
    initMenuBarField(1, loadItems);

    for (JMenu curr: menuItems) {
      menuBar.add(curr);
    }
    return menuBar;
  }

  public static JScrollPane ImagePane(BufferedImage image) {
    JLabel img = new JLabel(new ImageIcon(image));
    return new JScrollPane(img, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  }

  public static JScrollPane scriptInputBox() {
    JTextArea sTextArea = new JTextArea(10, 20);
    JScrollPane scrollPane = new JScrollPane(sTextArea);
    sTextArea.setLineWrap(true);
    //scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setPreferredSize(new Dimension(300, 500));
    scrollPane.setBorder(BorderFactory.createTitledBorder("Input Script"));
    return scrollPane;
  }

}
