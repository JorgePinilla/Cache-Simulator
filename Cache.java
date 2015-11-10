/*
  Basic structure of the cache. It stores a # of sets that's
  determined by the size of the cache, block size and the cache's associativity.
*/
import java.math.BigInteger;
import java.util.Random;

public class Cache {
	public BigInteger cache_size;
	public int cache_associativity;
	public BigInteger block_size;
	public static final int ADDRESS_32_BIT = 32;
	public int cache_hits;
	public int cache_misses;
	
	private Set[] sets;
	private BigInteger num_blocks;
	private BigInteger num_sets;
	private BigInteger offset_size;
	private BigInteger index_size;
	private BigInteger tag_size;
	
	public BigInteger getNumOfBlocks(){
		return this.num_blocks;
	}
	
	public BigInteger getNumOfSets(){
		return this.num_sets;
	}
	
	public BigInteger getOffsetSize(){
		return this.offset_size;
	}
	
	public BigInteger getIndexSize(){
		return this.index_size;
	}
	
	public BigInteger getTagSize(){
		return this.tag_size;
	}
	
	public void readFromCache(BigInteger index, BigInteger tag){
		/*
		  This methods checks if the given tag matches the tag that's
		  stored in the given set (index). If the tags match, then it's
		  considered a cache hit, otherwise it will replace the the tag
		  (that's stored in the set) with the given tag (considered a miss).
		*/
		Set current_set = this.sets[index.intValue()];
		Block[] s_blocks = current_set.getBlocks();
		Random rng = new Random();
		int i = 0;
		boolean result = false;
		while(i < this.cache_associativity && !result){
			if(s_blocks[i].getTag() != null)
				result = (s_blocks[i].getTag()).equals(tag);
			i++;
		}
		if(!result){
			int placement = 0;
			/*
			  If cache n-way set associative, the block that's being replaced
			  will be done at random.
			*/
			if(this.cache_associativity > 1)
				placement = rng.nextInt(this.cache_associativity);
			s_blocks[placement].setTag(tag);
			this.cache_misses++;
		}
		else
			this.cache_hits++;
	}
	
	public void writeToCache(BigInteger index, BigInteger tag){
		/*
		  This method writes the given tag to the given set (index) of the cache.
		*/
		Set current_set = this.sets[index.intValue()];
		Block[] s_blocks = current_set.getBlocks();
		Random rng = new Random();
		int placement = 0;
		/*
		  If cache n-way set associative, the location where the block is written
		  (within the set) is chosen at random.
		*/
		if(this.cache_associativity > 1)
			placement = rng.nextInt(this.cache_associativity);
		s_blocks[placement].setTag(tag);
		this.cache_hits++;
	}
	
	public Cache(BigInteger cacheSize, BigInteger blockSize, int associativity){
		this.cache_size = cacheSize;
		this.cache_associativity = associativity;
		this.block_size = blockSize;
		this.cache_hits = 0;
		this.cache_misses = 0;
		
		this.num_blocks = this.cache_size.divide(this.block_size);
		this.num_sets = new BigInteger(String.valueOf(this.num_blocks.intValue() / this.cache_associativity));
		
		this.offset_size = Util.log2(this.block_size);
		this.index_size = Util.log2(this.num_sets);
		
		BigInteger address_size = new BigInteger(String.valueOf(ADDRESS_32_BIT));
		this.tag_size = address_size.subtract(this.index_size).subtract(this.offset_size);
		
		this.sets = new Set[this.num_sets.intValue()];
		
		for(int i = 0; i < this.num_sets.intValue(); i++)
			this.sets[i] = new Set(new BigInteger(String.valueOf(i)), this.cache_associativity);
	}
}
