/**
 * 
 */
package com.lambdautils.util.function;

import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;

/**
 * @author ross.romano
 *
 */
public interface TestingFunctions {
	static <T, U> BiPredicate<T, U> biPredicate(final BiPredicate<T, U> predicate) {
		return predicate;
	}

	static <T, U> BinaryOperator<T> binaryOperator(final BinaryOperator<T> operator) {
		return operator;
	}

	static <T, U> BooleanSupplier booleanSupplier(final BooleanSupplier operator) {
		return operator;
	}
}
