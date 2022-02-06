package net.davekirkwood.tutorials.dijkstra.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.davekirkwood.tutorials.dijkstra.grid.Grid;
import net.davekirkwood.tutorials.dijkstra.grid.Grid.GRID_CELL_VALUE;
import net.davekirkwood.tutorials.dijkstra.grid.GridCoordinate;

public class DijkstraPath implements Comparable<DijkstraPath> {

	Set<GridCoordinate> steps = new HashSet<>();
	
	GridCoordinate lastStep = null;
	
	public DijkstraPath(DijkstraPath other, GridCoordinate step) {
		if(other != null) {
			for(GridCoordinate otherStep : other.steps) {
				steps.add(otherStep);
			}
		}
		steps.add(step);
		lastStep = step;
	}
	
	public List<GridCoordinate> getNextAvailableSteps(Grid grid) {
		List<GridCoordinate> adjacentCoordinates = lastStep.getAdjacentCoordinates();
		List<GridCoordinate> nextAvailableSteps = new ArrayList<GridCoordinate>();
		for(GridCoordinate candidateStep : adjacentCoordinates) {
			if(!steps.contains(candidateStep)) {
				if(grid.getCellValue(candidateStep) == GRID_CELL_VALUE.EMPTY || grid.getCellValue(candidateStep) == GRID_CELL_VALUE.END) {
					nextAvailableSteps.add(candidateStep);
				}
			}
		}
		return nextAvailableSteps;
	}
	
	public Set<GridCoordinate> getSteps() {
		return steps;
	}

	@Override
	public int compareTo(DijkstraPath o) {
		return steps.size() - o.steps.size();
	}
	
	
}
