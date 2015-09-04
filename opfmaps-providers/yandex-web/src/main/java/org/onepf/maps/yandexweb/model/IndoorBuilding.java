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

package org.onepf.maps.yandexweb.model;

import android.support.annotation.Nullable;

import org.onepf.opfutils.OPFLog;

import java.util.List;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public final class IndoorBuilding {

    //todo implement

    public int getActiveLevelIndex() {
        OPFLog.logStubCall();
        return 0;
    }

    public int getDefaultLevelIndex() {
        OPFLog.logStubCall();
        return 0;
    }

    @Nullable
    public List<IndoorLevel> getLevels() {
        OPFLog.logStubCall();
        return null;
    }

    public boolean isUnderground() {
        OPFLog.logStubCall();
        return false;
    }

}
