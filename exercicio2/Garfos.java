package exercicio2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Garfos implements Runnable{
    Integer garfos[];
    PipedInputStream pipeIn;
    PipedOutputStream pipeOut;
    
    public Garfos(PipedOutputStream pipeOut, PipedInputStream pipeIn, int quantosGarfos){
        this.pipeIn = pipeIn;
        this.pipeOut = pipeOut;
        this.garfos = new Integer[quantosGarfos];
        for(int i=0; i<quantosGarfos; i++){
            this.garfos[i] = 0;
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                ler();
            } catch (IOException ex) {
                Logger.getLogger(Garfos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private synchronized void ler() throws IOException{
        Integer numero = pipeIn.read();
        if(numero < 100){
            if(garfos[numero-1] != 0){
                responder(0);
            } else {
                garfos[numero-1] = 1;
                responder(1);
            }
        } else {
            garfos[numero-1-100] = 0;
        }
        System.out.println(Arrays.toString(garfos));
    }
    
    private synchronized void responder(Integer resposta) throws IOException{
        pipeOut.write(resposta);
    }
}
