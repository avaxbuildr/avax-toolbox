/*
 *
 * AVAX Toolbox - An Avalanche Toolbox
 * Copyright (C) 2023 @REKTBuildr
 *
 *
 * For more information, visit:
 * https://crypto.bi
 *
 *
 **/
package to.avax.toolbox.gui.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ToolboxUtils {
    public static Image getImage(String filename) {
        try {
            return ImageIO.read(Objects.requireNonNull(ToolboxUtils.class.getResourceAsStream(
                    "/" + filename)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
