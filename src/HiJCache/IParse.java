package HiJCache;

import org.w3c.dom.Element;

/**
 * 解析缓存的XML
 * @author 徐敏荣
 *
 * @param <T>
 */
public interface IParse<T> {
	/**
	 * 解析缓存的XML信息
	 * @param node
	 * @return
	 */
	public T Parse(Element node);
}
