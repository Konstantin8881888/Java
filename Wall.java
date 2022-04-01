package lesson;


    public class Wall implements Obstruction
    {
        private final int height = 10;
        @Override
        public boolean doIt(participant participant)
        {
            return participant.jump(height);
        }
    }
