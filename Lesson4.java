import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Lesson4
{

    public static final String SIGN_X = "X";
    public static final String SIGN_0 = "O";
    public static final String SIGN_NOT = "*";
    public static final int SIZE = 5;
    public static final int DOT_TO_WIN = 4;
    public static String[][] field = new String[SIZE][SIZE];
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)

    {
        game();
        sc.close();
    }


    public static void game()
    {
        initMap();
        while (true)
        {
            printMap();
            humanTurn();
            if (checkWin(SIGN_X))
            {
                System.out.println("Победил пользователь!");
                printMap();
                break;
            }
            if (isMapFull())
            {
                System.out.println("Ничья");
                printMap();
                break;
            }
            aiTurn();
            if (checkWin(SIGN_0))
            {
                System.out.println("Победил компьютер!");
                printMap();
                break;
            }
            if (isMapFull())
            {
                System.out.println("Ничья");
                printMap();
                break;
            }
        }
    }



    public static void initMap()
    {
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                field[i][j] = SIGN_NOT;
            }
        }
    }

    public static void printMap()
    {
        for (int i = 0; i <= SIZE; i++)
        {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++)
        {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++)
            {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void humanTurn()
    {
        int x;
        int y;
        do
        {

            System.out.println("Введите координаты x y (1 - " + SIZE + "): ");


            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        }
        while (isCellWrite(x, y));
        field[x][y] = SIGN_X;
    }

    public static void aiTurn()
    {
        int x = 0;
        int y = 0;
        boolean aiWin = false;
        boolean userWin = false;

        for (int i = 0; i < SIZE; i++)//Проверяем, может ли победить компьютер, поставив фишку в какое-либо из незанятых полей, если может - ставим.
        {
            for (int j = 0; j < SIZE; j++)
            {
                if (!isCellWrite(i, j))
                {
                    field[i][j] = SIGN_0;
                    if (checkWin(SIGN_0))
                    {
                        x = i;
                        y = j;
                        aiWin = true;
                    }
                    field[i][j] = SIGN_NOT;
                }
            }
        }

        if (!aiWin) //Если компьютер не может победить по предыдущему условию, проверяем, может ли победить игрок следующим ходом, если может - блокируем этот ход.
        {
            for (int i = 0; i < SIZE; i++)
            {
                for (int j = 0; j < SIZE; j++)
                {
                    if (!isCellWrite(i, j))
                    {
                        field[i][j] = SIGN_X;
                        if (checkWin(SIGN_X))
                        {
                            x = i;
                            y = j;
                            userWin = true;
                        }
                        field[i][j] = SIGN_NOT;
                    }
                }
            }
        }

        if (!aiWin && !userWin)// Если нет возможности на следующем оду победить кому-либо, ставим фишку компьютера в случайное поле.
        {
            do
            {
                Random random = new Random();
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            }
            while (isCellWrite(x, y));
        }
        field[x][y] = SIGN_0;
        System.out.println("Компьютер походил в точку с координатами " + (x + 1) + " и " + (y + 1));
    }


    public static boolean isCellWrite(int x, int y)//Проверяем занятость ячейки.
    {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE)
        {
            return false;
        }
        return !Objects.equals(field[x][y], SIGN_NOT);
    }

    public static boolean checkLine(int start_x, int start_y, int dx, int dy, String sign)//Проверяем линию по горизонтали, вертикали или диагонали
    {
        for (int i = 0; i < DOT_TO_WIN; i++)
        {
            if (!Objects.equals(field[start_x + i * dx][start_y + i * dy], sign))
                return false;
        }
        return true;
    }

    public static boolean checkWin(String sign)//Проверяем все линии. Если одна из них срабатывает, значит есть выигрышная.
    {
        for (int i = 0; i < SIZE; i++)
        {
            // Проверяем на выигрышность строки или столбцы.
            if (checkLine(i, 0, 0, 1, sign)||checkLine(0, i, 1, 0, sign)) return true;
        }
        // Проверяем на выигрышность диагонали.
        return checkLine(0, 0, 1, 1, sign) || checkLine(0, DOT_TO_WIN, 1, -1, sign);
    }
    private static boolean isMapFull()
    {
        boolean b = true;
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                if (Objects.equals(field[i][j], SIGN_NOT)) {
                    b = false;
                    break;//Прекращаем дальнейший перебор, поскольку это уже бессмысленно.
                }
            }
        }
        return b;
    }
}

