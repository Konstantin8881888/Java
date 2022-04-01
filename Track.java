package lesson;

public class Track
{
    private final Obstruction[] obstructions;

    public Track(Obstruction... obstructions)
    {
        this.obstructions = obstructions;
    }

    public void doIt(Team team)
    {
        System.out.println("На полосе препятствий - " + team.getName());
        for (participant c: team.getTeam())
        {
            int count = 0;
            for (Obstruction obs: obstructions)
            {
                if (obs.doIt(c))
                {
                    if (count%2 == 0)
                    {
                        System.out.println(c.toString() + " пробежал дорожку.");
                    }
                    else
                    {
                        System.out.println(c.toString() + " перепрыгнул стену.");
                    }

                    count++;
                }
                else break;
            }
            if (count == obstructions.length)
            {
                System.out.println(c.toString() + " прошёл полосу препятствий.");
            }
        }
    }
}
