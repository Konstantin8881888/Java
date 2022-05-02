public class Fruit
{
    protected final String name;
    protected final float weight;

    public Fruit(float weight, String name)
    {
        this.weight = weight;
        this.name = name;
    }

    public float getWeight()
    {
        return weight;
    }

    @Override
    public String toString()
    {
        return "Фрукт " + name + " весит " + weight;
    }
}
