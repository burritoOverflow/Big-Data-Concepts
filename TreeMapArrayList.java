// testing this slow implementation

import java.util.*;
import java.io.*;

public class TreeMapArrayList {

  public static void main(String[] args) throws FileNotFoundException, IOException {

    TreeMap<Integer, ArrayList<Integer>> TM = new TreeMap<Integer, ArrayList<Integer>>();
    TreeMap<Integer, ArrayList<Integer>> TM2 = new TreeMap<Integer, ArrayList<Integer>>();
    BufferedReader br = new BufferedReader(new FileReader("SampleText.txt"));

    // [0] is the num, [1] is the value
    // if [0] is not in TreeMap, add to TreeMap
    // with 0 as key, and the [1] as the first value in the treemap
    // else, append the [1] to [0]'s value

    String st;
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

      //TM.forEach((k, v) -> System.out.println("UserID: " + k + "\nValue: " + v + "\n\n"));
      // populate the second TM; this one will contain UserID: Friend List
      TM.forEach((k,v) -> {
                TM.forEach((k2,v2) -> {
                  // determine if k and k2 are friends
                    boolean isFriend = (v2.stream().filter(item -> v.contains(item.intValue())).findAny().orElse(null)) == null ? false : true;
                    if(isFriend) {
                      // if in map2, append value to list,
                      // else create entry with k as key, and k2 as first value
                      // in list
                        if(TM2.containsKey(k)) {
                            TM2.get(k).add(k2);
                          } else {
                            ArrayList<Integer> friendList = new ArrayList<Integer>();
                            friendList.add(k2);
                            TM2.put(k, friendList);
                          }
                        }
                      });
                    });

      // TM2.forEach((k, v) -> System.out.println("UserID: " + k + "\nFriend List: " + v + "\n\n"));

      // TM2.forEach((k, v) -> {
      //     ArrayList<Integer> temp = TM2.get(k);
      //     System.out.println("List for Key: " + k);
      //     for (Integer tempVal : temp)
      //     {
      //       System.out.println("Key: "+ k + " Value: " + tempVal);
      //     }
      //     System.out.println("\n");
      // });

    }
  }
