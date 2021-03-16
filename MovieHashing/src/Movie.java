
public class Movie {
    private String title;
    private int    yearReleased;
    private int    runningTime;
    private int	   hashKey;
    
    public Movie() {
        this ("", 0, 0);
    }
    
    public Movie (String title, int yearReleased, int runningTime) {
        this.title        = title.trim();
        this.yearReleased = yearReleased;
        this.runningTime  = runningTime;
        
        int[] codes = getFirstLetterCodes();
        hashKey = codes[0];
        for(int i = 1; i < codes.length; i++) {
        	hashKey = (hashKey * 128) + codes[i]; 
        }
        if(hashKey < 0) {
        	hashKey *= -1;
        }
    }
    
    public Movie (Movie m) {
        title        = m.title.trim();
        yearReleased = m.yearReleased;
        runningTime  = m.runningTime;
        hashKey      = m.hashKey;
    }
    
    public int getYearReleased() {
        return yearReleased;
    }
    
    public int getRunningTime() {
        return runningTime;
    }
    
    public String getTitle() {
        return title;
    }
    
    public int[] getFirstLetterCodes() {
        String [] words;
        int [] result;
        words = title.split(" ");
        result = new int[words.length];
        for (int wordNbr = 0; wordNbr < words.length; wordNbr++) {
            if (words.length != 0 && words[wordNbr].length() > 0) {
                result[wordNbr] = Character.valueOf(words[wordNbr].charAt(0));
            }
        }
        return result;
    }
    
    public int getHashKey() {
        return hashKey;
    }
    
    public String toString() {
        return "\""+title + "\" Released in : " + yearReleased + " Running Time : " + runningTime + " minutes";
    }

}