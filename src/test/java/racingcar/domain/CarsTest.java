package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.fixture.DrivingStrategyFixture.ALWAYS_MOVE;
import static racingcar.fixture.DrivingStrategyFixture.NEVER_MOVE;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.fixture.CarsFixture;

public class CarsTest {

    private final Cars cars = CarsFixture.cars();

    @Test
    @DisplayName("차 이름이 0개라면 예외가 발생한다")
    void new_cars_length_less_than_1() {
        assertThatThrownBy(() -> Cars.of(Collections.emptyList()))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("canDrive 반환값이 true라면 전진한다")
    void drive() {
        cars.drive(ALWAYS_MOVE);
        List<Integer> drivingDistances = cars.drivingDistances();

        assertThat(drivingDistances).containsExactly(1, 1, 1);
    }

    @Test
    @DisplayName("canDrive 반환값이 false라면 전진하지 않는다")
    void notDrive() {
        cars.drive(NEVER_MOVE);
        List<Integer> drivingDistances = cars.drivingDistances();

        assertThat(drivingDistances).containsExactly(0, 0, 0);
    }


    @Test
    @DisplayName("우승자 목록을 가져온다 (단일 우승자)")
    void winners_single() {
        List<Car> listCars = List.of(new Car("a", 1), new Car("b", 0));
        Cars cars = new Cars(listCars);

        Winners winners = cars.winners();
        assertThat(winners.names())
            .containsExactly("a");
    }

    @Test
    @DisplayName("우승자 목록을 가져온다 (복수 우승자)")
    void winners_multi() {
        List<Car> listCars = List.of(new Car("a", 1), new Car("b", 1), new Car("c", 0));
        Cars cars = new Cars(listCars);

        Winners winners = cars.winners();
        assertThat(winners.names())
            .containsExactly("a", "b");
    }
}