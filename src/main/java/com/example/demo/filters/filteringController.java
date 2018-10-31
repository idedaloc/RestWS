package com.example.demo.filters;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;

@RestController
public class filteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean sb = new SomeBean("value1","value2","value3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(sb);
		mapping.setFilters(filters);

		return mapping;
	}


	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveSomeBeanList() {
		
		List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1","value2","value3"),
				new SomeBean("value4","value5","value6"),
				new SomeBean("value7","value8","value9"));

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBeans);
		mapping.setFilters(filters);

		return mapping;
	}
}
