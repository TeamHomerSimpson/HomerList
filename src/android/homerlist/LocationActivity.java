package android.homerlist;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.os.Bundle;

public class LocationActivity extends Activity {

	private static final LatLng SYDNEY = new LatLng(-33.88,151.21);
	private static final LatLng MOUNTAIN_VIEW = new LatLng(37.4, -122.1);
	private GoogleMap map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_activity);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

		
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, 15));

		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomIn());

		// Zoom out to zoom level 10, animating with a duration of 2 seconds.
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

		// Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
		/*CameraPosition cameraPosition = new CameraPosition.Builder()
		    .target(MOUNTAIN_VIEW)      // Sets the center of the map to Mountain View
		    .zoom(17)                   // Sets the zoom
		    .bearing(90)                // Sets the orientation of the camera to east
		    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
		    .build();                   // Creates a CameraPosition from the builder
		map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/
		
	}

}