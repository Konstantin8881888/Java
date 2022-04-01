package lesson;

public class Cat implements participant
{
    private final String name = "Том";
    private final int runDistance = 300;
    private final int jumpDistance = 50;


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
        return "Кот " + name;
    }
}
