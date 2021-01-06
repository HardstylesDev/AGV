package agv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.imageio.ImageIO;

import agv.utils.Database;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;


public class Camera extends Component {
    /**
     * Met deze class maken we foto's en scannen we deze ook voor een QR-Code.
     */
    public Camera() {
        super("Camera");
    }

    String code;

    @Override
    public void onEnable() {
        snap();
    }

    public void snap() {
        Runtime rt = Runtime.getRuntime();
        String dateTime;
        String fileName;
        BinaryBitmap bitmap = null;
        int count = 1;
        for (int i = 1; i <= count; i++) {
            System.out.println("Started taking the snap: " + i);
            dateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            fileName = "snap_" + dateTime + ".png";
            Process snap;
            try {
                snap = rt.exec("raspistill --timeout 5 --output " + fileName + " --nopreview");
                snap.waitFor(); // Sync
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            File imageFile = new File(fileName);
            if (imageFile.exists()) {
                //System.out.println("imageFile exists..");
                System.out.println("About to scan the snap file: " + fileName + " for the QR code..");
                try {
                    BufferedImage image = ImageIO.read(imageFile);
                    int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
                    RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels);
                    bitmap = new BinaryBitmap(new HybridBinarizer(source));

                } catch (IOException e) {
                    System.out.println("Error while reading the image:" + imageFile);
                    e.printStackTrace();
                }
                if (bitmap == null) {
                    System.out.println("Bitmap is null as the Image is not in a recognizable format, hence the Decode is unsuccessful!!");
                }
                QRCodeReader reader = new QRCodeReader();
                try {
                    Result result = reader.decode(bitmap);

                    System.out.println("Scan Decode is successful!!! The decoded QR Code text is:\n");
                    System.out.println(result.getText() + "\n");
                    System.out.println(imageFile.getPath());
                    code = result.getText();
                    insert(result.getText());
                    System.out.println("========End of processing the snap " + i + "========");
                } catch (Exception e) {
                    System.out.println("Error");
                }
            }
        }
    }

    /**
     * zet de waarde van de camera in de database
     * @param waarde de gescande waarde
     */
    private void insert(String waarde) {
        Database db = new Database();
        db.connect();
        db.query(String.format("INSERT INTO `scan`(`scan`) VALUES (\"%s\")", waarde));
        db.disconnect();
    }
}