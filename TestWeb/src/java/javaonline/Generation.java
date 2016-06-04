/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaonline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.awt.Point;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author David
 */
public class Generation {
    
    public String getJSONPNextGeneration(int m , int n, ArrayList<Point> data) throws  JSONException
    {
        ArrayList<Point> r = getNextGeneration(m, n, data);
        
        JSONArray newPair;
        JSONArray newPairs = new JSONArray();
        for(int i=0; i<r.size(); i++){
            newPair = new JSONArray();
            newPair.put(r.get(i).x);
            newPair.put(r.get(i).y);
            newPairs.put(newPair);
        }
        return newPairs.toString();
    }
    
    public ArrayList<Point> getNextGeneration(int m, int n, ArrayList<Point> data){
        HashMap map = new HashMap<Point,Boolean>();
        for(int i=0; i<data.size(); i++){
            if(IsValid(m, n, data.get(i).x, data.get(i).y)){
                if(map.containsKey(data.get(i))){
                    map.replace(data.get(i),true);
                }else{
                    map.put(data.get(i),true);
                }
                AddNeighbors(m,n,data.get(i),map); 
            }
        }
        
        ArrayList resul = new ArrayList<Point>();
        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry)entries.next();
            if(FindMarkedNeighbors((Point)entry.getKey(),(Boolean)entry.getValue(),map)){
                resul.add((Point)entry.getKey());
            }
        }
        return resul;
    }
    
    private boolean FindMarkedNeighbors(Point p, Boolean b, HashMap<Point,Boolean> map){
        int count = 0;
        if (map.get(new Point(p.x-1,p.y-1)) != null){
            count += map.get(new Point(p.x-1,p.y-1)) ? 1 : 0;
        }
        if (map.get(new Point(p.x,p.y-1)) != null){
            count += map.get(new Point(p.x,p.y-1)) ? 1 : 0;
        }
        if (map.get(new Point(p.x-1,p.y)) != null){
            count += map.get(new Point(p.x-1,p.y)) ? 1 : 0;
        }
        if (map.get(new Point(p.x+1,p.y-1)) != null){
            count += map.get(new Point(p.x+1,p.y-1)) ? 1 : 0;
        }
        if (map.get(new Point(p.x+1,p.y)) != null){
            count += map.get(new Point(p.x+1,p.y)) ? 1 : 0;
        }
        if (map.get(new Point(p.x+1,p.y+1)) != null){
            count += map.get(new Point(p.x+1,p.y+1)) ? 1 : 0;
        }
        if (map.get(new Point(p.x,p.y+1)) != null){
            count += map.get(new Point(p.x,p.y+1)) ? 1 : 0;
        }
        if (map.get(new Point(p.x-1,p.y+1)) != null){
            count += map.get(new Point(p.x-1,p.y+1)) ? 1 : 0;
        }
        //b == 2 then 'stays the same'
        if(count < 2 || count > 3){
            b = false;
        }
        if(count == 3){
            b = true;
        }
        return b;
    }
    
    private void AddNeighbors(int m, int n, Point p, HashMap<Point,Boolean> map){
        if (IsValid(m,n,p.x-1,p.y-1)){
            map.putIfAbsent(new Point(p.x-1,p.y-1), false);
        }
        if (IsValid(m,n,p.x,p.y-1)){
            map.putIfAbsent(new Point(p.x,p.y-1), false);
        }
        if (IsValid(m,n,p.x-1,p.y)){
            map.putIfAbsent(new Point(p.x-1,p.y), false);
        }
        if (IsValid(m,n,p.x+1,p.y-1)){
            map.putIfAbsent(new Point(p.x+1,p.y-1), false);
        }
        if (IsValid(m,n,p.x+1,p.y)){
            map.putIfAbsent(new Point(p.x+1,p.y), false);
        }
        if (IsValid(m,n,p.x+1,p.y+1)){
            map.putIfAbsent(new Point(p.x+1,p.y+1), false);
        }
        if (IsValid(m,n,p.x,p.y+1)){
            map.putIfAbsent(new Point(p.x,p.y+1), false);
        }
        if (IsValid(m,n,p.x-1,p.y+1)){
            map.putIfAbsent(new Point(p.x-1,p.y+1), false);
        }
    }
    
    private boolean IsValid(int m, int n, int x, int y){
        if(0<=x && x<=(m-1) && 0<=y && y<=(n-1)){
            return true;
        }
        return false;
    }
}
