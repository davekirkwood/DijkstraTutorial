package net.davekirkwood.tutorials.dijkstra.grid;

public class HilbertMaze extends Grid {
	
	public HilbertMaze(int size) {
		super(4 * (int)Math.pow(2, size-1) + 1);
		
		for(int x=0; x<gridSize; x++) {
			for(int y=0; y<gridSize; y++) {
				setCellValue(new GridCoordinate(x,y),GRID_CELL_VALUE.EMPTY);
			}
		}
		
		setCellValue(new GridCoordinate(1,gridSize-4),GRID_CELL_VALUE.ROCK);
		setCellValue(new GridCoordinate(1,gridSize-3),GRID_CELL_VALUE.ROCK);
		setCellValue(new GridCoordinate(1,gridSize-2),GRID_CELL_VALUE.ROCK);
		setCellValue(new GridCoordinate(2,gridSize-2),GRID_CELL_VALUE.ROCK);
		setCellValue(new GridCoordinate(3,gridSize-2),GRID_CELL_VALUE.ROCK);
		setCellValue(new GridCoordinate(3,gridSize-3),GRID_CELL_VALUE.ROCK);
		setCellValue(new GridCoordinate(3,gridSize-4),GRID_CELL_VALUE.ROCK);
		
		for(int i=1; i<size; i++) {
			int limit = 4*(int)Math.pow(2, i-1);
			for(int x = 0; x<limit; x++) {
				for(int y = 0; y<limit; y++) {
					setCellValue(new GridCoordinate(x + limit, gridSize - 1 - y), getCellValue(new GridCoordinate(x, gridSize - 1 - y)));
					setCellValue(new GridCoordinate(x + limit, gridSize - 1 - y - limit), getCellValue(new GridCoordinate(y, gridSize - 1 - x)));
					setCellValue(new GridCoordinate(limit - x, gridSize - 1 - y - limit), getCellValue(new GridCoordinate(y, gridSize - 1 - x)));
				}
			}
			setCellValue(new GridCoordinate(limit, gridSize - limit), GRID_CELL_VALUE.ROCK);
			setCellValue(new GridCoordinate(1, gridSize-limit-1), GRID_CELL_VALUE.ROCK);
			setCellValue(new GridCoordinate((limit*2)-1, gridSize-limit-1), GRID_CELL_VALUE.ROCK);
		}
	}


}
