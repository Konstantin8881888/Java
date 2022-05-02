import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayToArray<T>
{
    public void changeIndex(T[] array, int index1, int index2)
    {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;

        for (T t:array )
        {
            System.out.println(t);
        }
    }

    public List<T> arrayToArrayList(T[] array)
    {
        return new ArrayList<>(Arrays.asList(array));
    }
}
