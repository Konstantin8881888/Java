package lesson;

public class MyArrayDataException extends NumberFormatException
{
    int i;
    int j;
    String a;

    public MyArrayDataException(int i, int j, String a)
    {
        super("Как минимум одно из значений массива не является числом! Ошибка обнаружена в ячейке с координатами: " + i + "  " + j + " Некорректное значение: " + a);
        this.i = i;
        this.j = j;
        this.a = a;
    }
}
