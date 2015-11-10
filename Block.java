/*
  Basic structure of a cache block. It stores the tag of the
  block.
*/
import java.math.BigInteger;

public class Block {
	private BigInteger tag;
	
	public BigInteger getTag(){
		return this.tag;
	}
	
	public void setTag(BigInteger tag){
		this.tag = tag;
	}
	
	public Block(){
		this.tag = null;
	}
}
