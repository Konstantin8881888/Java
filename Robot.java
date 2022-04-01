package lesson;

public class Robot implements participant
{
    private final String name = "Андроид";
    private final int runDistance = 200;
    private final int jumpDistance = 30;

    @Override
    public boolean run(int runDistance)
    {
        return this.runDistance >= runDistance;
    }

    @Override
    public boolean jump(int jumpHeight)
    {
        return this.jumpDistance >= jumpHeight;
    }

    @Override
    public String toString()
    {
        return "Робот " + name;
    }
}
