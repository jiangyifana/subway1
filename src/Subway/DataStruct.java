package Subway;

import java.io.*;
import java.util.*;

public class DataStruct {
    public static LinkedHashSet<List<Station>> lineSet = new LinkedHashSet<List<Station>>();   //线路集合

    public DataStruct(String pathname) throws IOException {

        File filename = new File("subway/" + pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String content = "";
        content = br.readLine();
        int linenum = Integer.parseInt(content.trim());
        for (int i = 0; i < linenum; i++) {
            content = br.readLine();
            List<Station> line = new ArrayList<Station>();
            String[] linearr = content.split(" ");
            String linename = linearr[0];
            for (int j = 1; j < linearr.length; j++) {
                int flag = 0;
                for (List<Station> l : lineSet) {
                    for (int k = 0; k < l.size(); k++) {
                        if (l.get(k).getName().equals(linearr[j])) {
                            List<String> newline = l.get(k).getLine();
                            newline.add(linename);
                            l.get(k).setLine(newline);
                            line.add(l.get(k));
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 1)
                        break;
                }
                if (j == linearr.length - 1 && linearr[j].equals(linearr[1])) {  //处理环线
                    line.get(0).getLinkStations().add(line.get(line.size() - 1));
                    line.get(line.size() - 1).getLinkStations().add(line.get(0));
                    flag = 1;
                }
                if (flag == 0)
                    line.add(new Station(linearr[j], linename));
            }
            for (int j = 0; j < line.size(); j++) {  //初始化每个车站相邻的车站
                List<Station> newlinkStations = line.get(j).getLinkStations();
                if (j == 0) {
                    newlinkStations.add(line.get(j + 1));
                    line.get(j).setLinkStations(newlinkStations);
                } else if (j == line.size() - 1) {
                    newlinkStations.add(line.get(j - 1));
                    line.get(j).setLinkStations(newlinkStations);
                } else {
                    newlinkStations.add(line.get(j + 1));
                    newlinkStations.add(line.get(j - 1));
                    line.get(j).setLinkStations(newlinkStations);
                }
            }
            lineSet.add(line);
        }
        br.close();
    }
}
