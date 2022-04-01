package lesson;

public class Human implements participant {
    private final String name = "Фёдор";
    private final int runDistance = 150;
    private final int jumpDistance = 30;


    @Override
    public boolean run(int runDistance) {
        return this.runDistance >= runDistance;
    }

    @Override
    public boolean jump(int jumpHeight) {
        return this.jumpDistance >= jumpHeight;
    }

    @Override
    public String toString() {
        return "Человек " + name;
    }
}
