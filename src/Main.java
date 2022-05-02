public class Main
{
    public static void main(String[] args)
    {
        System.out.println("---------------1, 2 задание:----------------");
        String[] strings = {"5", "3"};
        Integer[] integers = {7, 6};
        Double[] doubles = {5.2, 1.8};

        ArrayToArray<Object> array = new ArrayToArray<>();
        array.changeIndex(strings, 0, 1);
        array.changeIndex(integers, 0, 1);
        array.changeIndex(doubles, 0, 1);
        System.out.println(array.arrayToArrayList(strings).getClass().getName());

        System.out.println("\n" + "---------------3 задание:----------------" + "\n");

        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        for (int i = 0; i < 3; i++)
        {
            appleBox.addInBox(new Apple());
            orangeBox.addInBox(new Orange());
        }

        System.out.println("Коробка с яблоками " + appleBox.getFruits());
        System.out.println("Коробка с апельсинами " + orangeBox.getFruits());
        System.out.println("Вес коробки с яблоками " + appleBox.getWeight());
        System.out.println("Вес коробки с апельсинами " + orangeBox.getWeight());
        System.out.println("Сравнение веса коробок: " + appleBox.compare(orangeBox));

        System.out.println("\n" + "Добавляем фрукты для получения одинакового веса коробок:" + "\n");

        appleBox.addInBox(new Apple());
        appleBox.addInBox(new Apple());
        appleBox.addInBox(new Apple());
        orangeBox.addInBox(new Orange());

        System.out.println("Вес коробки с яблоками " + appleBox.getWeight());
        System.out.println("Вес коробки с апельсинами " + orangeBox.getWeight());
        System.out.println("Сравнение веса коробок: " + appleBox.compare(orangeBox));

        System.out.println("\n" + "Пересыпаем из коробки в коробку:" + "\n");

        Box<Apple> appleBox2 = new Box<>();
        appleBox2.addInBox(new Apple());
        appleBox.boxMoveToNextBox(appleBox2);
        System.out.println(appleBox.getFruits());
        System.out.println(appleBox2.getFruits());
    }
}
