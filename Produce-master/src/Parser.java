import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static ArrayList<Produce> produce;
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/text.txt");
        Scanner s = new Scanner(f);
        System.out.println(s);
        String string = s.nextLine();
        while(s.hasNextLine()) {
            string += s.nextLine();
        }
        String[] strings = string.split("<tr>");
        produce = new ArrayList<>();
        for(String line : strings) //System.out.println(p);
        {
            if(line.indexOf("</td>")>-1) {
                Produce p = new Produce(line.substring(line.indexOf(">") + 1, line.indexOf("</td>")), line);
                produce.add(p);
            }
        }
        clean();
        for(Produce p : produce)
        {
            String str = p.getLine();
            int i = str.indexOf("/td");
            str = str.substring(i);
            int month = 0;

            while(i > 0 && i < str.length() && month < 12)
            {
                str = str.substring(str.indexOf("<td") + 3);
                if(str.substring(0, str.indexOf("</td")).equals(" style=\"background-color:#78932a\"> "))
                {
                    p.inSeason(month);
                }
                else if(str.substring(0, str.indexOf("</td")).equals(" style=\"background-color:#c17915\"> "))
                 {
                p.available(month);
                 }
                month++;
                i = str.indexOf("/td") + 1;
                str = str.substring(i);
            }
        }
        ask();
    }
    public static void ask()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the month as a number");
        System.out.println(new ShoppingList(s.nextInt()));
    }
    public static void clean()
    {
        for(int i = 0; i < produce.size(); i++)
        {
            if(produce.get(i).getName().equalsIgnoreCase(" ")) {
                produce.remove(i);
                i--;
            }
        }
    }
    public static int search(String s)
    {
        for(int i = 0; i < produce.size(); i++)
        {
            if(produce.get(i).getName().indexOf(s) > -1) return i;
        }
        return -1;
    }
}