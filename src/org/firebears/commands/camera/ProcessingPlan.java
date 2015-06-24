package org.firebears.commands.camera;

import java.util.Vector;
import com.sun.squawk.util.Comparer;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.image.*;

/**
 * Encapsulates logic for processing particles within an image. A
 * {@code ProcessingPlan} contains an ordered list of {@link Filter Filters} to
 * be applied to the image. Also, this object can be used to sort a list of
 * particles so the best particles are at the beginning of the list.
 */
public abstract class ProcessingPlan implements Comparer {

    /**
     * Vertical tangent of half the vertical viewing angle. At any distance this
     * should be the distance from the middle of the frame to the top of the
     * frame, divided by the distance to the target.
     */
    final public static double Tv = 0.3839; // tangent of 21 degrees
    /**
     * Horizontal tangent of half the horizontal viewing angle. At any distance
     * this should be the distance from the middle of the frame to the side of
     * the frame, divided by the distance to the target.
     */
    final public static double Th = 0.5095; // tangent of 27 degrees
    /**
     * Estimated distances will be multiplied by this value.
     */
    public final double DISTANCE_FUDGE = 1.0;
//    public final double DISTANCE_FUDGE = 0.80;
//    public final double DISTANCE_FUDGE = 1.21;
    /**
     * The filters to be applied to an image. This should be filled in in the
     * constructor.
     */
    Vector filterList = new Vector();
    double targetWidth = 24.0;
    double targetHeight = 18.0;

    public Vector getFilterList() {
        return this.filterList;
    }

    /**
     * @param filter to be added to the end of the filter list.
     */
    protected void addFilter(Filter filter) {
        this.filterList.addElement(filter);
        filter.setPlan(this);
    }

    /**
     * Score to a particle. Higher scores indicate better particle matches.
     */
    public abstract int score(ParticleAnalysisReport p);

    /**
     * Compare two particles based on how close to the horizontal center they
     * are. If both particles are in the middle, compare based on how high up
     * they are.
     */
    public int compare(Object o1, Object o2) {
        ParticleAnalysisReport p1 = (ParticleAnalysisReport) o1;
        ParticleAnalysisReport p2 = (ParticleAnalysisReport) o2;
        return score(p2) - score(p1);
    }

    /**
     * @return estimated distance in inches.
     */
//    public double estimateDistance(ParticleAnalysisReport[] particles) {
//        if (particles == null || particles.length == 0) { return 0.0; }
//        ParticleAnalysisReport bestParticle = particles[0];
//        double y = this.targetHeight / 2; // half the height of target, in inches
//        double y1 = bestParticle.boundingRectHeight / 2.0; // pixels
//        double z = (bestParticle.imageHeight * y) / (y1 * 2 * ProcessingPlan.Tv);
//        return z * DISTANCE_FUDGE;
//    }
    public double estimateDistance(ParticleAnalysisReport[] particles) {
        if (particles == null || particles.length == 0) {
            return 0.0;
        }
        ParticleAnalysisReport bestParticle = particles[0];
        double y = this.targetWidth / 2; // half the width of target, in inches
        double y1 = bestParticle.boundingRectWidth / 2.0; // pixels
        double z = (bestParticle.imageWidth * y) / (y1 * 2 * ProcessingPlan.Th);
//        double f = (4.8 - bestParticle.particleToImagePercent) * 0.15;
//        return z * (DISTANCE_FUDGE + f);
        return z * DISTANCE_FUDGE;
    }

    /**
     * @param dist distance to target in inches.
     * @return estimated x deflection, measured in inches.
     */
    public double estimateHorizontalDeflection(ParticleAnalysisReport[] particles, double dist) {
        if (particles == null || particles.length == 0) {
            return 0.0;
        }
        ParticleAnalysisReport bestParticle = particles[0];
        double c = bestParticle.imageWidth / 2.0; // center in pixels
        double x1 = c - bestParticle.center_mass_x; // pixels
        double x = (-2 * ProcessingPlan.Th * x1 * dist) / bestParticle.imageWidth;
        return x;
    }

    /**
     * @param dist distance to target in inches.
     * @return estimated x deflection, measured in inches.
     */
    public double estimateVerticalDeflection(ParticleAnalysisReport[] particles, double dist) {
        if (particles == null || particles.length == 0) {
            return 0.0;
        }
        ParticleAnalysisReport bestParticle = particles[0];
        double c = bestParticle.imageHeight / 2.0; // center in pixels
        double y1 = c - bestParticle.center_mass_y; // pixels
        double y = (-2 * ProcessingPlan.Tv * y1 * dist) / bestParticle.imageHeight;
        return y;
    }

    /**
     * @param dist distance to target in inches.
     * @return estimated angle to target, in degrees.
     */
    public double estimateHorizontalAngle(ParticleAnalysisReport[] particles, double dist) {
        double x = estimateHorizontalDeflection(particles, dist);
        double angleInRadians = MathUtils.atan2(x, dist);
        return Math.toDegrees(angleInRadians);
    }

    /**
     * @param dist distance to target in inches.
     * @return estimated angle to target, in degrees.
     */
    public double estimateVerticalAngle(ParticleAnalysisReport[] particles, double dist) {
        double y = estimateVerticalDeflection(particles, dist);
        double angleInRadians = MathUtils.atan2(y, dist);
        return Math.toDegrees(angleInRadians);
    }

    /**
     * @return the type of the target. Zero indicates unknown.
     */
    public int getTargetType(ParticleAnalysisReport[] particles) {
        return 0;
    }

    /**
     * @return the type of the target. Zero indicates unknown.
     */
    public int getTargetType(ParticleAnalysisReport particle) {
        return 0;
    }
}
