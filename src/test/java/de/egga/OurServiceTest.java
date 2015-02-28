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
    void it_should_call_for_action() {
        OurService ourService = new OurService(riskyService);
        when(riskyService.getNull(eq(ANY_ID))).thenReturn(null);

        Integer value = ourService.getNull(ANY_ID);

        assertThat(value).isEqualTo(null);
    }

    @Test
    void it_should_call_for_action2() {
        OurService ourService = new OurService(riskyService);

        when(riskyService.throwException(eq(ANY_ID))).thenThrow(new RuntimeException());

        assertThrows(IdNotFoundException.class, () -> {
            ourService.throwException(ANY_ID);
        });
    }

    @Test
    void it_should_call_for_action3() {
        OurService ourService = new OurService(riskyService);
        when(riskyService.getOptional(eq(ANY_ID))).thenReturn(empty());

        Optional<Integer> value = ourService.getOptional(ANY_ID);

        assertThat(value).isEmpty();
    }

    @Test
    void it_should_call_for_action4() {
        OurService ourService = new OurService(riskyService);
        when(riskyService.getEither(eq(ANY_ID))).thenReturn(right("404"));

        Either<Integer, String> value = ourService.getEither(ANY_ID);

        assertThat(value.get()).isEqualTo("ID not found!");
    }
}
