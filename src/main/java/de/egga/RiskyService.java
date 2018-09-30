package de.egga;

import io.vavr.control.Either;

import java.util.Optional;

public interface RiskyService {

    public Integer getNull(String id);

    public Integer throwException(String id);

    public Optional<Integer> getOptional(String id);

    public Either<Integer, String> getEither(String id);
}
