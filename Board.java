import java.awt.Color;
import java.util.*;

public class Board {

	public static boolean iAmDebugging = true;
	public ArrayList<Connector> WhiteConnectors;
	public ArrayList<Connector> RedConnectors;
	public ArrayList<Connector> BlueConnectors;
	
	// Initialize an empty board with no colored edges.
	public Board ( ) {
		WhiteConnectors = new  ArrayList<Connector>();
		RedConnectors  = new  ArrayList<Connector>();
		BlueConnectors = new  ArrayList<Connector>();
		
		int startpoint = 2;
		for(int i = 1; i < 6; i++) {
			for (int j = startpoint; j <= 6; j++) {
				Connector c = new Connector(i,j);
				WhiteConnectors.add(c);
			}
			startpoint++;
				
		}
		
		
		// You fill this in.
	}
	
	// Add the given connector with the given color to the board.
	// Unchecked precondition: the given connector is not already chosen 
	// as RED or BLUE.
	public void add (Connector cnctr, Color c) {
		if (c != Color.RED && c!= Color.BLUE){
			System.err.println("Cannot Add a Connector of This Color");
			System.exit(1);
		} else if (c == Color.RED) {
			if (RedConnectors.contains(cnctr) || BlueConnectors.contains(cnctr)) {
				System.err.println("This Connector Already Exists");
				System.exit(1);
			} else if (!WhiteConnectors.contains(cnctr)){
				System.err.println("Not a Valid Connector");
				System.exit(1);
			} else {
				WhiteConnectors.remove(cnctr);
				RedConnectors.add(cnctr);
			}
		} else if (c == Color.BLUE) {
			if (RedConnectors.contains(cnctr) || BlueConnectors.contains(cnctr)) {
				//ALSO CHECK IF PART OF WHITE CONNECTORS? OR IF IT A VALID CONNECT TO ADD< NOT SAME PTS??
				System.err.println("This Connector Already Exists");
				System.exit(1);
			} else if (!WhiteConnectors.contains(cnctr)){
				System.err.println("Not a Valid Connector");
				System.exit(1);
			} else {
				WhiteConnectors.remove(cnctr);
				BlueConnectors.add(cnctr);
			}
		}
		// You fill this in.
	}
	
	// Set up an iterator through the connectors of the given color, 
	// which is either RED, BLUE, or WHITE. 
	// If the color is WHITE, iterate through the uncolored connectors.
	// No connector should appear twice in the iteration.  
	public Iterator<Connector> connectors (Color c) {
		if (c == Color.WHITE) {
			return WhiteConnectors.iterator();
		} else if (c == Color.RED) {
			return RedConnectors.iterator();
		} else if (c == Color.BLUE) {
			return BlueConnectors.iterator();
		} else{
			System.err.println("This in Not a Valid Color");
			System.exit(1);
			return null;
		}


	}
	
	// Set up an iterator through all the 15 connectors.
	// No connector should appear twice in the iteration.  
	public Iterator<Connector> connectors ( ) {
		ArrayList<Connector> values = new ArrayList<Connector>();
		values.addAll(WhiteConnectors);
		values.addAll(RedConnectors);
		values.addAll(BlueConnectors);
		// You fill this in.
		return values.iterator();
	}
	
	// Return the color of the given connector.
	// If the connector is colored, its color will be RED or BLUE;
	// otherwise, its color is WHITE.
	public Color colorOf (Connector e) {
		if (RedConnectors.contains(e)) {
			return Color.RED;
		} else if (BlueConnectors.contains(e)) {
			return Color.BLUE;
			
		} else {
		return Color.WHITE;
		}
	}
	
	// Unchecked prerequisite: cnctr is an initialized uncolored connector.
	// Let its endpoints be p1 and p2.
	// Return true exactly when there is a point p3 such that p1 is adjacent
	// to p3 and p2 is adjacent to p3 and those connectors have color c.
	public boolean formsTriangle (Connector cnctr, Color c) {
		int p1 = cnctr.endPt1();
		int p2 = cnctr.endPt2();
		if (!WhiteConnectors.contains(cnctr)) {
			System.err.println("Not A Valid Connector");
			System.exit(1);
			
		} else if (c == Color.RED) {
			for (int i = 1; i <=6; i++){ 
				if ((i != p1) && (i != p2)) {
					Connector c1 = new Connector(i,p1);
					Connector c2 = new Connector(i,p2);
					if (RedConnectors.contains(c1) && RedConnectors.contains(c2)) {
						return true;
					}
				}
			}
			return false;
			
		} else if (c == Color.BLUE) {
			for (int i = 1; i <=6; i++){ 
				if ((i != p1) && (i != p2)) {
					Connector c1 = new Connector(i,p1);
					Connector c2 = new Connector(i,p2);
					if (BlueConnectors.contains(c1) && BlueConnectors.contains(c2)) {
						return true;
					}
				}
			}
			return false;
		} else {
			System.err.println("Not a Valid Color");
			System.exit(1);
		}
		return false;
		// You fill this in.
	}
	
	// The computer (playing BLUE) wants a move to make.
	// The board is assumed to contain an uncolored connector, with no 
	// monochromatic triangles.
	// There must be an uncolored connector, since if all 15 connectors are colored,
	// there must be a monochromatic triangle.
	// Pick the first uncolored connector that doesn't form a BLUE triangle.
	// If each uncolored connector, colored BLUE, would form a BLUE triangle,
	// return any uncolored connector.
	public Connector choice ( ) {
		ArrayList<Connector> mySet= new ArrayList<Connector>();
		for (int i = 1; i< 6; i++) {
			mySet.add(new Connector(i,i+1));
		}
		mySet.add(new Connector(1,6));
		mySet.add(new Connector(3,6));
		mySet.add(new Connector(2,5));
		mySet.add(new Connector(1,4));
		
		for (Connector c: WhiteConnectors) {
			if (!this.formsTriangle(c, Color.BLUE) && !this.formsTriangle(c, Color.RED) && mySet.contains(c)) {
				return c;
			}
			
		}
		
		for (Connector c: WhiteConnectors) {
			if (!this.formsTriangle(c, Color.BLUE) && !this.formsTriangle(c, Color.RED)){
				return c;
			}
		}
		for (Connector c: WhiteConnectors) {
			if (!this.formsTriangle(c, Color.BLUE) && mySet.contains(c)){
				return c;
			}
		}
		for (Connector c: WhiteConnectors) {
			if (!this.formsTriangle(c, Color.BLUE) ){
				return c;
			}
		}
		
		return WhiteConnectors.get(0);
		// You fill this in.
	}

	// Return true if the instance variables have correct and internally
	// consistent values.  Return false otherwise.
	// Unchecked prerequisites:
	//	Each connector in the board is properly initialized so that 
	// 	1 <= myPoint1 < myPoint2 <= 6.
	// My notes: 
	// Num Red - Num Blue = 1 or 0 always
	// Total Number of Connectors including white = 15
	// Check for duplicate connectors.
	//	Check for a blue triangle, which shouldn't exist.
	public boolean isOK ( ) {
		for (Connector c: WhiteConnectors) {
			int p1 = c.endPt1();
			int p2 = c.endPt2();
			if (p1 <1 || p1 >6 || p2< 1 || p2 > 6 || p2 <= p1) {
				System.err.println("Board is not Properly Initialized");
				System.exit(1);
			}		
		}
		for (Connector c: RedConnectors) {
			int p1 = c.endPt1();
			int p2 = c.endPt2();
			if (p1 <1 || p1 >6 || p2< 1 || p2 > 6 || p2 <= p1) {
				System.err.println("Board is not Properly Initialized");
				System.exit(1);
			}		
		}
		for (Connector c: BlueConnectors) {
			int p1 = c.endPt1();
			int p2 = c.endPt2();
			if (p1 <1 || p1 >6 || p2< 1 || p2 > 6 || p2 <= p1) {
				System.err.println("Board is not Properly Initialized");
				System.exit(1);
			}		
		}
		
		int NumRed = RedConnectors.size();
		int NumBlue = BlueConnectors.size();
		int NumWhite = WhiteConnectors.size();
		if ((NumRed - NumBlue) != 1 && (NumRed - NumBlue) != 0){
			return false;
			
		} else if ((NumRed + NumBlue + NumWhite) != 15) {
			return false;
			
		} else if (NumWhite <= 0) {
			return false;
		}
		
		ArrayList<Connector> combined = new ArrayList<Connector>();
		combined.addAll(WhiteConnectors);
		combined.addAll(RedConnectors);
		combined.addAll(BlueConnectors);
		
		for (int check = 0; check < combined.size(); check++){
			for (int i = 0; i < combined.size(); i++){ 
			if (i != check){
				if (combined.get(i).equals(combined.get(check)))
					return false;
			}
		  }
		}
		
		for (Connector c: BlueConnectors){
			for (int i = 1; i <=6; i++){ 
				if ((i != c.endPt1()) && (i != c.endPt2())) {
					Connector c1 = new Connector(i,c.endPt1());
					Connector c2 = new Connector(i,c.endPt2());
					if (BlueConnectors.contains(c1) && BlueConnectors.contains(c2)) {
						return false;
					}
				}
			}
		}
		
		// You fill this in.
		return true;
	}
}
