package lesson;

public class Main
{
    public static void main (String[] args)
    {
        String[][] mass = {{"32", "18", "45", "8"},{"3", "15", "21", "33"},{"3", "6", "39", "5"},{"31", "5", "23", "87"}};
        //Можно было подставить случайные строки в массив конвертацией из случайных чисел, но не стал. Так удобнее было проверять на работу исключений.
        System.out.println("Сумма всех чисел массива: " + arrays(mass));

    }

    public static int arrays(String[][] mass)
    {
        int count = 0;
        int c;
        System.out.println("Таблица массива:");
        if (mass.length != 4)throw new MyArraySizeException("Массив неправильного размера!");
        for (int i = 0; i<mass.length; i++)
        {
            if (mass[i].length != 4) throw new MyArraySizeException("Массив неправильного размера!");
            for (int j = 0; j<mass.length; j++)
            {
                System.out.print(mass[i][j] + "|");
                try
                {
                    c = Integer.parseInt(mass[i][j]);
                }
                catch (NumberFormatException e)
                {
                    System.out.println();
                    System.out.println("-----------------------------------------");
                    System.out.println("Создание таблицы завершилось некорректно!");
                    throw new MyArrayDataException(i, j, mass[i][j]);
                }
                count = count + c;

            }
            System.out.println();
        }

        return count;
    }
}
