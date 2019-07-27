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
    /*获取服务列表 不需要手动驱逐缓存*/
    @LifeCache(duration = 10, timeUnit = TimeUnit.HOURS)
    Observable<Reply<HomeServiceEntity>> getHomeServiceCache(Observable<HomeServiceEntity> data);

    /*首页推荐门店数据*/
    @LifeCache(duration = 3, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<StoreListResult>> getHomeStoreCache(Observable<StoreListResult> data, EvictProvider evictProvider);

    /*首页全部门店数据 具备分页功能*/
    @LifeCache(duration = 3, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<StoreListResult>> getHomeStoreListCache(Observable<StoreListResult> data, DynamicKey key);

    /*首页推荐服务员数据*/
    @LifeCache(duration = 3, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<StaffListResult>> getHomeStaffCache(Observable<StaffListResult> data, EvictProvider evictProvider);

    /*首页推荐服务员数据 具备分页功能*/
    @LifeCache(duration = 3, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<StaffListResult>> getHomeStaffListCache(Observable<StaffListResult> data, DynamicKey key, EvictProvider evictProvider);

}
