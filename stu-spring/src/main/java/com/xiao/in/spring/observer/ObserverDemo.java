package com.xiao.in.spring.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ObserverDemo.java
 * @Description
 * @createTime 2021年02月21日 11:40:00
 */
public class ObserverDemo {

    public static void main(String[] args) {
        EventObservable eventObservable = new EventObservable();
        eventObservable.addObserver(new EventObserver());
        eventObservable.notifyObservers("发送某某消息");
    }

    static class EventObservable extends Observable {
        @Override
        public void notifyObservers(Object arg) {
            //需要将事件打开
            super.setChanged();
            super.notifyObservers(arg);
            super.clearChanged();
        }
    }

    static class EventObserver implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            System.out.println("收到事件： " + arg);
        }
    }
}
