package HiJCache;

import org.w3c.dom.Element;

/**
 * ���������XML
 * @author ������
 *
 * @param <T>
 */
public interface IParse<T> {
	public T Parse(Element node);
}
