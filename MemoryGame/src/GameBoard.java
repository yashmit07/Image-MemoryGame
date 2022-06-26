import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class GameBoard extends JFrame{

    private List<JButton> fruits;
    private JButton selectedImage;
    private JButton fruit1;
    private JButton fruit2;
    private Timer timer;
    private int ClickCount = 0;

    public GameBoard(){
        StartGame();
    }

    public void StartGame()
    {
        int matches = 10;
        List<JButton> imgList = new ArrayList<JButton>();
        List<Integer> imgIds = new ArrayList<Integer>();

        for (int i = 0; i < matches; i++){
            imgIds.add(i);
            imgIds.add(i);
        }
        Collections.shuffle(imgIds);

        for (int counter=0; counter<imgIds.size(); counter++){
            JButton objF = new JButton();
            objF.setName(Integer.toString(imgIds.get(counter)));
            objF.setBackground(Color.GRAY);
            objF.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    selectedImage = objF;
                    clickImage();
                }
            });
            imgList.add(objF);
        }
        this.fruits = imgList;

        //Add timer to hide cards
        timer = new Timer(750, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                checkMatch();
            }
        });
        timer.setRepeats(false);

        //Create Game board
        Container container = getContentPane();
        container.setLayout(new GridLayout(4, 5));
        for (JButton button : fruits){
            container.add(button);
        }
        setTitle("APCS - Final Project by Yashmit Singh!");
    }

    public void clickImage(){
        ClickCount++;
        //when first image is clicked
        if (fruit1 == null && fruit2 == null){
            fruit1 = selectedImage;
            //fruit1.setText(fruit1.getName());

            //get fruit image and set as icon
            String imgPath = "img/" + fruit1.getName() + ".gif";
            Icon icon = new ImageIcon(imgPath);
            fruit1.setIcon(icon);
        }

        if (fruit1 != null && fruit1 != selectedImage && fruit2 == null){
            fruit2 = selectedImage;
            //fruit2.setText(fruit2.getName());

            //get fruit image and set as icon
            String imgPath = "img/" + fruit2.getName() + ".gif";
            Icon icon = new ImageIcon(imgPath);
            fruit2.setIcon(icon);

            timer.start();
        }
    }

    public void checkMatch(){
        //check if both names are same
        if (fruit1.getName().equals(fruit2.getName())){
            disableMatchedImages();
        }
        else{
            resetFruit();
        }
    }

    public void disableMatchedImages()
    {
        //Set button to true to check if game is over
        fruit1.setSelected(true);
        fruit2.setSelected(true);

        //Clear all event notifications
        fruit1.removeNotify();
        fruit2.removeNotify();

        if (this.checkGameStatus()){
            String message = "";
            if(ClickCount < 40)
            {
                message = "You are an expert! You won in " +ClickCount+ " clicks.";
            }
            else if(ClickCount < 50)
            {
                message = "You did great! You won in " +ClickCount+ " clicks.";
            }
            else
            {
                message = "You won in " +ClickCount+ " clicks.";
            }

            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }

        fruit1 = null;
        fruit2 = null;
    }

    //check if all fruit images matched, if yes, return true otherwise return false
    public boolean checkGameStatus(){
        boolean bGameStatus = true;
        for(JButton f: this.fruits){
            if (f.isSelected() == false){
                bGameStatus = false;
            }
        }
        return bGameStatus;
    }

    public void resetFruit()
    {
        //reset fruit objects
        fruit1.setIcon(null);
        fruit2.setIcon(null);
        fruit1 = null;
        fruit2 = null;
    }

}