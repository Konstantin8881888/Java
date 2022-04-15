package lesson2_5;

@SuppressWarnings("IntegerDivisionInFloatingPointContext")

public class Main
{
    static final int size = 10000000;
    static final int halfSize = size/2;

    public static void main(String[] args) throws InterruptedException
    {
        float[] arr1 = new float[size];
        float[] arr2 = new float[size];

        methodNoThreads(arr1);
        methodWithThreads(arr2);
    }

    private static void methodNoThreads(float[] arr)
    {
        for (int i = 0; i < size; i++)
        {
            arr[i] = 1;
        }

        long startTime = System.currentTimeMillis();

        for (int j = 0; j < size; j++)
        {
            arr[j] = formula(arr[j], j);
        }

        System.out.println("Метод без распараллеливания процесса закончил выполнение за " + (System.currentTimeMillis() - startTime) + " миллисекунд");
    }

    private static void methodWithThreads(float[] arr) throws InterruptedException
    {
        for (int i = 0; i < size; i++)
        {
            arr[i] = 1;
        }

        long startTime2 = System.currentTimeMillis();

        float[] a1 = new float[halfSize];
        float[] a2 = new float[halfSize];

        System.arraycopy(arr, 0, a1,0, halfSize);
        System.arraycopy(arr, halfSize, a2, 0, halfSize);

        Thread thread1 = new Thread(() ->
        {
            for (int j = 0; j < halfSize; j++)
            {
                a1[j] = formula(a1[j], j);
            }
        });

        Thread thread2 = new Thread(() ->
        {
            for (int i = 0; i < halfSize; i++)
            {
                a2[i] = formula(a2[i], i);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join(); //Проверку на исключения в метод вынес.
        thread2.join();//Проверку на исключения в метод вынес.

        System.arraycopy(a1, 0, arr,0, halfSize);
        System.arraycopy(a2, 0, arr, halfSize, halfSize);

        System.out.println("Метод с разделением потоков закончил выполнение за " + (System.currentTimeMillis() - startTime2) + " миллисекунд");
    }

    private static float formula(float v, int i)
    {
        return (float) (v * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5) * Math.cos(0.4f + i/2));
    }
}
