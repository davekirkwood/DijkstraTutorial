package net.davekirkwood.tutorials.dijkstra.grid;

import java.util.ArrayList;
import java.util.List;

public class GridCoordinate {

	/*
	 * x-position
	 */
	private int x;

	/*
	 * y-position
	 */
	private int y;
	
	/**
	 * Construct the grid coordinate.
	 * @param x
	 * @param y
	 */
	public GridCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gets four potential adjacent coordinate to this coordinate.
	 * @return
	 */
	public List<GridCoordinate> getAdjacentCoordinates() {
		List<GridCoordinate> adjacentCoordinates = new ArrayList<>();
		adjacentCoordinates.add(new GridCoordinate(x, y+1));
		adjacentCoordinates.add(new GridCoordinate(x, y-1));
		adjacentCoordinates.add(new GridCoordinate(x+1, y));
		adjacentCoordinates.add(new GridCoordinate(x-1, y));
		return adjacentCoordinates;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridCoordinate other = (GridCoordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
	
	
	
}
