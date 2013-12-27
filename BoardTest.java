import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
	
	// Check empty board.
	public void testEmptyBoard ( ) {
		Board b = new Board ( );
	   	assertTrue (b.isOK ( ));
		assertTrue (checkCollection (b, 0, 0)); // applies more tests
		assertTrue (!b.formsTriangle (new Connector (1, 2), Color.RED));
		assertEquals(b.WhiteConnectors.size(), 15);
		
	}
	
	// Check one-connector board.
	public void test1Connector ( ) {
		Board b = new Board ( );
		b.add (new Connector (1, 2), Color.RED);
		assertTrue (b.isOK ( ));
		checkCollection (b, 1, 0);
		
		Iterator<Connector> iter = b.connectors (Color.RED);
		assertTrue (iter.hasNext ( ));
		Connector cnctr = iter.next ( );
		assertEquals (b.colorOf (cnctr), Color.RED);
		assertEquals (new Connector (1, 2), cnctr);
		assertTrue (!iter.hasNext ( ));
		
		assertTrue (!b.formsTriangle (new Connector(1,3), Color.RED));
		assertTrue (!b.formsTriangle (new Connector(5,6), Color.RED));
		assertTrue (!b.choice ( ).equals (new Connector (1, 2)));
		assertEquals (b.colorOf (b.choice ( )), Color.WHITE);
	}
	
	// More tests go here.
	
	// (a useful helper method)
	// Make the following checks on a board that should be legal:
	//	Check connector counts (# reds + # blues + # uncolored should be 16.
	//	Check red vs. blue counts.
	//	Check for duplicate connectors.
	//	Check for a blue triangle, which shouldn't exist.
	
	public void testiterator(){
		Board b = new Board ( );
		b.add (new Connector (1, 2), Color.RED);
		b.add (new Connector (3, 4), Color.RED);
		Iterator<Connector> iter = b.connectors(Color.RED);
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), new Connector(1,2));
		assertEquals(iter.next(), new Connector(3,4));
		assertFalse(iter.hasNext());
		Iterator<Connector> iter2 = b.connectors(Color.BLUE);
		assertFalse(iter2.hasNext());	
	}
	
	public void testconnectors(){
		Board b = new Board ( );
		b.add (new Connector (1, 2), Color.RED);
		b.add (new Connector (3, 4), Color.BLUE);
		Iterator<Connector> iter  = b.connectors();
		int count = 0;
		while(iter.hasNext()){
			assertTrue(iter.next() instanceof Connector);
			count++;
		}
		assertEquals(count, 15);
		
		
	}
	
	public void testformsTriangle(){
		Board b = new Board ( );
		b.add (new Connector (1, 2), Color.RED);
		b.add (new Connector (3, 4), Color.RED);
		assertFalse(b.formsTriangle(new Connector (2, 3), Color.RED));
		b.add (new Connector (2, 3), Color.BLUE);
		assertFalse(b.formsTriangle(new Connector (1, 3), Color.RED));
		b.add (new Connector (1, 6), Color.RED);
		assertTrue(b.formsTriangle(new Connector (2, 6), Color.RED));
	}
	
	public void testchoice(){
		Board b = new Board ( );
		b.add (new Connector (1, 2), Color.BLUE);
		b.add (new Connector (2, 3), Color.BLUE);
	    assertFalse(b.choice().equals(new Connector(1, 3)));
	}
	
	public void testcolorof(){
		Board b = new Board ( );
		b.add (new Connector (1, 2), Color.RED);
		assertEquals(b.colorOf(new Connector (1, 2)),Color.RED);
		assertEquals(b.colorOf(new Connector (2, 3)),Color.WHITE);
		b.add (new Connector (2, 3), Color.BLUE);
		assertEquals(b.colorOf(new Connector (2, 3)),Color.BLUE);
	}
	
	public void testIsOK(){
		Board b = new Board ( );
		b.add (new Connector (1, 2), Color.RED);
		b.add (new Connector (2, 3), Color.RED);
		assertFalse(b.isOK());
		b.add (new Connector (3, 4), Color.BLUE);
		assertTrue(b.isOK());
		assertTrue(checkCollection(b, 2, 1));
		b.RedConnectors.add(new Connector (1, 2));
		assertFalse(b.isOK());
		b.RedConnectors.remove(new Connector (1, 2));
		assertTrue(b.isOK());
		b.RedConnectors.add(new Connector (1, 3));
		assertFalse(b.isOK());
		assertFalse(checkCollection(b, 3, 1));
		b.RedConnectors.remove(new Connector (1, 3));
		assertTrue(b.isOK());
		b.RedConnectors.addAll(b.WhiteConnectors);
		assertFalse(b.isOK());
		assertFalse(checkCollection(b, 2, 1));
	}
	
	
	public boolean checkCollection (Board b, int redCount, int blueCount) {
		if (b.BlueConnectors.size() != blueCount) {
			return false;
		}
		if (b.RedConnectors.size() != redCount) {
			return false;
		}
		if (b.WhiteConnectors.size() + redCount + blueCount != 15) {
			return false;
		}
		return true;
	}
}
