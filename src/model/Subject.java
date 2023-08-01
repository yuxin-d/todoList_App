package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers;

    public Subject(){
        observers = new ArrayList<>();
    }

    public void addObserver(Observer o){
        observers.add(o);
    }


    public void notifyObservers(Item i){
        for(Observer o: observers){
            o.update(i);
        }
    }

}
