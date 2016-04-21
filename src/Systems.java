import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 40095 on 4/20/16.
 */
public class Systems {
    ArrayList<MFZ_System> onlineSystems;  //the 4 systems that exist NOT!!! how many of each system exist
    HashMap<String, Integer> systemsByType = new HashMap(6); //How many of each system exist

    Systems(int[] s) {
        /*
        s[i] represents # of systems of the type i
           i  0  |  1  |  2  |  3 |  4  |  5
        type DEF | MVM | MLE | DF | ART | COM
        */
        onlineSystems = new ArrayList<MFZ_System>(4);


        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i]; j++) {
                MFZ_System MFZSystem = new MFZ_System(i);
                onlineSystems.add(MFZSystem);
                if (MFZSystem.getType().equals("DEF") || MFZSystem.getType().equals("MVM") || MFZSystem.getType().equals("COM"))
                    MFZSystem.setDiceType("d6");
                else { //System is a weapon system
                    if (j == 0) //this is the first weapon with this range
                        MFZSystem.setDiceType("2d6");
                    else //there is already a weapon in this range
                        MFZSystem.setDiceType("d8");
                }
            }
        }
        for (int index = 0; index < onlineSystems.size(); index++) {
            systemsByType.put(onlineSystems.get(index).getType(), systemsByType.getOrDefault(onlineSystems.get(index).getType
                    (), new Integer(0)) + 1);
        }
    }

    int getNumOfSystemByType(String type) {
        if(systemsByType.get(type) == null)
            return 0;
        else
            return systemsByType.get(type);
    }


}
