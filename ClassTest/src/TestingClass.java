import testing.AfterSuite;
import testing.BeforeSuite;
import testing.Test;

public class TestingClass {

    @Test(priority = 3)
    public void testMethod1() {
        System.out.println("Приоритет данного метода = 3");
    }

    @Test(priority = 10)
    public void testMethod2() {
        System.out.println("Приоритет данного метода = 10");
    }

    @Test(priority = 1)
    public void testMethod3() {
        System.out.println("Приоритет данного метода = 1");
    }

    @Test(priority = 5)
    private void testMethod4() {
        System.out.println("Приоритет данного метода = 5 (приватный)");
    }

    @Test
    public void testMethod5() {
        System.out.println("Приоритет данного метода по умолчанию(5)");
    }

    @AfterSuite
    public void afterMethod() {
        System.out.println("Метод AfterSuite");
    }

    @BeforeSuite
    public void beforeSuiteMethod() {
        System.out.println("Метод BeforeSuite");
    }

}