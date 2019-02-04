

public class Main{
   /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World  !");
        Pizza p = new PizzaBuilder
            .setTaille("GRANDE")
            .setFromage("MOZZA")
            .setOlive(false)
            .setJambon(true).build();

        System.out.println(p.toString());
    }
}