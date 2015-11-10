import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;

public class Simulator {
	
	public static void main(String[] args){
		
		BigInteger cacheSize = new BigInteger(args[0]);
		BigInteger blockSize = new BigInteger(args[1]);
		int associativity = Integer.parseInt(args[2]);
		
		Cache cache = new Cache(cacheSize, blockSize, associativity);
		
		BigInteger tag_size = cache.getTagSize();
		BigInteger index_size = cache.getIndexSize();
		
		int reads = 0;
		int writes = 0;
		
		try {
			File file = new File(args[3]);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] token = line.split("\\s");
				String mode = token[1];
				String address = Util.hexTo32bitBin(token[2]);
				
				BigInteger blockAddress = Util.binToDec(address.substring(0, tag_size.intValue() + index_size.intValue()));
				BigInteger tag = Util.binToDec(address.substring(0, tag_size.intValue()));
				
				if(mode.equals("R")){
					reads++;
					BigInteger index = Util.binToDec(address.substring(tag_size.intValue(), tag_size.intValue() + index_size.intValue()));
					cache.readFromCache(index, tag);
				}
				else if(mode.equals("W")){
					writes++;
					BigInteger index = blockAddress.mod(cache.getNumOfSets());
					cache.writeToCache(index, tag);
				}
			}
			fileReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("# of sets: " + cache.getNumOfSets());
		System.out.println("# of reads: " + reads);
		System.out.println("# of writes: " + writes);
		System.out.println("# of cache misses: " + cache.cache_misses);
		System.out.println("# of cache hits: " + cache.cache_hits);
	}
}
