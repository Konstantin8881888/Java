public class Lesson5
{
    public static void main(String[] args)
    {
        Person[] persArray = new Person[5];//Создаём массив сотрудников (можно было бы имена и должности написать кириллицей, но решил не отходить от примера в ДЗ).
        persArray[0] = new Person("Vasya Pupkin", "Programmist","pupkin@gmail.com", "756114", 30000, 38);
        persArray[1] = new Person("Fedor Krolikow", "Designer","dis-fedya@gmail.ru", "89251611391", 31000, 42);
        persArray[2] = new Person("Iwan Prostujenko", "Accountant","schetovod@yandex.ru", "89529603535", 35000, 28);
        persArray[3] = new Person("Petr Kozlow", "Manager","manager-kozlow@yahoo.com", "132293", 70000, 44);
        persArray[4] = new Person("Inokentiy Shniperson", "Director","dirik@jizni.net", "89208333333", 130000, 21);

        printByAge(persArray);


    }

    private static void printByAge(Person[] persArray)
    {
        for (Person person : persArray)
        {
            if (person.getAge() > 40)
            {
                person.printPerson();
            }
        }
    }
}
