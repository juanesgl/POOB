import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Comparator;
    
    /**
     * La clase MaxwellContainer representa el contenedor en la simulación de Maxwell's Demon.
     * Este contenedor almacena partículas, demonios y agujeros.
     * Además, inicia la simulación de movimiento cuando se hace visible.
     * Atributos:
     * - `width`: Ancho total del contenedor.
     * - `height`: Altura total del contenedor.
     * - `particles`: Lista de partículas en el contenedor.
     * - `holes`: Lista de agujeros en el contenedor.
     * - `demons`: Lista de demonios en el contenedor.
     * - `border`: Representación gráfica del borde del contenedor.
     * - `leftChamber`: Representación gráfica de la cámara izquierda.
     * - `rightChamber`: Representación gráfica de la cámara derecha.
     * - `centralWall`: Representación gráfica de la pared central.
     * - `isVisible`: Indica si el contenedor es visible en la interfaz gráfica.
     * - `isRunning`: Indica si la simulación está en ejecución.
     * @author Daniel Ruiz Patiño
     * @author Juan Esteban Sánchez García
     * @version 1000 (Cycle 3)
     */

    public class MaxwellContainer {
        private int width;
        private int height;
        private List<Particle> particles;
        private List<Hole> holes;
        private List<Demon> demons;
        private Rectangle border;
        private Rectangle leftChamber;
        private Rectangle rightChamber;
        private Rectangle centralWall;
        private boolean isVisible;
        private boolean isRunning;
        private boolean did;
    
    
    
    /**
     * Constructor principal del contenedor. Inicializa las dimensiones, las cámaras (izquierda/derecha),
     * la pared central y las listas de partículas, demonios y agujeros.
     * 
     * @param width  Ancho inicial del contenedor (se duplica internamente).
     * @param height Altura del contenedor.
     */ 
    public MaxwellContainer(int width, int height) {
        this.width = 2 * width;
        this.height = height;
        particles = new ArrayList<>();
        holes = new ArrayList<>();
        demons = new ArrayList<>();
        isVisible = false;
        isRunning = false;
        border = new Rectangle();
        border.changeSize(height + 4, this.width + 4);
        border.changeColor("black");
        border.moveHorizontal(0);
        border.moveVertical(0);
        leftChamber = new Rectangle();
        leftChamber.changeSize(height, width);
        leftChamber.changeColor("white");
        leftChamber.moveHorizontal(0);
        rightChamber = new Rectangle();
        rightChamber.changeSize(height, width);
        rightChamber.changeColor("white");
        rightChamber.moveHorizontal(width);
        centralWall = new Rectangle();
        centralWall.changeSize(height, 1);
        centralWall.changeColor("gray");
        centralWall.moveHorizontal(width - 1);
        did = true;
    }
    
    
    
    
    /**
     * Constructor alternativo que inicializa el contenedor con un demonio y partículas predefinidas.
     * 
     * @param width         Ancho del contenedor.
     * @param height        Altura del contenedor.
     * @param d             Posición vertical del demonio.
     * @param b             Número de partículas azules.
     * @param r             Número de partículas rojas.
     * @param particlesData Lista de arrays con datos de partículas [px, py, vx, vy].
     */
    
    
    public MaxwellContainer(int width, int height, int d, int b, int r, List<int[]> particlesData) {
        this(width, height);
        addDemon(d);
        for(int i = 0; i < r + b; i++){
            int[] data = particlesData.get(i);
            int px = data[0], py = data[1], vx = data[2], vy = data[3];
            String color = (i < r) ? "blue" : "red";
            addParticle(color, px, py, vx, vy);
        }
    }
    
    
    /**
     * Añade una partícula normal al contenedor con posición, velocidad y color especificados.
     * 
     * @param color Color de la partícula (ej. "red", "blue").
     * @param px    Posición horizontal inicial (debe estar dentro del contenedor).
     * @param py    Posición vertical inicial (debe estar dentro del contenedor).
     * @param vx    Velocidad horizontal (no puede ser 0).
     * @param vy    Velocidad vertical (no puede ser 0).
     * @throws IllegalArgumentException Si la posición o velocidad son inválidas.
     */
    public void addParticle(String color, int px, int py, int vx, int vy) {
        if(px <= 0 || px >= width || py <= 0 || py >= height) {
            throw new IllegalArgumentException("Posición Inválida");
        }
        if(vx == 0 && vy == 0) {
            throw new IllegalArgumentException("Velocidad no Válida");
        }
        Particle particle = new NormalParticle(px, py, vx, vy, color, width, height);
        particles.add(particle);
        if(isVisible) particle.makeVisible();
    }
    
    
    /**
     * Verifica si el contenedor se inicializó correctamente.
     * 
     * @return `true` si la inicialización fue exitosa, `false` en caso contrario.
     */
    
    
    public boolean ok() { return did; }
    
    
    /**
     * Añade una partícula de tipo específico (normal, efímera, voladora, rotatoria) al contenedor.
     * 
     * @param type  Tipo de partícula ("normal", "ephemeral", "flying", "rotator").
     * @param color Color de la partícula.
     * @param px    Posición horizontal inicial.
     * @param py    Posición vertical inicial.
     * @param vx    Velocidad horizontal.
     * @param vy    Velocidad vertical.
     * @throws IllegalArgumentException Si el tipo, posición o velocidad son inválidos.
     */
    
    
    public void addParticle(String type,String color, int px, int py, int vx, int vy) {
        if(px <= 0 || px >= width || py <= 0 || py >= height) {
            throw new IllegalArgumentException("Posición no Válida");
        }
        if(vx == 0 && vy == 0) {
            throw new IllegalArgumentException("Velocidad no Válida");
        }
        
        
        if(type.equalsIgnoreCase("normal")) {
            Particle normalparticle = new NormalParticle(px, py, vx, vy, color, width, height);
            particles.add(normalparticle);
            if(isVisible) normalparticle.makeVisible();
        } else if (type.equalsIgnoreCase("ephemeral")) {
            Particle ephemeralparticle = new EphemeralParticle(px, py, vx, vy, color, width, height);
            particles.add(ephemeralparticle);
            if(isVisible) ephemeralparticle.makeVisible();
        } else if (type.equalsIgnoreCase("flying")) {
            Particle flyingparticle = new FlyingParticle(px, py, vx, vy, color, width, height);
            particles.add(flyingparticle);
            if(isVisible) flyingparticle.makeVisible();
        } else if (type.equalsIgnoreCase("rotator")) {
            Particle rotatorparticle = new RotatorParticle(px, py, vx, vy, color, width, height);
            particles.add(rotatorparticle);
            if(isVisible) rotatorparticle.makeVisible();
        }else {
            throw new IllegalArgumentException("Tipo no conocido: " + type);
        }
        
    }
    
    /**
     * Añade un demonio normal en la posición vertical especificada.
     * 
     * @param d Posición vertical del demonio (debe estar dentro del contenedor).
     * @throws IllegalArgumentException Si la posición es inválida.
     */
    
    
    public void addDemon(int d) {
        if(d <= 0 || d >= height) {
            throw new IllegalArgumentException("Posición no Válida");
        }
        Demon demon = new Demon(d, this.width / 2,"normal");
        demons.add(demon);
        if(isVisible) demon.makeVisible();
    }
    
    
    /**
     * Añade un demonio de tipo específico (normal, azul, débil) en la posición vertical especificada.
     * 
     * @param type Tipo de demonio ("normal", "blue", "weak").
     * @param d    Posición vertical del demonio.
     * @throws IllegalArgumentException Si el tipo o posición son inválidos.
     */
    
    public void addDemon(String type, int d) {
        if(d <= 0 || d >= height) {
            throw new IllegalArgumentException("Posición no Válida");
        }
        
        if(type.equalsIgnoreCase("normal")) {
            Demon demon = new Demon(d, this.width / 2,"normal");
            demons.add(demon);
            if(isVisible) demon.makeVisible();
        } else if (type.equalsIgnoreCase("blue")) {
            Demon blueDemon = new BlueDemon(d, this.width / 2);
            demons.add(blueDemon);
            if(isVisible) blueDemon.makeVisible();
        } else if (type.equalsIgnoreCase("weak")) {
            Demon weakDemon = new WeakDemon(d, this.width / 2);
            demons.add(weakDemon);
            if(isVisible) weakDemon.makeVisible();
        } else {
            throw new IllegalArgumentException("Tipo no conocido: " + type);
        }
    }

    /**
     * Elimina el último demonio añadido al contenedor.
     */
    
    public void deleteDemon() {
        if(!demons.isEmpty()){
            Demon last = demons.get(demons.size()-1);
            last.makeInvisible();
            demons.remove(demons.size()-1);
        }
    }
    
    /**
     * Abre la puerta del último demonio añadido durante un tiempo determinado.
     * 
     * @param milliseconds Tiempo en milisegundos que la puerta permanecerá abierta.
     */
    
    public void openGate(int milliseconds) {
        if(!demons.isEmpty()){
            Demon last = demons.get(demons.size()-1);
            last.setGateOpen(true);
            Timer timer = new Timer();
            timer.schedule(new TimerTask(){
                public void run(){
                    last.setGateOpen(false);
                }
            }, milliseconds);
        }
    }
    
    /**
     * Cierra manualmente la puerta del último demonio añadido.
     */
    
    public void closeGate() {
        if(!demons.isEmpty()){
            Demon last = demons.get(demons.size()-1);
            last.setGateOpen(false);
        }
    }
    
    
    
    /**
     * Elimina la última partícula añadida al contenedor.
     */    
    public void removeParticle() {
        if(!particles.isEmpty()){
            Particle last = particles.get(particles.size()-1);
            last.makeInvisible();
            particles.remove(particles.size()-1);
        }
    }
    
    
    
    /**
     * Añade un agujero normal al contenedor.
     */
    public void addHole() {
        Hole newHole = new Hole(width, height,"normal");
        holes.add(newHole);
        if(isVisible) newHole.makeVisible();
    }
    
    
    
    /**
     * Añade un agujero de tipo específico (normal, móvil) al contenedor.
     * 
     * @param type Tipo de agujero ("normal", "movil").
     * @throws IllegalArgumentException Si el tipo es inválido.
     */
    public void addHole(int containerWidth, int containerHeight,String type) {
        
        if(type.equalsIgnoreCase("normal")) {
            Hole normalHole = new Hole(width, height,"normal");
            holes.add(normalHole);
        if(isVisible) normalHole.makeVisible();
        } else if (type.equalsIgnoreCase("movil")) {
            Hole movilHole = new MovilHole(width, height);
            holes.add(movilHole);
        if(isVisible) movilHole.makeVisible();
        } else{
            throw new IllegalArgumentException("Tipo no conocido: " + type);
        }
        
    }
    
    
    /**
     * Elimina el último agujero añadido al contenedor.
     */
    public void removeHole() {
        if(!holes.isEmpty()){
            Hole last = holes.get(holes.size()-1);
            last.makeInvisible();
            holes.remove(holes.size()-1);
        }
    }
    
    /**
     * Devuelve una lista ordenada de partículas (por posición X, luego Y).
     * 
     * @return Lista de partículas ordenadas.
     */    
    public List<Particle> particles() {
        List<Particle> sorted = new ArrayList<>(particles);
        sorted.sort(Comparator.comparingInt(Particle::getX).thenComparingInt(Particle::getY));
        return sorted;
    }
     /**
     * Devuelve una lista ordenada de demonios (por posición Y).
     * 
     * @return Lista de demonios ordenados.
     */    
    public List<Demon> demons() {
        List<Demon> sorted = new ArrayList<>(demons);
        sorted.sort(Comparator.comparingInt(Demon::getY));
        return sorted;
    }
    
    
    /**
     * Devuelve una lista ordenada de agujeros (por posición X).
     * 
     * @return Lista de agujeros ordenados.
     */   
    public List<Hole> holes() {
        List<Hole> sorted = new ArrayList<>(holes);
        sorted.sort(Comparator.comparingInt(Hole::getX));
        return sorted;
    }
    
    
     /** @return Ancho total del contenedor. */
    public int getWidth() { return width; }
    
    /** @return Altura total del contenedor. */
    public int getHeight() { return height; }
    
    /** Hace visible el contenedor y todos sus elementos gráficos. */
    public void makeVisible() {
        if(!isVisible) {
            border.makeVisible();
            leftChamber.makeVisible();
            rightChamber.makeVisible();
            centralWall.makeVisible();
            for(Demon d : demons) d.makeVisible();
            for(Particle p : particles) p.makeVisible();
            isVisible = true;
        }
    }
    
    /** Hace invisible el contenedor y todos sus elementos gráficos. */
    
    public void makeInvisible() {
        if(isVisible) {
            border.makeInvisible();
            leftChamber.makeInvisible();
            rightChamber.makeInvisible();
            centralWall.makeInvisible();
            for(Demon d : demons) d.makeInvisible();
            for(Particle p : particles) p.makeInvisible();
            isVisible = false;
        }
    }
    
    
    /** @return `true` si el contenedor es visible, `false` en caso contrario. */
    public boolean isVisible() { return isVisible; }
    
    
     /**
     * Verifica si se alcanzó el "goal" de la simulación (partículas rojas a la derecha, azules a la izquierda).
     * 
     * @return `true` si se cumplió la condición, `false` en caso contrario.
     */
    public boolean isGoal() {
        int middleX = width / 2;
        return particles.stream().allMatch(p -> (p.getColor().equals("red") && p.getX() >= middleX) || (p.getColor().equals("blue") && p.getX() < middleX));
    }
    
    
    /**
     * Ejecuta la simulación de movimiento para un número determinado de pasos.
     * 
     * @param steps Número máximo de pasos a simular.
     */
    private void runSimulation(int steps) {
        isRunning = true;
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < steps && isRunning; i++){
            for(Particle p : particles) p.move(width, height, demons);
            for (Hole h : holes) {
            h.absorbs(particles);
            if (h instanceof MovilHole) {
                ((MovilHole) h).move();  // Asegurar que se mueva en cada paso
            }
            }


            if(isGoal()){
                long elapsed = System.currentTimeMillis() - startTime;
                double secs = elapsed / 1000.0;
                System.out.println("Goal alcanzado en " + secs + " segundos");
                finish();
                return;
            }
            try { Thread.sleep(5); } catch(InterruptedException e){ e.printStackTrace(); }
        }
        if(!isGoal()){
            long elapsed = System.currentTimeMillis() - startTime;
            double secs = elapsed / 1000.0;
            System.out.println("Fue imposible alcanzar el goal en " + secs + " segundos");
        }
        isRunning = false;
    }
    
    
    
     /** Inicia la simulación en un hilo separado (500 pasos máximos). */
    public void start() {
        System.out.println("Simulación Iniciada");
        Thread t = new Thread(() -> { runSimulation(500); });
        t.start();
    }
    
    
    /** Detiene la simulación manualmente. */
    public void finish() {
        isRunning = false;
        System.out.println("Simulación Terminada");
    }
}
