package pp.game;

public class BGM {
	
	public double loopStart = -1;
    public double loopEnd = -1;
    
    public String filename;
    
    public BGM(String filename){
    	this.filename = filename;
    }
    public BGM(String filename, double loopStart, double loopEnd){
    	this.filename = filename;
    	this.loopStart = loopStart;
    	this.loopEnd = loopEnd;
    }
}
