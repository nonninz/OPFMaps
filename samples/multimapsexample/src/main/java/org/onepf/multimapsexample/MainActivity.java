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

package org.onepf.multimapsexample;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.onepf.maps.amazon.AmazonMapProvider;
import org.onepf.maps.google.GoogleMapProvider;
import org.onepf.opfmaps.OPFMapOptions;
import org.onepf.opfmaps.listener.OPFOnMapClickListener;
import org.onepf.opfmaps.OPFOnMapConfigureListener;
import org.onepf.opfmaps.OPFMapProvider;
import org.onepf.opfmaps.OPFMapConfiguration;
import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.OPFOnMapLoadListener;
import org.onepf.opfmaps.listener.OPFOnMarkerClickListener;
import org.onepf.opfmaps.listener.OPFOnMarkerDragListener;
import org.onepf.opfmaps.model.OPFCircle;
import org.onepf.opfmaps.model.OPFInfoWindowAdapter;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFMarker;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private OPFMap map;

    @SuppressWarnings("PMD.ExcessiveMethodLength")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = (OPFMap) findViewById(R.id.map);
        List<OPFMapProvider> providers = new ArrayList<OPFMapProvider>();
        providers.add(new GoogleMapProvider());
        providers.add(new AmazonMapProvider());
        OPFMapConfiguration opfMapConfiguration = new OPFMapConfiguration(providers);

        OPFMapOptions options = new OPFMapOptions()
                .tiltGesturesEnabled(true)
                .zoomGesturesEnabled(true)
                .compassEnabled(true)
                .rotateGesturesEnabled(true);
        map.init(getFragmentManager(), opfMapConfiguration, new OPFOnMapConfigureListener() {
            @Override
            public void onError(int r) {
                //will be implemented later
            }

            @Override
            public void onConfigure(OPFMapProvider provider) {
                map.load(new OPFOnMapLoadListener() {
                    @Override
                    public void onMapLoad() {
                        map.setMapType(OPFMap.MapType.HYBRID);
                        map.setMyLocationEnabled(true);
                        map.setBuildingsEnabled(true);
//                        map.zoom((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2f);

                        //CHECKSTYLE:OFF
                        //markers
                        OPFMarker marker1 = new OPFMarker.Builder().setVisible(true)
                                .setLatLng(new OPFLatLng(37.773975, -122.40205))
                                .setTitle("marker #1")
                                .setSnippet("snippet #1")
                                .setIcon(R.drawable.ic_plusone_tall_off_client)
                                .setDraggable(true).build();
                        map.addMarker(marker1);

                        OPFMarker marker2 = new OPFMarker.Builder().setVisible(true)
                                .setLatLng(new OPFLatLng(55.752004, 37.617017))
                                .setTitle("marker #2")
                                .setSnippet("snippet #2")
                                .setIcon(R.drawable.ic_plusone_tall_off_client)
                                .setDraggable(true).build();
                        map.addMarker(marker2);

                        //circle
                        OPFCircle.Builder builder = new OPFCircle.Builder();
                        builder.setCenter(new OPFLatLng(55.752004, 37.617017))
                                .setRadius(1000000.0)
                                .setFillColor(Color.CYAN)
                                .setStrokeColor(Color.BLUE);
                        //CHECKSTYLE:ON
                        OPFCircle circle = builder.build();
                        map.addCircle(circle);

                        map.setOnMarkerDragListener(new OPFOnMarkerDragListener() {
                            @Override
                            public void onMarkerDragStart(@NonNull OPFMarker marker) {
                                //ignore
                            }

                            @Override
                            public void onMarkerDrag(@NonNull OPFMarker marker) {
                                //ignore
                            }

                            @Override
                            public void onMarkerDragEnd(@NonNull OPFMarker marker) {
                                //ignore
                            }
                        });

                        map.setOnMarkerClickListener(new OPFOnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(@NonNull OPFMarker marker) {
                                //ignore
                                return false;
                            }
                        });

                        map.setOnMapClickListener(new OPFOnMapClickListener() {
                            @Override
                            public void onMapClick(OPFLatLng latLng) {
                                //ignore
                            }
                        });

                        map.setInfoWindowAdapter(new OPFInfoWindowAdapter() {
                            @Override
                            public View getInfoWindow(OPFMarker marker) {
                                //noinspection InflateParams
                                View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.info_window, null);
                                TextView title = (TextView) inflate.findViewById(R.id.title);
                                title.setText(marker.getTitle());
                                TextView snippet = (TextView) inflate.findViewById(R.id.snippet);
                                snippet.setText(marker.getSnippet());
                                return inflate;
                            }

                            @Override
                            public View getInfoContents(OPFMarker marker) {
                                return null;
                            }
                        });

                    }

                    @Override
                    public void onError() {
                        //ignore
                    }
                });
            }
        }, options);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
