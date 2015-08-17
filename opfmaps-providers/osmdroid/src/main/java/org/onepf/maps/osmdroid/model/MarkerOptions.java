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

package org.onepf.maps.osmdroid.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.osmdroid.util.GeoPoint;

import static org.onepf.maps.osmdroid.utils.CompareUtils.isEquals;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public final class MarkerOptions implements Parcelable {

    public static final Creator<MarkerOptions> CREATOR = new Creator<MarkerOptions>() {
        @Override
        public MarkerOptions createFromParcel(final Parcel source) {
            return new MarkerOptions(source);
        }

        @Override
        public MarkerOptions[] newArray(final int size) {
            return new MarkerOptions[size];
        }
    };

    @Nullable
    private GeoPoint position = null;
    @Nullable
    private String title = null;
    @Nullable
    private BitmapDescriptor icon = null;
    @Nullable
    private String snippet = null;
    private float alpha = 1.0F;
    private float anchorU = 0.5F;
    private float anchorV = 1.0F;
    private boolean isDraggable = false;
    private boolean isFlat = false;
    private float infoWindowAnchorU = 0.5F;
    private float infoWindowAnchorV = 0.0F;
    private float rotation = 0.0F;
    private boolean isVisible = true;

    public MarkerOptions() {
    }

    private MarkerOptions(@NonNull final Parcel parcel) {
        this.position = parcel.readParcelable(GeoPoint.class.getClassLoader());
        this.title = parcel.readString();
        this.icon = parcel.readParcelable(BitmapDescriptor.class.getClassLoader());
        this.snippet = parcel.readString();
        this.alpha = parcel.readFloat();
        this.anchorU = parcel.readFloat();
        this.anchorV = parcel.readFloat();
        this.isDraggable = parcel.readByte() != 0;
        this.isFlat = parcel.readByte() != 0;
        this.infoWindowAnchorU = parcel.readFloat();
        this.infoWindowAnchorV = parcel.readFloat();
        this.rotation = parcel.readFloat();
        this.isVisible = parcel.readByte() != 0;
    }

    @NonNull
    public MarkerOptions alpha(final float alpha) {
        this.alpha = alpha;
        return this;
    }

    @NonNull
    public MarkerOptions anchor(final float u, final float v) {
        this.anchorU = u;
        this.anchorV = v;
        return this;
    }

    @NonNull
    public MarkerOptions draggable(final boolean draggable) {
        this.isDraggable = draggable;
        return this;
    }

    @NonNull
    public MarkerOptions flat(final boolean flat) {
        this.isFlat = flat;
        return this;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public float getAnchorU() {
        return this.anchorU;
    }

    public float getAnchorV() {
        return this.anchorV;
    }

    @Nullable
    public BitmapDescriptor getIcon() {
        return this.icon;
    }

    public float getInfoWindowAnchorU() {
        return this.infoWindowAnchorU;
    }

    public float getInfoWindowAnchorV() {
        return this.infoWindowAnchorV;
    }

    @Nullable
    public GeoPoint getPosition() {
        return this.position;
    }

    public float getRotation() {
        return this.rotation;
    }

    @Nullable
    public String getSnippet() {
        return this.snippet;
    }

    @Nullable
    public String getTitle() {
        return this.title;
    }

    @NonNull
    public MarkerOptions icon(@NonNull final BitmapDescriptor icon) {
        this.icon = icon;
        return this;
    }

    @NonNull
    public MarkerOptions infoWindowAnchor(final float u, final float v) {
        this.infoWindowAnchorU = u;
        this.infoWindowAnchorV = v;
        return this;
    }

    public boolean isDraggable() {
        return this.isDraggable;
    }

    public boolean isFlat() {
        return this.isFlat;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    @NonNull
    public MarkerOptions position(@NonNull final GeoPoint position) {
        this.position = position;
        return this;
    }

    @NonNull
    public MarkerOptions rotation(final float rotation) {
        this.rotation = rotation;
        return this;
    }

    @NonNull
    public MarkerOptions snippet(@NonNull final String snippet) {
        this.snippet = snippet;
        return this;
    }

    @NonNull
    public MarkerOptions title(@NonNull final String title) {
        this.title = title;
        return this;
    }

    @NonNull
    public MarkerOptions visible(final boolean visible) {
        this.isVisible = visible;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(position, flags);
        dest.writeString(title);
        dest.writeParcelable(icon, flags);
        dest.writeString(snippet);
        dest.writeFloat(alpha);
        dest.writeFloat(anchorU);
        dest.writeFloat(anchorV);
        dest.writeByte((byte) (isDraggable ? 1 : 0));
        dest.writeByte((byte) (isFlat ? 1 : 0));
        dest.writeFloat(infoWindowAnchorU);
        dest.writeFloat(infoWindowAnchorV);
        dest.writeFloat(rotation);
        dest.writeByte((byte) (isVisible ? 1 : 0));
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof MarkerOptions)) {
            return false;
        } else {
            final MarkerOptions other = (MarkerOptions) obj;

            return isEquals(this.position, other.position)
                    && isEquals(this.title, other.title)
                    && isEquals(this.icon, other.title)
                    && isEquals(this.snippet, other.snippet)
                    && this.alpha == other.alpha
                    && this.anchorU == other.anchorU
                    && this.anchorV == other.anchorV
                    && this.isDraggable == other.isDraggable
                    && this.isFlat == other.isFlat
                    && this.infoWindowAnchorU == other.infoWindowAnchorU
                    && this.infoWindowAnchorV == other.infoWindowAnchorV
                    && this.rotation == other.rotation
                    && this.isVisible == other.isVisible;
        }
    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (snippet != null ? snippet.hashCode() : 0);
        result = 31 * result + (alpha != +0.0f ? Float.floatToIntBits(alpha) : 0);
        result = 31 * result + (anchorU != +0.0f ? Float.floatToIntBits(anchorU) : 0);
        result = 31 * result + (anchorV != +0.0f ? Float.floatToIntBits(anchorV) : 0);
        result = 31 * result + (isDraggable ? 1 : 0);
        result = 31 * result + (isFlat ? 1 : 0);
        result = 31 * result + (infoWindowAnchorU != +0.0f ? Float.floatToIntBits(infoWindowAnchorU) : 0);
        result = 31 * result + (infoWindowAnchorV != +0.0f ? Float.floatToIntBits(infoWindowAnchorV) : 0);
        result = 31 * result + (rotation != +0.0f ? Float.floatToIntBits(rotation) : 0);
        result = 31 * result + (isVisible ? 1 : 0);
        return result;
    }
}
