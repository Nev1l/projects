package by.epam.utils;


public class PageNavigator {
/*	private static final Logger logger = LoggerFactory
			.getLogger(PageNavigator.class);*/
	private int total;
	private int current;

	public PageNavigator(int total, int current) {
		super();
		this.total = total;
		this.current = current;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}
	
	private boolean hasPrev(int p){
		boolean f = p-1>0 && p-1<=total;
		//logger.info("hasPrev("+f+")="+p);
		return f;
	}
	
	private boolean hasNext(int p){
		boolean f = p+1>0 && p+1<=total;
		//logger.info("hasNext("+f+")="+p);
		return f;
	}
	
	public boolean hasPrev1(){
		return hasPrev(current);
	}
	
	public boolean hasPrev2(){
		return hasPrev(current-1);
	}
	
	public boolean hasNext1(){
		return hasNext(current);
	}
	
	public boolean hasNext2(){
		return hasNext(current+1);
	}
	

}
