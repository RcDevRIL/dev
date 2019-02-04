package designPattern;
import main.java.com.cesi.Singleton;
/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Singleton s1 = Singleton.getInstance();
        System.out.println(s1.toString());
    }
}
