package pp.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import pp.gui.LevelCleared;
import pp.gui.LevelClock;

public class Data implements Serializable {
    
    private static final long serialVersionUID = 5264596641407544438L;
    
    public LevelData[] levels = new LevelData[40];
    //public int[] player_record_array = new int[40];
    public boolean unlocked_double_jump = false;
    public String username = "default";
    public int total_time_played = 0;
    public int total_jumps = 0;
    public int total_platforms_activated = 0;
    public int total_played = 0;
    
    public Data() {
    	// Create level data array to store times
        for (int i = 0; i < levels.length; ++i) {
            levels[i] = new LevelData();
        }
        
        // Check if save file exists, load it if so, create one if not
        File f = new File("myFile.txt");
        if(f.exists() && !f.isDirectory()) {
        	
            // Load save file
        	readFromFile();
        	LevelCleared.loadImages(); // this is a hack; change when all images are loaded
        } else {
        	
        	// Create save file for the first time
        	saveToFile();
            unlockLevel(0);
        }
    }
    
    public void saveToFile(){
        try {
        	FileOutputStream fos = new FileOutputStream(new File("myFile.txt"));
    		ObjectOutputStream oos = new ObjectOutputStream(fos);
    		oos.writeObject(this);
    		oos.close();
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void readFromFile(){
        try {
        	FileInputStream fin = new FileInputStream ("myFile.txt");
        	ObjectInputStream oin = new ObjectInputStream(fin);
        	Data d = (Data)oin.readObject();
        	oin.close();
        	fin.close();
        	
        	//this.player_record_array = d.player_record_array;
        	this.levels = d.levels;
        	this.total_jumps = d.total_jumps;
        	this.total_platforms_activated = d.total_platforms_activated;
        	this.total_played = d.total_played;
        	this.total_time_played = d.total_time_played;
    //        	this.unlocked_double_jump = d.unlocked_double_jump;
        	this.unlocked_double_jump = false;
        	this.username = d.username;
        	
        } catch (IOException ex) {
        	ex.printStackTrace();
        } catch (ClassNotFoundException ex) {}
    }
    
    public void lockAll() {
        for (LevelData level : levels) {
            level.lock();
        }
    }
    
    public void unlockAll() {
        for (LevelData level : levels) {
            level.unlock();
        }
    }
    
    public void unlockLevel(int level_num) {
        try {
            levels[level_num].unlock();
        }
        catch (Exception e) {
            System.out.println("ERROR: LevelData array hasn't been initialized, or level number " + level_num + " doesn't exist.");
        }
    }
    
    public void completeLevel(int level_num) {
        try {
            levels[level_num].complete();
        }
        catch (Exception e) {
            System.out.println("ERROR: LevelData array hasn't been initialized, or level number " + level_num + " doesn't exist.");
        }
    }
    
    public void finishLevel(int level_num) {
        try {
            levels[level_num].finish();
        }
        catch (Exception e) {
            System.out.println("ERROR: LevelData array hasn't been initialized, or level number " + level_num + " doesn't exist.");
        }
    }
    
    public void setMostActivated(int level_num, int num_activated) {
        try {
            levels[level_num].most_activated = num_activated;
        }
        catch (Exception e) {
            System.out.println("ERROR: LevelData array hasn't been initialized, or level number " + level_num + " doesn't exist.");
        }
    }
    
    public void setNumPlatforms(int level_num, int num_platforms) {
        try {
            levels[level_num].num_platforms = num_platforms;
        }
        catch (Exception e) {
            System.out.println("ERROR: LevelData array hasn't been initialized, or level number " + level_num + " doesn't exist.");
        }
    }
    
    public void setRecordTime(int level_num, long player_record) {
        try {
            levels[level_num].player_record = player_record;
        }
        catch (Exception e) {
            System.out.println("ERROR: LevelData array hasn't been initialized, or level number " + level_num + " doesn't exist.");
        }
    }
    
    public String getRecordTime(int level_num) {
        if (levels[level_num].player_record == 999999999) {
            return "??:??:??";
        }
        return LevelClock.getClockTime(levels[level_num].player_record * 1000000);
    }
    
    public boolean getNotFinished(int level_num) {
        boolean notfinished = true;
        try {
            if (levels[level_num].completed || levels[level_num].finished || !levels[level_num].locked) {
                notfinished = false;
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR: Level " + level_num + " doesn't exist.");
        }
        return notfinished;
    }
    
    public boolean getLocked(int level_num) {
        try {
            return levels[level_num].locked;
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR: Level " + level_num + " doesn't exist.");
            return true;
        }
    }
    
    public boolean getBronze(int level_num) {
        try {
            return levels[level_num].bronze;
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR: Level " + level_num + " doesn't exist.");
            return false;
        }
    }
    
    public boolean getSilver(int level_num) {
        try {
            return levels[level_num].silver;
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR: Level " + level_num + " doesn't exist.");
            return false;
        }
    }
    
    public boolean getGold(int level_num) {
        try {
            return levels[level_num].gold;
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR: Level " + level_num + " doesn't exist.");
            return false;
        }
    }
    
    public int getMostActivated(int level_num) {
        try {
            return levels[level_num].most_activated;
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR: Level " + level_num + " doesn't exist.");
            return -1;
        }
    }
    
    public int getNumPlatforms(int level_num) {
        try {
            return levels[level_num].num_platforms;
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR: Level " + level_num + " doesn't exist.");
            return -1;
        }
    }
    
    public boolean attemptSetRecord(int level_num, long milliseconds) {
        boolean new_record = false;
        try {
            if (milliseconds < levels[level_num].player_record) {
                setRecordTime(level_num, milliseconds);
                new_record = true;
            }
            return new_record;
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR: level " + level_num + " doesn't exist.");
            return false;
        }
    }
    
    public boolean attemptBeatGold(int level_num) {
        boolean value;
        if (levels[level_num].gold) {
            return false;
        }
        levels[level_num].gold = value = levels[level_num].player_record < LevelClock.getGoldTime(level_num);
        return value;
    }
    
    public boolean attemptBeatSilver(int level_num) {
        boolean value;
        if (levels[level_num].silver) {
            return false;
        }
        levels[level_num].silver = value = levels[level_num].player_record < LevelClock.getSilverTime(level_num);
        return value;
    }
    
    public boolean attemptBeatBronze(int level_num) {
        boolean value;
        if (levels[level_num].bronze) {
            return false;
        }
        levels[level_num].bronze = value = levels[level_num].player_record < LevelClock.getBronzeTime(level_num);
        return value;
    }

    // NOTE: This function is obsolete.  It was used to load player progress from
    // the website database.  player_record_array was initialized in GameFrame.loadFromHTMLParams().
//    private void readTimeFromServer() {
//        LevelCleared.loadImages();
//        for (int i = 0; i < 40; ++i) {
//            if (player_record_array[i] == 0) continue;
//            levels[i].player_record = player_record_array[i];
//            levels[i].finished = true;
//            System.out.println("record= " + levels[i].player_record + " bronze time=" + LevelClock.getBronzeTime(i));
//            if (levels[i].player_record <= LevelClock.getBronzeTime(i)) {
//                levels[i].bronze = true;
//                levels[i].finished = true;
//                levels[i].locked = false;
//                try {
//                    levels[i + 1].locked = false;
//                }
//                catch (Exception e) {
//                    // empty catch block
//                }
//            }
//            if (levels[i].player_record <= LevelClock.getSilverTime(i)) {
//                levels[i].silver = true;
//            }
//            if (levels[i].player_record > LevelClock.getGoldTime(i)) continue;
//            levels[i].gold = true;
//            levels[i].completed = true;
//        }
//    }

    // NOTE: This function is obsolete.  It was used to write player progress to
    // the website database.
//    private static void saveToPHP() {
//        try {
//            String line;
//            String encode_user = URLEncoder.encode(username, "UTF-8");
//            String[] encode_records = new String[40];
//            for (int i = 0; i < levels.length; ++i) {
//                String value_of = String.valueOf(Data.levels[i].player_record);
//                encode_records[i] = URLEncoder.encode(value_of, "UTF-8");
//            }
//            URL url = new URL("http://www.retrogrames.com/platform_panic/save.php");
//            URLConnection conn = url.openConnection();
//            conn.setDoOutput(true);
//            conn.getOutputStream();
//            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//            String write_string = "username=" + encode_user;
//            for (int i2 = 0; i2 < levels.length; ++i2) {
//                write_string = write_string + "&time_level" + i2 + "=" + encode_records[i2];
//            }
//            write_string = write_string + "&time_total=" + URLEncoder.encode(String.valueOf(total_time_played), "UTF-8");
//            write_string = write_string + "&jump_total=" + URLEncoder.encode(String.valueOf(total_jumps), "UTF-8");
//            write_string = write_string + "&platform_total=" + URLEncoder.encode(String.valueOf(total_platforms_activated), "UTF-8");
//            write_string = write_string + "&played_total=" + URLEncoder.encode(String.valueOf(total_played), "UTF-8");
//            wr.write(write_string);
//            wr.flush();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            while ((line = rd.readLine()) != null) {
//                System.out.println(line);
//            }
//            wr.close();
//            rd.close();
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }
//    }

    public class LevelData implements Serializable {
    	
        private static final long serialVersionUID = 542135114341335L;
    	
        public boolean locked = true;
        public boolean finished = false;
        public boolean completed = false;
        public int num_platforms = 0;
        public int most_activated = 0;
        public long player_record = 999999999;
        public String player_record_string = "xx:xx:xx";
        public boolean bronze = false;
        public boolean silver = false;
        public boolean gold = false;

        public void lock() {
            this.locked = true;
            this.finished = false;
            this.completed = false;
        }

        public void unlock() {
            this.locked = false;
            this.finished = false;
            this.completed = false;
        }

        public void finish() {
            this.locked = false;
            this.finished = true;
            this.completed = false;
        }

        public void complete() {
            this.locked = false;
            this.finished = true;
            this.completed = true;
        }
    }

}

