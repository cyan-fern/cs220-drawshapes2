package knox.drawshapes;

public class Range {
	public int min;
	public int max;
	public Range(int min,int max) {
		this.min=min;
		this.max=max;
	}
	public Range(int val) {
		this.min=val;
		this.max=val;
	}
	public void add(int val) {
		if(val<min) {this.min=val;}
		else if(val>max) {this.max=val;}
	}
	public Boolean intersect(Range r) {
		return !(r.min>this.max||this.min>r.max);
	}
}
