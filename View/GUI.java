package View;

import javax.swing.*;

import Model.ModelMainMenu;

import java.awt.*;
import java.awt.Container;
import java.awt.event.*;

public class GUI{
    protected static final Dimension SYSTEM_SIZE = new Dimension(960, 960);//3120, 3120 //48x20
    protected static final Color TARAWERA = new Color(14, 65, 96);
    protected static final Color MALIBU = new Color(113,171,233);
    protected static final Color SPRING_LEAVES = new Color(85, 131, 104);
    protected static final Color PINE_GLADE = new Color(118, 178, 181);
    protected static final Color TAWNY_PORT = new Color(114, 38, 89);
    protected static final Color BURNING_SAND = new Color(222, 137, 125);
    protected static final Color COPPER = new Color(196, 121, 66);
    protected static final Color AMAZON = new Color(45, 105, 77);
    protected static final Color PICKLED_BLUEWOOD = new Color(46, 62, 77);
    protected static final Color MAVERICK = new Color(217, 195, 206);
    protected static final Color TALLOW = new Color(162, 157, 137);
    protected static final Color ALPINE = new Color(179, 145, 45);
    protected static final Color MING = new Color(46, 109, 115);
    protected static final Color GOLD_SAND = new Color(229, 193, 126);
    protected static final Color PAARL = new Color(153, 89, 46);
    protected static final Color CANNON_PINK = new Color(152, 71, 150);
    protected static final Color SUNGLOW = new Color(255, 212, 40);

    private JFrame screen;
    private Container container;
    private MainMenu mainMenu;
    private SoundSettings soundMenu;
    private JButton btnBackToMainMenu1;
    private JButton btnBackToMainMenu2;
    private AboutAI aboutMenu;
    private ChooseLevel chooseLevelMenu;
    private NumberOfCells numberOfCellsMenu;
    private StartGoldMiner startAISystem;

    public GUI(ModelMainMenu sample){
        screen = new JFrame("Gold Miner");
        screen.setSize(SYSTEM_SIZE);
        screen.setLayout(new BorderLayout());
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setResizable(false);
        screen.getContentPane().setBackground(TARAWERA);
        container = screen.getContentPane();

        initializeGUIParts(sample);
        showMainMenu();

        //startAISystem = new StartGoldMiner();
        //container.add(new ModernScrollPane(startAISystem));

        screen.setVisible(true);
    }

    public void initializeGUIParts(ModelMainMenu sample){
        setUpBackToMainMenuBtn(btnBackToMainMenu1 = new JButton("Go back to Main Menu"));
        setUpBackToMainMenuBtn(btnBackToMainMenu2 = new JButton("Go back to Main Menu"));
        mainMenu = new MainMenu();
        soundMenu = new SoundSettings();
        aboutMenu = new AboutAI(sample.getStrAboutGoldMiner());
        aboutMenu.add(btnBackToMainMenu1);
        soundMenu.add(btnBackToMainMenu2);
        chooseLevelMenu = new ChooseLevel();
        numberOfCellsMenu = new NumberOfCells();
        //startAISystem = new StartGoldMiner(numberOfCells);
    }

    public void setActionListeners(ActionListener listener){
        mainMenu.getBtnStartAI().addActionListener(listener);
        mainMenu.getBtnAboutGoldMiner().addActionListener(listener);
        mainMenu.getBtnSoundSettings().addActionListener(listener);
        mainMenu.getBtnExitAI().addActionListener(listener);
        btnBackToMainMenu1.addActionListener(listener);
        btnBackToMainMenu2.addActionListener(listener);
        soundMenu.getBtnDefaultTrack().addActionListener(listener);
        soundMenu.getBtnTrack1().addActionListener(listener);
        soundMenu.getBtnTrack2().addActionListener(listener);
        numberOfCellsMenu.getTxtFldNumOfCells().addActionListener(listener);
        numberOfCellsMenu.getBtnSubmit().addActionListener(listener);
        chooseLevelMenu.getBtnRandom().addActionListener(listener);
        chooseLevelMenu.getBtnIntelligent().addActionListener(listener);
    }

    public void setUpBackToMainMenuBtn(JButton btn){
        btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        btn.setBackground(PINE_GLADE);
        btn.setForeground(MING);
        btn.setUI(new StyledButtonUI(10,15,10,15));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    } 

    public NumberOfCells getNumOfCellsMenu(){
        return numberOfCellsMenu;
    }

    public void showMainMenu(){
        container.add(mainMenu);
        screen.getContentPane().setBackground(TARAWERA);
        soundMenu.setVisible(false);
        aboutMenu.setVisible(false);
        chooseLevelMenu.setVisible(false);
        numberOfCellsMenu.setVisible(false);
        //startAISystem.setVisible(false);
        mainMenu.setVisible(true);
    }

    public void showSoundSettings(){
        container.add(soundMenu);
        mainMenu.setVisible(false);
        soundMenu.setVisible(true);
    }

    public void showAboutGoldMiner(){
        container.add(aboutMenu);
        mainMenu.setVisible(false);
        aboutMenu.setVisible(true);
    }

    public void showNumOfCellsMenu(){
        container.add(numberOfCellsMenu);
        mainMenu.setVisible(false);
        numberOfCellsMenu.setVisible(true);
    }

    public void showChooseLevelMenu(){
        container.add(chooseLevelMenu);
        numberOfCellsMenu.setVisible(false);
        chooseLevelMenu.setVisible(true);
    }

    public void showJOptionPane(ImageIcon icn, String message){
        JPanel pnl = new JPanel();
        pnl.setLayout(new FlowLayout());

        JLabel lblImage = new JLabel(icn);
        lblImage.setBackground(CANNON_PINK);
        lblImage.setUI(new StyledLabelUI(5,10,5,10));
        pnl.add(lblImage);

        JLabel lbl = new JLabel(message);
        lbl.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        lbl.setBackground(CANNON_PINK);
        lbl.setForeground(SUNGLOW);
        lbl.setUI(new StyledLabelUI(15,20,15,20));
        pnl.add(lbl);
        pnl.setOpaque(false);
        pnl.setVisible(true);
        JOptionPane.showMessageDialog(container, pnl, "oOoOo MINER ALERT oOoOo", JOptionPane.DEFAULT_OPTION);
    }

    /*public static void main(String[] args){
        GUI gui = new GUI();
    }*/
}