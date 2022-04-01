package lesson;

public class Treadmill implements Obstruction

{
    private final int distance = 50;
    @Override
    public boolean doIt(participant participant)
    {

        return participant.run(distance);
    }
}
