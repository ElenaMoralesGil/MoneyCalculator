package software.ulpgc.moneyCalculator.spark;

public interface SparkCommand {
    static Output output(int code, String result) {
        return new Output() {
            @Override
            public int code() {
                return code;
            }

            @Override
            public String result() {
                return result;
            }
        };
    }

    Output execute(Input input);

    interface Input {
        String get(String key);
    }

    interface Output {
        int code();

        String result();
    }
}
