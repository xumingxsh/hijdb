package HiJCache;

public final class CachData<T> {
	public CachData() {
	}
	
	public String getID(){
		return id;
	}
	
	public String getFile(){
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public T getData(){
		return data;
	}
	public void setData(T t) {
		data = t;
	}
	private String file;
	private String id;
	private T data; 
}
