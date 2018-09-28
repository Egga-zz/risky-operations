package de.egga;

import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static io.vavr.control.Either.right;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OurServiceTest {

    private static final String ANY_ID = "some customer";
    @Mock
    RiskyService riskyService;

    @InjectMocks
    OurService ourService;

    @Test
    void should_return_null() {
        OurService ourService = new OurService(riskyService);
        when(riskyService.getNull(eq(ANY_ID))).thenReturn(null);

        Integer value = ourService.getNull(ANY_ID);

        assertThat(value).isEqualTo(null);
    }

    @Test
    void should_throw_exception() {
        OurService ourService = new OurService(riskyService);

        when(riskyService.throwException(eq(ANY_ID))).thenThrow(new RuntimeException());

        assertThrows(IdNotFoundException.class, () -> {
            ourService.throwException(ANY_ID);
        });
    }

    @Test
    void should_return_optional() {
        OurService ourService = new OurService(riskyService);
        when(riskyService.getOptional(eq(ANY_ID))).thenReturn(empty());

        Optional<Integer> value = ourService.getOptional(ANY_ID);

        assertThat(value).isEmpty();
    }

    @Test
    void should_return_either() {
        OurService ourService = new OurService(riskyService);
        when(riskyService.getEither(eq(ANY_ID))).thenReturn(right("404"));

        Either<Integer, String> value = ourService.getEither(ANY_ID);

        assertThat(value.get()).isEqualTo("ID not found!");
    }
}
