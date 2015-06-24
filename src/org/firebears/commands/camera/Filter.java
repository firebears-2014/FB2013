package org.firebears.commands.camera;

import edu.wpi.first.wpilibj.image.Image;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 * Transformer for {@link Image} objects.
 */
public interface Filter {

    /**
     * Generate a new {@code Image} based on another. Note that these new images
     * will not be garbage collected by Java. You will need to eventually "free"
     * the image, or you may run out of memory.
     */
    public Image process(Image image) throws NIVisionException;

    /**
     * Set a pointer back to the owning {@code ProcessingPlan}.
     */
    public void setPlan(ProcessingPlan plan);
}
