package org.firebears.commands.camera;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 * Plan for finding rectangular targets.
 */
public class RectangleTargetPlan extends ProcessingPlan {

    /**
     * @param width Width of the target rectangle, in inches.
     * @param height Height of the target rectangle, in inches.
     */
    public RectangleTargetPlan(double width, double height) {
        this.targetWidth = width;
        this.targetHeight = height;

        this.addFilter(new ThresholdHsvFilter(54, 139, 0, 255, 20, 255));
        this.addFilter(new RemoveSmallFilter(false, 2));
        this.addFilter(new ConvexHullFilter(false));
//        this.addFilter(new ParticleFilter(this.targetWidth, this.targetHeight));
    }

    /**
     * Score to a particle. Higher scores indicate better particle matches.
     */
    public int score(ParticleAnalysisReport p) {
        return getTargetType(p) * 1000
                + verticalScore(p) * 100
                + ratioScore(p) * 10
                + horizontalScore(p);
    }

    /**
     * Give a score indicating how close the particle's ratio is to the target.
     *
     * @return an integer from 0 to 9.
     */
    protected int ratioScore(ParticleAnalysisReport p) {
        double ratio = (p.boundingRectWidth + 4.0) / (p.boundingRectHeight + 4.0);
//        double goalRatio = targetWidth / targetHeight;
        double goalRatio = 2.7;
        double diff = Math.abs(ratio - goalRatio);
        if (diff < 0.2) {
            return 8;
        }
        if (diff < 0.4) {
            return 7;
        }
        if (diff < 0.6) {
            return 6;
        }
        if (diff < 0.8) {
            return 5;
        }
        if (diff < 1.0) {
            return 4;
        }
        if (diff < 1.5) {
            return 3;
        }
        if (diff < 1.8) {
            return 2;
        }
        if (diff < 2.1) {
            return 1;
        }
        return 0;
    }

    /**
     * Give a score indicating how close the particle is to the horizontal
     * center of the image.
     *
     * @return an integer from 0 to 9.
     */
    protected int horizontalScore(ParticleAnalysisReport p) {
        double s = Math.abs(p.center_mass_x_normalized * 5);
        return 2 * (int) Math.floor(5.0 - s);
    }

    /**
     * Give a score indicating how high up in the image the particle is.
     *
     * @return an integer from 0 to 9.
     */
    protected int verticalScore(ParticleAnalysisReport p) {
        double s = 1.0 - p.center_mass_y_normalized;
        return (int) Math.floor(s * 5);
    }

    /**
     * Target types are: <br/>0 indicates unknown target type <br/>1 indicates
     * the low one point target <br/>2 indicates the middle two point target
     * <br/>3 indicates the high three point target <br/>4 indicates the top of
     * the pyramid
     *
     * @return the type of the target.
     */
    public int getTargetType(ParticleAnalysisReport[] particles) {
        if (particles == null || particles.length == 0) {
            return 0;
        }
        return getTargetType(particles[0]);
    }

    /**
     * Guess target type based on the width to height ratio.
     *
     * @return the type of the target. Zero indicates unknown.
     */
    public int getTargetType(ParticleAnalysisReport p) {
        double ratio = (p.boundingRectWidth + 4.0) / (p.boundingRectHeight + 4.0);
        if (ratio > 2.2) {
            return 3;
        } else if (ratio < 1.2) {
            return 1;
        } else {
            return 2;
        }
    }

    public String toString() {
        return "RectangleTargetPlan[" + targetWidth + "," + targetHeight + "]";
    }
}
