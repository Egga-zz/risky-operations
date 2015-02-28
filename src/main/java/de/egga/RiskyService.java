package de.egga;

import io.vavr.control.Either;

import java.util.Optional;

import static io.vavr.control.Either.right;
import static java.util.Optional.empty;

public class RiskyService {

    public Integer getNull(String id) {
        return null;
    }

    public Integer throwException(String id) {
        throw new RuntimeException();
    }

    public Optional<Integer> getOptional(String id) {
        return empty();
    }

    public Either<Integer, String> getEither(String id) {
        return right("404");
    }
}
