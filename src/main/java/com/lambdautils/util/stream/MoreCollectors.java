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
package com.lambdautils.util.stream;

import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Additional {@link Collectors}
 * 
 * @author rsromanojr
 */
public interface MoreCollectors {
	static <T> Collector<T, ImmutableList.Builder<T>, ImmutableList<T>> toImmutableList() {
		return Collector.of(
				ImmutableList.Builder::new, 
				ImmutableList.Builder::add, 
				(l, r) -> l.addAll(r.build()),
				ImmutableList.Builder<T>::build);
	}

	static <T> Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>> toImmutableSet() {
		return Collector.of(
				ImmutableSet.Builder::new, 
				ImmutableSet.Builder::add, 
				(l, r) -> l.addAll(r.build()),
				ImmutableSet.Builder<T>::build);
	}

	public static <T, K, V> Collector<T, ImmutableMap.Builder<K, V>, ImmutableMap<K, V>> toImmutableMap(
			final Function<? super T, ? extends K> keyMapper,
			final Function<? super T, ? extends V> valueMapper) {
		return Collector.of(
				ImmutableMap.Builder::new,
				(b, t) -> b.put(keyMapper.apply(t), valueMapper.apply(t)),
				(l, r) -> l.putAll(r.build()),
				ImmutableMap.Builder::build);
	}
}
