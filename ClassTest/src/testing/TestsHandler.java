package testing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;

public class TestsHandler
{
    private static Object object;

    public static void start(Class TestClass)
    {
        if (!checkBeforeAfterOnce(TestClass))
        {
            throw new RuntimeException();
        }

        try
        {
            object = TestClass.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            e.getStackTrace();
        }


        Method beforeMethod = null;
        Method afterMethod = null;

        ArrayList<Method> testMethods = new ArrayList<>();
        Method[] objMethods = TestClass.getDeclaredMethods();

        for (Method method : objMethods)
        {
            if (method.getAnnotation(BeforeSuite.class) != null)
            {
                beforeMethod = method;
            }
            else if (method.getAnnotation(AfterSuite.class) != null)
            {
                afterMethod = method;
            }
            else if (method.getAnnotation(Test.class) != null)
            {
                testMethods.add(method);
            }
        }

        testMethods.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));

        if (beforeMethod != null)
        {
            testMethods.add(0, beforeMethod);
        }

        if (afterMethod != null)
        {
            testMethods.add(afterMethod);
        }

        try {
            for (Method testMethod : testMethods)
            {
                if (Modifier.isPrivate(testMethod.getModifiers()))
                {
                    testMethod.setAccessible(true);
                }
                testMethod.invoke(object);
            }
        }
        catch (IllegalAccessException | InvocationTargetException e)
        {
            e.getStackTrace();
        }
    }

    private static boolean checkBeforeAfterOnce(Class TestClass)
    {
        int beforeCount = 0;
        int afterCount = 0;

        for (Method method : TestClass.getDeclaredMethods())
        {
            if (method.getAnnotation(BeforeSuite.class) != null)
            {
                beforeCount++;
            }
            if (method.getAnnotation(AfterSuite.class) != null)
            {
                afterCount++;
            }
        }

        return (beforeCount < 2) && (afterCount < 2);
    }
}