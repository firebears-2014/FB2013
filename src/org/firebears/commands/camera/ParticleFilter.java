package org.firebears.commands.camera;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.Image;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 * Filter particles based on {@link CriteriaCollection}.
 */
public class ParticleFilter implements Filter {

    public static final double RECT_MARGIN = 0.80;
    public static final double AREA_MARGIN = 0.50;
    /**
     * Width of the target rectangle, in inches.
     */
    double targetWidth = 24.0;
    /**
     * Width of the target rectangle, in inches.
     */
    double targetHeight = 18.0;
    CriteriaCollection cc;
    private ProcessingPlan plan = null;

    public ParticleFilter(double targetWidth, double targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        double targetArea = targetWidth * targetHeight * 1.3;
        this.cc = new CriteriaCollection();
        this.cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH,
                (int) (targetWidth * (1.0 - RECT_MARGIN)),
                (int) (targetWidth * (1.0 + RECT_MARGIN)), false);
        this.cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT,
                (int) (targetHeight * (1.0 - RECT_MARGIN)),
                (int) (targetHeight * (1.0 + RECT_MARGIN)), false);
        this.cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_CONVEX_HULL_AREA,
                (int) (targetArea * (1.0 - AREA_MARGIN)),
                (int) (targetArea * (1.0 + AREA_MARGIN)), false);
    }

    public Image process(Image image) throws NIVisionException {
        BinaryImage inputImg = (BinaryImage) image;
        BinaryImage outputImg = inputImg.particleFilter(cc);
        return outputImg;
    }

    public void setPlan(ProcessingPlan plan) {
        this.plan = plan;
    }

    public String toString() {
        return "ParticleFilter[" + targetWidth + ", " + targetHeight + "]";
    }
}
