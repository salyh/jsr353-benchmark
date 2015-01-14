package de.saly.json.jsr353.benchmark.data.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collection {

    private List<Simple> list;
    private Map<String, Simple> map;
    
    public void init() {
        list = new ArrayList<Simple>();
        Simple s = new Simple();
        s.init();
        
        list.add(s);
        list.add(s);
        list.add(s);
        list.add(s);
        list.add(s);
        
        
        map = new HashMap<String, Simple>();
        map.put("1", s);
        map.put("2", s);
        map.put("3", s);
        map.put("4", s);
        map.put("5", s);
        
    }
    
    public Collection() {
    }

    public List<Simple> getList() {
        return list;
    }

    public void setList(List<Simple> list) {
        this.list = list;
    }

    public Map<String, Simple> getMap() {
        return map;
    }

    public void setMap(Map<String, Simple> map) {
        this.map = map;
    }

}
