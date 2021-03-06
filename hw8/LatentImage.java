import java.util.*;
import java.util.function.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

class LatentImage {
   private Image in;
   private List<UnaryOperator<Color>> pendingOperations;

   public static LatentImage from(Image in) {
      LatentImage result = new LatentImage();
      result.in = in;
      result.pendingOperations = new ArrayList<>();
      return result;
   }

   LatentImage transform(UnaryOperator<Color> f) {
      pendingOperations.add(f);
      return this;
   }

   public Image toImage() {
      int width = (int) in.getWidth();
      int height = (int) in.getHeight();
      WritableImage out = new WritableImage(width, height);
      for (int x = 0; x < width; x++)
         for (int y = 0; y < height; y++) {
            Color c = in.getPixelReader().getColor(x, y);
            for (UnaryOperator<Color> f : pendingOperations) c = f.apply(c);
            out.getPixelWriter().setColor(x, y, c);
         }
      return out;
   }
}
