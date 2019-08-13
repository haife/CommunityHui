package com.kaiwukj.android.communityhui.mvp.http.api;/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.kaiwukj.android.communityhui.BuildConfig;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc 存放一些与 API 有关的东西,如请求地址,请求码等
 */
public interface Api {
    String APP_DOMAIN = BuildConfig.API_HOST;
    String RequestSuccess = "0";
    String IMG_URL = "";
    String QI_IMG = "http://qnzhsq.kaiwumace.com/";
}
