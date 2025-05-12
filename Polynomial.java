public class Polynomial{
    double[] coefficients;

    public Polynomial(){
        coefficients = new double[0];
    }

    public Polynomial(double[] coefficients){
        this.coefficients = new double[coefficients.length];
        for(int i = 0; i < coefficients.length; i++){
            this.coefficients[i] = coefficients[i];
        }
    }

    public Polynomial add(Polynomial poly){
        int length = Math.max(poly.coefficients.length, this.coefficients.length);
        double[] sums = new double[length];

        for(int i = 0; i < this.coefficients.length; i++){
            sums[i] += this.coefficients[i];
        }

        for(int i = 0; i < poly.coefficients.length; i++){
            sums[i] += poly.coefficients[i];
        }


        return new Polynomial(sums);
    }

    public double evaluate(double x){
        double sum=0;
        for(int i = 0; i < coefficients.length; i++){
            sum += coefficients[i]*Math.pow(x, i);
        }

        return sum;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}