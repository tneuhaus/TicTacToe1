
public class Location {
	
	int row, col;
	
	/**
	 * Method Location
	 *
	 *
	 */
	public Location(int r, int c) {
		row = r;
		col = c;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public String toString() {
		return "Location: " + row + " " + col;
	}
}
