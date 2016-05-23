package HiJUtil.Generic;

/**
 * 用于从匿名类返回数据
 * @author XuminRong
 *
 * @param <T>
 */
public interface IResult<T> {
	public boolean GetIsSuccess();
	public void SetIsSuccess(boolean isSuccess);
	public T Get();
	public void Set(T t);
}
