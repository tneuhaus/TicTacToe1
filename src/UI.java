import java.awt.*;          
import java.awt.event.*;  
import javax.swing.*; 

public class UI extends JFrame implements ActionListener
{
    private Game myGame;
    private JButton[][] buttonArray;
    private Container all;

    public UI(Game game)
    {
        super("Tic-Tac-Toe");
        myGame = game;
        buttonArray = new JButton[3][3];
        all = getContentPane();

        all.setLayout(new GridLayout(3, 3));

        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
            {
                buttonArray[r][c] = new JButton();
                buttonArray[r][c].setFont(new Font("the font", Font.PLAIN, 30));
                buttonArray[r][c].addActionListener(this);
                all.add(buttonArray[r][c]);
            }

        addWindowListener(new java.awt.event.WindowAdapter() 
            {
                public void windowClosing(WindowEvent evt) 
                {
                    System.exit(0);
                }
            });

        setSize( 400, 400);     
        setVisible(true);		

    }

    public void updateDisplay (char player, Location loc)
    {
        JButton temp = buttonArray[loc.getRow()][loc.getCol()];
        temp.setText("" + player);
    }

    public boolean displayWinner(Result result)
    {

        String choice = "";
        if (result == Result.X_WON)
            choice = "X won! Do you want to play again?";
        else if (result == Result.O_WON)
            choice = "O won! Do you want to play again?";
        else
            choice = "It's a tie.  Do you want to play again?";

        int response = JOptionPane.showConfirmDialog(this, choice, "End of Game", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION)
            return true;

        return false;

    }

    public void clearDisplay()
    {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
            {
                buttonArray[r][c].setText("");
            }
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton source = (JButton)e.getSource();
        Location loc = null; 

        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
            {
                if (buttonArray[r][c] == source)
                {
                    loc = new Location(r,c);
 //                   System.out.println("pressed button " + r + " " + c);
                    break;
                }
            }		

        myGame.pressed(loc);

    }

    public void endProgram()
    {
        System.exit(0);
    }
}
