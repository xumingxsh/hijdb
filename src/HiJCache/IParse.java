package HiJCache;

import org.w3c.dom.Element;

/**
 * ���������XML
 * @author ������
 *
 * @param <T>
 */
public interface IParse<T> {
	/**
	 * ���������XML��Ϣ
	 * @param node
	 * @return
	 */
	public T Parse(Element node);
}
