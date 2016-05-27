package HiJUtil.Generic;

/**
 * 单独参数,有返回值匿名类
 * @author XuminRong
 *
 * @param <T>
 * @param <V>
 */
public interface IEventRet8Param<T, V> {
	/**
	 * 
	 * @param v
	 * @return
	 */
	public T OnEvent(V v);
}
