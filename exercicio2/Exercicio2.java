/*
 * Carlos Alexandre Peron dos Santos
 *
 * Implementar o problema do jantar dos filósofos usando Java IO: PipedInputStream e PipedOutputStream.
 */
package exercicio2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Exercicio2 {

    public static void main(String[] args) throws IOException {
        PipedOutputStream pipeOutFilosofos = new PipedOutputStream();
        PipedInputStream pipeInGarfos = new PipedInputStream(pipeOutFilosofos, 1);
        PipedOutputStream pipeOutGarfos = new PipedOutputStream();
        PipedInputStream pipeInFilosofos = new PipedInputStream(pipeOutGarfos, 1);
        
        int quantosFilosofos = 5;
        
        Thread t;
        t = new Thread(new Garfos(pipeOutGarfos, pipeInGarfos, quantosFilosofos));
        t.start();
        
        for(int i=0; i<quantosFilosofos; i++){
            t = new Thread(new Filosofo(pipeOutFilosofos, pipeInFilosofos, i+1, quantosFilosofos));
            t.start();
        }
    }
}
