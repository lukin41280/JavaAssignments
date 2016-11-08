import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] split1 = new String[q1.getInfo().length()];
        split1 = q1.getInfo().split(" ");
        String lastWord1 = split1[split1.length-1];
        String[] split2 = new String[q2.getInfo().length()];
        split2 = q2.getInfo().split(" ");
        String lastWord2 = split2[split2.length-1];

        if (lastWord1.compareTo(lastWord2) < 0){
	        return -1; 
	       }
	    if (lastWord1.compareTo(lastWord2) > 0){
	        return 1;
	       }
	    if (lastWord1.compareTo(lastWord2) == 0){
	        if(q1.getMagnitude() < q2.getMagnitude()){
	            return -1;
	        }
	        if(q1.getMagnitude() > q2.getMagnitude()){
	            return 1;
	        }
	        return 0;
	    } 
	    return 0;       
   }
}
