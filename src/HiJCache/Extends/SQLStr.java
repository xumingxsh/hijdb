package HiJCache.Extends;

public class SQLStr {
	/**
	 * �Ƿ���SQL����
	 */
	private boolean Param;
	
	/**
	 * ��Ӧ���ı�
	 */
	private String text;
	
	/**
	 * ��Ӧ��ֵ,һ��ΪTextȥ��@
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
