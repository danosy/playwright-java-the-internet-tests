package results;

import errors.Error;

public sealed interface Result<T, E extends Error>
        permits Result.Ok, Result.Err {

    final class Ok<T, E extends Error> implements Result<T, E> {
        public final T value;
        public Ok(T value) { this.value = value; }
    }

    final class Err<T, E extends Error> implements Result<T, E> {
        public final E error;
        public Err(E error) { this.error = error; }
    }
}
