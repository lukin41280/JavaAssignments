import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2
{
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        EarthQuakeClient2 eqc = new EarthQuakeClient2(); 
        Filter f1 = new MagnitudeFilter(3.5, 4.5); 
        Filter f2 = new DepthFilter(-55000.0, -20000.0);
        Location jap = new Location(39.7392, -104.9903);
        //Filter f1 = new DistanceFilter(jap, 1000000);
        //Filter f2 = new PhraseFilter("end", "a");
        ArrayList<QuakeEntry> m7  = eqc.filter(list, f1); 
        ArrayList<QuakeEntry> m8 = eqc.filter(m7, f2);
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        } 
        System.out.println("Quakes found "+m8.size());
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        Filter m = new MagnitudeFilter(1.0,4.0);
        maf.addFilter(m);
        Filter d = new DepthFilter(-180000.0, -30000.0);
        maf.addFilter(d);
        Filter p = new PhraseFilter("any", "o");
        maf.addFilter(p);
        ArrayList<QuakeEntry> flist = filter(list, maf);
        for (QuakeEntry qe : flist){
            System.out.println(qe);
        }
        System.out.println("Filters used are: "+ maf.getName(maf));
        System.out.println("Quakes found "+flist.size());
    }
    
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        Filter m = new MagnitudeFilter(0.0,5.0);
        maf.addFilter(m);
        Location okl = new Location(55.7308,9.1153);
        Filter d = new DistanceFilter(okl, 3000000);
        maf.addFilter(d);
        Filter p = new PhraseFilter("any", "e");
        maf.addFilter(p);
        ArrayList<QuakeEntry> flist = filter(list, maf);
        for (QuakeEntry qe : flist){
            System.out.println(qe);
        }
        System.out.println("Quakes found "+flist.size());
    }
        
}
