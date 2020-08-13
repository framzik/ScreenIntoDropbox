import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

public class Uploader extends Thread {

  private static final String IMAGE_FORMAT = "png";
  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
  private String imageName = dateFormat.format(new Date());
  private String imageFilename = imageName + "." + IMAGE_FORMAT;

  private DbxClientV2 client;
  private BufferedImage image;

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public void setClient(DbxClientV2 client) {
    this.client = client;
  }

  @Override
  public void run() {
    if (image != null) {
      try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, IMAGE_FORMAT, baos);
        InputStream in = new ByteArrayInputStream(baos.toByteArray());
        FileMetadata metadata = client.files().uploadBuilder("/" + imageFilename)
            .uploadAndFinish(in);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

  }
}
