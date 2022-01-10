public class HomeWorkApp
{
    public static void main(String[] args)
    {
        printThreeWords();
        checkSumSign();
        printColor();

        //Здесь не стану получать случайное число или числа, пусть останутся изначально инициализированными.
        int a = 15;//Объявим для следующего метода, чтобы сделать его передающим значение и возвращающим результат.
        int b = 8;//Объявим для следующего метода, чтобы сделать его передающим значение и возвращающим результат.
        String res = compareNumbers(a, b);
        System.out.println("------------------------------------"); //Отделим визуально в консоле от результатов предыдущего метода
        System.out.println(res);
    }


    private static void printThreeWords()
    {
        System.out.println("--------------"); //Так красивее)
        System.out.println("|   Orange   |");
        System.out.println("|   Banana   |");
        System.out.println("|   Apple    |");
        System.out.println("--------------");
    }

    private static void checkSumSign()
    {
        int a = (int)(Math.random()*10);//Так интереснее. Программа будет выдавать при каждом запуске разные значения переменной.
        int b = -5;

        if ((a + b) >= 0) //Мог бы и созданием третьей переменной, но захотел так))
        {
            System.out.println("Сумма цифр " + a + " и " + b + " является положительной.");
            if ((a + b) == 0)
            {
                System.out.println("При этом, сумма цифр " + a + " и " + b + " является нулём."); //Тоже добавил для того, чтоб сделать интересней программу.
            }
        }
        else
        {
            System.out.println("Сумма цифр " + a + " и " + b + " является отрицательной.");
        }
        System.out.println("------------------------------------------");
    }

    private static void printColor()
    {
        int value = (int) ((Math.random() * 300) - 100);// Для интереса создадим псевдослучайное число от -100 до 199

        System.out.println("Случайное значение получилось: " + value);
        System.out.print("Результат - ");

        if (value <= 0)
        {
            System.out.println("Красный");
        }
        else if (value > 0 && value <= 100)
        {
            System.out.println("Жёлтый");
        }
        else
        {
            System.out.println("Зелёный");
        }
    }

    private static String compareNumbers(int num1, int num2)
    {
        String result = "";
        if (num1 >= num2)
        {
            result = "a >= b";
        }
        else
        {
            result = "a < b";
        }
        return result;
    }


}
