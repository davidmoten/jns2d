package msafluid;

import org.junit.Test;

public class MSAFluidSolver2DTest {

    @Test
    public void testSolver() {
        int fluidWidth = 50;
        int fluidHeight = 50;
        MSAFluidSolver2D s = new MSAFluidSolver2D(fluidWidth, fluidHeight);
        s.enableRGB(true).setFadeSpeed(0.000f).setDeltaT(0.5f).setVisc(0.0001f);

        // addForce(s, 2, 2, 20, 50);
        draw(s);
        for (int i = 0; i < 100; i++) {
            s.update();
            draw(s);
        }
    }

    // add force and dye to fluid, and create particles
    static void addForce(MSAFluidSolver2D fluidSolver, float x, float y, float dx, float dy) {
        float aspectRatio2 = 1;
        float speed = dx * dx + dy * dy * aspectRatio2; // balance the x and y
                                                        // components of speed
                                                        // with the screen
                                                        // aspect ratio
        if (speed > 0) {
            if (x < 0)
                x = 0;
            else if (x > 1)
                x = 1;
            if (y < 0)
                y = 0;
            else if (y > 1)
                y = 1;

            // float colorMult = 5;
            float velocityMult = 30.0f;

            int index = fluidSolver.getIndexForNormalizedPosition(x, y);

            // colorMode(HSB, 360, 1, 1);
            // float hue = ((x + y) * 180 + frameCount) % 360;
            // drawColor = color(hue, 1, 1);
            // colorMode(RGB, 1);
            //
            // fluidSolver.rOld[index] += red(drawColor) * colorMult;
            // fluidSolver.gOld[index] += green(drawColor) * colorMult;
            // fluidSolver.bOld[index] += blue(drawColor) * colorMult;

            // particleSystem.addParticles(x * width, y * height, 10);
            fluidSolver.uOld[index] += dx * velocityMult;
            fluidSolver.vOld[index] += dy * velocityMult;
        }
    }

    private static void draw(MSAFluidSolver2D s) {
        System.out.println("-----------");
        for (int x = 0; x < s.numX(); x++)
            for (int y = 0; y < s.numY(); y++) {
                System.out.format("%5d\t%5d\t%3.3f\t%3.3f", x, y, s.u(x, y), s.v(x, y));
            }
        System.out.println();
    }

}
