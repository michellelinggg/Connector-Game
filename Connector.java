public class Connector {
	
	// Implement an immutable connector that connects two points on the game board.
	// Invariant: 1 <= myPoint1 < myPoint2 <= 6.
	
	private int myPoint1, myPoint2;
	
	public Connector (int p1, int p2) {
		if (p1 < p2) {
			myPoint1 = p1;
			myPoint2 = p2;
		} else {
			myPoint1 = p2;
			myPoint2 = p1;
		}
	}
	
	public int endPt1 ( ) {
		return myPoint1;
	}
	
	public int endPt2 ( ) {
		return myPoint2;
	}
	
	public boolean equals (Object obj) {
		Connector e = (Connector) obj;
		return (e.myPoint1 == myPoint1 && e.myPoint2 == myPoint2);
	}
	
	public String toString ( ) {
		return "" + myPoint1 + myPoint2;
	}
	
	// Format of a connector is endPoint1 + endPoint2 (+ means string concatenation),
	// possibly surrounded by white space. Each endpoint is a digit between
	// 1 and 6, inclusive; moreover, the endpoints aren't identical.
	// If the contents of the given string is correctly formatted,
	// return the corresponding connector.  Otherwise, throw IllegalFormatException.
	public static Connector toConnector (String s) throws IllegalFormatException {
		if (s == null)
			throw new IllegalFormatException("A null String is not a Valid Connector");
		s = s.trim();
		char[] charArray = s.toCharArray();
		// CHANGE THIS TO USE TRIM()!!!!!
		
		if (charArray.length != 2 ) {
			throw new IllegalFormatException("Input was too long");
		} else {
			int p1;
			int p2;
			
					p1 = Character.getNumericValue(charArray[0]);
					p2 = Character.getNumericValue(charArray[1]);
					
					if (p1 == -1 || p2 == -1){
						throw new IllegalFormatException("You must input integers");
					} else if (p1 <1 || p1 >6 || p2< 1 || p2 > 6 || p1 == p2){
						throw new IllegalFormatException("The inputs are out of range");
					} else{
						Connector connector = new Connector(p1,p2);
						return connector;
					}		
			
		}
	}
}
