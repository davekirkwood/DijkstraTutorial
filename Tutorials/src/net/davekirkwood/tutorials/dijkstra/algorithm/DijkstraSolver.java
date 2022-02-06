package net.davekirkwood.tutorials.dijkstra.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.davekirkwood.tutorials.dijkstra.grid.Grid;
import net.davekirkwood.tutorials.dijkstra.grid.GridCoordinate;

public class DijkstraSolver {

	private Grid grid;
	private GridCoordinate start;
	private GridCoordinate end;
	
	public DijkstraSolver(Grid grid) {
		this.grid = grid;
		this.start = grid.getStart();
		this.end = grid.getEnd();
	}
	
	public DijkstraPath solve() {
		
		List<DijkstraPath> candidates = new ArrayList<>();
		candidates.add(new DijkstraPath(null, start));
		while(!candidates.isEmpty()) {
			
			Collections.sort(candidates);
			DijkstraPath candidate = candidates.remove(0);
			
			for(GridCoordinate step : candidate.getNextAvailableSteps(grid)) {
				
				DijkstraPath newPath = new DijkstraPath(candidate, step);
				if(step.equals(end)) {
					return newPath;
				} else {
					candidates.add(newPath);
				}
				
			}
			
			
		}
		return null;
		
	}
	
}
