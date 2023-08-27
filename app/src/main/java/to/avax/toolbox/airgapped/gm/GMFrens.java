package to.avax.toolbox.airgapped.gm;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class GMFrens {
    public static void main(String[] args) {
        char m = 'A';
        List<String> gms = new ArrayList<>();
        for (; m <= 'Z'; m++) {
            gms.add("G" + m);
        }
        Collections.shuffle(gms);
        System.out.println(String.join(" ", gms) + " frens");
    }
}
