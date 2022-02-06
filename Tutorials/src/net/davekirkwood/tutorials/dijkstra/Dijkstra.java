package net.davekirkwood.tutorials.dijkstra;

import net.davekirkwood.tutorials.dijkstra.algorithm.DijkstraPath;
import net.davekirkwood.tutorials.dijkstra.algorithm.DijkstraSolver;
import net.davekirkwood.tutorials.dijkstra.grid.Grid;
import net.davekirkwood.tutorials.dijkstra.grid.Grid.GRID_CELL_VALUE;
import net.davekirkwood.tutorials.dijkstra.grid.GridCoordinate;
import net.davekirkwood.tutorials.dijkstra.grid.HilbertMaze;
import net.davekirkwood.tutorials.dijkstra.ui.DijkstraUI;
import net.davekirkwood.tutorials.dijkstra.ui.GridListener;

public class Dijkstra implements GridListener {

	private DijkstraUI ui;
	private Grid grid;
	
	public Dijkstra(Grid grid) {
		this.grid = grid;
		ui = new DijkstraUI(grid, this);
	}
	
	public static void main(String[] args) {
		new Dijkstra(new HilbertMaze(4));
	}

	@Override
	public void click(GridCoordinate coord, GRID_CELL_VALUE value) {
		grid.setCellValue(coord, value);
		if(value == GRID_CELL_VALUE.END) {
			solve();
		}
		ui.refresh();
	}

	@Override
	public void clear() {
		grid.clear();
		ui.refresh();
	}
	
	public void solve() {

		DijkstraPath path = new DijkstraSolver(grid).solve();
		
		if(path != null) {
			for(GridCoordinate step : path.getSteps()) {
				grid.setCellValue(step, GRID_CELL_VALUE.ROUTE);
			}
		}
		
	}
	
}
