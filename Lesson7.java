public class Lesson7
{

    public static void main(String[] args)
    {
        Plate foodPlate = new Plate(70);
        System.out.println(foodPlate);
        System.out.println("-------------------------------------------------------------------------------");

        Cat[] arrayCat = new Cat[]
                {
                        new Cat("Фёдор", 5),
                        new Cat("Маркиз", 25),
                        new Cat("Феликс", 15),
                        new Cat("Никанор Петрович", 45),
                        new Cat("Дымок", 10),
                        new Cat("Барсик", 12),
                        new Cat("Рыжик", 17),
                        new Cat("Шумок", 8)
                };

        for (Cat cat: arrayCat)
        {
            System.out.println(cat.getStatus());
            cat.eat(foodPlate);
            System.out.println(foodPlate);
        }

        System.out.println("-------------------------------------------------------------------------------");

        for (Cat cat: arrayCat)
        {
            System.out.println(cat.getStatus());
        }
        System.out.println("-------------------------------------------------------------------------------");

        foodPlate.addFood(100);
        System.out.println("-------------------------------------------------------------------------------");


        for (Cat cat: arrayCat)
        {
            cat.eat(foodPlate);
            System.out.println(foodPlate);
        }

        System.out.println(foodPlate);
        System.out.println("-------------------------------------------------------------------------------");
        for (Cat cat: arrayCat)
        {
            System.out.println(cat.getStatus());
        }
    }

}
