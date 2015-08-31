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
package com.lambdautils.util.function;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.lambdautils.util.function.Unchecking;
import com.lambdautils.util.function.exception.UncheckedException;

/**
 * @author rsromanojr
 *
 */
public class UncheckingTest {
	@Test(expected = UncheckedException.class)
	public void biConsumer_given_biConsumer_that_throws_throws_wrapped() throws Exception {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		map.forEach(Unchecking.biConsumer((k, v) -> {
			throw new ClassNotFoundException();
		}));
	}

	@Test
	public void biConsumer_given_biConsumer_that_doesNot_throw_does_nothing() throws Exception {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		map.forEach(Unchecking.biConsumer((k, v) -> System.out.println("k = " + k + ", v= " + v)));
	}

	@Test(expected = UncheckedException.class)
	public void biFunction_given_biFunction_that_throws_rethrows_exception() throws Exception {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		map.computeIfPresent(1, Unchecking.biFunction((k, v) -> {
			throw new ClassNotFoundException();
		}));
	}

	@Test
	public void biFunction_given_biFunction_that_doesNot_throw_does_nothing() throws Exception {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		map.computeIfPresent(1, Unchecking.biFunction((k, v) -> k + v));
	}
	
	@Test(expected = UncheckedException.class)
	public void biPredicate_given_biPredicate_that_throws_rethrows_exception() throws Exception {
		TestingFunctions.biPredicate(
				Unchecking.biPredicate((t, u) -> { throw new ClassNotFoundException(); }))
				.test("one", "two");
	}

	@Test
	public void biPredicate_given_biPredicate_that_doesNot_throw_does_nothing() throws Exception {
		TestingFunctions.biPredicate(Unchecking.biPredicate((t, u) -> true ))
				.test("one", "two");
	}
}
