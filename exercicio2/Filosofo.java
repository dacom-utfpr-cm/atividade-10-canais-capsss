package exercicio2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Filosofo implements Runnable{
    PipedOutputStream pipeOut;
    PipedInputStream pipeIn;
    int idFilosofo;
    int quantosFilosofos;
    
    public Filosofo(PipedOutputStream pipeOut, PipedInputStream pipeIn, int idFilosofo, int quantosFilosofos){
        this.pipeOut = pipeOut;
        this.pipeIn = pipeIn;
        this.idFilosofo = idFilosofo;
        this.quantosFilosofos = quantosFilosofos;
    }

    @Override
    public void run() {
        Random r = new Random();
        boolean conseguiu = false;
        try {
            Thread.sleep(r.nextInt(9 * 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(!conseguiu){
            try {
                conseguiu = pegarGarfo(idFilosofo);
            } catch (IOException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private synchronized boolean pegarGarfo(Integer garfo) throws IOException{
        pipeOut.write(garfo);
        Integer resposta = pipeIn.read();
        if(resposta != 0){
            if((garfo+1) < quantosFilosofos){
                pipeOut.write(garfo+1);
            } else {
                pipeOut.write(1);
            }
            resposta = pipeIn.read();
            if(resposta != 0){
                System.out.println("filosofo " + idFilosofo + " conseguiu os 2 garfos");
                System.out.println("filosofo " + idFilosofo + " comendo");
                pipeOut.write((garfo)+100);
                if((garfo+1) < quantosFilosofos){
                    pipeOut.write(garfo+100);
                } else {
                    pipeOut.write((1)+100);
                }
                System.out.println("filosofo " + idFilosofo + " terminou de comer");
                return true;
            } else {
                System.out.println("filosofo " + idFilosofo + " nao conseguiu os 2 garfos");
                pipeOut.write((garfo)+100);
                return false;
            }
        } else {
            System.out.println("filosofo " + idFilosofo + " nao conseguiu o primeiro garfo");
            return false;
        }
    }
}