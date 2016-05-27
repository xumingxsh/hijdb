package HiJCache.Extends;

public class SQLStr {
	/**
	 * 是否是SQL参数
	 */
	private boolean Param;
	
	/**
	 * 对应的文本
	 */
	private String text;
	
	/**
	 * 对应的值,一般为Text去除@
	 */
	private String value;
	
	public String getValue() {
		return value;
	}

	public boolean isParam() {
		return Param;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
		if(text== null) {
			return;
		}
		if (text.indexOf("@") >= 0) {
			Param = true;
		} else {
			Param = false;
		}

		this.text = this.text.replace("\r\n", " ").replace("\r", " ").replace("\t", " ").replace("\n", " ");
		if (Param) {
			value = this.text.substring(1);
		}
	}
}
