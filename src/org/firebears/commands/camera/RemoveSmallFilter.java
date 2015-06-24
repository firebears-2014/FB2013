package org.firebears.commands.camera;

import edu.wpi.first.wpilibj.image.*;

/**
 * Filter out particles based on their size.
 */
public class RemoveSmallFilter implements Filter {

    private boolean connectivity = false;
    private int erosions = 2;
    private ProcessingPlan plan = null;

    public RemoveSmallFilter(boolean connectivity, int erosions) {
        this.connectivity = connectivity;
        this.erosions = erosions;
    }

    /**
     * Filter out particles based on their size.
     *
     * @param image Input {@link BinaryImage}.
     * @return output {@link BinaryImage}.
     */
    public Image process(Image image) throws NIVisionException {
        BinaryImage inputImg = (BinaryImage) image;
        BinaryImage outputImg = inputImg.removeSmallObjects(connectivity, erosions);
        return outputImg;
    }

    public void setPlan(ProcessingPlan plan) {
        this.plan = plan;
    }

    public String toString() {
        return "RemoveSmallFilter[" + connectivity + ", " + erosions + "]";
    }
}
