/*
 * Copyright (C) 2019 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.allinone.view.ui.weather

import androidx.lifecycle.ViewModel
import com.skydoves.allinone.api.ApiResponse
import com.skydoves.allinone.api.client.KMAClient
import com.skydoves.allinone.persistence.preference.PreferenceComponent_PreferenceComponent
import com.skydoves.allinone.utils.LocalUtils
import timber.log.Timber
import javax.inject.Inject

class WeatherViewModel @Inject
constructor(private val kmaClient: KMAClient) : ViewModel() {

  private val setting = PreferenceComponent_PreferenceComponent.getInstance().Settings()

  init {
    Timber.d("injection WeatherViewModel")
  }

  fun test() {
    kmaClient.fetchWeather(LocalUtils.getLocalUrl(0)) {
      when (it) {
        is ApiResponse.Success -> {
          Timber.d("Success")
          Timber.d(it.data?.channel.toString())
        }
        is ApiResponse.Failure.Error -> {
          Timber.d("Failure")
          Timber.d(it.code.toString())
        }
        is ApiResponse.Failure.Exception -> {
          Timber.d("${it.message}")
        }
      }
    }
  }

  fun getLocal() = setting.local
}
