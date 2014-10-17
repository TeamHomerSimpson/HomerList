package android.homerlist;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationActivity extends Activity implements LocationListener, OnMarkerDragListener{

	private GoogleMap map;
	private LocationManager locationManager;
	private static final long MIN_TIME = 400;
	private static final float MIN_DISTANCE = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_activity);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this); //You can also use LocationManager.GPS_PROVIDER and LocationManager.PASSIVE_PROVIDER        
	
	    map.setOnMarkerDragListener(this);
	}

	public void onLocationChanged(Location location) {
		LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
	    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14);
	    map.animateCamera(cameraUpdate);
	    locationManager.removeUpdates(this);
	    
	    @SuppressWarnings("unused")
		Marker userMarker = map.addMarker(new MarkerOptions()
	                              .position(latLng)
	                              .draggable(true));
	}

	@SuppressWarnings("deprecation")
	public void onMarkerDragEnd(Marker marker) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Is this your desired location?");
		alertDialog.setMessage(String.valueOf(marker.getPosition()));
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		   public void onClick(DialogInterface dialog, int which) {
		      // TODO Add your code for the button here.
		   }
		});
		// Set the Icon for the Dialog
		alertDialog.show();
						
	}

	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onMarkerDragStart(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}