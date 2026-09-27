package pguel.computacao_grafica.common.util;

import pguel.computacao_grafica.common.model.Point;
import pguel.computacao_grafica.common.model.OrthoBounds;
import pguel.computacao_grafica.common.model.WindowSize;

public class CoordUtils {
    public static Point mapWindowToOrtho(Point pixelPoint, OrthoBounds ortho, WindowSize window){
        float xm = ortho.getLeft() + (ortho.getRight() - ortho.getLeft()) * pixelPoint.getX() / window.getWidth();
        float ym = ortho.getTop() + (ortho.getBottom() - ortho.getTop()) * pixelPoint.getY() / window.getHeight();
        return new Point(xm, ym);
    }
}
