package main.java.com.cesi;

public class Singleton{

    private static Singleton instance = new Singleton();

    private Singleton() {
        
    }

    public static Singleton getInstance(){
        return instance;
    }

    public String toString(){
        return ("singletooooon");
    }
}
