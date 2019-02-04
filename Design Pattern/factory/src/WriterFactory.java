
public class WriterFactory{
    public static Writer getWriter(String mode) {
        if(mode.equals("file")){
            return new FileWriter();
        }
        else if(mode.equals("db")){
            return new DatabaseWriter();
        }
        return null;
    }
}