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
        PipedOutputStream pipeOut = new PipedOutputStream();
        PipedInputStream pipeIn = new PipedInputStream(pipeOut, 3);
        
        Thread t;
        t = new Thread(new Filosofo(pipeOut, pipeIn, 1));
        t.start();
    }
    
}
