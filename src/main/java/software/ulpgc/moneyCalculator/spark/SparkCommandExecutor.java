package software.ulpgc.moneyCalculator.spark;

import spark.Request;
import spark.Response;

public record SparkCommandExecutor(Request request, Response response) {
    public String execute(SparkCommand command) {
        SparkCommand.Output output = command.execute(input());
        response.status(output.code());
        return output.result();
    }

    private SparkCommand.Input input() {
        return new SparkCommand.Input() {
            @Override
            public String get(String key) {
                return oneOf(request.params(key), request.queryParams(key));
            }

            private String oneOf(String a, String b) {
                return a != null ? a : b;
            }
        };
    }
}

