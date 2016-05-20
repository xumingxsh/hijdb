package HiJUtil.Generic;

/**
 * 单个参数,无返回值接口
 * @author xuminrong
 *
 * @param <T>
 */
public interface IEvent8Param<T> {
	/**
	 * @param t
	 */
	public void OnEvent(T t);
}
