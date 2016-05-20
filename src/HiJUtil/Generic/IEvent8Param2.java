package HiJUtil.Generic;

/**
 * 两个参数,无返回值接口
 * @author XuminRong
 *
 * @param <T>
 * @param <A>
 */
public interface IEvent8Param2<T, A> {
	/**
	 * @param t
	 * @param a
	 */
	public void OnEvent(T t, A a);
}
