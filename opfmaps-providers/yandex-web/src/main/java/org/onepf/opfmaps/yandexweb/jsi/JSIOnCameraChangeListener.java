/*
 * Copyright 2012-2015 One Platform Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onepf.opfmaps.yandexweb.jsi;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.webkit.JavascriptInterface;
import org.onepf.opfmaps.yandexweb.listener.OnCameraChangeListener;
import org.onepf.opfmaps.yandexweb.model.CameraPosition;
import org.onepf.opfmaps.yandexweb.model.LatLng;

/**
 * @author Roman Savin
 * @since 08.09.2015
 */
public final class JSIOnCameraChangeListener {

    public static final String JS_INTERFACE_NAME = "OnCameraChangeListener";

    @NonNull
    private final OnCameraChangeListener listener;

    @NonNull
    private final Handler handler = new Handler(Looper.getMainLooper());

    public JSIOnCameraChangeListener(@NonNull final OnCameraChangeListener listener) {
        this.listener = listener;
    }

    @JavascriptInterface
    public void onCameraChange(final double lat, final double lng, final float zoom, final double offsetX, final double offsetY) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onCameraChange(CameraPosition.fromLatLngZoom(new LatLng(lat, lng), zoom), offsetX, offsetY);
            }
        });
    }
}
