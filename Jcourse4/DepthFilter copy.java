
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{
    private double deepMin;
    private double deepMax;
    
    public DepthFilter(double min, double max) { 
        deepMin = min;
        deepMax = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getDepth() >= deepMin && qe.getDepth() <= deepMax; 
    } 
    
    public String getName(MatchAllFilter maf){
        return " Depth ";
    }

}
