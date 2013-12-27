import junit.framework.TestCase;


public class ConnectorTest extends TestCase {
	
	public void testtoConnector (){
		Connector c1 = Connector.toConnector(" 12 ");
		assertEquals(c1.endPt1(), 1);
		assertEquals(c1.endPt2(), 2);
		
		Connector c2 = Connector.toConnector(" 31 ");
		assertEquals(c2.endPt1(), 1);
		assertEquals(c2.endPt2(), 3);
		
		c2 = Connector.toConnector("31 ");
		assertEquals(c2.endPt1(), 1);
		assertEquals(c2.endPt2(), 3);
		
		c2 = Connector.toConnector(" 31");
		assertEquals(c2.endPt1(), 1);
		assertEquals(c2.endPt2(), 3);
		
		c2 = Connector.toConnector("31");
		assertEquals(c2.endPt1(), 1);
		assertEquals(c2.endPt2(), 3);
		
		
		
		//c2 = Connector.toConnector("3 1");
		//c2 = Connector.toConnector(" 3-1");

		try{
			Connector c3 = Connector.toConnector("3a");
			assertFalse(true);
		}
		catch (IllegalFormatException e) {
			assertTrue(true);
		}
		
		String s = new String();
		try{
			Connector c4 = Connector.toConnector(s);
			assertFalse(true);
		}
		catch (IllegalFormatException e) {
			assertTrue(true);
		}
		
		try{
			Connector c5 = Connector.toConnector(null);
			assertFalse(true);
		}
		catch (IllegalFormatException e) {
			assertTrue(true);
		}
		
		try{
			Connector c5 = Connector.toConnector("123");
			assertFalse(true);
		}
		catch (IllegalFormatException e) {
			assertTrue(true);
		}
		
	}

}
