import java.awt.*;
import java.util.StringJoiner;

public class ThoughtBubble {
    public int xpos;
    public int ypos;
    public int width;
    public int height;
    public Image pic;
    public Rectangle rec;
    public boolean isAlive;

    public ThoughtBubble(int pXpos, int pYpos,Image picParameter, boolean tesAlivae){

        xpos = pXpos;
        ypos = pYpos;
        width = 150;
        height = 150;
        rec = new Rectangle(xpos, ypos, width, height);
        isAlive= true;


    }



}
