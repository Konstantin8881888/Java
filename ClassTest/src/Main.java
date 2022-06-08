import testing.TestsHandler;

public class Main {
    public static void main(String[] args) {

        TestingClass testingClass = new TestingClass();
        TestsHandler.start(testingClass.getClass());

    }
}
