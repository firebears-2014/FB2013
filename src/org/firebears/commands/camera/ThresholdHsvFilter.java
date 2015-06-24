package org.firebears.commands.camera;

import edu.wpi.first.wpilibj.image.*;

/**
 * Transform a {@link ColorImage} into a {@link BinaryImage} based on hue,
 * saturation, and value thresholds.
 */
public class ThresholdHsvFilter implements Filter {

    private int hueMin;
    private int hueMax;
    private int satMin;
    private int satMax;
    private int valMin;
    private int valMax;
    ProcessingPlan plan = null;

    /**
     * @param hMin Minimum hue, from 0 to 255.
     * @param hMax Maximum hue, from 0 to 255.
     * @param sMin Minimum saturation, from 0 to 255.
     * @param sMax Maximum saturation, from 0 to 255.
     * @param vMin Minimum value, from 0 to 255.
     * @param vMax Maximum value, from 0 to 255.
     */
    public ThresholdHsvFilter(
            int hMin, int hMax,
            int sMin, int sMax,
            int vMin, int vMax) {
        this.hueMin = hMin;
        this.hueMax = hMax;
        this.satMin = sMin;
        this.satMax = sMax;
        this.valMin = vMin;
        this.valMax = vMax;
    }

    /**
     * Return a mask of the areas of the image that fall within the given ranges
     * for hue values
     *
     * @param image Input {@link ColorImage}.
     * @return output {@link BinaryImage}.
     */
    public Image process(Image image) throws NIVisionException {
        ColorImage inputImg = (ColorImage) image;
        BinaryImage outputImg = inputImg.thresholdHSV(this.hueMin,
                this.hueMax, this.satMin, this.satMax, this.valMin, this.valMax);
        return outputImg;
    }

    public void setPlan(ProcessingPlan plan) {
        this.plan = plan;
    }

    public String toString() {
        return "ThresholdHsvFilter["
                + hueMin + "," + hueMax + ", "
                + satMin + "," + satMax + ", "
                + valMin + "," + valMax + "]";
    }
}
