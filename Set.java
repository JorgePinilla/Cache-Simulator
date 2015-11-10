/*
  Basic structure of a cache set. The number of blocks it stores
  is dependent on the associatovity of the cache.
*/
import java.math.BigInteger;

public class Set{
	private BigInteger index;
	private Block[] blocks;
	
	public BigInteger getIndex(){
		return this.index;
	}
	
	public Block[] getBlocks(){
		return this.blocks;
	}
	
	public Set(BigInteger index, int associativity){
		this.index = index;
		this.blocks = new Block[associativity];
		
		for(int i = 0; i < associativity; i++){
			this.blocks[i] = new Block();
		}
	}
}
