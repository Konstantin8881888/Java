package lesson3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Задание 1:");
        System.out.println("-----------------------------------------------------------------------");

        String[] array = {"Книга", "Завтрак", "Фото", "Машина", "Небо", "Вода", "Машина", "Скала", "Фото", "Машина", "Самолёт", "Стена", "Вода"};
        HashSet<String> myList = new HashSet<>(Arrays.asList(array));

        System.out.println(myList);

        HashMap<String, Integer> myCounter = new HashMap<>();
        for (String b: array)
        {
            myCounter.put(b, (myCounter.getOrDefault(b, 0) + 1));
        }
        System.out.print("Строки встречаются в количестве: ");
        System.out.println(myCounter);


        System.out.println("-----------------------------------------------------------------------");
        System.out.println(" Задание 2:");
        System.out.println("-----------------------------------------------------------------------");

        PhoneBook.add("Ivanov", "255716");
        PhoneBook.add("Ivanov", "89536786654");
        PhoneBook.add("Petrov", "345678");
        PhoneBook.add("Sidorov", "678545");
        PhoneBook.add("Andreev", "255456");
        PhoneBook.add("Semyonov", "965457");
        PhoneBook.add("Konovalov", "435443");
        PhoneBook.add("Strelcov", "566777");
        PhoneBook.add("Kalinov", "390456");
        PhoneBook.add("Sidorov", "754677");

        PhoneBook.get("Ivanov");
        PhoneBook.get("Kalinov");
        PhoneBook.get("Strelcov");
        PhoneBook.get("Andronov");



    }
  static class PhoneBook
 {
     private static final HashMap<String, HashSet<String>> phoneMap = new HashMap<>();

     private static void add(String name, String phone)
     {
         HashSet<String> phoneSet = phoneMap.getOrDefault(name, new HashSet<>());
         phoneSet.add(phone);
         phoneMap.put(name, phoneSet);
     }

     private static void get(String name)
     {
         if (phoneMap.containsKey(name))
         {
             System.out.println("Абонент " + name + " имеет номера: " + phoneMap.get(name));
         }
         else System.out.println("Такого абонента нет в справочнике!");
     }
 }

}
