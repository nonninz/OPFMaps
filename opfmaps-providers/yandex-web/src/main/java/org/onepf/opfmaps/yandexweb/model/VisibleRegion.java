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

package org.onepf.opfmaps.yandexweb.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.utils.CompareUtils;

/**
 * @author Roman Savin
 * @since 27.08.2015
 */
public final class VisibleRegion implements Parcelable {

    public static final Creator<VisibleRegion> CREATOR = new Creator<VisibleRegion>() {
        @Override
        public VisibleRegion createFromParcel(final Parcel source) {
            return new VisibleRegion(source);
        }

        @Override
        public VisibleRegion[] newArray(final int size) {
            return new VisibleRegion[size];
        }
    };

    @NonNull
    private final LatLng nearLeft;
    @NonNull
    private final LatLng nearRight;
    @NonNull
    private final LatLng farLeft;
    @NonNull
    private final LatLng farRight;
    @NonNull
    private final LatLngBounds latLngBounds;

    public VisibleRegion(@NonNull final LatLng nearLeft,
                         @NonNull final LatLng nearRight,
                         @NonNull final LatLng farLeft,
                         @NonNull final LatLng farRight,
                         @NonNull final LatLngBounds bounds) {
        this.nearLeft = nearLeft;
        this.nearRight = nearRight;
        this.farLeft = farLeft;
        this.farRight = farRight;
        this.latLngBounds = bounds;
    }

    private VisibleRegion(@NonNull final Parcel parcel) {
        this.nearLeft = parcel.readParcelable(LatLng.class.getClassLoader());
        this.nearRight = parcel.readParcelable(LatLng.class.getClassLoader());
        this.farLeft = parcel.readParcelable(LatLng.class.getClassLoader());
        this.farRight = parcel.readParcelable(LatLng.class.getClassLoader());
        this.latLngBounds = parcel.readParcelable(LatLngBounds.class.getClassLoader());
    }

    @NonNull
    public LatLng getNearLeft() {
        return nearLeft;
    }

    @NonNull
    public LatLng getNearRight() {
        return nearRight;
    }

    @NonNull
    public LatLng getFarLeft() {
        return farLeft;
    }

    @NonNull
    public LatLng getFarRight() {
        return farRight;
    }

    @NonNull
    public LatLngBounds getLatLngBounds() {
        return latLngBounds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(nearLeft, flags);
        dest.writeParcelable(nearRight, flags);
        dest.writeParcelable(farLeft, flags);
        dest.writeParcelable(farRight, flags);
        dest.writeParcelable(latLngBounds, flags);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            final VisibleRegion other = (VisibleRegion) obj;

            return CompareUtils.isEquals(this.nearLeft, other.nearLeft)
                    && CompareUtils.isEquals(this.nearRight, other.nearRight)
                    && CompareUtils.isEquals(this.farLeft, other.farLeft)
                    && CompareUtils.isEquals(this.farRight, other.farRight)
                    && CompareUtils.isEquals(this.latLngBounds, other.latLngBounds);
        }
    }

    @Override
    public int hashCode() {
        int result = nearLeft.hashCode();
        result = 31 * result + nearRight.hashCode();
        result = 31 * result + farLeft.hashCode();
        result = 31 * result + farRight.hashCode();
        result = 31 * result + latLngBounds.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "VisibleRegion{nearLeft=" + this.nearLeft + ", nearRight=" + this.nearRight
                + ", farLeft=" + this.farLeft + ", farRight=" + this.farRight
                + ", latLngBounds=" + this.latLngBounds + "}";
    }
}
