import static org.junit.Assert.*;

import org.junit.Test;


public class BoardTest {

	@Test
	public void testHamming() {
		Board one = new Board(new int[][]{{1,2,3},{4,5,6},{7,8,0}});
		assertEquals(0,one.hamming());
		assertEquals(3,one.dimension());
		Board two = new Board(new int[][]{{8,1,3},{4,0,2},{7,6,5}});
		assertTrue(two.hamming() == 5);
		Board three = new Board(new int[][]{{0,1,2},{3,4,5},{6,7,8}});
		assertTrue(three.hamming() == 8);
		Board four = new Board(new int[][]{{0,2,3},{4,6,5},{7,8,1}});
		assertTrue(four.hamming() == 3);
	}
	
	@Test
	public void testManhattan() {
		Board one = new Board(new int[][]{{1,2,3},{4,5,6},{7,8,0}});
		assertEquals(0,one.manhattan());
		assertEquals(3,one.dimension());
		Board two = new Board(new int[][]{{8,1,3},{4,0,2},{7,6,5}});
		assertTrue(two.manhattan() == 10);
		Board three = new Board(new int[][]{{0,1,2},{3,4,5},{6,7,8}});
		assertTrue(three.manhattan() == 12);
		Board four = new Board(new int[][]{{0,2,3},{4,6,5},{7,8,1}});
		assertTrue(four.manhattan() == 6);
	}
	@Test
	public void testEqual() {
		Board one = new Board(new int[][]{{1,2,3},{4,5,6},{7,8,0}});
		Board two = new Board(new int[][]{{1,2,3},{4,5,6},{7,8,0}});
		assertTrue(one.equals(two));
		assertTrue(one.equals(one));
		Board three = new Board(new int[][]{{0,1,2},{3,4,5},{6,7,8}});
		assertFalse(one.equals(three));		
		assertFalse(one.equals(new Integer(1)));
		assertFalse(one.equals(null));
	}
}
