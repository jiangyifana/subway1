package Subway;

import java.util.*;

public class Station {
    private String name;  //站点名
    private List<String> line = new ArrayList<String>();  //所在线路（换乘站有多条）
    private List<Station> linkStations = new ArrayList<Station>();  //与之相邻的站点

    public Station(String name, String line) {
        this.name = name;
        this.line.add(line);
    }

    public Station(String name) {
        this.name = name;
    }

    public Station() {

    }

    //这里没有放get&set和构造器的代码

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLine() {
        return line;
    }

    public void setLine(List<String> line) {
        this.line = line;
    }

    public List<Station> getLinkStations() {
        return linkStations;
    }

    public void setLinkStations(List<Station> linkStations) {
        this.linkStations = linkStations;
    }
}
