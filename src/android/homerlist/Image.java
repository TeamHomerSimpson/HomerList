package android.homerlist;

import java.util.Date;
import java.util.UUID;

import com.telerik.everlive.sdk.core.model.base.DataItem;
import com.telerik.everlive.sdk.core.serialization.ServerProperty;
import com.telerik.everlive.sdk.core.serialization.ServerType;
@ServerType("image")

public class Image extends DataItem {
	@ServerProperty("Title")
	private String title;
	@ServerProperty("Author")
    private String author;
	@ServerProperty("PublicationDate")
    private Date publicationDate;

    private UUID pictureId;
    
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public Date getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

	public UUID getPictureId() {
		return pictureId;
	}

	public void setPictureId(UUID pictureId) {
		this.pictureId = pictureId;
	}
}