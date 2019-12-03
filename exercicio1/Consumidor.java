package exercicio1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor implements Runnable{
    ByteBuffer buf;
    Pipe.SourceChannel sourceChannel;
    
    public Consumidor(Pipe.SourceChannel sourceChannel){
        this.sourceChannel = sourceChannel;
        buf = ByteBuffer.allocate(20);
    }

    @Override
    public void run() {
        try {
            sourceChannel.read(buf);
            buf.rewind();
            System.out.println("Consumidor " + Thread.currentThread().getId() + " consumiu: " + buf.getInt());
        } catch (IOException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
