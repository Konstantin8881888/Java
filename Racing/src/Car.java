import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable
{
    private static int CARS_COUNT;
    private final Race race;
    private final int speed;
    private final String name;
    private static final CyclicBarrier startBarrier;
    private static final CountDownLatch countDownLatchFinish;
    private static final CountDownLatch countDownLatchReady;

    static
    {
        countDownLatchFinish = Main.countDownLatchFinish;
        countDownLatchReady = Main.countDownLatchReady;
        startBarrier = Main.startBarrier;
    }

    String getName()
    {
        return name;
    }
    int getSpeed()
    {
        return speed;
    }
    Car(Race race, int speed)
    {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            countDownLatchReady.countDown();
            System.out.println(this.name + " готов");
            startBarrier.await();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        final ArrayList<Stage> stages = race.getStages();
        for (Stage stage : stages)
        {
            stage.go(this);
        }
        countDownLatchFinish.countDown();
    }
}