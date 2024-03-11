package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private final List<Car> cars = new ArrayList<>();

    public Cars(List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException("차는 1대 이상이어야 합니다");
        }
        this.cars.addAll(cars);
    }

    public static Cars fromCarNames(List<String> carNames) {
        return carNames.stream()
            .map(Car::new)
            .collect(Collectors.collectingAndThen(Collectors.toList(), Cars::new));
    }

    public void drive(DrivingStrategy drivingStrategy) {
        for (Car car : cars) {
            car.drive(drivingStrategy);
        }
    }

    public List<Integer> drivingDistances() {
        return cars.stream()
            .map(Car::drivingDistance)
            .collect(Collectors.toList());
    }

    public String result() {
        return cars.stream()
            .map(Car::result)
            .collect(Collectors.joining("\n"));
    }

    public Winners winners() {
        return Winners.from(cars);
    }
}