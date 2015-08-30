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
package com.rsromanojr.lambda.util.function;

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
@SuppressWarnings("unchecked")
public interface Catching<F, E extends Exception> {
	F onException(Consumer<E> handler) throws E;

	static <T, U, E extends Exception> Catching<BiConsumer<T, U>, E> biConsumer(
			final ThrowingBiConsumer<T, U, E> consumer) {
		return new Catching<BiConsumer<T, U>, E>() {
			@Override
			public BiConsumer<T, U> onException(final Consumer<E> handler) throws E {
				return (t, u) -> {
					try {
						consumer.accept(t, u);
					} catch (Exception ex) {
						handler.accept((E) ex);
					}
				};
			}
		};
	}

	static <T, U, R, E extends Exception> ObjCatching<BiFunction<T, U, R>, R, E> biFunction(
			final ThrowingBiFunction<T, U, R, E> funcition) {
		return new ObjCatching<BiFunction<T, U, R>, R, E>() {
			@Override
			public BiFunction<T, U, R> onException(final Function<E, R> handler) throws E {
				return (t, u) -> {
					try {
						return funcition.apply(t, u);
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}

	static <T, U, E extends Exception> ObjCatching<BiPredicate<T, U>, Boolean, E> biPredicate(
			final ThrowingBiPredicate<T, U, E> predicate) {
		return new ObjCatching<BiPredicate<T, U>, Boolean, E>() {
			@Override
			public BiPredicate<T, U> onException(final Function<E, Boolean> handler) throws E {
				return (t, u) -> {
					try {
						return predicate.test(t, u);
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}

	static <T, E extends Exception> ObjCatching<BinaryOperator<T>, T, E> binaryOperator(
			final ThrowingBinaryOperator<T, E> operator) {
		return new ObjCatching<BinaryOperator<T>, T, E>() {
			@Override
			public BinaryOperator<T> onException(final Function<E, T> handler) throws E {
				return (t, u) -> {
					try {
						return operator.apply(t, u);
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> ObjCatching<BooleanSupplier, Boolean, E> booleanSupplier(
			final ThrowingBooleanSupplier<E> supplier) {
		return new ObjCatching<BooleanSupplier, Boolean, E>() {
			@Override
			public BooleanSupplier onException(final Function<E, Boolean> handler) throws E {
				return () -> {
					try {
						return supplier.getAsBoolean();
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}

	static <T, E extends Exception> Catching<Consumer<T>, E> consumer(final ThrowingConsumer<T, E> consumer) {
		return new Catching<Consumer<T>, E>() {
			@Override
			public Consumer<T> onException(final Consumer<E> handler) throws E {
				return t -> {
					try {
						consumer.accept(t);
					} catch (Exception ex) {
						handler.accept((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> DoubleCatching<DoubleBinaryOperator, E> doubleBinaryOperator(
			final ThrowingDoubleBinaryOperator<E> operator) {
		return new DoubleCatching<DoubleBinaryOperator, E>() {
			@Override
			public DoubleBinaryOperator onException(final ToDoubleFunction<E> handler) throws E {
				return (t, u) -> {
					try {
						return operator.applyAsDouble(t, u);
					} catch (Exception ex) {
						return handler.applyAsDouble((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> Catching<DoubleConsumer, E> doubleConsumer(final ThrowingDoubleConsumer<E> consumer) {
		return new Catching<DoubleConsumer, E>() {
			@Override
			public DoubleConsumer onException(final Consumer<E> handler) throws E {
				return (t) -> {
					try {
						consumer.accept(t);
					} catch (Exception ex) {
						handler.accept((E) ex);
					}
				};
			}
		};
	}

	static <R, E extends Exception> ObjCatching<DoubleFunction<R>, R, E> doubleFunction(
			final ThrowingDoubleFunction<R, E> function) {
		return new ObjCatching<DoubleFunction<R>, R, E>() {
			@Override
			public DoubleFunction<R> onException(final Function<E, R> handler) throws E {
				return (t) -> {
					try {
						return function.apply(t);
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> ObjCatching<DoublePredicate, Boolean, E> doublePredicate(
			final ThrowingDoublePredicate<E> predicate) {
		return new ObjCatching<DoublePredicate, Boolean, E>() {
			@Override
			public DoublePredicate onException(final Function<E, Boolean> handler) throws E {
				return t -> {
					try {
						return predicate.test(t);
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> DoubleCatching<DoubleSupplier, E> doubleSupplier(
			final ThrowingDoubleSupplier<E> supplier) {
		return new DoubleCatching<DoubleSupplier, E>() {
			@Override
			public DoubleSupplier onException(final ToDoubleFunction<E> handler) throws E {
				return () -> {
					try {
						return supplier.getAsDouble();
					} catch (Exception ex) {
						return handler.applyAsDouble((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> IntCatching<DoubleToIntFunction, E> doubleToIntFunction(
			final ThrowingDoubleToIntFunction<E> function) {
		return new IntCatching<DoubleToIntFunction, E>() {
			@Override
			public DoubleToIntFunction onException(final ToIntFunction<E> handler) throws E {
				return t -> {
					try {
						return function.applyAsInt(t);
					} catch (Exception ex) {
						return handler.applyAsInt((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> LongCatching<DoubleToLongFunction, E> doubleToLongFunction(
			final ThrowingDoubleToLongFunction<E> function) {
		return new LongCatching<DoubleToLongFunction, E>() {
			@Override
			public DoubleToLongFunction onException(final ToLongFunction<E> handler) throws E {
				return t -> {
					try {
						return function.applyAsLong(t);
					} catch (Exception ex) {
						return handler.applyAsLong((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> DoubleCatching<DoubleUnaryOperator, E> doubleUnaryOperator(
			final ThrowingDoubleUnaryOperator<E> operator) {
		return new DoubleCatching<DoubleUnaryOperator, E>() {
			@Override
			public DoubleUnaryOperator onException(final ToDoubleFunction<E> handler) throws E {
				return t -> {
					try {
						return operator.applyAsDouble(t);
					} catch (Exception ex) {
						return handler.applyAsDouble((E) ex);
					}
				};
			}
		};
	}

	static <T, R, E extends Exception> ObjCatching<Function<T, R>, R, E> function(
			final ThrowingFunction<T, R, E> function) {
		return new ObjCatching<Function<T, R>, R, E>() {
			@Override
			public Function<T, R> onException(final Function<E, R> handler) throws E {
				return t -> {
					try {
						return function.apply(t);
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> IntCatching<IntBinaryOperator, E> intBinaryOperator(
			final ThrowingIntBinaryOperator<E> operator) {
		return new IntCatching<IntBinaryOperator, E>() {
			@Override
			public IntBinaryOperator onException(final ToIntFunction<E> handler) throws E {
				return (t, u) -> {
					try {
						return operator.applyAsInt(t, u);
					} catch (Exception ex) {
						return handler.applyAsInt((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> Catching<IntConsumer, E> intConsumer(final ThrowingIntConsumer<E> consumer) {
		return new Catching<IntConsumer, E>() {
			@Override
			public IntConsumer onException(final Consumer<E> handler) throws E {
				return (t) -> {
					try {
						consumer.accept(t);
					} catch (Exception ex) {
						handler.accept((E) ex);
					}
				};
			}
		};
	}

	static <R, E extends Exception> ObjCatching<IntFunction<R>, R, E> intFunction(
			final ThrowingIntFunction<R, E> function) {
		return new ObjCatching<IntFunction<R>, R, E>() {
			@Override
			public IntFunction<R> onException(final Function<E, R> handler) throws E {
				return (t) -> {
					try {
						return function.apply(t);
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> ObjCatching<IntPredicate, Boolean, E> intPredicate(
			final ThrowingIntPredicate<E> predicate) {
		return new ObjCatching<IntPredicate, Boolean, E>() {
			@Override
			public IntPredicate onException(final Function<E, Boolean> handler) throws E {
				return t -> {
					try {
						return predicate.test(t);
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> IntCatching<IntSupplier, E> intSupplier(final ThrowingIntSupplier<E> supplier) {
		return new IntCatching<IntSupplier, E>() {
			@Override
			public IntSupplier onException(final ToIntFunction<E> handler) throws E {
				return () -> {
					try {
						return supplier.getAsInt();
					} catch (Exception ex) {
						return handler.applyAsInt((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> DoubleCatching<IntToDoubleFunction, E> intToDoubleFunction(
			final ThrowingIntToDoubleFunction<E> function) {
		return new DoubleCatching<IntToDoubleFunction, E>() {
			@Override
			public IntToDoubleFunction onException(final ToDoubleFunction<E> handler) throws E {
				return t -> {
					try {
						return function.applyAsDouble(t);
					} catch (Exception ex) {
						return handler.applyAsDouble((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> LongCatching<IntToLongFunction, E> intToLongFunction(
			final ThrowingIntToLongFunction<E> function) {
		return new LongCatching<IntToLongFunction, E>() {
			@Override
			public IntToLongFunction onException(final ToLongFunction<E> handler) throws E {
				return t -> {
					try {
						return function.applyAsLong(t);
					} catch (Exception ex) {
						return handler.applyAsLong((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> IntCatching<IntUnaryOperator, E> intUnaryOperator(
			final ThrowingIntUnaryOperator<E> operator) {
		return new IntCatching<IntUnaryOperator, E>() {
			@Override
			public IntUnaryOperator onException(final ToIntFunction<E> handler) throws E {
				return t -> {
					try {
						return operator.applyAsInt(t);
					} catch (Exception ex) {
						return handler.applyAsInt((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> LongCatching<LongBinaryOperator, E> longBinaryOperator(
			final ThrowingLongBinaryOperator<E> operator) {
		return new LongCatching<LongBinaryOperator, E>() {
			@Override
			public LongBinaryOperator onException(final ToLongFunction<E> handler) throws E {
				return (t, u) -> {
					try {
						return operator.applyAsLong(t, u);
					} catch (Exception ex) {
						return handler.applyAsLong((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> Catching<LongConsumer, E> longConsumer(final ThrowingLongConsumer<E> consumer) {
		return new Catching<LongConsumer, E>() {
			@Override
			public LongConsumer onException(final Consumer<E> handler) throws E {
				return (t) -> {
					try {
						consumer.accept(t);
					} catch (Exception ex) {
						handler.accept((E) ex);
					}
				};
			}
		};
	}

	static <R, E extends Exception> ObjCatching<LongFunction<R>, R, E> longFunction(
			final ThrowingLongFunction<R, E> function) {
		return new ObjCatching<LongFunction<R>, R, E>() {
			@Override
			public LongFunction<R> onException(final Function<E, R> handler) throws E {
				return (t) -> {
					try {
						return function.apply(t);
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> ObjCatching<LongPredicate, Boolean, E> longPredicate(
			final ThrowingLongPredicate<E> predicate) {
		return new ObjCatching<LongPredicate, Boolean, E>() {
			@Override
			public LongPredicate onException(final Function<E, Boolean> handler) throws E {
				return t -> {
					try {
						return predicate.test(t);
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> LongCatching<LongSupplier, E> longSupplier(final ThrowingLongSupplier<E> supplier) {
		return new LongCatching<LongSupplier, E>() {
			@Override
			public LongSupplier onException(final ToLongFunction<E> handler) throws E {
				return () -> {
					try {
						return supplier.getAsLong();
					} catch (Exception ex) {
						return handler.applyAsLong((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> DoubleCatching<LongToDoubleFunction, E> longToDoubleFunction(
			final ThrowingLongToDoubleFunction<E> function) {
		return new DoubleCatching<LongToDoubleFunction, E>() {
			@Override
			public LongToDoubleFunction onException(final ToDoubleFunction<E> handler) throws E {
				return t -> {
					try {
						return function.applyAsDouble(t);
					} catch (Exception ex) {
						return handler.applyAsDouble((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> IntCatching<LongToIntFunction, E> longToIntFunction(
			final ThrowingLongToIntFunction<E> function) {
		return new IntCatching<LongToIntFunction, E>() {
			@Override
			public LongToIntFunction onException(final ToIntFunction<E> handler) throws E {
				return t -> {
					try {
						return function.applyAsInt(t);
					} catch (Exception ex) {
						return handler.applyAsInt((E) ex);
					}
				};
			}
		};
	}

	static <E extends Exception> LongCatching<LongUnaryOperator, E> longUnaryOperator(
			final ThrowingLongUnaryOperator<E> operator) {
		return new LongCatching<LongUnaryOperator, E>() {
			@Override
			public LongUnaryOperator onException(final ToLongFunction<E> handler) throws E {
				return t -> {
					try {
						return operator.applyAsLong(t);
					} catch (Exception ex) {
						return handler.applyAsLong((E) ex);
					}
				};
			}
		};
	}

	static <T, E extends Exception> Catching<ObjDoubleConsumer<T>, E> objDoubleConsumer(
			final ThrowingObjDoubleConsumer<T, E> consumer) {
		return new Catching<ObjDoubleConsumer<T>, E>() {
			@Override
			public ObjDoubleConsumer<T> onException(final Consumer<E> handler) throws E {
				return (t, d) -> {
					try {
						consumer.accept(t, d);
					} catch (Exception ex) {
						handler.accept((E) ex);
					}
				};
			}
		};
	}

	static <T, E extends Exception> Catching<ObjIntConsumer<T>, E> objIntConsumer(
			final ThrowingObjIntConsumer<T, E> consumer) {
		return new Catching<ObjIntConsumer<T>, E>() {
			@Override
			public ObjIntConsumer<T> onException(final Consumer<E> handler) throws E {
				return (t, i) -> {
					try {
						consumer.accept(t, i);
					} catch (Exception ex) {
						handler.accept((E) ex);
					}
				};
			}
		};
	}

	static <T, E extends Exception> Catching<ObjLongConsumer<T>, E> objLongonsumer(
			final ThrowingObjLongConsumer<T, E> consumer) {
		return new Catching<ObjLongConsumer<T>, E>() {
			@Override
			public ObjLongConsumer<T> onException(final Consumer<E> handler) throws E {
				return (t, l) -> {
					try {
						consumer.accept(t, l);
					} catch (Exception ex) {
						handler.accept((E) ex);
					}
				};
			}
		};
	}

	static <T, E extends Exception> ObjCatching<Predicate<T>, Boolean, E> predicate(
			final ThrowingPredicate<T, E> predicate) {
		return new ObjCatching<Predicate<T>, Boolean, E>() {
			@Override
			public Predicate<T> onException(final Function<E, Boolean> handler) throws E {
				return t -> {
					try {
						return predicate.test(t);
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}

	static <T, E extends Exception> ObjCatching<Supplier<T>, T, E> supplier(final ThrowingSupplier<T, E> supplier) {
		return new ObjCatching<Supplier<T>, T, E>() {
			@Override
			public Supplier<T> onException(final Function<E, T> handler) throws E {
				return () -> {
					try {
						return supplier.get();
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}

	static <T, U, E extends Exception> DoubleCatching<ToDoubleBiFunction<T, U>, E> toDoubleBiFunction(
			final ThrowingToDoubleBiFunction<T, U, E> funcition) {
		return new DoubleCatching<ToDoubleBiFunction<T, U>, E>() {
			@Override
			public ToDoubleBiFunction<T, U> onException(final ToDoubleFunction<E> handler) throws E {
				return (t, u) -> {
					try {
						return funcition.applyAsDouble(t, u);
					} catch (Exception ex) {
						return handler.applyAsDouble((E) ex);
					}
				};
			}
		};
	}

	static <T, E extends Exception> DoubleCatching<ToDoubleFunction<T>, E> toDoubleFunction(
			final ThrowingToDoubleFunction<T, E> funcition) {
		return new DoubleCatching<ToDoubleFunction<T>, E>() {
			@Override
			public ToDoubleFunction<T> onException(final ToDoubleFunction<E> handler) throws E {
				return t -> {
					try {
						return funcition.applyAsDouble(t);
					} catch (Exception ex) {
						return handler.applyAsDouble((E) ex);
					}
				};
			}
		};
	}

	static <T, U, E extends Exception> IntCatching<ToIntBiFunction<T, U>, E> toIntBiFunction(
			final ThrowingToIntBiFunction<T, U, E> funcition) {
		return new IntCatching<ToIntBiFunction<T, U>, E>() {
			@Override
			public ToIntBiFunction<T, U> onException(final ToIntFunction<E> handler) throws E {
				return (t, u) -> {
					try {
						return funcition.applyAsInt(t, u);
					} catch (Exception ex) {
						return handler.applyAsInt((E) ex);
					}
				};
			}
		};
	}

	static <T, E extends Exception> IntCatching<ToIntFunction<T>, E> toIntFunction(
			final ThrowingToIntFunction<T, E> funcition) {
		return new IntCatching<ToIntFunction<T>, E>() {
			@Override
			public ToIntFunction<T> onException(final ToIntFunction<E> handler) throws E {
				return t -> {
					try {
						return funcition.applyAsInt(t);
					} catch (Exception ex) {
						return handler.applyAsInt((E) ex);
					}
				};
			}
		};
	}

	static <T, U, E extends Exception> LongCatching<ToLongBiFunction<T, U>, E> toLongBiFunction(
			final ThrowingToLongBiFunction<T, U, E> funcition) {
		return new LongCatching<ToLongBiFunction<T, U>, E>() {
			@Override
			public ToLongBiFunction<T, U> onException(final ToLongFunction<E> handler) throws E {
				return (t, u) -> {
					try {
						return funcition.applyAsLong(t, u);
					} catch (Exception ex) {
						return handler.applyAsLong((E) ex);
					}
				};
			}
		};
	}

	static <T, E extends Exception> LongCatching<ToLongFunction<T>, E> toLongFunction(
			final ThrowingToLongFunction<T, E> funcition) {
		return new LongCatching<ToLongFunction<T>, E>() {
			@Override
			public ToLongFunction<T> onException(final ToLongFunction<E> handler) throws E {
				return t -> {
					try {
						return funcition.applyAsLong(t);
					} catch (Exception ex) {
						return handler.applyAsLong((E) ex);
					}
				};
			}
		};
	}

	static <T, E extends Exception> ObjCatching<UnaryOperator<T>, T, E> unaryOperator(
			final ThrowingUnaryOperator<T, E> operator) {
		return new ObjCatching<UnaryOperator<T>, T, E>() {
			@Override
			public UnaryOperator<T> onException(final Function<E, T> handler) throws E {
				return t -> {
					try {
						return operator.apply(t);
					} catch (Exception ex) {
						return handler.apply((E) ex);
					}
				};
			}
		};
	}
}
