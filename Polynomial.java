import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class Polynomial{
    double[] coefficients;      //non-zero
    int[] exponents;

    public Polynomial(){
        coefficients = new double[1];
        exponents = new int[1];
        coefficients[0] = 0;
        exponents[0] = 0;

    }

    public Polynomial(double[] coefficients, int[] exponents){
        this.coefficients = new double[coefficients.length];
        for(int i = 0; i < coefficients.length; i++){
            this.coefficients[i] = coefficients[i];
        }

        this.exponents = new int[exponents.length];
        for(int i = 0; i < exponents.length; i++){
            this.exponents[i] = exponents[i];
        }
    }

    public Polynomial(File f){
        Scanner s = null;
        String temp = "";
        String str = "";
        String[] numbers;

        try{
            s = new Scanner(f);
        } catch (FileNotFoundException e){	
            System.out.println("File Not Found");
        }

        temp = s.nextLine();
        str += temp.charAt(0);

        for(int i = 1; i < temp.length(); i++){
            if(temp.charAt(i) == '-'){
                str += "+";
            }
            str += temp.charAt(i);
        }

        String[] terms = str.split("\\+");
        coefficients = new double[terms.length];
        exponents = new int[terms.length];

        for(int i = 0; i < terms.length; i++){
            numbers = terms[i].split("x");
            coefficients[i] = Double.parseDouble(numbers[0]);

            if(numbers.length == 1){
                exponents[i] = 0;
            } else {
                exponents[i] = Integer.parseInt(numbers[1]);
            }
        }
    }

    

    public Polynomial add(Polynomial poly){
        double[] xCoeffs = poly.coefficients;
        int[] xExpos = poly.exponents;
        int freeIndex = 0;

        double[] newCoeffs;
        int[] newExpos;

        int length = exponents.length;
        //finding length of new arrays
        for(int i = 0; i < xExpos.length; i++){
            if(findIndex(exponents, xExpos[i]) == -1){
                length++;
            }
        }

        newCoeffs = new double[length];
        newExpos = new int[length];
        
        for(int i = 0; i < length; i++){
            newExpos[i] = -1;
        }

        //initialize
        int temp;
        for(int i = 0; i < exponents.length; i++){
            temp = findIndex(newExpos, exponents[i]);
            if(temp == -1){
                newExpos[freeIndex] = exponents[i]; 
                newCoeffs[freeIndex] = coefficients[i];
                //System.out.println("newExpo: " + newExpos[freeIndex] + ", newCoeff: " + newCoeffs[freeIndex]);
                freeIndex++;
            } else {
                newCoeffs[temp] += coefficients[i]; 
            }
        }

        for(int i = 0; i < xExpos.length; i++){
            temp = findIndex(newExpos, xExpos[i]);
            if(temp == -1){
                newExpos[freeIndex] = xExpos[i]; 
                newCoeffs[freeIndex] = xCoeffs[i];
                freeIndex++;
            } else {
                newCoeffs[temp] += xCoeffs[i]; 
            }
        }

        return removeZeros(new Polynomial(newCoeffs, newExpos));
    }

    //helper
    public int findIndex(int[] arr, int x){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == x){
                return i;
            }
        }

        return -1;
    }

    public double evaluate(double x){
        double sum=0;
        for(int i = 0; i < coefficients.length; i++){
            sum += coefficients[i]*Math.pow(x, exponents[i]);
        }

        return sum;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }

    public Polynomial multiply(Polynomial p){
        Polynomial result = new Polynomial();

        double[] tempCoeffs = new double[exponents.length];
        int[] tempExpos = new int[exponents.length];
        Polynomial temp;

        double currCoef;
        int currExpo;

        for(int i = 0; i < p.exponents.length; i++){
            currCoef = p.coefficients[i];
            currExpo = p.exponents[i];
            
            for(int j = 0; j < exponents.length; j++){
                tempCoeffs[j] = currCoef * coefficients[j];
                tempExpos[j] = currExpo + exponents[j];
            }

            temp = new Polynomial(tempCoeffs, tempExpos);
            result = result.add(temp);
        }


        return removeZeros(result);
    }

    public Polynomial removeZeros(Polynomial p){
        double[] newCoeffs = p.coefficients;
        int[] newExpos = p.exponents;

        if(p.exponents.length == 1){
            return p;
        }

        for(int i = 0; i < newCoeffs.length; i++){
            if(newCoeffs[i] == 0.0){
                newCoeffs = remove(newCoeffs, i);
                newExpos = remove(newExpos, i);
                i -= 1;
            }

            if(newExpos.length == 1){
                return p;
            }
        }

        return new Polynomial(newCoeffs, newExpos);

    }

    public double[] remove(double[] coeffs, int index){
        int length = coeffs.length;
        double[] newCoeffs = new double[length-1];
        int found = 0;

        for(int i = 0; i < length; i++){
            if(i == index){
                found = 1;
            } else {
                newCoeffs[i-found] = coeffs[i];
            }
        }

        return newCoeffs;
    }
    
    public int[] remove(int[] expos, int index){
        int length = expos.length;
        int[] newExpos = new int[length-1];
        int found = 0;

        for(int i = 0; i < length; i++){
            if(i == index){
                found = 1;
            } else {
                newExpos[i-found] = expos[i];
            }
        }

        return newExpos;
    }

    public void saveToFile(String s){
        String str = getString();

        try{
            FileWriter out = new FileWriter(s);
            out.write(str);
            out.close();
        }catch (IOException e){
            System.out.println("Error: " +e);
        }
    }

    public String getString(){
        String result = "";
        
        for(int i = 0 ; i < coefficients.length; i++){
            if(coefficients[i] < 0){
                result += "-";
                result += (-1)*coefficients[i];
            } else {
                if(i != 0){
                    result += "+";
                }
                result += coefficients[i];
            }

            if(exponents[i] != 0){
                    result += "x"+exponents[i];
            }
        }

        return result;
    }


}