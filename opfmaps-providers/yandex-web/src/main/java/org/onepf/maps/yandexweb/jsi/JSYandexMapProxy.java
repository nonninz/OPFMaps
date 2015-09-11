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

package org.onepf.maps.yandexweb.jsi;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import org.onepf.maps.yandexweb.model.Circle;
import org.onepf.maps.yandexweb.model.LatLng;
import org.onepf.maps.yandexweb.model.Marker;
import org.onepf.maps.yandexweb.model.Polygon;
import org.onepf.maps.yandexweb.utils.ConvertUtils;
import org.onepf.opfmaps.model.OPFMapType;

import java.util.List;

import static java.util.Locale.US;

/**
 * @author Roman Savin
 * @since 08.09.2015
 */
public final class JSYandexMapProxy {

    public static final String FILL_COLOR_OPTION = "fillColor";
    public static final String STROKE_COLOR_OPTION = "strokeColor";
    public static final String STROKE_WIDTH_OPTION = "strokeWidth";
    public static final String Z_INDEX_OPTION = "zIndex";
    public static final String VISIBLE_OPTION = "visible";
    public static final String DRAGGABLE_OPTION = "draggable";
    public static final String ICON_COLOR_OPTION = "iconColor";
    public static final String BALLOON_CONTENT_HEADER_OPTION = "balloonContentHeader";
    public static final String BALLOON_CONTENT_BODY_OPTION = "balloonContentBody";

    private static final String SET_TYPE_FUNCTION_NAME = "setType";
    private static final String SET_CENTER_FUNCTION_NAME = "setCenter";
    private static final String SET_ZOOM_LEVEL_FUNCTION_NAME = "setZoom";
    private static final String SET_MY_LOCATION_ENABLED_FUNCTION_NAME = "setMyLocationEnabled";
    private static final String SET_MY_LOCATION_BUTTON_ENABLED_FUNCTION_NAME = "setMyLocationButtonEnabled";
    private static final String SET_SCROLL_GESTURES_ENABLED_FUNCTION_NAME = "setScrollGesturesEnabled";
    private static final String SET_ZOOM_CONTROLS_ENABLED_FUNCTION_NAME = "setZoomControlsEnabled";
    private static final String SET_ZOOM_GESTURES_ENABLED_FUNCTION_NAME = "setZoomGesturesEnabled";
    private static final String ADD_CIRCLE_FUNCTION_NAME = "addCircle";
    private static final String ADD_MARKER_FUNCTION_NAME = "addMarker";
    private static final String ADD_POLYGON_FUNCTION_NAME = "addPolygon";
    private static final String HIDE_BALLOON_FUNCTION_NAME = "hideBalloon";
    private static final String SHOW_BALLOON_FUNCTION_NAME = "showBalloon";
    private static final String TOGGLE_BALLOON_FUNCTION_NAME = "toggleBalloon";
    private static final String SET_GEO_OBJECT_OPTION = "setGeoObjectOption";
    private static final String SET_GEO_OBJECT_PROPERTY = "setGeoObjectProperty";
    private static final String REMOVE_GEO_OBJECT_FUNCTION_NAME = "removeGeoObject";
    private static final String SET_GEO_OBJECT_COORDINATES_FUNCTION_NAME = "setGeoObjectCoordinates";
    private static final String SET_CIRCLE_RADIUS_FUNCTION_NAME = "setCircleRadius";

    private JSYandexMapProxy() {
        throw new UnsupportedOperationException();
    }

    public static void setMapType(@NonNull final WebView webView,
                                  @NonNull final OPFMapType mapType) {
        evaluateJSFunctionAsync(webView, null, SET_TYPE_FUNCTION_NAME, wrapToQuotes(ConvertUtils.convertMapTypeToJs(mapType)));
    }

    public static void setMapCenter(@NonNull final WebView webView,
                                    @NonNull final LatLng center) {
        evaluateJSFunctionAsync(webView, null, SET_CENTER_FUNCTION_NAME,
                Double.toString(center.getLat()), Double.toString(center.getLng()));
    }

    public static void setZoomLevel(@NonNull final WebView webView,
                                    final float zoomLevel) {
        evaluateJSFunctionAsync(webView, null, SET_ZOOM_LEVEL_FUNCTION_NAME, Float.toString(zoomLevel));
    }

    public static void setMyLocationEnabled(@NonNull final WebView webView,
                                            final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, null, SET_MY_LOCATION_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    public static void setMyLocationButtonEnabled(@NonNull final WebView webView,
                                                  final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, null, SET_MY_LOCATION_BUTTON_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    public static void setScrollGesturesEnabled(@NonNull final WebView webView,
                                                final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, null, SET_SCROLL_GESTURES_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    public static void setZoomControlsEnabled(@NonNull final WebView webView,
                                              final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, null, SET_ZOOM_CONTROLS_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    public static void setZoomGesturesEnabled(@NonNull final WebView webView,
                                              final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, null, SET_ZOOM_GESTURES_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    public static void addCircle(@NonNull final WebView webView,
                                 @NonNull final Circle circle) {

        evaluateJSFunctionAsync(
                webView,
                null,
                ADD_CIRCLE_FUNCTION_NAME,
                wrapToQuotes(circle.getId()),
                Double.toString(circle.getCenter().getLat()),
                Double.toString(circle.getCenter().getLng()),
                Double.toString(circle.getRadius()),
                wrapToQuotes(ConvertUtils.convertColor(circle.getFillColor())),
                wrapToQuotes(ConvertUtils.convertColor(circle.getStrokeColor())),
                Float.toString(circle.getStrokeWidth()),
                Float.toString(circle.getZIndex()),
                Boolean.toString(circle.isVisible())
        );
    }

    public static void addMarker(@NonNull final WebView webView,
                                 @NonNull final Marker marker,
                                 @NonNull final String color) {
        evaluateJSFunctionAsync(
                webView,
                null,
                ADD_MARKER_FUNCTION_NAME,
                wrapToQuotes(marker.getId()),
                Double.toString(marker.getPosition().getLat()),
                Double.toString(marker.getPosition().getLng()),
                wrapToQuotes(marker.getTitle()),
                wrapToQuotes(marker.getSnippet()),
                Boolean.toString(marker.isVisible()),
                Boolean.toString(marker.isDraggable()),
                wrapToQuotes(color)
        );
    }


    public static void addPolygon(@NonNull final WebView webView, @NonNull final Polygon polygon) {
        evaluateJSFunctionAsync(
                webView,
                null,
                ADD_POLYGON_FUNCTION_NAME,
                wrapToQuotes(polygon.getId()),
                formatThreeDimensionalJSLatLngArray(polygon.getPoints(), polygon.getHoles()),
                wrapToQuotes(ConvertUtils.convertColor(polygon.getFillColor())),
                wrapToQuotes(ConvertUtils.convertColor(polygon.getStrokeColor())),
                Float.toString(polygon.getStrokeWidth()),
                Float.toString(polygon.getZIndex()),
                Boolean.toString(polygon.isVisible())
        );
    }

    public static void hideInfoWindow(@NonNull final WebView webView,
                                      @NonNull final String id) {
        evaluateJSFunctionAsync(webView, null, HIDE_BALLOON_FUNCTION_NAME, wrapToQuotes(id));
    }

    public static void showInfoWindow(@NonNull final WebView webView,
                                      @NonNull final String id) {
        evaluateJSFunctionAsync(webView, null, SHOW_BALLOON_FUNCTION_NAME, wrapToQuotes(id));
    }

    public static void toggleInfoWindow(@NonNull final WebView webView,
                                        @NonNull final String id) {
        evaluateJSFunctionAsync(webView, null, TOGGLE_BALLOON_FUNCTION_NAME, wrapToQuotes(id));
    }

    public static void setGeoObjectOption(@NonNull final WebView webView,
                                          @NonNull final String id,
                                          @NonNull final String option,
                                          @NonNull final Object value) {
        setGeoObjectOptionFormatted(webView, id, option, value.toString());
    }

    public static void setGeoObjectOption(@NonNull final WebView webView,
                                          @NonNull final String id,
                                          @NonNull final String option,
                                          @NonNull final String value) {
        setGeoObjectOptionFormatted(webView, id, option, wrapToQuotes(value));
    }

    public static void setGeoObjectProperty(@NonNull final WebView webView,
                                            @NonNull final String id,
                                            @NonNull final String option,
                                            @NonNull final Object value) {
        setGeoObjectPropertyFormatted(webView, id, option, value.toString());
    }

    public static void setGeoObjectProperty(@NonNull final WebView webView,
                                            @NonNull final String id,
                                            @NonNull final String option,
                                            @NonNull final String value) {
        setGeoObjectPropertyFormatted(webView, id, option, wrapToQuotes(value));
    }

    public static void setGeoObjectCoordinates(@NonNull final WebView webView,
                                               @NonNull final String id,
                                               @NonNull final LatLng center) {
        evaluateJSFunctionAsync(webView, null, SET_GEO_OBJECT_COORDINATES_FUNCTION_NAME,
                wrapToQuotes(id), formatJSLatLngArray(center));
    }

    public static void setGeoObjectCoordinates(@NonNull final WebView webView,
                                               @NonNull final String id,
                                               @NonNull final List<LatLng> points,
                                               @Nullable final List<List<LatLng>> holes) {
        evaluateJSFunctionAsync(webView, null, SET_GEO_OBJECT_COORDINATES_FUNCTION_NAME, wrapToQuotes(id), formatThreeDimensionalJSLatLngArray(points, holes));
    }

    public static void setCircleRadius(@NonNull final WebView webView,
                                       @NonNull final String id,
                                       final double radius) {
        evaluateJSFunctionAsync(webView, null, SET_CIRCLE_RADIUS_FUNCTION_NAME, wrapToQuotes(id), Double.toString(radius));
    }

    public static void removeGeoObject(@NonNull final WebView webView,
                                       @NonNull final String id) {
        evaluateJSFunctionAsync(webView, null, REMOVE_GEO_OBJECT_FUNCTION_NAME, wrapToQuotes(id));
    }

    private static void setGeoObjectOptionFormatted(@NonNull final WebView webView,
                                                    @NonNull final String id,
                                                    @NonNull final String option,
                                                    @NonNull final String value) {
        evaluateJSFunctionAsync(webView, null, SET_GEO_OBJECT_OPTION, wrapToQuotes(id), wrapToQuotes(option), value);
    }

    private static void setGeoObjectPropertyFormatted(@NonNull final WebView webView,
                                                      @NonNull final String id,
                                                      @NonNull final String property,
                                                      @NonNull final String value) {
        evaluateJSFunctionAsync(webView, null, SET_GEO_OBJECT_PROPERTY, wrapToQuotes(id), wrapToQuotes(property), value);
    }

    private static void evaluateJSFunctionAsync(@NonNull final WebView webView,
                                                @Nullable final ValueCallback<String> resultCallback,
                                                @NonNull final String function,
                                                @Nullable final String... params) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript(formatScript(function, params), resultCallback);
        } else {
            //todo make in worker thread
            webView.loadUrl("javascript:" + formatScript(function, params));
        }
    }

    private static String formatThreeDimensionalJSLatLngArray(@NonNull final List<LatLng> points,
                                                              @Nullable final List<List<LatLng>> holes) {
        final StringBuilder stringBuilder = new StringBuilder("[[");

        for (LatLng point : points) {
            stringBuilder.append('[').append(point.getLat()).append(',').append(point.getLng()).append("],");
        }
        stringBuilder.append("],");

        if (holes != null) {
            for (List<LatLng> hole : holes) {
                stringBuilder.append('[');
                for (LatLng holePoint : hole) {
                    stringBuilder.append('[').append(holePoint.getLat()).append(',').append(holePoint.getLng()).append("],");
                }
                stringBuilder.append("],");
            }
        }

        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private static String formatJSLatLngArray(@NonNull final LatLng latLng) {
        return String.format(US, "[%s, %s]", latLng.getLat(), latLng.getLng());
    }

    @NonNull
    private static String wrapToQuotes(@Nullable final String param) {
        return String.format(US, "'%s'", param != null ? param : "");
    }

    @NonNull
    private static String formatScript(@NonNull final String function,
                                       @Nullable final String[] params) {
        final StringBuilder builder = new StringBuilder(function).append('(');
        if (params != null) {
            final int length = params.length;
            for (int i = 0; i < length; ++i) {
                builder.append(params[i]);
                if (i != length - 1) {
                    builder.append(',');
                }
            }
        }

        builder.append(')');
        return builder.toString();
    }
}