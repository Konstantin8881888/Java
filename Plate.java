public class Plate
{
    private int food;

    protected Plate(int food)
    {
        this.food = food;
    }

    protected boolean eating(int apetit)
    {
        if (food - apetit >= 0)
        {
            food -= apetit;
            return true;

        }
        else
        {
            return false;
        }
    }

    protected void addFood(int food)
    {
        this.food = food;
        System.out.println("В тарелку положили " + food + " грамм еды.");
    }

    @Override
    public String toString()
    {
        return "В тарелке сейчас " + food + " грамм еды.";
    }
}
