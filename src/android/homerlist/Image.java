package android.homerlist;

import java.util.UUID;

import com.telerik.everlive.sdk.core.model.base.DataItem;
import com.telerik.everlive.sdk.core.serialization.ServerProperty;
import com.telerik.everlive.sdk.core.serialization.ServerType;
@ServerType("Image")

public class Image extends DataItem {
	@ServerProperty("Name")
	private String name;
	
	@ServerProperty("Image")
    private UUID pictureId;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    public UUID getPictureId() {
		return pictureId;
	}

	public void setPictureId(UUID pictureId) {
		this.pictureId = pictureId;
	}	
}