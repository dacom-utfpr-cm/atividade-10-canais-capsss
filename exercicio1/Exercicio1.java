/*
 * Carlos Alexandre Peron dos Santos
 *
 * Implementar o problema do produtor-consumidor usando Java NIO: Pipe, Pipe.SinkChannel e Pipe.SourceChannel.
 */
package exercicio1;

import java.io.IOException;
import java.nio.channels.Pipe;

public class Exercicio1 {

    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel  = pipe.sink();
        Pipe.SourceChannel sourceChannel = pipe.source();
        
        int quantosProdutores = 5;
        int quantosConsumidores = 5;

        Thread t;
        for(int i=0; i<quantosProdutores; i++){
            t = new Thread(new Produtor(sinkChannel));
            t.start();
        }
        
        for(int i=0; i<quantosConsumidores; i++){
            t = new Thread(new Consumidor(sourceChannel));
            t.start();
        }
        
    }
    
}
