package HiJUtil.Generic;

/**
 * @author admin
 *
 * @param <R>
 * @param <P1>
 * @param <P2>
 */
public interface IEventRet8Param2<R, P1, P2> {
	/**
	 * @param p1
	 * @param p2
	 * @return
	 */
	public R OnEvent(P1 p1, P2 p2);
}
