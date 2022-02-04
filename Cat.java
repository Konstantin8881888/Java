public class Cat
{
    private String name;
    private int apetit;
    private boolean hungry = true;

    protected Cat(String name, int apetite)
    {
        this.name = name;
        this.apetit = apetite;
    }

    protected String getStatus()
    {
        return hungry ? name + " голоден." : name + " не голодный.";
    }

    protected void eat(Plate plate)
    {
        if (hungry && plate.eating(apetit))
        {
            hungry = false;
        }
    }
}
