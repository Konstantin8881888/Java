public class Animal
{
    private final String name;
    private final int runDistance;
    private final int swimDistance;
    private static int count = 0;
    private final String rod;



    protected Animal(String name, int runDistance, int swimDistance, String rod)
    {
        this.rod = rod;
        this.name = name;
        this.runDistance = runDistance;
        this.swimDistance = swimDistance;
        count++;
    }

    @Override
    public String toString()
    {
        return (rod + " " + name +  " пробежал(проплыл) ");
    }


    protected String run(int distance)
    {
        return distance <= runDistance ? (toString() + distance + " метра(метров).") : (rod + " " + name + " не смог пробежать " + distance + " метров и упал обессиленый через " + runDistance + " метров.");
    }

    protected String swim(int distance)
    {
        return distance <= swimDistance ? (toString() + distance + " метра(метров).") : (rod + " " + name + " не смог проплыть " + distance + " метров и утонул через " + swimDistance + " метров.");
    }

    public static int getCount()
    {
        return count;
    }
}
