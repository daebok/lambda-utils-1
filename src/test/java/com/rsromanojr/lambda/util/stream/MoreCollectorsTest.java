/*
 * Copyright (c) 2015 Ross Romano
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package com.rsromanojr.lambda.util.stream;

import static com.rsromanojr.lambda.util.stream.MoreCollectors.toImmutableList;
import static com.rsromanojr.lambda.util.stream.MoreCollectors.toImmutableMap;
import static com.rsromanojr.lambda.util.stream.MoreCollectors.toImmutableSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

/**
 * @author rsromanojr
 *
 */
public class MoreCollectorsTest {
	@Test
	public void toImmutableList_collects() throws Exception {
		ImmutableList<String> toCheck = Stream.of("one", "two", "three").collect(toImmutableList());
		assertNotNull(toCheck);
		assertEquals(3, toCheck.size());
	}

	@Test
	public void toImmutableList_parrellel_stream_collects() throws Exception {
		ImmutableList<String> toCheck = Stream.of("one", "two", "three").parallel().collect(toImmutableList());
		assertNotNull(toCheck);
		assertEquals(3, toCheck.size());
	}

	@Test
	public void toImmutableSet_collects() throws Exception {
		ImmutableSet<String> toCheck = Stream.of("one", "two", "three").collect(toImmutableSet());
		assertNotNull(toCheck);
		assertEquals(3, toCheck.size());
	}

	@Test
	public void toImmutableSet_parrellel_stream_collects() throws Exception {
		ImmutableSet<String> toCheck = Stream.of("one", "two", "three").parallel().collect(toImmutableSet());
		assertNotNull(toCheck);
		assertEquals(3, toCheck.size());
	}

	@Test
	public void toImmutableMap_collects() throws Exception {
		Map<String, String> toTransform = Maps.newHashMap();
		toTransform.put("one", "one");
		toTransform.put("two", "two");
		toTransform.put("three", "three");
		ImmutableMap<String, Integer> toCheck = toTransform.entrySet().stream()
				.collect(toImmutableMap(e -> e.getKey(), e -> e.getValue().length()));
		assertNotNull(toCheck);
		assertEquals(3, toCheck.size());
		toTransform.entrySet().stream().forEach(
				e -> assertEquals(Integer.valueOf(e.getValue().length()), Integer.valueOf(toCheck.get(e.getKey()))));
	}

	@Test
	public void toImmutableMap_parrellel_stream_collects() throws Exception {
		Map<String, String> toTransform = Maps.newHashMap();
		toTransform.put("one", "one");
		toTransform.put("two", "two");
		toTransform.put("three", "three");
		ImmutableMap<String, Integer> toCheck = toTransform.entrySet().parallelStream()
				.collect(toImmutableMap(e -> e.getKey(), e -> e.getValue().length()));
		assertNotNull(toCheck);
		assertEquals(3, toCheck.size());
		toTransform.entrySet().stream().forEach(
				e -> assertEquals(Integer.valueOf(e.getValue().length()), Integer.valueOf(toCheck.get(e.getKey()))));
	}
}
