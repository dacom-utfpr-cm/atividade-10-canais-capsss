package exercicio2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Filosofo implements Runnable{
    PipedOutputStream pipeOut;
    PipedInputStream pipeIn;
    int idFilosofo;
    
    public Filosofo(PipedOutputStream pipeOut, PipedInputStream pipeIn, int idFilosofo){
        this.pipeOut = pipeOut;
        this.pipeIn = pipeIn;
        this.idFilosofo = idFilosofo;
    }

    @Override
    public void run() {
        try {
            pipeOut.write(idFilosofo);
            System.out.println("1");
            pipeOut.write(idFilosofo+1);
            System.out.println("2");
            pipeOut.write(idFilosofo+2);
        } catch (IOException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            System.out.println(pipeIn.read());
            System.out.println(pipeIn.read());
        } catch (IOException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
