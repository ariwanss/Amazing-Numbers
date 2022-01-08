package numbers;

public class Main {
    public static void main(String[] args) {
        numbers.Application application = new Application();
        boolean keepRunning = true;
        while (keepRunning) {
            keepRunning = application.run();
        }
    }
}


