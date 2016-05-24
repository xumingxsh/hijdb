package HiJCache.Extends;

public class SQLStr {
	private boolean Param;
	private String text;
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isParam() {
		return Param;
	}
	public void setParam(boolean param) {
		Param = param;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
		if (this.text != null) {
			this.text = this.text.replace("\r\n", " ").replace("\r", " ").replace("\t", " ");
		}
	}
}
