import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class Bricks {
    public static int array[][];
    public static int width,height;
    Random r=new Random();
    public Bricks(int rows,int cols){
    	array=new int[rows][cols];
    	for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				array[i][j]=1;
			}
		}
    	width=540/cols;
    	height=150/rows;
    }
    public void draw(Graphics2D g){
    	for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				if(array[i][j]>0){
					g.setColor(new Color(r.nextInt(255),r.nextInt(255) ,r.nextInt(255)));
					g.fillRect(j*width+80,i*height+50, width,height);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*width+80,i*height+50, width,height);
				}
			}
		}
    }
    public static void setbrickvalue(int value,int row,int col){
    	array[row][col]=value;
    }
}
