/**
 * 
 */
package com.rsromanojr.lambdautils.util.function;

import java.util.function.BiPredicate;

/**
 * @author ross.romano
 *
 */
public interface TestingFunctions {
	static <T, U> BiPredicate<T, U> biPredicate(final BiPredicate<T, U> predicate) {
		return predicate;
	}
}
