public class Lesson6
{
    public static void main(String[] args)
    {
        Cat barsik = new Cat("Барсик", 200);
        Cat murzik = new Cat("Мурзик", 300);
        Dog sharik = new Dog("Шарик", 500, 10);
        Dog belyash = new Dog("Беляш", 700, 20);

        System.out.println("На полосу препятствий из животных вышло - " + Animal.getCount() + ". Из них котов - " + Cat.countCat + ", собак - " + Dog.countDog + ".");
        System.out.println(sharik.run(550));
        System.out.println(barsik.swim(10));
        System.out.println(murzik.run(150));
        System.out.println(belyash.swim(15));

    }


}
