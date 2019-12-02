/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio1;

import java.io.IOException;
import java.nio.channels.Pipe;

public class Exercicio1 {

    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel  = pipe.sink();
        Pipe.SourceChannel sourceChannel = pipe.source();

        Thread t;
        for(int i=0; i<1; i++){
            t = new Thread(new Produtor(sinkChannel));
            t.start();
        }
        
        for(int i=0; i<1; i++){
            t = new Thread(new Consumidor(sourceChannel));
            t.start();
        }
        
    }
    
}
