
/**
 * Write a description of p here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String whereType;
    private String phraseType;
    
    public PhraseFilter(String where, String phrase) { 
        whereType = where;
        phraseType = phrase;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return (whereType.equals("start") && qe.getInfo().startsWith(phraseType)) ||
            (whereType.equals("end") && qe.getInfo().endsWith(phraseType)) ||
            (whereType.equals("any") && qe.getInfo().contains(phraseType));
    } 
    
    public String getName(MatchAllFilter maf){
        return " Phrase ";
    }

}
