package HiJUtil.Generic;

import HiJUtil.HiTypeHelper;

public class HiResult<T> implements IResult<T> {
	@Override
	public boolean GetIsSuccess() {
		// TODO Auto-generated method stub
		return isOK;
	}
	
	@Override
	public void SetIsSuccess(boolean isSuccess) {
		isOK = isSuccess;		
	}


	@Override
	public T Get() {
		// TODO Auto-generated method stub
		return t;
	}

	@Override
	public void Set(T t) {
		this.t = t;

	}
	boolean isOK = false;
	T t;
}
