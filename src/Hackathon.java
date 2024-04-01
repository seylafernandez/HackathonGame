import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Hackathon implements Runnable, KeyListener {
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;
    public Artist artist;
    public Image artistPic;
    public ThoughtBubble bubble;
    public ThoughtBubble baseBubble;
    public ThoughtBubble bubble2;
    public ThoughtBubble bubble3;
    public Image bubblePic;
    public Image bubble1Pic;
    public Image bubble2Pic;
    public Image bubble3Pic;
    public Image titleScreen;
    public InvisibleRectangle rect;
    public Image invisiblePic;
    public int points;
    private JFrame mainFrame;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel title;
    private JLabel goal;
    private JLabel instructions;
    private JLabel note;
    private JLabel start;
    private Color lightBlue;
    private Color lightGreen;
    private Color lightPink;
    private Color goodluck;

    private Font font;
    private Font font2;
    private Font goalFont;
    public boolean go = false;




    public JLabel pC;
    public String counter;
    public Image background;
    public Image easel;

    public static void main(String[] args) {
        Hackathon ex = new Hackathon();
        new Thread(ex).start();
    }

    public Hackathon (){
//        canvas.addKeyListener(this);
        GUI();
        System.out.println("we are here");

        artistPic = Toolkit.getDefaultToolkit().getImage("artist.png");
        artist = new Artist(200, 350, artistPic);

        //bubblePic = Toolkit.getDefaultToolkit().getImage("Soap-Bubbles-PNG-Free-Image.png");
        bubble3Pic = Toolkit.getDefaultToolkit().getImage("Bubble.png");
        background = Toolkit.getDefaultToolkit().getImage("download.jpg");
        titleScreen = Toolkit.getDefaultToolkit().getImage("titleScreen.png");

        easel= Toolkit.getDefaultToolkit().getImage("easel.png");
        // bubble1Pic = Toolkit.getDefaultToolkit().getImage("bubble2.png");
        // bubble2Pic = Toolkit.getDefaultToolkit().getImage("bubble3.png");


        //  bubble = new ThoughtBubble(100,300,bubble1Pic, true);
        // bubble2 = new ThoughtBubble(100,300,bubble2Pic, false);
        bubble3 = new ThoughtBubble(100,250,bubble3Pic, false);
        //  invisiblePic = Toolkit.getDefaultToolkit().getImage("blue-neon-button-glowing-neon-button.png");
        rect = new InvisibleRectangle(100,250,-12);

        counter= "Points: "+ points;


    }

    @Override
    public void run() {
        while (true) {
            if(go == true) {
                moveObjects();
                checkIntersections();
            }
            render();
            System.out.println("rendering");
            pause(20);
        }

    }

    public void checkIntersections() {
        if (rect.rec.intersects(bubble3.rec)) {
            bubble3.isAlive = true;
        }
        else {
            bubble3.isAlive = false;
        }
    }


    public void GUI() {
        frame = new JFrame("Artist's Inspiration");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
canvas.addKeyListener(this);
        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }


    private void render() {
        System.out.println("go is " + go);
        if (go==false){
            System.out.println("graphics");
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, WIDTH, HEIGHT);
            g.drawImage(titleScreen, 0, 0, WIDTH, HEIGHT, null);
            g.dispose();
            bufferStrategy.show();

        } // start screen
        else {
            System.out.println("graphics 2");
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, WIDTH, HEIGHT);

            //include images here

            g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
            g.drawImage(artist.pic, artist.xpos, artist.ypos, artist.width, artist.height, null);
            g.drawString(counter, 500, 100);
            g.drawImage(easel, 450, 355, 200, 400, null);


            if (bubble3.isAlive == true) {
                g.drawImage(bubble3Pic, bubble3.xpos, bubble3.ypos, bubble3.width, bubble3.height, null);


            }

            //g.drawRect(rect.xpos, rect.ypos, rect.width, rect.height);



            g.dispose();
            bufferStrategy.show();
        }
    }

    public void moveObjects(){
        rect.move();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //char key = e.getKeyChar();     //gets the character of the key pressed
        int keyCode = e.getKeyCode();
        System.out.println("key code: " + keyCode);
        if(keyCode == 32 && bubble3.isAlive == true){
            points = points + 1;
            System.out.println("Points = " + points);


        }else if(keyCode == 32 && bubble3.isAlive == false){
            points = points - 1;
            System.out.println("Points = " + points);


        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("key code: " + keyCode);
        if(keyCode == 32 && bubble3.isAlive == true){
            points = points + 1;
            System.out.println("Points = " + points);
            counter = "Points: "+points;

        }else if(keyCode == 32 && bubble3.isAlive == false){
            points = points - 1;
            System.out.println("Ouch! Points = " + points);
            counter = "Points: "+points;
        }

        if(keyCode == 10){
            go = true;
            System.out.print("working");
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }
}
