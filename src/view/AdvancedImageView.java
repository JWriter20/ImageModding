package view;

import controller.Notifiable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdvancedImageView extends JFrame implements AdvancedView,
    ActionListener, ItemListener {

  private String currentTitle;
  private final JMenuItem[] editItems = {
      new JMenuItem("Create Layer"),
      new JMenuItem("Sepia"), new JMenuItem("Grayscale"),
      new JMenuItem("Sharpen"), new JMenuItem("Blur"),
      new JMenuItem("Mosaic"), new JMenuItem("Shrink"),
  };

  private List<JMenu> layers = new ArrayList<JMenu>();

  private final JMenuItem[] saveItems = {
      new JMenuItem("Save Layer as PPM"), new JMenuItem("Save Layer As PNG"),
      new JMenuItem("Save Layer as JPG"), new JMenuItem("Save All as PPM"),
      new JMenuItem("Save All as PNG"), new JMenuItem("Save all as JPG")};

  private final JMenuItem[] loadItems = {
      new JMenuItem("Load Script"), new JMenuItem("Load Layer"),
      new JMenuItem("Load multi-layer Image")
  };

  private JTextArea sTextArea;
  private final JMenu[] menuItems = {new JMenu("Save"), new JMenu("Load"), new JMenu("Edit"),
      new JMenu("Layer Options")};
  private JSplitPane mainSplitPane;  // split the window in top and bottom
  private JPanel imagePanel;       // container panel for the top
  private JPanel textPanel;    // container panel for the bottom
  private JLabel fileNameDisplay;
  private Notifiable v;

  public AdvancedImageView(Notifiable v) {
    this.v = v;
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

    //imagePanel.add();
    textPanel.add(scriptInputBox());
    textPanel.add(submitScript());
    textPanel.add(titleSetter());

    setSize(600, 750);
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
    System.out.println(v);
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

  private String saveFileName() {
    String s = JOptionPane.showInputDialog("Save as");
    if (s == null) {
      return getTitle();
    } else {
      return s;
    }
  }

  private String getSaveFolder() {
    String s = getOpenPath("File save location", false);
    s = s.substring(0, s.length() - 1);
    return s.substring(s.lastIndexOf("/"));
  }

  private void transform(String command) {
    if(!layers.isEmpty()) {
      v.update(command);
    } else {
      showCreateLayerDialog();
    }
  }

  private void showCreateLayerDialog() {
    JOptionPane.showMessageDialog(this, "You must create a layer first before you can edit" +
        "click on edit -> Create Layer. To change the current layer click on it in Layer Options.");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "SetName" : String s = JOptionPane.showInputDialog("Please enter your username");
      if (s != null) {
        fileNameDisplay.setText("Layer name: " + s);
      } break;
      case "Create Layer":
        String layerName = JOptionPane.showInputDialog("Enter the layer name");
        if (layerName != null) {
          v.update("create layer " + layerName);
          layers.add(new JMenu(layerName));
          setJMenuBar(menuBar());
        }else {
          v.update("create layer " + getName());
        }
        break;
      case "Sepia": transform("sepia"); break;
      case "Grayscale": transform("grayScale"); break;
      case "Sharpen": transform("sharp"); break;
      case "Blur": transform("blur"); break;
      case "Mosaic": transform("mosaic"); break;
      case "Shrink": transform("shrink"); break;
      case "Save Layer as PPM":
        v.update("save " + saveFileName() + ".ppm"); break;
      case "Save Layer As PNG":
        v.update("save " + saveFileName() + ".png"); break;
      case "Save Layer as JPG":
        v.update("save " + saveFileName() + ".jpg"); break;
      case "Save All as PPM": v.update("save-multi " + getSaveFolder() + " ppm");break;
      case "Save All as PNG": v.update("save-multi " + getSaveFolder() + " png");break;
      case "Save all as JPG": v.update("save-multi " + getSaveFolder() + " jpg");break;
      case "Submit":
        System.out.println(v);
        v.runScript(sTextArea.getText()); break;
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
      default:
        String[] command = e.getActionCommand().split(" ");
        if (command.length != 2) {
          throw new IllegalArgumentException("Invalid command");
        }else {
          switch (command[1]) {
            case "Set As Current":
              v.update("current " + command[0]);
              setTitle(command[0]);
              break;
            case "Hide Layer":
              v.update("invisible " + command[0]);
              break;
            case "Show Layer":
              v.update("visible " + command[0]);
              break;
          }
        }
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
    for (JMenu layer: layers) {
      JMenuItem curr = new JMenuItem("Set As Current");
      curr.setActionCommand(layer.getText() + " " + curr.getText());
      curr.addActionListener(this);
      JMenuItem invis = new JMenuItem("Hide Layer");
      invis.setActionCommand(layer.getText() + " " + invis.getText());
      invis.addActionListener(this);
      JMenuItem vis = new JMenuItem("Show Layer");
      vis.setActionCommand(layer.getText() + " " + vis.getText());
      vis.addActionListener(this);
      layer.add(curr);
      layer.add(invis);
      layer.add(vis);
    }

    for (JMenu curr: menuItems) {
      menuBar.add(curr);
    }
    return menuBar;
  }

  public void setImage(BufferedImage image) {
    JLabel img = new JLabel(new ImageIcon(image));
    imagePanel.add(new JScrollPane(img, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
  }

  public void repaint(BufferedImage image) {
    setImage(image);
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
