package HiJUtil.Generic;

/**
 * ��������,�з���ֵ������
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
