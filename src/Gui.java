import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Gui {
    //tu otwieramy okno gry
    JFrame okno = new JFrame("UltimateTicTacToe");
    //na tym panelu znajduje się plansza i historia
    JPanel mainPanel = new JPanel();
    //tu zapisuje się historia gry
    JPanel history = new JPanel(new BorderLayout());
    //tu znajduje się plansza
    JPanel mainPlansza = new JPanel();


//tu znajduje się menu
    JPanel menuPanel = new JPanel();
//zmienna przechowująca informacje o tym który gracz ostatnio wykonał ruch
    String lastLabel = "O";
    //jakie są informacje w tabelce
    DefaultTableModel defaultTableModel;
    //który ruch został wykonany
    private int counter = 0;

    private int[] buttonsNumbers = {3, 2, 1, 6, 5, 4, 9, 8, 7};
    private int[] panelsNumbers = {6, 7, 8, 3, 4, 5, 0, 1, 2};

    private int lastPressed = -1;
//otwieranie okna
    public Gui(){
        //okno
        okno.setSize(1000, 500);
        okno.setLayout(new BorderLayout());
        okno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        okno.setLocationRelativeTo(null);
        okno.setResizable(false);

        setMenu();

        okno.setVisible(true);


    }
//wyświetlanie menu
    public void setMenu() {
        okno.remove(mainPanel);
        okno.remove(history);

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabel = new JLabel("WELCOME TO ULTIMATE TIC TAC TOE");
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(jLabel);
        JLabel jLabel2 = new JLabel("Select player:");
        jLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(jLabel2);
        JRadioButton onePlayer = new JRadioButton("One player");
        onePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        JRadioButton twoPlayers = new JRadioButton("Two players");
        twoPlayers.setAlignmentX(Component.CENTER_ALIGNMENT);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(onePlayer);
        buttonGroup.add(twoPlayers);

        menuPanel.add(onePlayer);
        menuPanel.add(twoPlayers);

        JButton jButton = new JButton("START");
        jButton.addActionListener(new ActionListener() {
            @Override
            //rozpoczynanie gry
            public void actionPerformed(ActionEvent e) {
                setGame();
            }
        });
        jButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(jButton);


        okno.add(menuPanel);

        okno.revalidate();
        okno.repaint();
    }
//uruchomienie gry, wyświetlenie planszy
    public void setGame() {
        okno.remove(menuPanel);
        //panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setFocusable(true);

        mainPlansza.setLayout(new GridLayout(3,3));
        for (int i = 0; i < 9; i++) {
            final int[] toAdd = {-2};
            JPanel miniPlansza = new JPanel(new GridLayout(3, 3));
            miniPlansza.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            for (int j = 8; j >= 0; j--) {
                JButton jButton = new JButton();
                int finalI = i;
                int finalJ = j;
                jButton.addActionListener(new ActionListener() {
                    //przy naciśnięciu przycisku zmienia się symbol na planszy
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (lastPressed == -1 || finalI == panelsNumbers[lastPressed - 1]) {
                            if (jButton.getText().isEmpty()) {
                                setPlayerLabel(jButton);
                                addHistory(finalI, buttonsNumbers[finalJ]);
                                lastPressed = buttonsNumbers[finalJ];
                            }
                        }
                    }
                });
                jButton.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        Integer.parseInt(e.getKeyChar() + "");
                    }
                });
                //dodawanie przycisków
                miniPlansza.add(jButton);
            }
            //dodawanie plansz
            mainPlansza.add(miniPlansza);

        }

//historia
        defaultTableModel = new DefaultTableModel(new String[]{"L.P.", "Gracz", "Główna Plansza", "Miejsce na Konkretnej Planszy"}, 0);
        JTable jTable = new JTable(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        history.add(jScrollPane);

        mainPanel.add(mainPlansza);
        okno.add(mainPanel, BorderLayout.CENTER);
        okno.add(history, BorderLayout.EAST);

        okno.revalidate();
        okno.repaint();
    }

    public JFrame getOkno() {
        return okno;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getMainPlansza() {
        return mainPlansza;
    }

    public JPanel getMiniPlansza() {
        return null;
    }
//kto teraz gra
    public void setPlayerLabel(JButton button) {
        if (lastLabel.equals("O")) lastLabel = "X";
        else lastLabel = "O";
        button.setText(lastLabel);
    }

//wpisywanie historii
    public void addHistory(int maleMiejsce, int miejscePrzycisku) {
        String[] maleMiejsceTab = {"LG", "SG", "PG", "LS", "SS", "PS", "LD", "SD", "PD"};
        String[] miejscePrzyciskuTab = {"LD", "SD", "PD", "LS", "SS", "PS", "LG", "SG", "PG"};

        String[] his = new String[4];
        his[0] = ++counter + "";
        his[1] = lastLabel;
        his[2] = maleMiejsceTab[maleMiejsce];
        his[3] = miejscePrzyciskuTab[miejscePrzycisku - 1];
        defaultTableModel.addRow(his);
    }
}
