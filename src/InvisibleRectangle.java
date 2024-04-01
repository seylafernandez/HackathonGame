import java.awt.*;
public class InvisibleRectangle {

    public int xpos;
    public int ypos;
    public int dx;
    public int width;
    public int height;
    public Image pic; //will comment out
    public Rectangle rec;

    public InvisibleRectangle(int pXpos, int pYpos, int dxParameter) {
        xpos = pXpos;
        ypos = pYpos;
        width = 150;
        height = 150;
        dx = dxParameter;
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void move(){
        xpos = xpos + dx;
        ypos = ypos;

        if(xpos < 0){
            xpos = 1000;
        }

        rec = new Rectangle(xpos, ypos, width, height);


    }


    }
