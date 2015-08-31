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

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

/**
 * @author rsromanojr
 *
 */
public final class Rethrowing {
	@SuppressWarnings("unchecked")
	private static <R, E extends Exception> R throwException(final Exception toThrow) throws E {
		throw (E) toThrow;
	}

	public static <T, U, E extends Exception> BiConsumer<T, U> biConsumer(final ThrowingBiConsumer<T, U, E> consumer)
			throws E {
		return Catching.biConsumer(consumer).onException(Rethrowing::throwException);
	}

	public static <T, U, R, E extends Exception> BiFunction<T, U, R> biFunction(
			final ThrowingBiFunction<T, U, R, E> funcition) throws E {
		return Catching.biFunction(funcition).onException(Rethrowing::throwException);
	}

	public static <T, U, E extends Exception> BiPredicate<T, U> biPredicate(
			final ThrowingBiPredicate<T, U, E> predicate) throws E {
		return Catching.biPredicate(predicate).onException(Rethrowing::throwException);
	}

	public static <T, E extends Exception> BinaryOperator<T> binaryOperator(final ThrowingBinaryOperator<T, E> operator)
			throws E {
		return Catching.binaryOperator(operator).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> BooleanSupplier booleanSupplier(final ThrowingBooleanSupplier<E> supplier)
			throws E {
		return Catching.booleanSupplier(supplier).onException(Rethrowing::throwException);
	}

	public static <T, E extends Exception> Consumer<T> consumer(final ThrowingConsumer<T, E> consumer) throws E {
		return Catching.consumer(consumer).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> DoubleBinaryOperator doubleBinaryOperator(
			final ThrowingDoubleBinaryOperator<E> operator) throws E {
		return Catching.doubleBinaryOperator(operator).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> DoubleConsumer doubleConsumer(final ThrowingDoubleConsumer<E> consumer)
			throws E {
		return Catching.doubleConsumer(consumer).onException(Rethrowing::throwException);
	}

	public static <R, E extends Exception> DoubleFunction<R> doubleFunction(final ThrowingDoubleFunction<R, E> function)
			throws E {
		return Catching.doubleFunction(function).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> DoublePredicate doublePredicate(final ThrowingDoublePredicate<E> predicate)
			throws E {
		return Catching.doublePredicate(predicate).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> DoubleSupplier doubleSupplier(final ThrowingDoubleSupplier<E> supplier)
			throws E {
		return Catching.doubleSupplier(supplier).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> DoubleToIntFunction doubleToIntFunction(
			final ThrowingDoubleToIntFunction<E> function) throws E {
		return Catching.doubleToIntFunction(function).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> DoubleToLongFunction doubleToLongFunction(
			final ThrowingDoubleToLongFunction<E> function) throws E {
		return Catching.doubleToLongFunction(function).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> DoubleUnaryOperator doubleUnaryOperator(
			final ThrowingDoubleUnaryOperator<E> operator) throws E {
		return Catching.doubleUnaryOperator(operator).onException(Rethrowing::throwException);
	}

	public static <T, R, E extends Exception> Function<T, R> function(final ThrowingFunction<T, R, E> function)
			throws E {
		return Catching.function(function).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> IntBinaryOperator intBinaryOperator(final ThrowingIntBinaryOperator<E> operator)
			throws E {
		return Catching.intBinaryOperator(operator).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> IntConsumer intConsumer(final ThrowingIntConsumer<E> consumer) throws E {
		return Catching.intConsumer(consumer).onException(Rethrowing::throwException);
	}

	public static <R, E extends Exception> IntFunction<R> intFunction(final ThrowingIntFunction<R, E> function)
			throws E {
		return Catching.intFunction(function).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> IntPredicate intPredicate(final ThrowingIntPredicate<E> predicate) throws E {
		return Catching.intPredicate(predicate).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> IntSupplier intSupplier(final ThrowingIntSupplier<E> supplier) throws E {
		return Catching.intSupplier(supplier).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> IntToDoubleFunction intToDoubleFunction(
			final ThrowingIntToDoubleFunction<E> function) throws E {
		return Catching.intToDoubleFunction(function).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> IntToLongFunction intToLongFunction(final ThrowingIntToLongFunction<E> function)
			throws E {
		return Catching.intToLongFunction(function).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> IntUnaryOperator intUnaryOperator(final ThrowingIntUnaryOperator<E> operator)
			throws E {
		return Catching.intUnaryOperator(operator).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> LongBinaryOperator longBinaryOperator(
			final ThrowingLongBinaryOperator<E> operator) throws E {
		return Catching.longBinaryOperator(operator).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> LongConsumer longConsumer(final ThrowingLongConsumer<E> consumer) throws E {
		return Catching.longConsumer(consumer).onException(Rethrowing::throwException);
	}

	public static <R, E extends Exception> LongFunction<R> longFunction(final ThrowingLongFunction<R, E> function)
			throws E {
		return Catching.longFunction(function).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> LongPredicate longPredicate(final ThrowingLongPredicate<E> predicate) throws E {
		return Catching.longPredicate(predicate).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> LongSupplier longSupplier(final ThrowingLongSupplier<E> supplier) throws E {
		return Catching.longSupplier(supplier).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> LongToDoubleFunction longToDoubleFunction(
			final ThrowingLongToDoubleFunction<E> function) throws E {
		return Catching.longToDoubleFunction(function).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> LongToIntFunction longToIntFunction(final ThrowingLongToIntFunction<E> function)
			throws E {
		return Catching.longToIntFunction(function).onException(Rethrowing::throwException);
	}

	public static <E extends Exception> LongUnaryOperator longUnaryOperator(final ThrowingLongUnaryOperator<E> operator)
			throws E {
		return Catching.longUnaryOperator(operator).onException(Rethrowing::throwException);
	}

	public static <T, E extends Exception> ObjDoubleConsumer<T> objDoubleConsumer(
			final ThrowingObjDoubleConsumer<T, E> consumer) throws E {
		return Catching.objDoubleConsumer(consumer).onException(Rethrowing::throwException);
	}

	public static <T, E extends Exception> ObjIntConsumer<T> objIntConsumer(final ThrowingObjIntConsumer<T, E> consumer)
			throws E {
		return Catching.objIntConsumer(consumer).onException(Rethrowing::throwException);
	}

	public static <T, E extends Exception> ObjLongConsumer<T> objLongonsumer(
			final ThrowingObjLongConsumer<T, E> consumer) throws E {
		return Catching.objLongonsumer(consumer).onException(Rethrowing::throwException);
	}

	public static <T, E extends Exception> Predicate<T> predicate(final ThrowingPredicate<T, E> predicate) throws E {
		return Catching.predicate(predicate).onException(Rethrowing::throwException);
	}

	public static <T, E extends Exception> Supplier<T> supplier(final ThrowingSupplier<T, E> supplier) throws E {
		return Catching.supplier(supplier).onException(Rethrowing::throwException);
	}

	public static <T, U, E extends Exception> ToDoubleBiFunction<T, U> toDoubleBiFunction(
			final ThrowingToDoubleBiFunction<T, U, E> funcition) throws E {
		return Catching.toDoubleBiFunction(funcition).onException(Rethrowing::throwException);
	}

	public static <T, E extends Exception> ToDoubleFunction<T> toDoubleFunction(
			final ThrowingToDoubleFunction<T, E> funcition) throws E {
		return Catching.toDoubleFunction(funcition).onException(Rethrowing::throwException);
	}

	public static <T, U, E extends Exception> ToIntBiFunction<T, U> toIntBiFunction(
			final ThrowingToIntBiFunction<T, U, E> funcition) throws E {
		return Catching.toIntBiFunction(funcition).onException(Rethrowing::throwException);
	}

	public static <T, E extends Exception> ToIntFunction<T> toIntFunction(final ThrowingToIntFunction<T, E> funcition)
			throws E {
		return Catching.toIntFunction(funcition).onException(Rethrowing::throwException);
	}

	public static <T, U, E extends Exception> ToLongBiFunction<T, U> toLongBiFunction(
			final ThrowingToLongBiFunction<T, U, E> funcition) throws E {
		return Catching.toLongBiFunction(funcition).onException(Rethrowing::throwException);
	}

	public static <T, E extends Exception> ToLongFunction<T> toLongFunction(
			final ThrowingToLongFunction<T, E> funcition) throws E {
		return Catching.toLongFunction(funcition).onException(Rethrowing::throwException);
	}

	public static <T, E extends Exception> UnaryOperator<T> unaryOperator(final ThrowingUnaryOperator<T, E> operator)
			throws E {
		return Catching.unaryOperator(operator).onException(Rethrowing::throwException);
	}
}
