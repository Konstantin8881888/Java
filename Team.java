package lesson;

public class Team
{
    private final participant[] team;
    private final String name;

    public Team(String name, participant...part)
    {
        this.team = part;
        this.name = name;
    }

    public
    participant[] getTeam()
    {
        return team;
    }

    public String getName()
    {
        return name;
    }
}
