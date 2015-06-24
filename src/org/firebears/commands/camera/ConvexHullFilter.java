package org.firebears.commands.camera;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.Image;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 * Fill in enclosed areas.
 */
public class ConvexHullFilter implements Filter {

    boolean connectivity8 = false;
    private ProcessingPlan plan = null;

    public ConvexHullFilter(boolean conn8) {
        this.connectivity8 = conn8;
    }

    /**
     * Fill in enclosed areas.
     *
     * @param image Input {@link BinaryImage}.
     * @return output {@link BinaryImage}.
     */
    public Image process(Image image) throws NIVisionException {
        BinaryImage inputImg = (BinaryImage) image;
        BinaryImage outputImg = inputImg.convexHull(connectivity8);
        return outputImg;
    }

    public void setPlan(ProcessingPlan plan) {
        this.plan = plan;
    }

    public String toString() {
        return "ConvexHullFilter[" + connectivity8 + "]";
    }
}
