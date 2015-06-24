package org.firebears.commands.camera;

import edu.wpi.first.wpilibj.image.*;

/**
 * Transform a {@link ColorImage} into a {@link BinaryImage} based on hue,
 * saturation, and value thresholds.
 */
public class ThresholdRgbFilter implements Filter {

    private int redMin;
    private int redMax;
    private int greenMin;
    private int greenMax;
    private int blueMin;
    private int blueMax;
    private ProcessingPlan plan;

    /**
     * @param rMin Minimum red, from 0 to 255.
     * @param rMax Maximum red, from 0 to 255.
     * @param gMin Minimum green, from 0 to 255.
     * @param gMax Maximum green, from 0 to 255.
     * @param bMin Minimum blue, from 0 to 255.
     * @param bMax Maximum blue, from 0 to 255.
     */
    public ThresholdRgbFilter(
            int rMin, int rMax,
            int gMin, int gMax,
            int bMin, int bMax) {
        this.redMin = rMin;
        this.redMax = rMax;
        this.greenMin = gMin;
        this.greenMax = gMax;
        this.blueMin = bMin;
        this.blueMax = bMax;
    }

    /**
     * Return a mask of the areas of the image that fall within the given ranges
     * for color values
     *
     * @param image Input {@link ColorImage}.
     * @return output {@link BinaryImage}.
     */
    public Image process(Image image) throws NIVisionException {
        ColorImage inputImg = (ColorImage) image;
        BinaryImage outputImg = inputImg.thresholdRGB(this.redMin,
                this.redMax, this.greenMin, this.greenMax, this.blueMin, this.blueMax);
        return outputImg;
    }

    public void setPlan(ProcessingPlan plan) {
        this.plan = plan;
    }

    public String toString() {
        return "ThresholdRgbFilter["
                + redMin + "," + redMax + ", "
                + greenMin + "," + greenMax + ", "
                + blueMin + "," + blueMax + "]";
    }
}
