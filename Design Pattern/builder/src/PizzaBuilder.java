

public class PizzaBuilder{
    String taille, fromage;
    boolean olive,jambon;

    PizzaBuilder setTaille(String t){
        taille = t;
        return this;
    }
    PizzaBuilder setFromage(String f){
        fromage = f;
        return this;
    }
    PizzaBuilder setOlive(boolean o){
        olive = o;
        return this;
    }
    PizzaBuilder setFromage(boolean f){
        fromage = f;
        return this;
    }

    PizzaBuilder build(){
        return new Pizza(builder.this);
    }
}