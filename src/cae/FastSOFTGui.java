package cae;

import javax.swing.JFrame;
import javax.swing.UIManager;


public class FastSOFTGui {

	private static JFrame frame;

	public static void main(String[] args) {
		
        try
        { UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() ); }
        catch ( Exception e )
        { System.err.println( e ); }
        
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		frame = new JFrame("Fast SOFT");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainPanel newContentPane = new MainPanel();
		newContentPane.createComponents();
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
		frame.setLocation(200,200);
	}
}
