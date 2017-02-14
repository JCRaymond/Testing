import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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

    public static double timeReadForChunkSize(FileInputStream in, int chunkSize) throws IOException {
        long s = System.nanoTime();
        int numBytes = in.available();
        int numChunks = numBytes/chunkSize;
        byte[] bytes = new byte[chunkSize];
        byte[] byteChunk = new byte[chunkSize];
        for (int i=0; i<numChunks; i++){
            in.read(byteChunk);
            bytes = byteChunk;
        }
        long e = System.nanoTime();
        return (e-s)/1000000000.;
    }

    public static void main(String[] args) throws IOException {
        String fileIn = "testing.txt";
        String fileOut = "testing.srnk";

        File fin = new File(path + fileIn);
        File fout = new File(path + fileOut);

        //fin.delete();
        if (!fin.isFile()){
            int size = (int)Math.pow(2, 25);
            System.out.println("Generating...");
            System.out.println(size + " bytes");
            fin = genBigFile(fileIn, size);
            System.out.println("Generated File!");
        }

        FileInputStream in = new FileInputStream(fin);

//        HashMap<byte[], Integer> amounts = new HashMap<>();
//        int seqLen = 4;
//        int chunkSize = 65536;
//        byte[] bytes = new byte[chunkSize];
//        int numBytes = in.available();
//        int numReads = (int) Math.ceil(numBytes/(double)chunkSize);
//        for (int i=0; i<numReads; i++){
//            in.read(bytes);
//            for (int j=0; j<chunkSize && i*chunkSize+j<numBytes; j+=seqLen){
//                byte[] seq = Arrays.copyOfRange(bytes, j, j+seqLen);
//                Integer amnt = amounts.getOrDefault(seq, 0);
//                amounts.put(seq, amnt+1);
//            }
//        }
//
//        for (byte[] seq : amounts.keySet()){
//            if (amounts.get(seq) > 1){
//                StringBuilder sb = new StringBuilder("[");
//                for (byte b : seq){
//                    sb.append((char)b).append(", ");
//                }
//                sb.delete(sb.length()-2, sb.length());
//                sb.append("]=").append(amounts.get(seq));
//                System.out.println(sb);
//            }
//        }

        HashMap<Integer[], Integer> test = new HashMap<>();
        test.put(new Integer[]{0}, 1);
        test.put(new Integer[]{1}, 1);
        test.put(new Integer[]{1}, 2);
        System.out.println(test);
    }
}
