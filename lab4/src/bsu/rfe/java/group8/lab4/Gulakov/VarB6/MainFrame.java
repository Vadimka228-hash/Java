package bsu.rfe.java.group8.lab45.Gulakov.varB6;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;



public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int HEIGHT = 600;
	private final int WIDTH = 600;
	private boolean fileLoaded = false;
	private GraphicsDisplay display = new GraphicsDisplay();
	private JCheckBoxMenuItem showAxisMenuItem;
	private JCheckBoxMenuItem showMarkersMenuItem;
	private JFileChooser fileChooser = null;
	private DataInputStream in;
	public MainFrame()  {
		super("Вывод графика функции");
		setSize(WIDTH, HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("Файл");
		menuBar.add(fileMenu);
		Action openGraphicsAction = new AbstractAction("Открыть файл") {

					private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {
				if (fileChooser==null) {
					fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File(".")); 
					} 
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION); 
					openGraphics(fileChooser.getSelectedFile());
				
			}
		};
		fileMenu.add(openGraphicsAction);
		
		JMenu graphicsMenu = new JMenu("График");
		menuBar.add(graphicsMenu);
	
		Action showAxisAction = new AbstractAction("Показать оси координат") {
			
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				display.setShowAxis(showAxisMenuItem.isSelected());
			}
		};
		showAxisMenuItem = new JCheckBoxMenuItem(showAxisAction);
		graphicsMenu.add(showAxisMenuItem);
		showAxisMenuItem.setSelected(true);
		    
		    
		Action showMarkersAction = new AbstractAction("Показать маркеры точек") {
			

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
			display.setShowMarkers(showMarkersMenuItem.isSelected());	
			}
		};
		showMarkersMenuItem = new JCheckBoxMenuItem(showMarkersAction);
		graphicsMenu.add(showMarkersMenuItem);
		showMarkersMenuItem.setSelected(true);
		graphicsMenu.addSeparator();
		graphicsMenu.addMenuListener(new GraphicsMenuListener());
		getContentPane().add(display, BorderLayout.CENTER);
	}
	
	protected void openGraphics(File selectedFile) {
			 try {
			      in = new DataInputStream(new FileInputStream(selectedFile));
			      ArrayList<Double[]> graphicsData = new ArrayList<Double[]>(50);
			      while (in.available() > 0) {
			        Double x = Double.valueOf(in.readDouble());
			        Double y = Double.valueOf(in.readDouble());
			        graphicsData.add(new Double[] { x, y });
			      }
			      if (graphicsData.size() > 0) {
			        fileLoaded = true;
			        display.showGraphics(graphicsData);
			      }
		}catch (FileNotFoundException e){
			
		}catch (IOException e){
			
			}
		}

	private class GraphicsMenuListener implements MenuListener {

		
		
		
		public void menuDeselected(MenuEvent arg0) {
			
		}
		
		public void menuSelected(MenuEvent arg0) {
			
			
			showAxisMenuItem.setEnabled(fileLoaded);
			showMarkersMenuItem.setEnabled(fileLoaded);
	
		}

		@Override
		public void menuCanceled(MenuEvent arg0) {
			
		}
	}
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}