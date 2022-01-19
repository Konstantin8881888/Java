import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Lesson3
{
    public static void main(String[] args) {
        // Задание 1:
        int[] array = {1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0};
        revert(array);
        System.out.println("------------------------------------------------------");//Визуально отделяем задания.

        //Задание 2:
        createArray();
        System.out.println("------------------------------------------------------");//Визуально отделяем задания.

        //Задание 3:
        multipl();
        System.out.println("------------------------------------------------------");//Визуально отделяем задания.

        //Задание 4:
        draw();
        System.out.println("------------------------------------------------------");//Визуально отделяем задания.

        //Задание 5:
        int len = ((int) (Math.random() * 8)) + 1;
        int initialValue = (int) (Math.random() * 8);
        System.out.println(Arrays.toString(arrayAdValue(len, initialValue)));
        System.out.println("------------------------------------------------------");//Визуально отделяем задания.

        //Задание 6:
        //Пердположим, что диапазон возможных значений элементов массива от 0 до 100.
        int max = 0;
        int min = 100;
        int[] array5 = {9, 5, 23, 72, 1, 0, 5, 32, 14, 81, 59, 41};
        minOrMax(max, min, array5);
        System.out.println("------------------------------------------------------");//Визуально отделяем задания.

        //Задание 7:
        int[] array6 = {2, 6, 7, 5, 11, 4};
        System.out.println(equalPartsArray(array6));
        System.out.println("------------------------------------------------------");//Визуально отделяем задания.

        //Задание 8:
        Scanner scanner = new Scanner(System.in);// Предоставим пользователю возможность выбора насколько сдвинуть.
        System.out.println("Введите число, на которое хотите сдвинуть элементы массива: ");

        int n = 0;
        try
        {
            n = scanner.nextInt();
        }
        catch (InputMismatchException e)
        {
            System.out.println("Вами введено не число!");
        }

        scanner.close();
        //Массив возьму из задания 6.
        System.out.println("Первоначальный массив: " + Arrays.toString(array5));
        moveElemArray(n, array5);


    }



    private static void revert(int[] array)
    {
        System.out.println(Arrays.toString(array));
        for (int i = 0; i < array.length; i++)
        {
            array[i] = array[i] == 1 ? 0 : 1;
        }
        System.out.println(Arrays.toString(array));
    }
    //-----------------------------------------------------------------------------------------------
    private static void createArray()
    {
        int[] array2 = new  int[100];
        for (int i = 0; i < array2.length ; i++)
        {
            array2[i] = i + 1;
        }
        System.out.println(Arrays.toString(array2));
    }
    //--------------------------------------------------------------------------------------------
  private static void multipl()
  {
      int[] array3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
      for (int i = 0; i < array3.length; i++)
      {
          if (array3[i] < 6)
          {
              array3[i] = array3[i] * 2;
          }
      }
      System.out.println(Arrays.toString(array3));
  }
  //---------------------------------------------------------------------------------------------

    private static void draw()
    {
        int[][] mass = new int[][] {{9, 9, 9, 9, 9, 9}, {9, 9, 9, 9, 9, 9}, {9, 9, 9, 9, 9, 9}, {9, 9, 9, 9, 9, 9}, {9, 9, 9, 9, 9, 9}, {9, 9, 9, 9, 9, 9}};
        for (int i = 0; i < mass.length; i++)
        {
            for (int j = 0; j < mass[i].length; j++)
            {
                if(i == j || j == (mass.length - 1) - i)
                {
                    mass[i][j] = 1;
                }
                System.out.print(mass[i][j] + " ");
            }
            System.out.println();
        }
    }
    //---------------------------------------------------------------------------------------------

    private static int[] arrayAdValue(int a, int initialValue)
    {
        int[] array4 = new int[a];
        Arrays.fill(array4, initialValue);// IDEA любезно подсказала заменить цикл for)))
        return array4;
    }
    //-------------------------------------------------------------------------------------------------
    private static void minOrMax(int max, int min, int[] array5)
    {
        for (int j : array5) {
            if (j < min) {
                min = j;
            }
            if (j > max) {
                max = j;
            }
        }
        System.out.println("В массиве " + Arrays.toString(array5) + " минимальный элемент равен " + min + ", а максимальный " + max);
    }
    //--------------------------------------------------------------------------------------------------
    private static boolean equalPartsArray(int[] array6)
    {
        int lengArr = array6.length - 1;
        int startPos = 0;
        int left = 0;
        int right = 0;
        boolean res = false;

        while (true) {//Бесконечны цикл нам больше подойдёт, по-моему.
            if (left > right)
            {
                right += array6[lengArr--];//Сдвигаем влево, прибавляя предыдущий элемент.
            }
            else
            {
                left += array6[startPos++];//Сдвигаем вправо прибавляя следующий элемент или присваиваем значение первого элемента массива.
            }
            System.out.println("Число слева - " + left + " Число справа - " + right);//Выведем, чтобы знать, где что произошло при работе программы и когда.
            if (left == right)//Первоначально не сработает, так как в else присвоится значение первого элемента.
            {
                res = true;//Заканчиваем цикл, так как нашли место, которое будет давать true по условию задачи.
                break;
            }
            if (startPos > lengArr)
            {
                break; //Прекращаем цикл, если дальше перебирать бессмысленно или сдвигать место сравнения некуда.
            }

        }
        return res;
    }
    //----------------------------------------------------------------------------------------------------------
    private static void moveElemArray(int n, int[] array5)
    {
        if(n != 0)
        {

            if (n > 0)
            {
                for (int i = 0; i < n; i++)
                {
                    int temp = array5[0];//Создадим временную локальную переменную.
                    array5[0] = array5[array5.length - 1];


                    for (int j = 1; j < array5.length - 1; j++)// Цикл для сдвига.
                    {
                        array5[array5.length - j] = array5[array5.length - j - 1];
                    }

                    array5[1] = temp; //Переносим временное значение во второй элемент массива
                }
            }
            else //Сделаем похожий цикл, на случай, если пользователем будет введено отрицательное значение.
            {
                for (int i = 0; i > n; i--)
                {
                    int temp = array5[array5.length - 1];//Теперь присваиваем  с конца массива.
                    array5[array5.length - 1] = array5[0];

                    for (int j = 1; j < array5.length - 1; j++)
                    {
                        array5[j - 1] = array5[j];
                    }

                    array5[array5.length - 2] = temp;//Соответственно переносим временное в предпоследний элемент массива.
                }
            }

        }
        else
        {
            System.out.println("Сдвигать на введённое вами бессмысленно.");//Выведем если пользователь введёт ноль или сработает исключение
        }

        System.out.println("Конечный массив: " + Arrays.toString(array5));
    }



}
