public class NBody {
    public static double readRadius(String filename)
    {
        In in = new In(filename);
        in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String filename)
    {
        In in = new In(filename);
        int l = in.readInt();
        Planet[] ps = new Planet[l];
        in.readDouble();
        for (int i = 0;i < l;i++)
        {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            ps[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return ps;
    }
    public static void main(String[] args)
    {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double Radius = readRadius(filename);
        Planet[] Planets = readPlanets(filename);
        int l = Planets.length;
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-Radius,Radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images\\starfield.jpg");
        for (Planet p : Planets)
        {
            p.draw();
        }
        StdDraw.show();
        double time = 0;
        for (;time <= T;time += dt)
        {
            double[] xForces = new double[l];
            double[] yForces = new double[l];
            for (int i = 0;i < l;i++)
            {
                
                xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
                yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
            }
            for (int i = 0;i < l;i++)
            {
                Planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0,0,"images\\starfield.jpg");
            for (Planet p : Planets)
            {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", Planets.length);
        StdOut.printf("%.2e\n", Radius);
        for (int i = 0; i < Planets.length; i++)
        {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                  Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);   
        }
    }
}
