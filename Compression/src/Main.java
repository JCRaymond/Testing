import java.io.*;
import java.util.ArrayList;

public class Main {

    private static String path = "Compression/Files/";

    public static File genBigFile(String fileName, int numBytes) throws IOException {
        int chunkSize = 16384;
        double numChunks = (int)(numBytes/(double)chunkSize + 0.99999);
        File f = new File(path+fileName);
        f.delete();
        FileOutputStream fout = new FileOutputStream(f);
        byte[] byteChunk = new byte[chunkSize];
        for (int i=0; i<numChunks; i++){
            for (int j=0; j<chunkSize; j++){
                byteChunk[j] = (byte)(Math.random()*(96) + 32);
            }
            fout.write(byteChunk);
        }
        fout.close();
        return f;
    }

    public static void copyValsIn(byte[] big, byte[] small, int startIndex){
        for (int i = 0; i<small.length; i++){
            big[startIndex+i] = small[i];
        }
    }

    public static double timeReadForChunkSize(FileInputStream in, int chunkSize) throws IOException {
        long s = System.nanoTime();
        int numBytes = in.available();
        int numChunks = numBytes/chunkSize;
        byte[] bytes = new byte[chunkSize];
        byte[] byteChunk = new byte[chunkSize];
        for (int i=0; i<numChunks; i++){
            in.read(byteChunk);
            copyValsIn(bytes, byteChunk, 0);
        }
        long e = System.nanoTime();
        return (e-s)/1000000000.;
    }

    public static void main(String[] args) throws IOException {
        String fileIn = "testing.txt";
        String fileOut = "testing.srnk";

        File fin = new File(path + fileIn);
        File fout = new File(path + fileOut);

        fin.delete();
        if (!fin.isFile()){
            int size = (int)Math.pow(2, 32);
            System.out.println("Generating...");
            System.out.println(size + " bytes");
            fin = genBigFile(fileIn, size);
            System.out.println("Generated File!");
        }

        FileInputStream in = new FileInputStream(fin);
        System.out.println(in.available() + " bytes");

        int lastChunkSize = 2048;
        int chunkSize = lastChunkSize;
        int numTrials = 100;
        double lastTime = timeReadForChunkSize(in, chunkSize);
        double time;
        chunkSize *= 2;
        for (int i=0; i<numTrials; i++){
            in = new FileInputStream(fin);
            time = timeReadForChunkSize(in, chunkSize);
            System.out.println(chunkSize + ": " + time);
            if (time < lastTime){
                lastChunkSize = chunkSize;
                chunkSize *= 2;
            }
            else {
                int temp = chunkSize;
                chunkSize = lastChunkSize;
                lastChunkSize = temp;
            }
            lastTime = time;
        }

    }
}
