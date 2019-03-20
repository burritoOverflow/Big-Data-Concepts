import java.util.*;
import java.io.*;

public class TreeMapArrayList {

  public static void main(String[] args) throws FileNotFoundException, IOException {

    TreeMap<Integer, ArrayList<Integer>> TM = new TreeMap<Integer, ArrayList<Integer>>();

    BufferedReader br = new BufferedReader(new FileReader("SampleText.txt"));

    String st;
    // [0] is the num, [1] is the value
    // if [0] is not in TreeMap, add to TreeMap
    // with 0 as key, and the [1] as the first value in the treemap
    // else, append the [1] to [0]'s value

    while ((st = br.readLine()) != null) {
      String[] splitString = st.split("\t");

      Integer val0 = Integer.parseInt(splitString[0]);
      Integer val1 = Integer.parseInt(splitString[1]);

      // check if exists
      ArrayList<Integer> valList = TM.get(val0);

      // if nothing is returned, add it to the map.
      // else, if the value is found in the map, append the val1 to the list
      if (valList == null) {
        valList = new ArrayList<Integer>();
        valList.add(val1);
        TM.put(val0, valList);
      } else {
        valList.add(val1);
      }
    }

    Set set = TM.entrySet();
    Iterator it = set.iterator();
    while (it.hasNext()) {
      Map.Entry ME = (Map.Entry)it.next();
      // trust but verify
      System.out.print(ME.getKey() + ": " + ME.getValue() + "\n\n");
    }
  }
}
