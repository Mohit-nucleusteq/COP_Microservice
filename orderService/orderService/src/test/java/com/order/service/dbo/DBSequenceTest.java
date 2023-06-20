package com.order.service.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class DBSequenceTest {

	@Test
	public void testGetterAndSetter() {
		DBSequence dBSequence = new DBSequence();
		assertNull(dBSequence.getId());
		dBSequence.setId("1");
		assertEquals("1", dBSequence.getId());

		assertNull(dBSequence.getSeqNo());
		dBSequence.setSeqNo(1L);
		assertEquals(1L, dBSequence.getSeqNo());
	}

	@Test
	public void testEqualAndHashCode() {
		String id = "1";
		Long seqNo = 1L;
		DBSequence dBSequence1 = setUpData(id, seqNo);
		assertEquals(dBSequence1, dBSequence1);
		assertEquals(dBSequence1.hashCode(), dBSequence1.hashCode());
		assertNotEquals(dBSequence1, new Object());
		
		DBSequence dBSequence2 = setUpData(id, seqNo);
		assertEquals(dBSequence1, dBSequence2);
		assertEquals(dBSequence1.hashCode(), dBSequence2.hashCode());
		
		dBSequence2 = setUpData("2", seqNo);
		assertNotEquals(dBSequence1, dBSequence2);
		assertNotEquals(dBSequence1.hashCode(), dBSequence2.hashCode());
		
		dBSequence2 = setUpData(id, 2L);
		assertNotEquals(dBSequence1, dBSequence2);
		assertNotEquals(dBSequence1.hashCode(), dBSequence2.hashCode());
		
		dBSequence1 = new DBSequence();
		dBSequence2 = new DBSequence();
		assertEquals(dBSequence1, dBSequence2);
		assertEquals(dBSequence1.hashCode(), dBSequence2.hashCode());
	}

	@Test
	public void testToString() {
		String id = "1";
		Long seqNo = 1L;
		DBSequence dBSequence = setUpData(id, seqNo);

		assertEquals("DBSequence(id=1, seqNo=1)", dBSequence.toString());
	}
	private DBSequence setUpData(String id, Long seqNo) {
		DBSequence dBSequence = new DBSequence();
		dBSequence.setId(id);
		dBSequence.setSeqNo(seqNo);
		return dBSequence;
	}
}
