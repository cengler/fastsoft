package cae;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

import cae.model.MainPanel;
import cae.resource.FSImageEnum;
import cae.resource.FSResourceUtil;


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
		ImageIcon icon = FSResourceUtil.getIcon(FSImageEnum.PROGRAM_ICON);
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainPanel newContentPane = new MainPanel();
		newContentPane.createComponents();
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
		frame.setLocation(200,200);
	}
}
