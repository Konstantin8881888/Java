public class Person
{
    private String name;
    private String position;
    private String email;
    private String telephon;
    private int pay;
    private int age;




    protected Person(String name, String position, String email, String telephon, int pay, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.telephon = telephon;
        this.pay = pay;
        this.age = age;
    }

    //Метод печати.
    protected void printPerson()//
    {
        System.out.println("--------------------------------------------------");
        System.out.println("Сотрудник " + name + " занимает должность " + position + ", имеет электронную почту: " + email + " и номер телефона - " + telephon +
                           ", при этом, получает зарплату " + pay + " в возрасте " + age + " лет(года).");
    }
    //Геттеры и Сеттеры. Лишние удалил, так код компактней, остальные всё равно не используются в нашем задании.
    protected int getAge()
    {
        return age;
    }
}
