package solver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static solver.App.DELTA;

class AppTest {

    private App app;

    @BeforeEach
    void init() {
        app = new App();
    }

    /**
     * Написать тест, который проверяет, что для уравнения x^2+1 = 0 корней нет (возвращается пустой массив)
     */
    @Test
    void shouldReturnEmpty() {
        var response = app.solve(1, 0, 1);
        assertEquals(0, response.length);
    }

    /**
     * Написать тест, который проверяет, что для уравнения x^2-1 = 0 есть два корня кратности 1 (x1=1, x2=-1)
     */
    @Test
    void shouldFindTwoRoots() {
        var response = app.solve(1, 0, -1);
        assertThat(response)
                .hasSize(2)
                .contains(1, -1);
    }

    /**
     * Написать тест, который проверяет, что для уравнения x^2+2x+1 = 0 есть один корень кратности 2 (x1= x2 = -1).
     * С учетом того, что дискриминант тоже нельзя сравнивать с 0 через знак равенства,
     * подобрать такие коэффициенты квадратного уравнения для случая одного корня кратности два,
     * чтобы дискриминант был отличный от нуля, но меньше заданного эпсилон.
     * Эти коэффициенты должны заменить коэффициенты в тесте из п. 7.
     */
    @Test
    void shouldFindOneRoot() {
        var response = app.solve(1.00000001, 1.9999999, 1);
        assertEquals(1, response.length);
        assertTrue(Math.abs(response[0] - (-1)) <= DELTA);
    }

    /**
     * Написать тест, который проверяет, что коэффициент a не может быть равен 0. В этом случае solve выбрасывает исключение.
     * Примечание. Учесть, что a имеет тип double и сравнивать с 0 через == нельзя.
     * Посмотреть какие еще значения могут принимать числа типа double,
     * кроме числовых и написать тест с их использованием на все коэффициенты. solve должен выбрасывать исключение.
     */
    @Test
    void shouldThrowWhenZeroEqA() {
        assertThrows(RuntimeException.class, () -> app.solve(0, 2, 3));
    }

    /**
     * Посмотреть какие еще значения могут принимать числа типа double, кроме числовых и написать тест с их
     * использованием на все коэффициенты. solve должен выбрасывать исключение.
     *
     * @param invalid отличное от числа значение
     */
    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NEGATIVE_INFINITY,
            Double.POSITIVE_INFINITY,
            Double.NaN
    })
    void shouldThrowWithInvalidInputs(double invalid) {
        assertAll(
                () -> assertThrows(RuntimeException.class, () -> app.solve(invalid, 2, 3)),
                () -> assertThrows(RuntimeException.class, () -> app.solve(1, invalid, 3)),
                () -> assertThrows(RuntimeException.class, () -> app.solve(1, 2, invalid))
        );

    }

}