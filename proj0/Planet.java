public class Planet
{
    public static double G = 6.67e-11;

    public double xxPos; 
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xP,double yP,double xV,double yV,double m,String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p)
    {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p)
    {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        double r = Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
        return r;
    }
    public double calcForceExertedBy(Planet p)
    {
        double f = G * mass * p.mass / Math.pow(calcDistance(p),2);
        return f;
    }
    public double calcForceExertedByX(Planet p)
    {
        double f = calcForceExertedBy(p);
        double dx = p.xxPos - xxPos;
        double fx = f * (dx) / calcDistance(p);
        return fx;
    }
    public double calcForceExertedByY(Planet p)
    {
        double f = calcForceExertedBy(p);
        double dy = p.yyPos - yyPos;
        double fy = f * (dy) / calcDistance(p);
        return fy;
    }
    public double calcNetForceExertedByX(Planet[] ps)
    {
        double f = 0;
        for (Planet p : ps)
        {
            if (this != p)
            {
                f += calcForceExertedByX(p);
            }
        }
        return f;
    }
    public double calcNetForceExertedByY(Planet[] ps)
    {
        double f = 0;
        for (Planet p : ps)
        {
            if (this != p)
            {
                f += calcForceExertedByY(p);
            }
        }
        return f;
    }
    public void update(double dt,double fx,double fy)
    {
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += dt * ax;
        yyVel += dt * ay;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
    public void draw()
    {
        StdDraw.picture(xxPos,yyPos,"images\\"+imgFileName);
    }
}
