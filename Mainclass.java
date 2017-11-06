import javax.swing.JFrame;


public class Mainclass {
	public static void main(String[] args) {
		JFrame f=new JFrame();
		Game game=new Game();
		f.setBounds(10, 10, 700, 600);
		f.setTitle("My First Game");
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(game);

	}

}
