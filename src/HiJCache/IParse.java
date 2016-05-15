package HiJCache;

import org.w3c.dom.Element;

/**
 * ½âÎö»º´æµÄXML
 * @author ĞìÃôÈÙ
 *
 * @param <T>
 */
public interface IParse<T> {
	public T Parse(Element node);
}
