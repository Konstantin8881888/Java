public class Main
{

        static char c = 'A';
        static Object monitor = new Object();

        public static void main(String[] args)
        {

                new Thread(new WaitNotify('A', 'B')).start();
                new Thread(new WaitNotify('B', 'C')).start();
                new Thread(new WaitNotify('C', 'A')).start();
        }


        static class WaitNotify implements Runnable
        {
                private char currentLetter;
                private char nextLetter;

                public WaitNotify(char currentLetter, char nextLetter)
                {
                        this.currentLetter = currentLetter;
                        this.nextLetter = nextLetter;
                }

                @Override
                public void run()
                {
                        for (int i = 0; i < 5; i++)
                        {
                                synchronized (monitor)
                                {
                                        try
                                        {
                                                while (c != currentLetter)
                                                {
                                                        monitor.wait();
                                                }
                                                System.out.print(currentLetter);
                                                c = nextLetter;
                                                monitor.notifyAll();
                                        }
                                        catch (InterruptedException e)
                                        {
                                                System.out.println(e);;
                                        }
                                }
                        }
                }
        }

}

