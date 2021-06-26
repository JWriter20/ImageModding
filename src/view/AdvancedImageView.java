package view;

import controller.Notifiable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

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

  private JTextArea sTextArea;
  private final JMenu[] menuItems = {new JMenu("Save"), new JMenu("Load"), new JMenu("Edit")};
  private JSplitPane mainSplitPane;  // split the window in top and bottom
  private JPanel imagePanel;       // container panel for the top
  private JPanel textPanel;    // container panel for the bottom
  private Notifiable v;
  private JLabel fileNameDisplay;

  public AdvancedImageView(BufferedImage image) {
    setTitle("Untitled-" + new Date().getTime());
    mainSplitPane = new JSplitPane();
    imagePanel = new JPanel();
    textPanel = new JPanel();

    getContentPane().setLayout(new GridLayout());
    getContentPane().add(mainSplitPane);

    mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    mainSplitPane.setDividerLocation(500);
    mainSplitPane.setTopComponent(imagePanel);
    mainSplitPane.setBottomComponent(textPanel);

    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
    imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));

    setJMenuBar(menuBar());

    imagePanel.add(ImagePane(image));
    textPanel.add(scriptInputBox());
    textPanel.add(submitScript());
    textPanel.add(titleSetter());



    setSize(500, 750);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    pack();

  }

  private String getOpenPath(String desc, boolean filterOn, String... types) throws IllegalArgumentException {
    final JFileChooser fileChooser = new JFileChooser(".");
    if (filterOn) {
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
          desc, types);
      fileChooser.setFileFilter(filter);
    } else {
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    int resp = fileChooser.showOpenDialog(AdvancedImageView.this);
    if (resp == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    throw new IllegalArgumentException("File not Valid");
  }


  @Override
  public void setNotifiable(Notifiable v) {
    this.v = v;
  }

  private JButton submitScript() {
    JButton submit = new JButton("Run Script");
    submit.addActionListener(this);
    submit.setActionCommand("Submit");
    return submit;
  }

  private JPanel titleSetter() {
    JPanel inputDialogPanel = new JPanel();
    inputDialogPanel.setLayout(new FlowLayout());

    JButton inputButton = new JButton("Click to set fileName");
    inputButton.setActionCommand("SetName");
    inputButton.addActionListener(this);
    inputDialogPanel.add(inputButton);
    setTitle(inputDialogPanel.getName());
    fileNameDisplay = new JLabel("Filename: " + getTitle());
    inputDialogPanel.add(fileNameDisplay);
    return inputDialogPanel;
  }

  private String getSaveFolder() {
    String s = getOpenPath("File save location", false);
    s = s.substring(0, s.length() - 1);
    return s.substring(s.lastIndexOf("/"));
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "SetName" : String s = JOptionPane.showInputDialog("Please enter your username");
      if (s != null) {
        fileNameDisplay.setText("File name: " + s);
      } break;
      case "Sepia": v.update("sepia"); break;
      case "Grayscale": v.update("grayScale"); break;
      case "Sharpen": v.update("sharp"); break;
      case "Blur": v.update("blur"); break;
      case "Mosaic": v.update("mosaic"); break;
      case "Shrink": v.update("shrink"); break;
      case "Save Layer as PPM": v.update("save " + getTitle() + ".ppm"); break;
      case "Save Layer As PNG": v.update("save " + getTitle() + ".png"); break;
      case "Save Layer as JPG": v.update("save " + getTitle() + ".jpg"); break;
      case "Save All as PPM": v.update("save-multi " + getSaveFolder() + ".ppm");break;
      case "Save All as PNG": v.update("save-multi " + getSaveFolder() + ".png");break;
      case "Save all as JPG": v.update("save-multi " + getSaveFolder() + ".jpg");break;
      case "Submit": v.runScript(sTextArea.getText()); break;
      case "Load Script":
        v.runFileScript(getOpenPath("Text file containing script",true,"txt")); break;
      case "Load Layer":
        String loadedName = getOpenPath("JPG, PNG, and PPM images",
            true,"jpg", "png", "jpeg", "ppm");
        v.update("load " + loadedName.substring(loadedName.lastIndexOf('/')));
         break;
      case "Load multi-layer Image":
        String loadedMulti = getOpenPath("Text file containing layered image data",true, "txt");
        v.update("load " + loadedMulti);
        break;
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

  public void repaint(BufferedImage image) {
    imagePanel.remove(0);
    imagePanel.add(ImagePane(image));
    super.repaint();
  }

  public JScrollPane scriptInputBox() {
    sTextArea = new JTextArea(10, 20);
    JScrollPane scrollPane = new JScrollPane(sTextArea);
    sTextArea.setLineWrap(true);
    //scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setPreferredSize(new Dimension(300, 500));
    scrollPane.setBorder(BorderFactory.createTitledBorder("Input Script"));
    return scrollPane;
  }

}
