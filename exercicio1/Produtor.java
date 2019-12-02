package exercicio1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Produtor implements Runnable{
    ByteBuffer buf;
    Pipe.SinkChannel sinkChannel;
    
    public Produtor(Pipe.SinkChannel sinkChannel){
        this.sinkChannel = sinkChannel;
        buf = ByteBuffer.allocate(128);
        buf.clear();
    }

    @Override
    public void run() {
        //String s = Long.toString(Thread.currentThread().getId());
        System.out.println("Produtor " + Thread.currentThread().getId() + " produziu: " + Thread.currentThread().getId());
        buf.flip();
        while(buf.hasRemaining()){
            try {
                sinkChannel.write(buf);
            } catch (IOException ex) {
                Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
}
