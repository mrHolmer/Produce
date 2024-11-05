import java.util.ArrayList;
public class ShoppingList
{
    private ArrayList<Produce> list;
    private int month;

    public ShoppingList(int month)
    {
        this.month = month - 1;
        list = new ArrayList<Produce>();
        for (Produce p : Parser.produce) {
            if (p.getAvailable()[month - 1]) list.add(p);
        }

        availableSort();

        seasonSort();

        freshSort();




    }

    public void availableSort()
    {
        for(int i = 1; i < list.size(); i++)
        {
            int index = i;
            Produce p = list.get(index);
            while(index > 0 && list.get(index - 1).getTotalAvailable() > p.getTotalAvailable())
            {
                index--;
            }
            if(index != i)
            {
                list.add(index, list.remove(i));
            }
        }
    }

    public void freshSort()
    {
        for(int i = 1; i < list.size(); i++) {
            Produce p = list.get(i);
            int index = i;
            while (index > 0 && !list.get(index - 1).inSeason[month] && p.inSeason[month]) {
                index--;
            }
            list.add(index, list.remove(i));
        }
    }

    public void seasonSort()
    {
        for(int i = 1; i < list.size(); i++)
        {
            int index = i;
            Produce p = list.get(i);
            while(index > 0 && list.get(index - 1).getTotalInSeason() > p.getTotalInSeason())
            {
                index--;
            }
            if(index != i)
            {
                list.add(index, list.remove(i));
            }
        }
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
            s += p.getName() + " " + p.getTotalInSeason() + " " + p.getTotalAvailable() + "\n";
        }
        return s;
    }
}