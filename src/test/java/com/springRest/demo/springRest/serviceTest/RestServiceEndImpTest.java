package com.springRest.demo.springRest.serviceTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Before;
import org.junit.Test;

import com.springRest.demo.SpringRest.service.RestServiceEndImp;

public class RestServiceEndImpTest {

	RestServiceEndImp restServiceEndImp;

	@Before
	public void setUp() throws Exception {
		restServiceEndImp = new RestServiceEndImp();
	}

	@Test
	public void testGetNthFebbonic() throws Exception {
		assertEquals(55, restServiceEndImp.getNthFebbonic(10));
	}

	@Test
	public void testGetReverse() throws Exception {
		String test = "india is";
		assertEquals("aidni si ", restServiceEndImp.getReverse(test));
	}

	@Test
	public void testGetTriangle() throws Exception {
		int a = 2;
		int b = 2;
		int c = 1;
		assertEquals("Isosceles", restServiceEndImp.getTriangle(a, b, c));
	}

	@Test
	public void testGetArray() throws Exception {
		Map<String, Integer[]> map = new HashMap<>();
		map.put("Array1", new Integer[] { 1, 2, 3, 4 });
		map.put("Array2", new Integer[] { 3, 4, 5, 6 });
		map.put("Array3", new Integer[] { 6, 1, 3, 55 });
		Map<String, Integer[]> map2 = new HashMap<>();
		map2.put("Array", new Integer[] { 1, 2, 3, 4, 5, 6, 55 });
		assertThat(restServiceEndImp.getArray(map).size(), is(1));
		assertThat(restServiceEndImp.getArray(map),
				IsMapContaining.hasEntry("Array", new Integer[] { 1, 2, 3, 4, 5, 6, 55 }));

	}

}
