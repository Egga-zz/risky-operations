package de.egga;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

@RequiredArgsConstructor
public class OurService {

    private static final int OFFSET = 5;
    private final RiskyService riskyService;

    public Integer getNull(String id) {

        Integer result = riskyService.getNull(id);

        if (result != null) {
            return transform(result);
        }

        return null;
    }

    public Integer throwException(String id) {

        try {
            return transform(riskyService.throwException(id));

        } catch (RuntimeException e) {
            throw new IdNotFoundException();
        }
    }

    public Optional<Integer> getOptional(String id) {

        return riskyService.getOptional(id)
                .map(this::transform);
    }

    public Either<Integer, String> getEither(String id) {

        Either<Integer, String> result = riskyService.getEither(id);

        if (result.isLeft()) {
            return left(transform(result.getLeft()));
        }

        return right("ID not found!");
    }

    private Integer transform(Integer left) {
        return left + OFFSET;
    }
}
