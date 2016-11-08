
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace
{
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int maxIdx = from;
        for (int i=from+1; i< quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        for (int i=0; i< 50; i++) {
            int maxIdx = getLargestDepth(in,i);
            
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
            System.out.println("Did pass "+i);
        }   
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        
        for (int k = 1; k < numSorted; k++){
            if (quakeData.get(k-1).getMagnitude() > quakeData.get(k).getMagnitude()){
                QuakeEntry first = quakeData.get(k-1);
                QuakeEntry second = quakeData.get(k);
                quakeData.set(k-1, second);
                quakeData.set(k, first);
            }           
        }        
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for (int k = 0; k < in.size(); k++){
            onePassBubbleSort(in, in.size());
            //for (QuakeEntry qe: in) { 
                //System.out.println(qe);
            //} 
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        boolean checker = true;
        for (int k = 1; k < quakes.size(); k++){
            if(quakes.get(k-1).getMagnitude() <= quakes.get(k).getMagnitude()){ 
                continue;
                
            }
            else{
                checker = false;
                break;
            }
        }
        return checker;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        int count = 0;
        for (int k = 0; k < in.size(); k++){
            count = k + 1;
            onePassBubbleSort(in, in.size());
            checkInSortedOrder(in);
            //System.out.print(count+") "+"List is sorted? "+checkInSortedOrder(in)+"\n");            
            if (checkInSortedOrder(in) == true){
                break;
            }
        }
        System.out.println("**sortByMag..WithBubbleSortWithCheck took "+count+" passes to sort**");
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int count = 0;
        for (int k = 0; k< in.size(); k++){
            
            count = k+1;
            int minIdx = getSmallestMagnitude(in,k);
            QuakeEntry qi = in.get(k);
            QuakeEntry qmin = in.get(minIdx);
            in.set(k,qmin);
            in.set(minIdx,qi);
        
            checkInSortedOrder(in);
            if (checkInSortedOrder(in) == true){
                break;
            }
            
        }
        System.out.println("**sortByMagnitudeWithCheck took "+count+" passes to complete**");
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
        System.out.println("read data for "+list.size()+" quakes"); 
        //for (QuakeEntry qe: list) { 
            //System.out.println(qe);
        //} 
        //sortByMagnitude(list);
        sortByLargestDepth(list);
        //System.out.println(list.get(50));
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        System.out.println("EathQuakes in sorted order:");
        int count = 1;
        for (QuakeEntry qe: list) { 
            System.out.println(count + " "+qe);
            count++;
        } 
        
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
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
}
