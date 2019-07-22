package com.kaiwukj.android.communityhui.mvp.http.api.cache;

import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/2 RxCache 用法
 */
public interface CommonCache {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<HomeServiceEntity>> getHomeServiceCache(Observable<HomeServiceEntity> data, DynamicKey idLastUserQueried, EvictProvider evictProvider);


    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<StoreListResult>> getHomeStoreCache(Observable<StoreListResult> data, DynamicKey idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<StaffListResult>> getHomeStaffCache(Observable<StaffListResult> data, DynamicKey idLastUserQueried, EvictProvider evictProvider);

    @LifeCache(duration = 30, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<StaffListResult>> getMyAddressCache(Observable<StaffListResult> data, DynamicKey idLastUserQueried, EvictProvider evictProvider);
}
