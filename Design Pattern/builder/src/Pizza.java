import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class Pizza {
    public String taille, fromage;
    public boolean olive,jambon;

    Pizza(PizzaBuilder builder){
        taille = builder.taille;
        fromage = builder.fromage;
        olive = builder.olive;
        jambon = builder.jambon;
    }

    @Override
    public String toString(){
        return "Pizza: "+this.taille+";"+this.fromage+";"+this.olive+";"+this.fromage+"\n BON APP";
    }
}