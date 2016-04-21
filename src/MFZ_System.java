/**
 * Created by 40095 on 4/20/16.
 */
public class MFZ_System {
    boolean active;
    String diceType;
    int roll;
    String type;

    MFZ_System(int t) {
        active = true;
        type = SystemType.types[t];
    }

    public String getDiceType() {
        return diceType;
    }

    public void setDiceType(String diceType) {
        this.diceType = diceType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    public int getRoll() {
        if (isActive()) {
            if (diceType.equals("d6")) {
                roll = 1 + (int) (Math.random() * ((6 - 1) + 1));
            }
            if (diceType.equals("2d6")) {
                roll = (1 + (int) (Math.random() * ((6 - 1) + 1)) + (1 + (int) (Math.random() * ((6 - 1) + 1))));
            }
            if (diceType.equals("d8")) {
                roll = 1 + (int) (Math.random() * ((8 - 1) + 1));
            }
        } else {
            roll = 0;
        }
        return roll;
    }

    public String toString() {
        String desc = type;
        if (!active) {
            desc += " (destroyed)";
        } else {
            desc += " (active)";
        }
        return desc;
    }

    public String getType() {
        return type;
    }
}
