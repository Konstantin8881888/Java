import java.util.ArrayList;

@SuppressWarnings("ALL")
public class Box<T extends Fruit>
{
    private final ArrayList<T> fruits;

    public Box()
    {
        fruits = new ArrayList<>();
    }

    public ArrayList<T> getFruits()
    {
        return fruits;
    }

    public void addInBox(T fruit)
    {
        this.fruits.add(fruit);
    }

    public float getWeight()
    {
        float boxWeight = 0.0f;

        for (T fruit: fruits)
        {
            boxWeight += fruit.getWeight();
        }
        return boxWeight;
    }

    public void addFruits(ArrayList<T> fruits)
    {
        this.fruits.addAll(fruits);
    }

    public void boxMoveToNextBox(Box<T> box)
    {
        box.addFruits(getFruits());
        this.fruits.clear();
    }

    public boolean compare(Box<?> box)
    {
        return Float.compare(getWeight(), box.getWeight()) == 0;
    }
}
