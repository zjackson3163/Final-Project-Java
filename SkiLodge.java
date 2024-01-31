import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; 

public class SkiLodge{
    public static void main (String [] args)
    {
        //initializing variables and arraylists
        String line;
        String result = "";
        String resultCost = "0";
        boolean found = false;
        ArrayList<String> services = new ArrayList<String>();
        ArrayList<Integer> servicesTotal = new ArrayList<Integer>();

        //placeholders to add in array list, without it, it gave me an index error
        services.add("");
        servicesTotal.add(0);

        //try catch for file exceptions
        //file reader
        try{
            BufferedReader br = new BufferedReader(new FileReader("SkiSales.txt"));
            while ((line = br.readLine()) != null)
            {
                //if line starts with "Service Sold: " get the word/service given after, set to "result" var
                //if service in the "result" var is found in the arraylist set found to true, if not add it to array, add cost placeholder
                if (line.contains("Service Sold: "))
                {
                    result = line.substring(14);
                    for (int i = 0; i < services.size(); i++)
                    {
                        if (services.get(i).equals(result))
                        {
                            found = true;
                        }
                    }
                    if (found == false)
                    {
                        services.add(result);
                        servicesTotal.add(0);
                    }
                    found = false;
                }

                //if line starts with "Cost: " get the amount/cost given after, set to "resultCost" var
                //find the service last set in "result" var in "services" arraylist and set the "serviceCost" array value of that index to the value of the current value + the new found value   
                if (line.contains("Amount: "))
                {
                    resultCost = line.substring(8);
                    for (int i = 0; i < services.size(); i++)
                    {
                        if (services.get(i).equals(result))
                        {
                            int newTotal = servicesTotal.get(i) + Integer.parseInt(resultCost);
                            servicesTotal.set(i ,newTotal);
                        }
                    }
                }
            }
            br.close();
        }
        catch (FileNotFoundException exception){
            return;
        }
        catch (IOException exception){
            return;
        }
        

        //prints final arraylists
        System.out.println("Services and their respective service totals: ");
        for (int i = 1; i < services.size(); i++)
        {
            System.out.println("\t-" + services.get(i) + ": " + servicesTotal.get(i));
        }
    }
}