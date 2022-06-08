import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
//        int[] arrayExp = {1, 4, 1, 5};
//        System.out.println(Arrays.toString(newArrayAfter4(arrayExp)));
//        System.out.println(correctlyChecked1And4InArray(arrayExp));
    }


    public static int[] newArrayAfter4(int[] array)
    {
        for (int i = array.length - 1; i > -1; i--)
        {
            if (array[i] == 4)
            {
                i++;
                int[] newArray = new int[array.length - i];

                System.arraycopy(array, i, newArray, 0, newArray.length);
                return newArray;
            }

        }
        throw new RuntimeException();
    }

    public static boolean correctlyChecked1And4InArray(int[] array2)
    {
        int countOne = 0;
        int countFour = 0;

        for (int j : array2)
        {
            if (j == 1)
            {
                countOne++;
            }
            else if (j == 4)
            {
                countFour++;
            }

        }
        return countOne > 0 && countFour > 0;
    }
}
