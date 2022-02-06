package net.davekirkwood.tutorials.dijkstra.ui;

import net.davekirkwood.tutorials.dijkstra.grid.Grid.GRID_CELL_VALUE;
import net.davekirkwood.tutorials.dijkstra.grid.GridCoordinate;

public interface GridListener {

	public void click(GridCoordinate coord, GRID_CELL_VALUE value);
	
	public void clear();
	
}
