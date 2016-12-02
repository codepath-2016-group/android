package com.codepath.apps.findmate.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Make sure you have included the Android Maps Utility library
 * See: https://developers.google.com/maps/documentation/android-api/utility/
 *
 * Gradle config:
 *
 * dependencies {
 *   'com.google.maps.android:android-maps-utils:0.4+'
 * }
 */

public class MapUtils {

    public static BitmapDescriptor createBubble(Context context, int style, String title) {
        IconGenerator iconGenerator = new IconGenerator(context);
        iconGenerator.setStyle(style);
        Bitmap bitmap = iconGenerator.makeIcon(title);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
        return bitmapDescriptor;
    }

    public static void createBubbleFromImage(Context context, String urlPath, SimpleTarget<Bitmap> target) {

        Glide.with(context)
                .load(urlPath)
                .asBitmap()
                .transform(new CropCircleTransformation(context))
                .into(target);
    }

    public static Marker addMarker(GoogleMap map, LatLng point, String title,
                                   String snippet,
                                   BitmapDescriptor marker) {
        // Creates and adds marker to the map
        MarkerOptions options = new MarkerOptions()
                .position(point)
                .title(title)
//                .snippet(snippet)
                .icon(marker);
        return map.addMarker(options);
    }

//    public static void addProfilePicMarker(Context content, final User member, final GoogleMap map) {
//        MapUtils.createBubbleFromImage(content, member.getProfilePic(), new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
//                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
//
//                MapUtils.addMarker(map,new LatLng(member.getLocation().getLatitude(),
//                        member.getLocation().getLongitude()), member.getFullName(), member.getFullName(),bitmapDescriptor);
//            }
//
//        });
//    }
}
