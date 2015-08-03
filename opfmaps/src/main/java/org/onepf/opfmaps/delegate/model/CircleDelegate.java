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

package org.onepf.opfmaps.delegate.model;

import android.support.annotation.NonNull;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 29.07.2015
 */
public interface CircleDelegate {

    @NonNull
    OPFLatLng getCenter();

    int getFillColor();

    @NonNull
    String getId();

    double getRadius();

    int getStrokeColor();

    float getStrokeWidth();

    float getZIndex();

    boolean isVisible();

    void remove();

    void setCenter(@NonNull final OPFLatLng center);

    void setFillColor(final int color);

    void setRadius(final double radius);

    void setStrokeColor(final int color);

    void setStrokeWidth(final float width);

    void setVisible(final boolean visible);

    void setZIndex(final float zIndex);
}
