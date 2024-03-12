package racingcar.domain;

public class Round {

    private int round;

    private Round(int round) {
        this.round = round;
    }

    public static Round of(int round) {
        if (round <= 0) {
            throw new IllegalArgumentException("라운드 수는 자연수만 가능합니다");
        }
        return new Round(round);
    }

    public void progress() {
        round--;
    }

    public boolean isEnd() {
        return round == 0;
    }
}
