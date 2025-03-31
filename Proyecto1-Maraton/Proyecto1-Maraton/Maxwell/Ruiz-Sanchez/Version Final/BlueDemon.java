
/**
 * Write a description of class BlueDemon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BlueDemon extends Demon {
    public BlueDemon(int d, int w) {
        super(d, w, "blue");
        // Forzamos que la puerta siempre esté abierta:
        setGateOpen(true);
    }
    
    @Override
    public void setGateOpen(boolean state) {
        // Ignoramos el parámetro y siempre dejamos la puerta abierta.
        super.setGateOpen(true);
    }
    
    public boolean canPass(Particle p) {
        return isGateOpen() && p.isBlue();
    }
}



