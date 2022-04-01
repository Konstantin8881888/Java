package lesson;

public class Main
{
    public static void main(String[] args)
    {
        Cat cat = new Cat();
        Robot robot = new Robot();
        Human human = new Human();
        Wall wall = new Wall();
        Treadmill treadmill = new Treadmill();
        Team team = new Team("Команда", cat, robot, human);
        Track track = new Track(wall, treadmill);
        track.doIt(team);

    }
}
