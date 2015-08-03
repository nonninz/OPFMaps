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

package org.onepf.maps.amazon.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.amazon.geo.mapsv2.model.BitmapDescriptor;
import com.amazon.geo.mapsv2.model.GroundOverlayOptions;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.LatLngBounds;
import org.onepf.opfmaps.delegate.model.GroundOverlayOptionsDelegate;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class AmazonGroundOverlayOptionsDelegate implements GroundOverlayOptionsDelegate {

    public static final Creator<AmazonGroundOverlayOptionsDelegate> CREATOR = new Creator<AmazonGroundOverlayOptionsDelegate>() {
        @Override
        public AmazonGroundOverlayOptionsDelegate createFromParcel(final Parcel source) {
            return new AmazonGroundOverlayOptionsDelegate(source);
        }

        @Override
        public AmazonGroundOverlayOptionsDelegate[] newArray(final int size) {
            return new AmazonGroundOverlayOptionsDelegate[size];
        }
    };

    @NonNull
    private final GroundOverlayOptions groundOverlayOptions;

    public AmazonGroundOverlayOptionsDelegate() {
        this.groundOverlayOptions = new GroundOverlayOptions();
    }

    private AmazonGroundOverlayOptionsDelegate(@NonNull final Parcel parcel) {
        this.groundOverlayOptions = parcel.readParcelable(GroundOverlayOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public AmazonGroundOverlayOptionsDelegate anchor(final float u, final float v) {
        groundOverlayOptions.anchor(u, v);
        return this;
    }

    @NonNull
    @Override
    public AmazonGroundOverlayOptionsDelegate bearing(final float bearing) {
        groundOverlayOptions.bearing(bearing);
        return this;
    }

    @Override
    public float getAnchorU() {
        return groundOverlayOptions.getAnchorU();
    }

    @Override
    public float getAnchorV() {
        return groundOverlayOptions.getAnchorV();
    }

    @Override
    public float getBearing() {
        return groundOverlayOptions.getBearing();
    }

    @Nullable
    @Override
    public OPFLatLngBounds getBounds() {
        return new OPFLatLngBounds(new AmazonLatLngBoundsDelegate(groundOverlayOptions.getBounds()));
    }

    @Override
    public float getHeight() {
        return groundOverlayOptions.getHeight();
    }

    @Nullable
    @Override
    public OPFBitmapDescriptor getImage() {
        return new OPFBitmapDescriptor(new AmazonBitmapDescriptorDelegate(groundOverlayOptions.getImage()));
    }

    @Nullable
    @Override
    public OPFLatLng getLocation() {
        return new OPFLatLng(new AmazonLatLngDelegate(groundOverlayOptions.getLocation()));
    }

    @Override
    public float getTransparency() {
        return groundOverlayOptions.getTransparency();
    }

    @Override
    public float getWidth() {
        return groundOverlayOptions.getWidth();
    }

    @Override
    public float getZIndex() {
        return groundOverlayOptions.getZIndex();
    }

    @NonNull
    @Override
    public AmazonGroundOverlayOptionsDelegate image(@NonNull final OPFBitmapDescriptor image) {
        groundOverlayOptions.image((BitmapDescriptor) image.getDelegate().getBitmapDescriptor());
        return this;
    }

    @Override
    public boolean isVisible() {
        return groundOverlayOptions.isVisible();
    }

    @NonNull
    @Override
    public AmazonGroundOverlayOptionsDelegate position(@NonNull final OPFLatLng location,
                                                       final float width,
                                                       final float height) {
        groundOverlayOptions.position(new LatLng(location.getLat(), location.getLng()), width, height);
        return this;
    }

    @NonNull
    @Override
    public AmazonGroundOverlayOptionsDelegate position(@NonNull final OPFLatLng location, final float width) {
        groundOverlayOptions.position(new LatLng(location.getLat(), location.getLng()), width);
        return this;
    }

    @NonNull
    @Override
    public AmazonGroundOverlayOptionsDelegate positionFromBounds(@NonNull final OPFLatLngBounds bounds) {
        groundOverlayOptions.positionFromBounds(new LatLngBounds(
                new LatLng(bounds.getSouthwest().getLat(), bounds.getNortheast().getLng()),
                new LatLng(bounds.getNortheast().getLat(), bounds.getNortheast().getLng())
        ));
        return this;
    }

    @NonNull
    @Override
    public AmazonGroundOverlayOptionsDelegate transparency(final float transparency) {
        groundOverlayOptions.transparency(transparency);
        return this;
    }

    @NonNull
    @Override
    public AmazonGroundOverlayOptionsDelegate visible(final boolean visible) {
        groundOverlayOptions.visible(visible);
        return this;
    }

    @NonNull
    @Override
    public AmazonGroundOverlayOptionsDelegate zIndex(final float zIndex) {
        groundOverlayOptions.zIndex(zIndex);
        return this;
    }

    @Override
    public int describeContents() {
        return groundOverlayOptions.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(groundOverlayOptions, flags);
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof AmazonGroundOverlayOptionsDelegate)) return false;

        return groundOverlayOptions.equals(((AmazonGroundOverlayOptionsDelegate) other).groundOverlayOptions);
    }

    @Override
    public int hashCode() {
        return groundOverlayOptions.hashCode();
    }

    @Override
    public String toString() {
        return groundOverlayOptions.toString();
    }
}
