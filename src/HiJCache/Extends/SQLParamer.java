package HiJCache.Extends;

/**
 * SQL����
 * @author ������
 *
 */
public final class SQLParamer {
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	private String name;
	private String value;
}
