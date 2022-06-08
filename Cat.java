public class Cat extends Animal
{
    private static final String rod = "Кот";
    public static int countCat;

    protected Cat(String name, int runDistance)
    {
        super(name, runDistance, 0, rod);
        countCat++;
    }

}
