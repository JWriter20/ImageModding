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

/**
 * A class used to view the program.
 */
public class AdvancedImageView extends JFrame implements AdvancedView,
    ActionListener {

  private String currentLayerName;
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
  private JPanel imagePanel;       // container panel for the top
  private JLabel fileNameDisplay;
  private Notifiable v;

  /**
   * Creates an AdvancedImageView object with the given Notifiable object.
   *
   * @param v The notifiable object
   */
  public AdvancedImageView(Notifiable v) {
    this.v = v;
    setTitle("Untitled-" + new Date().getTime());
    JSplitPane mainSplitPane = new JSplitPane(); // split the window in top and bottom
    imagePanel = new JPanel();
    JPanel textPanel = new JPanel(); // container panel for the bottom

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
    //textPanel.add(titleSetter());

    setSize(800, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    pack();

  }

  public void addLayer(String name) {
    layers.add(new JMenu(name));
  }

  private String getOpenPath(String desc, boolean filterOn, String... types)
      throws IllegalArgumentException {
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
      return f.getName();
    }
    throw new IllegalArgumentException("File not Valid");
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

    JButton inputButton = new JButton("Set layer name");
    inputButton.setActionCommand("SetName");
    inputButton.addActionListener(this);
    inputDialogPanel.add(inputButton);
    setTitle(inputDialogPanel.getName());
    fileNameDisplay = new JLabel("Layer name: " + getTitle());
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
    return getOpenPath("File save location", false);
  }

  private void transform(String command) {
    if (!layers.isEmpty()) {
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
      case "SetName":
        String s = JOptionPane.showInputDialog("Please enter your username");
        if (s != null) {
          fileNameDisplay.setText("Layer name: " + s);
        }
        break;
      case "Create Layer":
        String layerName = JOptionPane.showInputDialog("Enter the layer name");
        if (layerName != null) {
          v.update("create layer " + layerName);
          layers.add(new JMenu(layerName));
          setJMenuBar(menuBar());
        } else {
          v.update("create layer " + getName());
        }
        break;
      case "Sepia":
        transform("sepia");
        break;
      case "Grayscale":
        transform("grayscale");
        break;
      case "Sharpen":
        transform("sharp");
        break;
      case "Blur":
        transform("blur");
        break;
      case "Mosaic":
        JOptionPane.showMessageDialog(this,
                "This function may take awhile to perform, it will update upon completion.");
        String numSeeds = JOptionPane.showInputDialog("Enter the number of seeds");
        if (numSeeds != null) {
          transform("mosaic " + numSeeds);
        }

        break;
      case "Shrink":
        JTextField width = new JTextField(5);
        JTextField height = new JTextField(5);
        JPanel twoArgs = new JPanel();
        twoArgs.add(new JLabel("width: "));
        twoArgs.add(width);
        twoArgs.add(new JLabel("height: "));
        twoArgs.add(height);
        int res = JOptionPane.showConfirmDialog(null, twoArgs,
                "Enter your new desired width and height", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
          transform("shrink " + width.getText() + " " + height.getText());
        }

        break;
      case "Save Layer as PPM":
        v.update("save " + saveFileName() + ".ppm");
        break;
      case "Save Layer As PNG":
        v.update("save " + saveFileName() + ".png");
        break;
      case "Save Layer as JPG":
        v.update("save " + saveFileName() + ".jpg");
        break;
      case "Save All as PPM":
        v.update("save-multi " + getSaveFolder() + " ppm");
        break;
      case "Save All as PNG":
        v.update("save-multi " + getSaveFolder() + " png");
        break;
      case "Save all as JPG":
        v.update("save-multi " + getSaveFolder() + " jpg");
        break;
      case "Submit":
        v.runScript(sTextArea.getText());
        break;
      case "Load Script":
        String sciptPath = getOpenPath("Text file containing script", true, "txt");
        System.out.println(sciptPath);
        v.runFileScript(sciptPath);
        break;
      case "Load Layer":
        String loadedName = getOpenPath("JPG, PNG, and PPM images",
            true, "jpg", "png", "jpeg", "ppm");
        v.update("load " + loadedName);
        break;
      case "Load multi-layer Image":
        String loadedMulti = getOpenPath("Text file containing layered image data",
                true, "txt");
        v.update("load " + loadedMulti);
        break;
      default:
        String[] command = e.getActionCommand().split(" ");
        if (command.length != 2) {
          throw new IllegalArgumentException("Invalid command");
        } else {
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
            default: {
              //Should not get here
            }
          }
        }
    }
  }


  //Initializes the MenuBar field
  private void initMenuBarField(int menuIndex, JMenuItem[] items) {
    for (JMenuItem curr : items) {
      menuItems[menuIndex].add(curr);
      curr.setActionCommand(curr.getText());
      curr.addActionListener(this);
    }
  }

  /**
   * Creates a JMenuBar.
   * @return Returns the JMenuBar
   */
  public JMenuBar menuBar() {
    JMenuBar menuBar = new JMenuBar();

    initMenuBarField(2, editItems);
    initMenuBarField(0, saveItems);
    initMenuBarField(1, loadItems);
    for (JMenu layer : layers) {
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

    for (JMenu curr : menuItems) {
      menuBar.add(curr);
    }
    return menuBar;
  }

  /**
   * Sets the image to the given one.
   * @param image The given image
   */
  public void setImage(BufferedImage image) {
    JLabel img = new JLabel(new ImageIcon(image));
    imagePanel.removeAll();
    imagePanel.add(new JScrollPane(img, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
  }

  /**
   * Repaints the image as the given one.
   * @param image The given image
   */
  public void repaint(BufferedImage image) {
    imagePanel.setVisible(false);
    setImage(image);
    imagePanel.repaint();
    imagePanel.setVisible(true);
  }

  /**
   * Creates the JScrollPane that is used for the user to input a script.
   * @return A JScrollPane that can be used for this purpose
   */
  public JScrollPane scriptInputBox() {
    sTextArea = new JTextArea(10, 20);
    JScrollPane scrollPane = new JScrollPane(sTextArea);
    sTextArea.setLineWrap(true);
    //scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setPreferredSize(new Dimension(800, 700));
    scrollPane.setBorder(BorderFactory.createTitledBorder("Input Script"));
    return scrollPane;
  }

}
