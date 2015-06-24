package org.firebears.commands.camera;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 * Processing plan using target width height rather than width.
 */
public class RectangleTargetPlan2 extends RectangleTargetPlan {

    public final double DISTANCE_FUDGE = 1.21;

    public RectangleTargetPlan2(double width, double height) {
        super(width, height);
    }

    /**
     * @return estimated distance in inches.
     */
    public double estimateDistance(ParticleAnalysisReport[] particles) {
        if (particles == null || particles.length == 0) {
            return 0.0;
        }
        ParticleAnalysisReport bestParticle = particles[0];
        double y = this.targetHeight / 2; // half the height of target, in inches
        double y1 = bestParticle.boundingRectHeight / 2.0; // pixels
        double z = (bestParticle.imageHeight * y) / (y1 * 2 * ProcessingPlan.Tv);
        return z * DISTANCE_FUDGE;
    }
}
