public class Produce {
    private String line;
    private String name;
    private boolean[] available;
    public boolean[] inSeason;
    private int totalAvailable;
    private int totalInSeason;

    public Produce(String n, String l)
    {
        name = n;
        line = l;
        available  = new boolean[12];
        inSeason = new boolean[12];
    }
    public String getLine()
    {
        return line;
    }
    public String getName()
    {
        return name;
    }

    public int getTotalAvailable()
    {
        return totalAvailable;
    }
    public int getTotalInSeason()
    {
        return totalInSeason;
    }

    public boolean[] getAvailable()
    {
        return available;
    }

    public void inSeason(int i)
    {
        inSeason[i] = true;
        available[i] = true;
        totalInSeason++;
        totalAvailable++;
    }
    public void available(int i)
    {
        available[i] = true;
        totalAvailable++;
    }
    public String inSeason()
    {
        String s = "";
        int i = 1;
        for(boolean b : inSeason)
        {
            s += i +  " " + b + "\n";
            i++;
        }
        return s;
    }
    public String available()
    {
        String s = "";
        int i = 1;
        for(boolean b : available)
        {
            s += i +  " " + b + "\n";
            i++;
        }
        return s;
    }


    public String toString()
    {
        return name + ": " + totalInSeason;
    }

}
