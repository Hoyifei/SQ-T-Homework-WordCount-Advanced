package test.com.wordcount;
import java.util.Comparator;
public class PairComparator implements Comparator<WordCountPair>{
    @Override
    public int compare(WordCountPair a, WordCountPair b){
        if(a.count>b.count){
            return(-1);
        }else if(a.count<b.count){
            return(1);
        }else{
            return(a.word.compareTo(b.word));
        }
    }
}
