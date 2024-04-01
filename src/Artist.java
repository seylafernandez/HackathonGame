import java.awt.*;
public class Artist {

    public int xpos;                //the x position
    public int ypos;                //the y position
    public int width;
    public int height;
    public Image pic;

    public Artist(int pXpos, int pYpos, Image picParameter) {
        xpos = pXpos;
        ypos = pYpos;
        width = 190;
        height = 350;
        pic = picParameter;
    }
}
