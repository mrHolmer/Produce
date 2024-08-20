import java.util.ArrayList;
public class ShoppingList
{
    private ArrayList<Produce> list;

    public ShoppingList(int month)
    {
        list = new ArrayList<Produce>();
        for (Produce p : Parser.produce) {
            if (p.getAvailable()[month - 1]) list.add(p);
        }

        sortAvailable();
        sortInSeason();
    }

    public void sortInSeason()
    {
        for(int i = 0; i < list.size() - 1; i++)
        {
            int min = i;
            for(int j = i + 1; j < list.size(); j++)
            {
                if(list.get(j).getTotalInSeason() < list.get(min).getTotalInSeason())
                {
                    min = j;
                }
            }
            Produce temp = list.get(i);
            list.set(i, list.get(min));
            list.set(min, temp);
        }
    }
    public void sortAvailable()
    {
        for(int i = 0; i < list.size() - 1; i++)
        {
            int min = i;
            for(int j = i + 1; j < list.size(); j++)
            {
                if(list.get(j).getTotalAvailable() < list.get(min).getTotalAvailable())
                {
                    min = j;
                }
            }
            Produce temp = list.get(min);
            list.set(min, list.get(i));
            list.set(i, temp);
        }
    }

    public ArrayList<Produce> getList()
    {
        return list;
    }
    public String toString()
    {
        String s = "";
        for(Produce  p : list)
        {
            s += p.getName() + "\n";
        }
        return s;
    }
}