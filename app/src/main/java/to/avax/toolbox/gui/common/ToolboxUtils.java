/*
 *
 * AVAX Toolbox - An Avalanche Toolbox
 * Copyright (C) 2023 AVAX Buildr @avaxbuildr
 *
 *
 * For more information, visit:
 * https://crypto.bi
 * https://avax.to
 * https://twitter.com/avaxbuildr
 *
 *
 **/
package to.avax.toolbox.gui.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ToolboxUtils {
    public static Image getImage(String filename) {
        try {
            return ImageIO.read(ToolboxUtils.class.getResourceAsStream(
                    "/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
