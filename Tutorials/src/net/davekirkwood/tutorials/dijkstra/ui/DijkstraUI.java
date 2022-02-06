package net.davekirkwood.tutorials.dijkstra.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import net.davekirkwood.tutorials.dijkstra.grid.Grid;
import net.davekirkwood.tutorials.dijkstra.grid.GridCoordinate;
import net.davekirkwood.tutorials.dijkstra.grid.Grid.GRID_CELL_VALUE;

public class DijkstraUI extends JFrame {

	private static int CELLSIZE = 25;
	
	private enum PHASE { START, END };
	
	private PHASE phase = PHASE.START;
	
	class GridButton extends JButton {
		private int x;
		private int y;
		public GridButton(int x, int y) {
			super("");
			this.x = x;
			this.y = y;
			setOpaque(true);
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new Thread(new Runnable() {
						public void run() {
							switch(phase) {
							case END:
								listener.click(new GridCoordinate(x, y), GRID_CELL_VALUE.END);
								phase = PHASE.START;
								setStatus();
								break;
							case START:
								listener.clear();
								listener.click(new GridCoordinate(x, y), GRID_CELL_VALUE.START);
								phase = PHASE.END;
								setStatus();
								break;
							default:
								break;
							
							}
						}
					}).start();
					
				}				
			});
		}
		public void refresh() {
			switch(grid.getCellValue(new GridCoordinate(x, y))) {
			case EMPTY:
				setBackground(Color.WHITE);
				setText("");
				break;
			case END:
				setBackground(Color.RED);
				setText("E");
				break;
			case ROUTE:
				setBackground(Color.BLUE);
				setText("");
				break;
			case START:
				setBackground(Color.GREEN);
				setText("S");
				break;
			default:
				setForeground(Color.WHITE);
				setText("");
				break;
			
			}
		}
	}
	
	private GridListener listener;
	
	private List<GridButton> gridButtons = new ArrayList<GridButton>();
	
	private Grid grid;
	
	private JPanel gridPanel = new JPanel();
	private JTextArea status;
	
	public DijkstraUI(Grid grid, GridListener listener) {
		
		this.grid = grid;
		this.listener = listener;
		
		this.setLayout(new BorderLayout());
		status = new JTextArea();
		this.add(gridPanel, BorderLayout.CENTER);
		this.add(status, BorderLayout.SOUTH);
		
		gridPanel.setLayout(new GridLayout(grid.getGridSize(), grid.getGridSize()));
		for(int y=grid.getGridSize()-1; y>=0; y--) {
			for(int x=0; x<grid.getGridSize(); x++) {
				switch(grid.getCellValue(new GridCoordinate(x, y))) {
				case ROCK:
					JLabel label = new JLabel();
					label.setBackground(Color.BLACK);
					label.setOpaque(true);
					gridPanel.add(label);
					break;
				default:
					GridButton gridButton = new GridButton(x, y);
					gridButtons.add(gridButton);
					gridButton.refresh();
					gridPanel.add(gridButton);
					break;				
				}
			}			
		}
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setSize(grid.getGridSize() * CELLSIZE, 25 + (grid.getGridSize() * CELLSIZE));		
		this.setVisible(true);
		
		setStatus();
		
	}
	
	public void refresh() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					for(GridButton gridButton : gridButtons) {
						gridButton.refresh();
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setStatus() {
		switch(phase) {
		case END:
			status.setText("Click end");
			break;
		case START:
			status.setText("Click start");
			break;
		default:
			break;
		
		}
	}
	
}
