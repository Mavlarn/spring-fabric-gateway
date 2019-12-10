package io.ecsoya.fabric.service.impl;

import java.util.Collections;
import java.util.List;

import io.ecsoya.fabric.FabricQuery;
import io.ecsoya.fabric.FabricQueryRequest;
import io.ecsoya.fabric.FabricQueryResponse;
import io.ecsoya.fabric.FabricRequest;
import io.ecsoya.fabric.bean.FabricBlock;
import io.ecsoya.fabric.bean.FabricHistory;
import io.ecsoya.fabric.bean.IFabricObject;
import io.ecsoya.fabric.service.IChaincodeService2;

public abstract class ContextChaincodeService<T extends IFabricObject> extends ChaincodeServiceBase<T>
		implements IChaincodeService2<T> {

	@Override
	protected FabricRequest newRequest(String function, String... arguments) {
		return new FabricRequest(function, arguments);
	}

	@Override
	protected <F> FabricQueryRequest<F> newQueryRequest(Class<F> type, String function, String... arguments) {
		return new FabricQueryRequest<>(type, function, arguments);
	}

	@Override
	public int extCreate(T object) {
		return create(object).status;
	}

	@Override
	public int extUpdate(T object) {
		return update(object).status;
	}

	@Override
	public int extDelete(String key, String type) {
		return delete(key, type).status;
	}

	@Override
	public T extGet(String key, String type) {
		return get(key, type).data;
	}

	@Override
	public List<FabricHistory> extHistory(String key, String type) {
		return history(key, type).data;
	}

	@Override
	public List<T> extQuery(FabricQuery query) {
		FabricQueryResponse<List<T>> result = query(query);
		if (result.isOk(true)) {
			return result.data;
		}
		return Collections.emptyList();
	}

	@Override
	public List<T> extList() {
		return list().data;
	}

	@Override
	public int extCount(FabricQuery query) {
		Number count = count(query).data;
		if (count != null) {
			return count.intValue();
		}
		return 0;
	}

	@Override
	public boolean extExists(FabricQuery query) {
		Boolean exists = exists(query).data;
		if (exists != null) {
			return exists.booleanValue();
		}
		return false;
	}

	@Override
	public FabricBlock extBlock(String key, String type) {
		return block(key, type).data;
	}

}