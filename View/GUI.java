package View;

import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import java.awt.event.*;

import Model.*;

//represents a GUI class
//GUI <= MainMenu <= (Click Start System) <= NumberOfCells <= ChooseLevel <= StartGoldMiner & Counters (begin mining process)
//GUI <= MainMenu <= SoundSettings (Click Sound Settings) <= (sound track will change)
//GUI <= MainMenu <= AboutAI (Click About Gold Miner) <= (about AI will appear)
//GUI <= MainMenu <= (Click Exit System) <= (JFrame - screen will close)
public class GUI
{
    //sizes are in px
    protected static final Dimension SYSTEM_SIZE = new Dimension(960, 960);//Size of Frame //3120, 3120 //48x20
    protected static final int CELL_DIMENSION = 48; //Size per cell
    //Color palette
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
    public static ImageIcon imgError = new ImageIcon("Lib/utilities/Error.png");
    public static ImageIcon imgCongrats = new ImageIcon("Lib/utilities/Congratulations.png");
    public static ImageIcon imgLoser = new ImageIcon("Lib/utilities/Loser.png");
    public static ImageIcon imgMessage = new ImageIcon("Lib/utilities/Message.png");

    //Most of the GUI parts (MainMenu, SoundSettings, etc.) extends JPanel
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
    private Counters counterScreen;

    //create GUI instance
    public GUI(ModelMainMenu modelMainMenu)
    {
        screen = new JFrame("Gold Miner");
        screen.setSize(SYSTEM_SIZE);
        screen.setLayout(new BorderLayout());
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setResizable(false); //frame is not resizable
        screen.getContentPane().setBackground(TARAWERA);
        container = screen.getContentPane();//when you add something to this container, it will reflect on the screen (JFrame)

        initializeGUIParts(modelMainMenu);
        showMainMenu();

        screen.setVisible(true);
    }

    //initialize GUI parts (see comments above for GUI parts hierarchy)
    public void initializeGUIParts(ModelMainMenu modelMainMenu)
    {
        setUpBackToMainMenuBtn(btnBackToMainMenu1 = new JButton("Go back to Main Menu"));
        setUpBackToMainMenuBtn(btnBackToMainMenu2 = new JButton("Go back to Main Menu"));
        mainMenu = new MainMenu();
        soundMenu = new SoundSettings();
        aboutMenu = new AboutAI(modelMainMenu.getStrAboutGoldMiner());
        aboutMenu.add(btnBackToMainMenu1);
        soundMenu.add(btnBackToMainMenu2);
        chooseLevelMenu = new ChooseLevel();
        numberOfCellsMenu = new NumberOfCells();
        startAISystem = new StartGoldMiner(modelMainMenu);//default system has 8 cells
        counterScreen = new Counters("Random");//default system has a Random Agent
    }

    //sets ActionListeners to all buttons within GUI parts
    public void setActionListeners(ActionListener listener) 
    {
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
        chooseLevelMenu.getBtnSmart().addActionListener(listener);
        counterScreen.getBtnStartMining().addActionListener(listener);
        counterScreen.getGoBtn().addActionListener(listener);
        counterScreen.getQuitBtn().addActionListener(listener);
    }

    //customizes BackToMainMenuBtns to be used in MainMenu when SoundSettings
    //or AboutGoldMiner is clicked
    public void setUpBackToMainMenuBtn(JButton btn)
    {
        btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        btn.setBackground(PINE_GLADE);
        btn.setForeground(MING);
        btn.setUI(new StyledButtonUI(10,15,10,15));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    } 

    //returns numberOfCellsMenu JPanel so Model.Game will know the number of
    //cells inputted by a user
    public NumberOfCells getNumOfCellsMenu()
    {
        return numberOfCellsMenu;
    }

    public StartGoldMiner getStartGoldMiner()
    {
        return startAISystem;
    }

    //will show MainMenu when go back to main menu button is clicked
    public void showMainMenu()
    {
        screen.getContentPane().setBackground(TARAWERA);
        //make all other GUI parts invisible except the MainMenu
        soundMenu.setVisible(false);
        aboutMenu.setVisible(false);
        chooseLevelMenu.setVisible(false);
        numberOfCellsMenu.setVisible(false);
        startAISystem.setVisible(false);
        counterScreen.closeCounterFrame();
        mainMenu.setVisible(true);
        container.add(mainMenu);
    }

    //will show SoundSettings when SoundSettings is clicked on the MainMenu
    public void showSoundSettings()
    {
        container.add(soundMenu);
        //make all other GUI parts invisible except SoundSettings
        mainMenu.setVisible(false);
        soundMenu.setVisible(true);
    }

    //will show AboutAI when AboutGoldMiner is clicked on the MainMenu
    public void showAboutGoldMiner()
    {
        container.add(aboutMenu);
        //make all other GUI parts invisible except AboutAI
        mainMenu.setVisible(false);
        aboutMenu.setVisible(true);
    }

    //will show NumberOfCells when StartSystem is clicked on the MainMenu
    public void showNumOfCellsMenu()
    {
        container.add(numberOfCellsMenu);
        //make all other GUI parts invisible except NumberOfCells
        //this will help a user insert their desired Model.Board.MAX_DIMENSION
        mainMenu.setVisible(false);
        numberOfCellsMenu.setVisible(true);
    }

    //will show ChooseLevel when StartSystem is clicked on the MainMenu
    public void showChooseLevelMenu()
    {
        container.add(chooseLevelMenu);
        //make all other GUI parts invisible except ChooseLevel
        //this will help a user choose what level of rational behavior they would like to see
        numberOfCellsMenu.setVisible(false);
        chooseLevelMenu.setVisible(true);
    }

    //will show StartGoldMiner after desired MAX_DIMENSIONhas been set and
    //desired level of rational behavior has been chosen
    //signals start of mining process
    public void showStartGoldMiner(ModelMainMenu modelMainMenu, String lvl)
    {
        startAISystem = null; //put in updatestartgoldminer() later
        startAISystem = new StartGoldMiner(modelMainMenu);
        chooseLevelMenu.setVisible(false);
        startAISystem.setVisible(true);
        container.add(new ModernScrollPane(startAISystem));
        //counterScreen = null; //put in updatestartgoldminer() later
        counterScreen.updateLvl(lvl);
    }

    public void updateStartGoldMiner()
    {

    }

    //will show pop up messages customized based on a particular scenario
    //example: [Gold Miner found Golden Square]
    //         icn(Congratulations.png), message("Congratulations")
    public void showJOptionPane(ImageIcon icn, String message)
    {
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

    //enables StartMiningBtn when mining process is yet to start
    //disables StartMiningBtn when mining process is ongoing
    public void updateStartMiningBtn(boolean bIsEnabled)
    {
        counterScreen.getBtnStartMining().setEnabled(bIsEnabled);
    }

    //makes Counters (with JFrame and action counters label inside) invisible when mining process
    //is done and user is back on the MainMenu
    public void makeCounterInvisible()
    {
        counterScreen.closeCounterFrame();
    }

    public void updateCounterScreen(int nMoves, int nScans, int nRotates, int nPathCost)
    {
        counterScreen.updateCounters(nMoves, nScans, nRotates, nPathCost);
    }

    public void updateMiner(Board gameBoard)
    {
        startAISystem.updateGoldMinerProper(gameBoard);
    }
}