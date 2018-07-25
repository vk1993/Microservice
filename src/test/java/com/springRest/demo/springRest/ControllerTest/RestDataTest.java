package com.springRest.demo.springRest.ControllerTest;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springRest.demo.SpringRest.Controller.RestData;
import com.springRest.demo.SpringRest.service.RestServiceEnd;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class RestDataTest {

	private MockMvc mvc;

	@MockBean
	private RestServiceEnd restServiceEnd;

	@InjectMocks
	private RestData restdata;

	@Before
	public void steUP() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(restdata).build();
	}

	@Test
	public void testGetNthFibonacci() throws Exception {

		when(restServiceEnd.getNthFebbonic(10L)).thenReturn(55);

		mvc.perform(get("/api/Fibonacci?n=10")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(header().string("pragma", "no-cache"))
				.andExpect(header().string("cache-Control", "no-cache"))
				.andExpect(header().string("expires", "-1"))
				.andExpect(content().json(AbstractRestControllerTest.asJsonString(55)));

//		mvc.perform(get("/api/Fibonacci?n=ff")).andExpect(status().isBadRequest())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
//				.andExpect(header().string("pragma", "no-cache"))
//				.andExpect(content().json(AbstractRestControllerTest.asJsonString(55)));

		verify(restServiceEnd,times(1)).getNthFebbonic(10);
	}

	@Test
	public void testGetReverse() throws Exception {
		when(restServiceEnd.getReverse("hi hello")).thenReturn("ih olleh");

		 mvc.perform(get("/api/ReverseWords?sentence=hi hello")).andExpect(status().isOk())
				.andExpect(header().string("pragma", "no-cache"))
				.andExpect(header().string("cache-Control", "no-cache"))
				.andExpect(header().string("expires", "-1"))
				 .andExpect(content().string("ih olleh"));

		verify(restServiceEnd,times(1)).getReverse("hi hello");
	}

	@Test
	public void testGetTriangle() throws Exception {
		when(restServiceEnd.getTriangle(2,2,1)).thenReturn("Isosceles");

		mvc.perform(get("/api/TriangleType?a=2&b=2&c=1"))
				.andExpect(status().isOk())
				.andExpect(header().string("pragma", "no-cache"))
				.andExpect(header().string("cache-Control", "no-cache"))
				.andExpect(header().string("expires", "-1"))
				.andExpect(content().string("Isosceles"));

		verify(restServiceEnd,times(1)).getTriangle(2,2,1);
	}


	/* public void testMakeOneArray() throws Exception {
		Map<String,Integer[]> inputmap = new HashMap();
		inputmap.put("Array1",new Integer[]{1,2,3,4,5,6});
		inputmap.put("Array2",new Integer[]{2,4,5,7,8});
		inputmap.put("Array3",new Integer[]{1,2,3,4,5,6});

		Map<String,Integer[]> output = new HashMap();
		output.put("Array",new Integer[]{1,2,3,4,5,6,7,8});


		String outputvalues ="{\n" +
				"  \"Array\":[1,2,3,4,5,6,7,8]\n" +
				"}";
		when(restServiceEnd.getArray(inputmap)).thenReturn(output);

				mvc.perform(post("/api/makeonearray")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
				        .content(AbstractRestControllerTest.asJsonString(inputmap)))
						.andExpect(status().isOk())
						.andExpect(content().json(AbstractRestControllerTest.asJsonString(output)));
						//.andExpect(jsonPath("$.*",hasSize(1)));
		//when(restServiceEnd.getArray());
        verify(restServiceEnd,times(1)).getArray(inputmap);
	}
*/
}
