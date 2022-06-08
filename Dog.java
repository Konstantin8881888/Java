public class Dog extends Animal
{
    private static final String rod = "Пёс";
    public static int countDog;

    protected Dog(String name, int runDistance, int swimDistance)
    {
        super(name, runDistance, swimDistance, rod);
        countDog++;
    }
}
