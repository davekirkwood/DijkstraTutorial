package net.davekirkwood.tutorials.dijkstra.grid;

public abstract class Grid {
	
	/**
	 * Possible cell values.
	 */
	public enum GRID_CELL_VALUE { INVALID, EMPTY, ROCK, START, END, ROUTE };
	
	/*
	 * Array representing the Grid.
	 */
	
	private GRID_CELL_VALUE[][] grid;
	
	/**
	 * Size of the grid.
	 */
	protected int gridSize;
	
	/**
	 * Marked start position.
	 */
	private GridCoordinate start;
	
	/**
	 * Marked end position.
	 */
	private GridCoordinate end;
	
	/**
	 * Construct a square grid of the given size.
	 * @param gridSize
	 */
	public Grid(int gridSize) {
		this.gridSize = gridSize;
		grid = new GRID_CELL_VALUE[gridSize][gridSize];
	}
	
	/**
	 * @return the grid
	 */
	public GRID_CELL_VALUE[][] getGrid() {
		return grid;
	}
	
	/**
	 * @return the size of the grid
	 */
	public int getGridSize() {
		return gridSize;
	}
	
	/**
	 * Get the value of the cell at the specified coordinate.
	 * @param coord The coordinate to check.
	 * @return Value of cell at coordinate.
	 */
	public GRID_CELL_VALUE getCellValue(GridCoordinate coord) {
		try {
			return grid[coord.getX()][coord.getY()];
		} catch(ArrayIndexOutOfBoundsException e) {
			return GRID_CELL_VALUE.INVALID;
		}
	}
	
	/**
	 * Set the value of the cell at the specified coordinate.
	 * @param coord The coordinate to set the value at.
	 * @param value Teh value to set.
	 */
	public void setCellValue(GridCoordinate coord, GRID_CELL_VALUE value) {
		grid[coord.getX()][coord.getY()] = value;
		if(value == GRID_CELL_VALUE.START) {
			start = coord;
		}
		if(value == GRID_CELL_VALUE.END) {
			end = coord;
		}
	}

	/**
	 * Clear the grid of start, end and routes.
	 */
	public void clear() {
		start = null;
		end= null;
		for(int x=0; x<gridSize; x++) {
			for(int y=0; y<gridSize; y++) {
				GridCoordinate coord = new GridCoordinate(x, y);
				if(getCellValue(coord) != GRID_CELL_VALUE.ROCK) {
					setCellValue(coord,GRID_CELL_VALUE.EMPTY);
				}
			}
		}
	}
	
	/**
	 * Gets the marked start cell.
	 * @return the coordinate of the start cell.
	 */
	public GridCoordinate getStart() {
		return start;
	}
	
	/**
	 * Gets the marked end cell.
	 * @return the coordinate of the end cell.
	 */
	public GridCoordinate getEnd() {
		return end;
	}
	
}
