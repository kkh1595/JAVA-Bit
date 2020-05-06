package scoreProject;

import java.io.Serializable;
import java.text.DecimalFormat;


public class ScoreDTO implements Comparable<ScoreDTO>,Serializable{
    private int id,kor,eng,math,tot;
    private String name;
    private double avg;
    private DecimalFormat df= new DecimalFormat("0.##");
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getKor() {
        return kor;
    }
    public void setKor(int kor) {
        this.kor = kor;
    }
    public int getEng() {
        return eng;
    }
    public void setEng(int eng) {
        this.eng = eng;
    }
    public int getMath() {
        return math;
    }
    public void setMath(int math) {
        this.math = math;
    }
    public int getTot() {
        return tot;
    }
    public void calc(){
        tot=kor+eng+math;
        avg=tot/3.0; 
        avg=Double.parseDouble(df.format(avg));
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getAvg() {
        return avg;
    }
    @Override
    public int compareTo(ScoreDTO dto) {
        return this.tot>dto.tot ? -1:1; 
    }
}
