package de.egga;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@RequiredArgsConstructor
public class OurService {

    private static final int OFFSET = 5;
    private final RiskyService riskyService;

    public Integer getNull(String id) {

        Integer result = riskyService.getNull(id);

        if (result != null) {
            return result + OFFSET;
        }

        return null;
    }

    public Integer throwException(String id) {

        try {
            Integer result = riskyService.throwException(id);
            return result + OFFSET;

        } catch (RuntimeException e) {
            throw new IdNotFoundException();
        }
    }

    public Optional<Integer> getOptional(String id) {

        Optional<Integer> result = riskyService.getOptional(id);

        if (result.isPresent()) {
            return of(result.get() + OFFSET);
        }

        return empty();
    }

    public Either<Integer, String> getEither(String id) {

        Either<Integer, String> result = riskyService.getEither(id);

        if (result.isLeft()) {
            return left(result.getLeft() + OFFSET);
        }

        return right("ID not found!");
    }
}
