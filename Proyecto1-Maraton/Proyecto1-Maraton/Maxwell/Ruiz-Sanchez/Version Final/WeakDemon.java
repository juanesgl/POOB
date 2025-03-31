
/**
 * Write a description of class WeakDemon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WeakDemon extends Demon {
    private boolean used = false;
    public WeakDemon(int d, int w) {
        super(d, w, "weak");
        setGateOpen(true);
    }
    public boolean canPass(Particle p) {
        if(isGateOpen() && !used) {
            used = true;
            makeInvisible();
            return true;
        }
        return false;
    }
}

