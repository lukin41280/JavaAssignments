
/**
 * Write a description of D here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location locale;
    private double distMax;
    
    public DistanceFilter(Location loc, double max) { 
        locale = loc;
        distMax = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return (qe.getLocation().distanceTo(locale)) < distMax; 
    } 

    public String getName(MatchAllFilter maf){
        return " Distance ";
    }
}
